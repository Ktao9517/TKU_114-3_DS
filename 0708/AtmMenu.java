import java.util.Scanner;
public class AtmMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int balance = 1000; 
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== ATM 選單 ===");
            System.out.println("1：查詢餘額");
            System.out.println("2：存款");
            System.out.println("3：提款");
            System.out.println("0：離開");
            System.out.print("請輸入選項：");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("目前的餘額為:" + balance);
                    break;
                case 2:
                    System.out.print("請輸入存款金額: ");
                    int depositAmount = scanner.nextInt();

                    if (depositAmount > 0) {
                        balance += depositAmount;
                        System.out.println("存款金額:" + depositAmount + "，可提款金額:" + balance);
                    } else {
                        System.out.println("存款金額必須大於 0！");
                    }
                    break;
                case 3:
                    System.out.print("請輸入提款金額: ");
                    int withdrawAmount = scanner.nextInt();

                    if (withdrawAmount > 0) {
                        if (withdrawAmount <= balance) {
                            balance -= withdrawAmount;
                            System.out.println("提款金額:" + withdrawAmount + "，目前餘額:" + balance);
                        } else {
                            System.out.println("提款金額不能大於目前餘額！");
                        }
                    } else {
                        System.out.println("提款金額必須大於 0！");
                    }
                    break;
                case 0:
                    System.out.println("結束本次使用！");
                    break;
                default:
                    System.out.println("請重新輸入！");
                    break;
            }
        }
        scanner.close();
    }
}

