import java.util.Scanner;
public class OrderSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalItems = 0;
        int totalAmount = 0;
        
        int choice;
        
        while (true) {
            System.out.println("\n=== Order Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入選項：");
            choice = scanner.nextInt();
            
            if (choice == 0) {
                break; 
            }
            
            System.out.print("請輸入數量：");
            int items = scanner.nextInt();
            
            if (items <= 0) {
                System.out.println("數量必須大於 0");
                continue; 
            }
            
            int price = 0;
            
            switch (choice) {
                case 1:
                    price = 30;
                    break;
                case 2:
                    price = 35;
                    break;
                case 3:
                    price = 50;
                    break;
                default:
                    System.out.println("請重新選擇！");
                    continue;
            }
            
            int subtotal = price * items;
            totalAmount += subtotal;     
            totalItems += items;   
            
            System.out.println("Subtotal:"+subtotal);
        }
        
        System.out.println("\n=== Receipt ===");
        System.out.println("Total items:"+totalItems);
        System.out.println("Total amount:"+totalAmount);
        
        scanner.close();
    }
}

