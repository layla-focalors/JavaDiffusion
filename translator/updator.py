from optimum.onnxruntime import ORTStableDiffusionXLPipeline

model_id = "stabilityai/stable-diffusion-xl-base-1.0"
pipeline = ORTStableDiffusionXLPipeline.from_pretrained(model_id)

prompt = "sailing ship in storm by Leonardo da Vinci"
image = pipeline(prompt).images[0]

# STABLE DIFFUSION 모델 DOWNLOAD
# CLI : optimum-cli export onnx --model runwayml/stable-diffusion-v1-5 sd_v15_onnx/
