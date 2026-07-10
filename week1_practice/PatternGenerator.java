import java.util.Scanner;
public class PatternGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            printMenu();

            System.out.print("請輸入選項：");
            int choice = sc.nextInt();


            switch (choice) {
                case 1:
                    printMultiplicationTable();
                    break;
                case 2:
                    int height1 = readPositiveInt(sc, "請輸入高度：");
                    printTriangle(height1);
                    break;
                case 3:
                    int height2 = readPositiveInt(sc, "請輸入高度：");
                    printReverseTriangle(height2);
                    break;
                case 4:
                    int rows = readPositiveInt(sc, "請輸入列數：");
                    int cols = readPositiveInt(sc, "請輸入欄數：");
                    printNumberGrid(rows, cols);
                    break;
                case 0:
                    System.out.println("程式結束！");
                    sc.close();
                    return;
                default:
                    System.out.println("選項錯誤，請重新輸入！");
            }
            System.out.println();
        }
    }

    public static void printMenu() {
        System.out.println("=== Pattern Menu ===");
        System.out.println("1. Multiplication Table");
        System.out.println("2. Right Triangle");
        System.out.println("3. Reverse Triangle");
        System.out.println("4. Number Grid");
        System.out.println("0. Exit");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int value;

        while (true) {
            System.out.print(message);
            value = sc.nextInt();

            if (value > 0) {
                return value;
            } 
            else {
                System.out.println("輸入必須大於 0！");
            }
        }
    }

    public static void printMultiplicationTable() {
        System.out.println("=== Multiplication Table ===");

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(
                    j + " x " + i + " = " + (i * j) + "\t"
                );
            }
            System.out.println();
        }
    }

    public static void printTriangle(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printReverseTriangle(int height) {
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
