import java.util.Scanner;
public class ProductManagementSystem {

    private static final int MAX_SIZE = 10;
    private static Product[] products = new Product[MAX_SIZE];
    private static int productCount = 0;

    private static int addCount = 0;
    private static int sellCount = 0;
    private static int restockCount = 0;
    private static int priceChangeCount = 0;
    private static int searchCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        initializeProducts();

        System.out.println("========== 商品管理系統啟動 ==========");
        System.out.println("已載入 " + productCount + " 項初始商品，剩餘空間: " + (MAX_SIZE - productCount));


        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-9): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    searchProduct(sc);
                    break;
                case 3:
                    addProduct(sc);
                    break;
                case 4:
                    sellProduct(sc);
                    break;
                case 5:
                    restockProduct(sc);
                    break;
                case 6:
                    updatePrice(sc);
                    break;
                case 7:
                    displayLowStockProducts();
                    break;
                case 8:
                    displayTotalInventoryValue();
                    break;
                case 9:
                    showOperationSummary();
                    System.out.println("系統已結束，感謝使用！");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇，請輸入 1-9。");
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("\n========== 商品管理系統選單 ==========");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
        System.out.println("======================================");
    }

    private static void initializeProducts() {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);
        productCount = 5;
    }

    private static void displayAllProducts() {
        if (productCount == 0) {
            System.out.println("目前沒有任何商品。");
            return;
        }
        System.out.println("\n【全部商品清單】共 " + productCount + " 項");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-4s %-15s %8s %8s %12s%n", "編號", "名稱", "價格", "庫存", "庫存價值");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < productCount; i++) {
            Product p = products[i];
            System.out.printf("%-4d %-15s %8d %8d %12d%n",
                    (i + 1), p.getName(), p.getPrice(), p.getStock(), p.getInventoryValue());
        }
        System.out.println("--------------------------------------------------");
    }

    private static void searchProduct(Scanner sc) {
        System.out.print("請輸入要搜尋的商品完整名稱: ");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            keyword = sc.nextLine().trim();
        }
        searchCount++;

        Product found = findProductByName(keyword);
        if (found != null) {
            System.out.println("\n【搜尋結果】找到商品：");
            System.out.println("  名稱: " + found.getName());
            System.out.println("  價格: " + found.getPrice() + " 元");
            System.out.println("  庫存: " + found.getStock() + " 件");
            System.out.println("  庫存價值: " + found.getInventoryValue() + " 元");
            System.out.println("  是否低庫存: " + (found.isLowStock() ? "是" : "否"));
        } else {
            System.out.println("找不到名稱為 \"" + keyword + "\" 的商品。");
        }
    }

    private static void addProduct(Scanner sc) {
        if (productCount >= MAX_SIZE) {
            System.out.println("新增失敗：商品陣列已滿（最多 " + MAX_SIZE + " 項）！");
            return;
        }

        System.out.print("請輸入商品名稱: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            name = sc.nextLine().trim();
        }

        if (name.isEmpty()) {
            System.out.println("新增失敗：商品名稱不可空白。");
            return;
        }

        if (findProductByName(name) != null) {
            System.out.println("新增失敗：已存在相同名稱的商品（忽略大小寫）！");
            return;
        }

        System.out.print("請輸入價格 (>0): ");
        int price = readInt(sc);
        System.out.print("請輸入初始庫存 (>=0): ");
        int stock = readInt(sc);

        Product newProduct = new Product(name, price, stock);
        products[productCount] = newProduct;
        productCount++;
        addCount++;
        System.out.println("新增成功！" + newProduct);
    }

    private static void sellProduct(Scanner sc) {
        System.out.print("請輸入要出售的商品名稱: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = sc.nextLine().trim();

        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("出售失敗：找不到該商品。");
            return;
        }

        System.out.print("請輸入出售數量 (>0): ");
        int qty = readInt(sc);

        if (p.sell(qty)) {
            sellCount++;
            System.out.println("出售成功！商品: " + p.getName() + "，剩餘庫存: " + p.getStock());
        } else {
            System.out.println("出售失敗：數量必須 >0 且不可超過目前庫存（目前: " + p.getStock() + "）。");
        }
    }

    private static void restockProduct(Scanner sc) {
        System.out.print("請輸入要補貨的商品名稱: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = sc.nextLine().trim();

        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("補貨失敗：找不到該商品。");
            return;
        }

        System.out.print("請輸入補貨數量 (>0): ");
        int qty = readInt(sc);

        if (p.restock(qty)) {
            restockCount++;
            System.out.println("補貨成功！商品: " + p.getName() + "，目前庫存: " + p.getStock());
        } else {
            System.out.println("補貨失敗：數量必須大於 0。");
        }
    }

    private static void updatePrice(Scanner sc) {
        System.out.print("請輸入要修改價格的商品名稱: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = sc.nextLine().trim();

        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("修改失敗：找不到該商品。");
            return;
        }

        System.out.print("請輸入新價格 (>0): ");
        int newPrice = readInt(sc);

        if (p.setPrice(newPrice)) {
            priceChangeCount++;
            System.out.println("價格修改成功！商品: " + p.getName() + "，新價格: " + p.getPrice());
        } else {
            System.out.println("修改失敗：價格必須大於 0。");
        }
    }

    private static void displayLowStockProducts() {
        System.out.println("\n【低庫存商品】(庫存 < 10)");
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i].isLowStock()) {
                System.out.println("  - " + products[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  目前沒有低庫存商品。");
        }
    }

    private static void displayTotalInventoryValue() {
        long total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getInventoryValue();
        }
        System.out.println("全部庫存總價值: " + total + " 元");
    }

    private static void showOperationSummary() {
        System.out.println("\n========== 操作摘要 ==========");
        System.out.println("目前商品數量: " + productCount + " / " + MAX_SIZE);
        System.out.println("新增商品次數: " + addCount);
        System.out.println("出售操作次數: " + sellCount);
        System.out.println("補貨操作次數: " + restockCount);
        System.out.println("價格修改次數: " + priceChangeCount);
        System.out.println("搜尋操作次數: " + searchCount);
        System.out.println("==============================");
    }

    private static Product findProductByName(String name) {
        if (name == null) return null;
        String target = name.trim().toLowerCase();
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().toLowerCase().equals(target)) {
                return products[i];
            }
        }
        return null;
    }

    private static int readInt(Scanner sc) {
        while (true) {
            if (sc.hasNextInt()) {
                int val = sc.nextInt();
                sc.nextLine(); 
                return val;
            } else {
                System.out.print("請輸入有效整數: ");
                sc.next();
            }
        }
    }

    private static void runSampleTests() {
        System.out.println("\n>>>>> 開始執行自動測試案例 <<<<<");

        System.out.println("\n[測試1] 顯示全部商品");
        displayAllProducts();

        System.out.println("\n[測試2] 搜尋 Mouse");
        Product m = findProductByName("Mouse");
        System.out.println(m != null ? "找到: " + m : "找不到");

        System.out.println("\n[測試3] 搜尋 '  mouse  '（忽略大小寫空白）");
        m = findProductByName("  mouse  ");
        System.out.println(m != null ? "找到: " + m : "找不到");

        System.out.println("\n[測試4] 搜尋不存在商品 Phone");
        System.out.println(findProductByName("Phone") == null ? "正確：找不到" : "錯誤");

        System.out.println("\n[測試5] 新增 Laptop");
        if (productCount < MAX_SIZE && findProductByName("Laptop") == null) {
            products[productCount++] = new Product("Laptop", 25000, 3);
            addCount++;
            System.out.println("新增成功: " + products[productCount - 1]);
        }

        System.out.println("\n[測試6] 嘗試新增重複名稱 keyboard");
        System.out.println(findProductByName("keyboard") != null ? "正確偵測到重複" : "錯誤");

        System.out.println("\n[測試7] 出售 Mouse 3 件");
        Product mouse = findProductByName("Mouse");
        if (mouse != null && mouse.sell(3)) {
            sellCount++;
            System.out.println("成功，剩餘: " + mouse.getStock());
        }

        System.out.println("\n[測試8] 出售 Mouse 超過庫存");
        System.out.println(mouse != null && !mouse.sell(999) ? "正確拒絕" : "錯誤");

        System.out.println("\n[測試9] 補貨 Monitor 20");
        Product mon = findProductByName("Monitor");
        if (mon != null && mon.restock(20)) {
            restockCount++;
            System.out.println("成功，目前庫存: " + mon.getStock());
        }

        System.out.println("\n[測試10] 修改 Headset 價格為 1390");
        Product hs = findProductByName("Headset");
        if (hs != null && hs.setPrice(1390)) {
            priceChangeCount++;
            System.out.println("成功，新價格: " + hs.getPrice());
        }

        System.out.println("\n[測試11] 顯示低庫存");
        displayLowStockProducts();

        System.out.println("\n[測試12] 全部庫存總價值");
        displayTotalInventoryValue();

        System.out.println("\n>>>>> 自動測試結束 <<<<<\n");
    }
}

/**
 * 1. 初始顯示全部商品 → 應顯示 5 筆預設商品
 * 2. 依完整名稱搜尋 "Mouse" → 找到並顯示 Mouse 資訊
 * 3. 搜尋 "  mouse  "（忽略大小寫與前後空白）→ 仍找到
 * 4. 搜尋不存在名稱 "Phone" → 顯示找不到
 * 5. 新增商品 "Keyboard" 價格 1200 庫存 15 → 成功
 * 6. 再次新增同名 "keyboard"（忽略大小寫）→ 失敗，提示重複
 * 7. 連續新增直到陣列滿（第 6~10 筆）→ 第 11 次新增失敗
 * 8. 出售商品 "Mouse" 數量 3 → 庫存減少，成功
 * 9. 出售超過庫存數量 → 失敗，庫存不變
 * 10. 補充庫存 "Monitor" 數量 20 → 庫存增加
 * 11. 修改價格為負數 → 失敗
 * 12. 修改價格為正數 → 成功
 * 13. 顯示低庫存商品 → 正確列出 stock < 10 的項目
 * 14. 顯示全部庫存總價值 → 正確計算 sum(price * stock)
 * 15. 結束並顯示操作摘要 → 顯示操作次數統計
 */