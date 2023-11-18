import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
class CopyUnzipEngine {
    public static void AddUnzip(){
        System.out.println("----- Unzip Engine -----");
        System.out.println("경고! 이 프로세스는 귀하의 디바이스에 unzip.exe 엔진을 추가합니다.");
        System.out.println("이 프로세스는 프로세스의 실행 후에도 유지됩니다.");
        Path sourcePath = Paths.get("./Assets/unzip.exe");
        Path targetPath = Paths.get("c:\\windows\\system32");
        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("파일이 성공적으로 복사되었습니다.");
        } catch (Exception e) {
            System.out.println("파일 복사에 실패하였습니다.");
            System.out.println("에러 401: 권한이 부족하여 파일을 복사하지 못했습니다.");
            System.out.println("혹은 파일이 이미 존재하고 있어, 파일을 복사하지 못했습니다.");
        }
        finally {
            System.out.println("Running Next process");
        }
    }
}

class Unzip extends CopyUnzipEngine{
    public static void UnzipSys(String model_Name){
        System.out.println("----- Model Unzip Toolkit -----");
        Scanner sc = new Scanner(System.in);
        System.out.println("!TIP : 모델은 model 폴더에 저장됩니다.");
        System.out.print("압축 해제 할 모델의 이름을 입력하세요 : ");
        String saveFilePath = "./model";
        System.out.println("압축 해제 할 모델 경로 : " + saveFilePath + "/" + model_Name);
        System.out.println("압축 해제 할 모델 크기에 따라 시간이 소요될 수 있습니다.");
        CopyUnzipEngine.AddUnzip();
        try {
            System.out.println("실행할 코드 : " + "unzip" + saveFilePath + "/" + model_Name);
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "unzip", "-o", saveFilePath + "/" + model_Name, "-d", saveFilePath);
            Process p = pb.start();
            p.waitFor();
            System.out.println("모델 압축 해제가 완료되었습니다. ( Unzip Model Complete )");
        } catch (Exception e) {
            System.out.println("모델 압축 해제에 실패하였습니다. ( Unzip Model Failed )");
            System.out.println("해당 경로에 이미 압축 해제된 모델이 존재하는지 확인해보세요!");
        }
    }
}
public class prompt {
    public static void main(String[] args) {
        boolean isexit = false;
//        모델 이름과 주소 저장
        String model_Name;
        String fileURL = "https://github.com/layla-focalors/JavaDiffusion/releases/download/download/";
//        isexit 객체를 사용해 프로그램의 종료 여부를 판단
        Scanner sc = new Scanner(System.in);
//        sc를 통해 스캐너 객체 생성
        do{
//            메뉴 생성
            System.out.println("-------- Menu --------");
            System.out.println("1. 시스템 상태 체크 ( Get System Info )");
            System.out.println("2. 이미지 생성 ( Create Image )");
            System.out.println("3  모델 다운로드 ( Download Model )");
            System.out.println("4. ZIP 모델 압축해제 ( Model Unzip toolkit )");
            System.out.println("5. ONNX 기반 모델 다운로더 ( ONNX Model Downloader )");
            System.out.println("6. 프로그램 종료 ( Exit Program )");
            System.out.println("----------------------");
            System.out.print("메뉴를 선택하세요 : ");
            int value = sc.nextInt();
//            value를 통해 메뉴 선택
            String core;
            switch(value){
//                -> 를 사용한 switch 문은 break가 필요하지 않음
                case 1 -> {
                    Scanner scs = new Scanner(System.in);
                    System.out.println("시스템 정보를 txt 파일로 출력하시겠습니까? ( 기본설정 : 텍스트 생성 안함 ) ");
                    System.out.print("텍스트 파일로 생성하고 싶으시다면 yes를 입력해주세요 : ");
                    core = scs.nextLine();
                    if(core.equals("yes")){
                        System.out.println("텍스트 파일을 생성합니다. ( Create Text File )");
                        System.out.println("Get System Info - Using dxdiag");
                        try {
                            String filePath = "./dxdiag.txt";
                            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dxdiag", "/t", filePath);
                            Process p = pb.start();
                            p.waitFor();
                        } catch (Exception e) {
                            System.out.println("에러가 발생하였습니다. 시스템에 GPU 자원이 존재하는지 확인하세요.");
                        }
                        System.out.println("텍스트 파일이 생성되었습니다.");
                    }else {
                        System.out.println("텍스트 파일을 생성하지 않습니다. ( Not Create Text File )");
                        System.out.println("Get System Info - Using dxdiag");
                        System.out.println("GPU(렌더링) 항목이 없다면 프로그램이 CPU 모드로 실행됩니다.");
                        System.out.println("연산에 많은 시간이 소요될 수 있으니 주의해주시길 바랍니다.");
                        try {
//                            파일 경로 생성
                            String filePath = "./log.txt";
//                            dxdiag(원도우 시스템 매니저)에 시스템 정보 요청
                            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dxdiag", "/t", filePath);
//                            프로세스 메서드 실행
                            Process p = pb.start();
//                            동작 대기
                            p.waitFor();

//                            파일 정보 읽어오기
                            BufferedReader br = new BufferedReader(new FileReader(filePath));
                            String line;
                            System.out.println("------SYSTEM INFO------");
//                            반복문 순회, 라인 정보 출력
                            while ((line = br.readLine()) != null) {
                                if (line.trim().startsWith("Card name:") || line.trim().startsWith("Current Mode:")) {
                                    System.out.println(line.trim());
                                }
                            }
//                            파일 탐색 객체 종료
                            br.close();
//                            파일 경로 재설정, 파일 메서드 참조 파일 생성
                            File file = new File(filePath);
                            try{
//                                파일 삭제
                                file.delete();
                            } catch (Exception e) {
                                System.out.println("log파일 삭제에 실패하였습니다.");
                            } finally {
                                file.delete();
                                try {
                                    br.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
//                            에러처리
                        } catch (Exception e) {
                            System.out.println("에러가 발생하였습니다. 시스템에 GPU 자원이 존재하는지 확인하세요.");
                        }
                    }
                }
                case 2 -> {
//                    이미지 생성 : stable diffusion loader
                    System.out.println("Create Image");
                }
                case 3 -> {
                  System.out.println("----- Model Downloader -----");
                  Scanner models = new Scanner(System.in);
                  System.out.println("!TIP : 모델은 model 폴더에 저장됩니다.");
                  System.out.print("다운로드 할 모델의 이름을 입력하세요 : ");
                  String saveFilePath = "./model";
                  model_Name = models.nextLine();
                  System.out.println("다운로드할 모델 경로 : " + fileURL + model_Name);
                  System.out.println("다운로드 할 모델 크기에 따라 시간이 소요될 수 있습니다.");
                  try {
                      URL url = new URL(fileURL + model_Name);
                      try {
                            InputStream in = url.openStream();
                            Files.copy(in, Path.of(saveFilePath + "/" + model_Name), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("모델 다운로드가 완료되었습니다. ( Download Model Complete )");
                        } catch (Exception e) {
                            System.out.println("모델 다운로드에 실패하였습니다. ( Download Model Failed )");
                      }
                  } catch (Exception e) {
                      System.out.println("모델을 찾을 수 없습니다. ( Can't Find Model )");
                  }

                }
                case 4 -> {
                    System.out.println("모델 압축 해제");
                    Unzip unzip = new Unzip();
                    System.out.print("압축 해제 할 모델의 이름을 입력하세요 : ");
                    Scanner scx = new Scanner(System.in);
                    String model_name = scx.nextLine();
                    Unzip.UnzipSys(model_name);
                }
                case 5 -> {
                    System.out.println("---- ONNX Model Downloader -----");
                    System.out.println("경고 : 해당 모델 다운로더 시스템은 파이썬이 존재할 경우에만 동작합니다.");
                    System.out.println("해당 명령이 동작하지 않을 경우, 파이썬의 인터프리터 & pip이 설치되어 있는지 확인하세요.");
                    System.out.println("파이썬이 설치되어 있지 않다면, https://www.python.org/downloads/ 에서 파이썬을 설치하세요.");
                }
                case 6 -> {
//                    프로그램 종료
                    System.out.println("Exit Program");
                    isexit = true;
                }
                default -> {
                    System.out.println("잘못된 입력입니다. ( Wrong Input )");
                }
            }
        } while(!isexit);
    }
}
