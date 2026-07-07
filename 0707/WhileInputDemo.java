import java.util.Scanner;
public class WhileInputDemo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入整數：");
        int number = sc.nextInt();

        while (number != 0) {
            System.out.println(number);
        }
        System.out.println("輸入為0");
        sc.close();
    }
}
