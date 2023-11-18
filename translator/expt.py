from diffusers import EulerDiscreteScheduler, UNet2DConditionModel, AutoencoderKL
from transformers import CLIPTextModel, AutoTokenizer
import torch

model_id = "stabilityai/stable-diffusion-2-1"

scheduler = EulerDiscreteScheduler.from_pretrained(model_id, subfolder="scheduler")
text_encoder = CLIPTextModel.from_pretrained(model_id, subfolder="text_encoder", torchscript=True, return_dict=False)
vae = AutoencoderKL.from_pretrained(model_id, subfolder="vae", torchscript=True, return_dict=False)
unet_model = UNet2DConditionModel.from_pretrained(model_id, subfolder="unet", torchscript=True, return_dict=False)
tokenizer = AutoTokenizer.from_pretrained(model_id, subfolder="tokenizer")
print(scheduler, text_encoder, unet_model, tokenizer)

vae.eval()
unet_model.eval()
text_encoder.eval()

prompt = "Astronaut riding a horse"
tokenized = tokenizer(prompt,
                      padding="max_length", max_length=tokenizer.model_max_length,
                      truncation=True, return_tensors="pt")

print(tokenized)

text_embeddings = text_encoder(**tokenized)
traced_text = torch.jit.trace(text_encoder, (tokenized["input_ids"], tokenized['attention_mask']))

latents = torch.randn((1, 4, 64, 64))
latent_model_input = torch.cat([latents] * 2)
text_embeddings = torch.stack([text_embeddings[0], text_embeddings[0]]).squeeze()


def forward(self, sample, timestep, encoder_hidden_states,
            class_labels=None, return_dict: bool = False, ):
    return UNet2DConditionModel.forward(self, sample, timestep, encoder_hidden_states, class_labels, return_dict)


unet_model.forward = forward.__get__(unet_model, UNet2DConditionModel)
traced_unet = torch.jit.trace(unet_model, (latent_model_input, torch.tensor([981]), text_embeddings))

def encode(self, x: torch.FloatTensor):
    h = self.encoder(x)
    return self.quant_conv(h)


def decode(self, z: torch.FloatTensor, return_dict: bool = False):
    return AutoencoderKL.decode(self, z, return_dict)

print(vae)
vae.encode = encode.__get__(vae, AutoencoderKL)
vae.decode = decode.__get__(vae, AutoencoderKL)
traced_vae = torch.jit.trace_module(vae, {"encode": [torch.ones((1, 3, 512, 512), dtype=torch.float32)],
                                          "decode": [latents]})
torch.jit.save(traced_vae, "vae_model.pt")
torch.jit.save(traced_unet, "unet_model.pt")
torch.jit.save(traced_text, "clip_model.pt")