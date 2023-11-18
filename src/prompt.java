import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.File;

public class prompt {
    public static void main(String[] args) {
        boolean isexit = false;
//        isexit 객체를 사용해 프로그램의 종료 여부를 판단
        Scanner sc = new Scanner(System.in);
//        sc를 통해 스캐너 객체 생성
        do{
//            메뉴 생성
            System.out.println("-------- Menu --------");
            System.out.println("1. 시스템 상태 체크 ( Get System Info )");
            System.out.println("2. 이미지 생성 ( Create Image )");
            System.out.println("3. 프로그램 종료 ( Exit Program )");
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
                            String filePath = "./log.txt";
                            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dxdiag", "/t", filePath);
                            Process p = pb.start();
                            p.waitFor();

                            BufferedReader br = new BufferedReader(new FileReader(filePath));
                            String line;
                            System.out.println("------SYSTEM INFO------");
                            while ((line = br.readLine()) != null) {
                                if (line.trim().startsWith("Card name:") || line.trim().startsWith("Current Mode:")) {
                                    System.out.println(line.trim());
                                }
                            }
                            br.close();
                            File file = new File(filePath);
                            try{
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
                        } catch (Exception e) {
                            System.out.println("에러가 발생하였습니다. 시스템에 GPU 자원이 존재하는지 확인하세요.");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Create Image");
                }
                case 3 -> {
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
