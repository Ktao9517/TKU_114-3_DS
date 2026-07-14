import java.util.Scanner;

public class ProductArraySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};
        
        int purchaseCount = 0;
        int restockCount = 0;
        int totalRevenue = 0;
        
        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-7): ");
            int choice = getIntInput(sc);
            
            if (choice == 7) {
                showOperationSummary(purchaseCount, restockCount, totalRevenue);
                break;
            }
            
            switch (choice) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    System.out.print("請輸入商品編號 (1-5): ");
                    int id2 = getValidId(sc);
                    queryProduct(id2, names, prices, stocks);
                    break;
                case 3:
                    System.out.print("請輸入商品編號 (1-5): ");
                    int id3 = getValidId(sc);
                    System.out.print("請輸入購買數量: ");
                    int qty3 = getPositiveInt(sc, "購買數量");
                    if (purchaseProduct(id3, qty3, names, prices, stocks)) {
                        purchaseCount++;
                        totalRevenue += prices[id3 - 1] * qty3;
                    }
                    break;
                case 4:
                    System.out.print("請輸入商品編號 (1-5): ");
                    int id4 = getValidId(sc);
                    System.out.print("請輸入補貨數量: ");
                    int qty4 = getPositiveInt(sc, "補貨數量");
                    if (restockProduct(id4, qty4, names, stocks)) {
                        restockCount++;
                    }
                    break;
                case 5:
                    showLowStock(names, stocks);
                    break;
                case 6:
                    int totalVal = calculateTotalValue(prices, stocks);
                    System.out.println("全部庫存總價值: " + totalVal + " 元");
                    break;
                default:
                    System.out.println("無效選擇，請輸入 1-7 之間的數字。");
            }
            System.out.println();
        }
        
        sc.close();
    }
    
    public static void displayMenu() {
        System.out.println("========== 商品陣列管理系統 ==========");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (庫存 < 10)");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束並顯示操作摘要");
        System.out.println("======================================");
    }
    
    public static int getIntInput(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("請輸入整數！");
            sc.next();
        }
        return sc.nextInt();
    }
    
    public static int getValidId(Scanner sc) {
        while (true) {
            int id = getIntInput(sc);
            if (id >= 1 && id <= 5) {
                return id;
            }
            System.out.print("編號必須為 1-5，請重新輸入: ");
        }
    }
    
    public static int getPositiveInt(Scanner sc, String label) {
        while (true) {
            int n = getIntInput(sc);
            if (n > 0) {
                return n;
            }
            System.out.print(label + "必須大於 0，請重新輸入: ");
        }
    }
    
    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n商品編號\t商品名稱\t\t單價\t庫存");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d\t\t%-12s\t%d\t%d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }
    
    public static void queryProduct(int id, String[] names, int[] prices, int[] stocks) {
        int idx = id - 1;
        System.out.println("\n【商品查詢結果】");
        System.out.println("商品編號: " + id);
        System.out.println("商品名稱: " + names[idx]);
        System.out.println("單價: " + prices[idx] + " 元");
        System.out.println("目前庫存: " + stocks[idx] + " 件");
    }
    
    public static boolean purchaseProduct(int id, int qty, String[] names, int[] prices, int[] stocks) {
        int idx = id - 1;
        if (qty > stocks[idx]) {
            System.out.println("購買失敗：庫存不足！目前庫存: " + stocks[idx] + " 件");
            return false;
        }
        stocks[idx] -= qty;
        System.out.println("購買成功！商品: " + names[idx] + "，數量: " + qty + " 件");
        System.out.println("扣除後庫存: " + stocks[idx] + " 件");
        return true;
    }
    
    public static boolean restockProduct(int id, int qty, String[] names, int[] stocks) {
        int idx = id - 1;
        stocks[idx] += qty;
        System.out.println("補貨成功！商品: " + names[idx] + "，補貨 " + qty + " 件");
        System.out.println("目前庫存: " + stocks[idx] + " 件");
        return true;
    }
    
    public static void showLowStock(String[] names, int[] stocks) {
        System.out.println("\n【低庫存商品清單】(庫存 < 10)");
        boolean found = false;
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] < 10) {
                System.out.println((i + 1) + ". " + names[i] + " - 庫存: " + stocks[i] + " 件");
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }
    
    public static int calculateTotalValue(int[] prices, int[] stocks) {
        int total = 0;
        for (int i = 0; i < prices.length; i++) {
            total += prices[i] * stocks[i];
        }
        return total;
    }
    
    public static void showOperationSummary(int purchaseCount, int restockCount, int totalRevenue) {
        System.out.println("\n========== 操作摘要 ==========");
        System.out.println("購買操作次數: " + purchaseCount + " 次");
        System.out.println("補貨操作次數: " + restockCount + " 次");
        System.out.println("累計銷售金額: " + totalRevenue + " 元");
        System.out.println("感謝使用商品陣列管理系統！");
        System.out.println("==============================");
    }
}