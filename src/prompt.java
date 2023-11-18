import java.util.Scanner;

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
            switch(value){
//                -> 를 사용한 switch 문은 break가 필요하지 않음
                case 1 -> {
                    System.out.println("Get System Info");
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
