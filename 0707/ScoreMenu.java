import java.util.Scanner;
public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.print("請輸入 Java 成績：");
        int scoreJava = sc.nextInt();

        System.out.print("請輸入 English 成績：");
        int scoreEnglish = sc.nextInt();

        System.out.print("請輸入 Math 成績：");
        int scoreMath = sc.nextInt();

        double average = (scoreJava + scoreEnglish + scoreMath) / 3.0;

        String pass;
        if (average >= 60) {
            pass = "及格";
        } else {
            pass = "不及格";
        }

        String grade;
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int choice = -1;

        while (choice != 0) {
            System.out.println("1. 顯示平均分數");
            System.out.println("2. 顯示及格狀態");
            System.out.println("3. 顯示等第");
            System.out.println("0. 離開");
            System.out.print("請選擇：");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("姓名：" + name);
                    System.out.println("平均分數：" + average);
                    break;
                case 2:
                    System.out.println("姓名：" + name);
                    System.out.println("及格狀態：" + pass);
                    break;
                case 3:
                    System.out.println("姓名：" + name);
                    System.out.println("等第：" + grade);
                    break;
                case 0:
                    System.out.println("程式結束！");
                    break;
            }
        }
        sc.close();
    }
}
