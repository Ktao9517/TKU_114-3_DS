import java.util.Scanner;
public class AtmSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;

        while (true) {
            printMenu();

            System.out.print("請輸入選項：");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    printBalance(balance);
                    break;
                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    depositCount++;
                    totalDeposit += depositAmount;
                    printBalance(balance);
                    break;
                case 3:
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    if (canWithdraw(balance, withdrawAmount)) {
                        balance = withdraw(balance, withdrawAmount);
                        withdrawCount++;
                        totalWithdraw += withdrawAmount;
                        printBalance(balance);
                    } else {
                        System.out.println("餘額不足，無法提款！");
                    }
                    break;
                case 4:
                    printSummary(
                            balance,
                            depositCount,
                            withdrawCount,
                            totalDeposit,
                            totalWithdraw
                    );
                    break;
                case 0:
                    printSummary(
                            balance,
                            depositCount,
                            withdrawCount,
                            totalDeposit,
                            totalWithdraw
                    );
                    
                    sc.close();
                    return;
                default:
                    System.out.println("選項錯誤，請重新輸入！");
            }
            System.out.println();
        }
    }

    public static void printMenu() {

        System.out.println("=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        int amount;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();

            if (amount > 0) {
                return amount;
            } 
            else {
                System.out.println("金額必須大於 0！");
            }
        }
    }

    public static int deposit(int balance, int amount) {

        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {

        return balance - amount;
    }

    public static boolean canWithdraw(int balance, int amount) {

        return amount <= balance;
    }

    public static void printBalance(int balance) {

        System.out.println("Balance: " + balance);
    }

    public static void printSummary(
            int balance,
            int depositCount,
            int withdrawCount,
            int totalDeposit,
            int totalWithdraw) {

        System.out.println();
        System.out.println("=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
    }
}