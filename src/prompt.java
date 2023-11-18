import org.w3c.dom.Text;

import java.awt.image.BufferedImage;
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
import java.io.InputStreamReader;
import java.io.File;import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

class CycleGAN_options {
    int width;
    int height;
    int buffer_size;
    int batch_size;
    void SetWidth(int _width){
        this.width = _width;
    }
    void SetHeight(int _height){
        this.height = _height;
    }
    void SetBufferSize(int _buffer_size){
        this.buffer_size = _buffer_size;
    }
    void SetBatchSize(int _batch_size){
        this.batch_size = _batch_size;
    }
    CycleGAN_options(int _width, int _height, int _buffer_size, int _batch_size){
        this.width = _width;
        this.height = _height;
        this.buffer_size = _buffer_size;
        this.batch_size = _batch_size;
    }
    int GetWidth(){
        return this.width;
    }
    int GetHeight(){
        return this.height;
    }
    int GetBufferSize(){
        return this.buffer_size;
    }
    int GetBatchSize(){
        return this.batch_size;
    }
}

class ImageLoader{
    public static List<BufferedImage> loadImages(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        List<BufferedImage> images = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                try {
                    images.add(ImageIO.read(file));
                } catch (IOException e) {
                    System.out.println("Error reading file " + file.getName());
                }
            }
        }
        return images;

class CycleGAN_GetDataset {
    public static void CycleGAN_GetDataset(String dataset_name, String data_type){
        List<BufferedImage> images = ImageLoader.loadImages("./public_dataset/" + dataset_name + "/" + data_type);
    }
}

class CycleGAN{
    public static void CycleGAN(String filepath, String Model_number){
        System.out.println("CycleGAN");
        CycleGAN_TRAIN();
    }
    private static void CycleGAN_TRAIN(){
        System.out.println("------ CycleGAN_TrainOPTIONS -----");
        CycleGAN_options cgo = new CycleGAN_options(256, 256, 400, 1);
        System.out.println("Width : " + cgo.GetWidth());
        System.out.println("Height : " + cgo.GetHeight());
        System.out.println("Buffer Size : " + cgo.GetBufferSize());
        System.out.println("Batch Size : " + cgo.GetBatchSize());
    }
}

class Diffusion {
//    안될 것 같으면 CycleGAN으로 선회
    public static void Diffusion(String prompt, int batch_size){
        TextParser(prompt);
    }
    private static void TextParser(String prompt){
        System.out.println(prompt);
    }
    private static void ImgSave(String img_name){
        String DefaultPath = "./output/" + img_name;
    };
}

class IsFiles {
    public static boolean IsExists(String Filename){
        return Searcher(Filename);
    }
    static boolean IsExits2(String Filename){
        return Searcher(Filename);
    }

    private static boolean Searcher(String Filename) {
        String filePath = "./data/is" + Filename + ".txt";
        System.out.println("파일 경로 : " + filePath);
        File file = new File(filePath);  // 파일의 전체 경로를 사용합니다.
        if(file.exists()){
            System.out.println("파일이 존재합니다.");
            return true;
        }else {
            System.out.println("파일이 존재하지 않습니다.");
            return false;
        }
    }
}

class GEN_TXT {
    public static void TextGen(String log){
        try {
            String filePath = "./data/is" + log + ".txt";
            File file = new File(filePath);
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("파일 생성에 오류가 발생했습니다.");
            }
        } catch (Exception e) {
            System.out.println("파일 생성에 실패했습니다.");
            e.printStackTrace();
        }
    }
}

class JythonMX extends GEN_TXT {
    public static void JythonInstaller(){
        try {
            System.out.println("------ Jython Installer -----");
            System.out.println("이 과정은 Jython FrameWork 를 설치합니다.");
            System.out.println("설치되는 파일이 궁금하시다면, Assets/JythonInstaller ... .jar 파일을 참고하세요");
            System.out.println("Jython 파일은 Assets 폴더에 저장되며, 이 파일은 추후에 삭제하셔도 무방합니다.");
            System.out.println("경고: 해당 파일은 직접 설치해주셔야 하며, 기본 설정은 변경하지 마십시오.");
            ProcessBuilder pbs = new ProcessBuilder("cmd.exe", "/c", "java -jar Assets/jython-installer-2.7.3.jar");
            Process ps = pbs.start();
            BufferedReader readers = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            String lines;
            while ((lines = readers.readLine()) != null) {
                System.out.println(lines);
            }
            ps.waitFor();
            TextGen("Jython");
            System.out.println("Jython 설치가 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ONNX_loader extends Pydownloader {

}

class Pydownloader {
    public static void pyloader(String model_git, String model_name){
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "pip install optimum[\"onnxruntime\"]");
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
            ProcessBuilder pbs = new ProcessBuilder("cmd.exe", "/c", "pip install transformers");
            Process ps = pbs.start();
            BufferedReader readers = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            String lines;
            while ((lines = readers.readLine()) != null) {
                System.out.println(lines);
            }
            ps.waitFor();
            ProcessBuilder pbss = new ProcessBuilder("cmd.exe", "/c", "optimum-cli export onnx --model %s %s/".format(model_git, model_name));
            Process pss = pbss.start();
            BufferedReader readerss = new BufferedReader(new InputStreamReader(pss.getInputStream()));
            String liness;
            while ((liness = readerss.readLine()) != null) {
                System.out.println(liness);
            }
            pss.waitFor();
            Path sourcePath = Paths.get("./" + model_name);
            Path targetPath = Paths.get("./model");
            try {
//                파일 이동
                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("파일이 성공적으로 복사되었습니다.");
            } catch (Exception e) {
                System.out.println("파일 복사에 실패하였습니다.");
                System.out.println("에러 401: 권한이 부족하여 파일을 복사하지 못했습니다.");
                System.out.println("혹은 파일이 이미 존재하고 있어, 파일을 복사하지 못했습니다.");
            }
            finally {
                System.out.println("Running Next process");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

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
            System.out.println("7. 개발자 깃허브 보기 ( Show Developer Github )");
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
//                    CHANGED : CycleGAN도 추가
                    System.out.println("----- Image Generator -----");
                    Scanner cmk = new Scanner(System.in);
                    System.out.println("1. CycleGAN");
                    System.out.println("2. JVM - StableDiffusion");
                    System.out.print("모델을 선택해주세요! : ");
                    int valuesx = cmk.nextInt();
                    if(valuesx == 1){
                        System.out.println("-------- CycleGAN ---------");
                        System.out.println("이미지를 생성할 모델을 선택해주세요!");
                        System.out.println("1. Anime Model ( Genshin Impact Style ) ");
                        System.out.println("2. nor2snow Model ( Winter Style )");
                        System.out.println("3. Horse2Zibra Model ( Horse Style ) ");
                        Scanner mxu = new Scanner(System.in);
                        int core_x = mxu.nextInt();
                        switch(core_x){
                            case 1 -> {
                                System.out.println("-------- Anime Model ---------");
                                CycleGAN cg = new CycleGAN();
                                cg.CycleGAN("Anime.png", "1");
                                System.out.println("이미지 생성이 완료되었습니다. ( Image Create Complete )");
                            }
                            case 2 -> {
                                System.out.println("-------- Nor2snow Model ---------");
                                CycleGAN cg = new CycleGAN();
                                cg.CycleGAN("Action.png", "2");
                                System.out.println("이미지 생성이 완료되었습니다. ( Image Create Complete )");
                            }
                            case 3 -> {
                                System.out.println("-------- Horse2Zibra Model ---------");
                                CycleGAN cg = new CycleGAN();
                                cg.CycleGAN("Horse.png", "3");
                                System.out.println("이미지 생성이 완료되었습니다. ( Image Create Complete )");
                            }
                            default -> {
                                System.out.println("잘못된 입력입니다. ( Wrong Input )");
                            }
                        }
                    }else if(valuesx == 2){
                        System.out.println("---- JVM - StableDiffusion -----");
                        System.out.println("이미지 생성을 위해 다음의 답변에 답해주세요!");
                        System.out.print("이미지 생성을 Java Models 를 통해 진행하시겠습니까? (yes/no) : ");
                        Scanner scu = new Scanner(System.in);
                        String corex = scu.nextLine();
                        if(corex.equals("yes")){
                            System.out.println("---- JVM Generator -----");
                            System.out.println("필요한 입력값을 입력해주세요! ");
                            Scanner scxp = new Scanner(System.in);
                            System.out.print("이미지의 배치 사이즈를 입력하세요 : ");
                            int batch_size = scxp.nextInt();
                            System.out.print("이미지의 프롬프트를 입력하세요 : ");
                            Scanner osc = new Scanner(System.in);
                            String prompt = osc.nextLine();
                            Diffusion dif = new Diffusion();
                            dif.Diffusion(prompt, batch_size);
                        }else if(corex.equals("no")){
                            IsFiles isf = new IsFiles();
                            if(isf.IsExists("Jython")){
                                System.out.println("Jython FrameWork가 설치되어 있습니다.");
                            }else {
                                System.out.println("Jython FrameWork가 설치되어 있지 않습니다.");
                                System.out.println("Jython FrameWork를 설치합니다.");
                                JythonMX jmx = new JythonMX();
                                jmx.JythonInstaller();
                            }
                        }else {
                            System.out.println("알 수 없는 요청입니다. ( Unknown Request )");
                        }
                    }else {
                        System.out.println("Unknown Request");
                    }
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
                    Pydownloader pdl = new Pydownloader();
                    Scanner px = new Scanner(System.in);
                    System.out.print("다운로드할 모델의 허깅페이스 허브 주소를 입력하세요 : ");
                    String model_name_git = px.nextLine();
                    System.out.println("다운로드할 모델의 이름를 입력하세요 : ");
                    String model_name = px.nextLine();
                    pdl.pyloader(model_name_git,model_name);
                }
                case 6 -> {
//                    프로그램 종료
                    System.out.println("Exit Program");
                    isexit = true;
                }
                case 7 -> {
                    System.out.println("----- Developer Github -----");
                    System.out.println("Developer : Layla-Focalors");
                    System.out.println("https://github.com/layla-focalors");
                    System.out.println("Ascii ARTS");
                    System.out.println("" +
                            "  ###       ##     ##  ##   ####       ##              #######   #####     ####     ##     ####      #####   ######    #####\n" +
                            "   ##      ####    ##  ##    ##       ####              ##   #  ##   ##   ##  ##   ####     ##      ##   ##   ##  ##  ##   ##\n" +
                            "   ##     ##  ##   ##  ##    ##      ##  ##             ## #    ##   ##  ##       ##  ##    ##      ##   ##   ##  ##  #\n" +
                            "   ##     ##  ##    ####     ##      ##  ##   ######    ####    ##   ##  ##       ##  ##    ##      ##   ##   #####    #####\n" +
                            "   ##     ######     ##      ##   #  ######             ## #    ##   ##  ##       ######    ##   #  ##   ##   ## ##        ##\n" +
                            "   ##     ##  ##     ##      ##  ##  ##  ##             ##      ##   ##   ##  ##  ##  ##    ##  ##  ##   ##   ##  ##  ##   ##\n" +
                            "  ####    ##  ##    ####    #######  ##  ##            ####      #####     ####   ##  ##   #######   #####   #### ##   #####");
                }
                case 1229 -> {
                    System.out.println("----- Test -----");
                    System.out.println("Layla Model");
                    System.out.println("1. file exists");
                    System.out.println("2. delete package");
                    System.out.print("테스트할 명령어 번호를 입력해주세요 : ");
                    Scanner xpcm = new Scanner(System.in);
                    int ios = xpcm.nextInt();
                    switch (ios){
                        case 1 -> {
                            IsFiles isf = new IsFiles();
                            System.out.print("파일이 존재하는지 확인할 파일의 이름을 입력하세요 : ");
                            Scanner ucx = new Scanner(System.in);
                            String test_name = ucx.nextLine();
                            System.out.println(isf.IsExists(test_name));
                        }
                        case 2 -> {
                            System.out.println("패키지 리스트 삭제");
                            Scanner dsc = new Scanner(System.in);
                            System.out.print("삭제할 패키지 이름을 입력하세요 : ");
                            String dscm = dsc.nextLine();
                            String filePath = "./data/is" + dscm + ".txt";
                            File file = new File(filePath);
                            try{
                                file.delete();
                                System.out.println("파일 삭제에 성공하였습니다.");
                            } catch (Exception e) {
                                System.out.println("파일 삭제에 실패하였습니다.");
                            } finally {
                                file.delete();
                                try {
                                    dsc.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        default -> {
                            System.out.println("잘못된 입력입니다. ( Wrong Input )");
                        }
                    }

                }
                default -> {
                    System.out.println("잘못된 입력입니다. ( Wrong Input )");
                }
            }
        } while(!isexit);
    }
}
