import os

def download():
    os.system('pip install optimum["onnxruntime"]')
    os.system('pip install transformers')
    os.system('optimum-cli export onnx --model runwayml/stable-diffusion-v1-5 sd_v15_onnx/')

if __name__ == "__main__":
    download()