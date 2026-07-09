import java.util.Scanner;
public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalItems = 0;
        int totalAmount = 0;

        while (true) {
            showMenu();
            System.out.print("請輸入選項：");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            int price = getPrice(choice);

            if (price == 0) {
                System.out.println("請重新選擇！");
                continue;
            }

            int quantity = readQuantity(scanner);
            int subtotal = calculateSubtotal(price, quantity);

            totalItems += quantity;
            totalAmount += subtotal;

            System.out.println("Subtotal: " + subtotal);
        }
        printReceipt(totalItems, totalAmount);
        scanner.close();
    }
    
    public static void showMenu() {
        System.out.println("\n=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int getPrice(int choice) {
        switch (choice) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 50;
            default:
                return 0;
        }
    }

    public static int readQuantity(Scanner scanner) {
        int quantity;

        while (true) {
            System.out.print("請輸入數量：");
            quantity = scanner.nextInt();

            if (quantity > 0) {
                return quantity;
            }

            System.out.println("數量必須大於 0，請重新輸入！");
        }
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("\n=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }
}
