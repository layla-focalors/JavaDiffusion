# import torch

# STABLE Diffusion 모델 불러오기
# model = YourModel()  # 여기에 STABLE Diffusion 모델을 불러오는 코드를 작성하세요.
# model.eval()

# 더미 입력 생성
# input_shape = (1, 3, 224, 224)  # 입력 형태를 모델에 맞게 수정하세요.
# dummy_input = torch.randn(input_shape)

# 모델을 ONNX로 변환
# onnx_file_path = 'model.onnx'  # 출력 파일 경로
# torch.onnx.export(model, dummy_input, onnx_file_path)

import torch
import torch.onnx
import updator
import wget

wget.download('https://huggingface.co/stabilityai/stable-diffusion-xl-base-1.0/resolve/main/sd_xl_base_1.0.safetensors')
model = './sd_xl_base_1.0.safetensors'
model.eval()

