import java.util.Scanner;

public class ProductDataManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        String[] names = new String[records.length];
        int[] prices = new int[records.length];
        int[] stocks = new int[records.length];

        boolean parseOk = parseRecords(records, names, prices, stocks);
        if (!parseOk) {
            System.out.println("初始資料解析失敗，程式結束。");
            sc.close();
            return;
        }

        System.out.println("初始資料解析成功！");
        displayTable(names, prices, stocks);

        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-7): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    displayTable(names, prices, stocks);
                    break;
                case 2:
                    System.out.print("請輸入完整商品名稱: ");
                    String full = sc.nextLine().trim();
                    searchExact(full, names, prices, stocks);
                    break;
                case 3:
                    System.out.print("請輸入部分名稱關鍵字: ");
                    String part = sc.nextLine().trim();
                    searchPartial(part, names, prices, stocks);
                    break;
                case 4:
                    showLowStock(names, stocks);
                    break;
                case 5:
                    int totalValue = calculateTotalValue(prices, stocks);
                    System.out.println("全部庫存總價值: " + totalValue + " 元");
                    break;
                case 6:
                    System.out.print("請輸入新商品資料 (格式: 名稱,價格,庫存): ");
                    String newRecord = sc.nextLine().trim();
                    String[] newNames = new String[names.length + 1];
                    int[] newPrices = new int[prices.length + 1];
                    int[] newStocks = new int[stocks.length + 1];
                    
                    System.arraycopy(names, 0, newNames, 0, names.length);
                    System.arraycopy(prices, 0, newPrices, 0, prices.length);
                    System.arraycopy(stocks, 0, newStocks, 0, stocks.length);

                    if (addNewRecord(newRecord, newNames, newPrices, newStocks, names.length)) {
                        names = newNames;
                        prices = newPrices;
                        stocks = newStocks;
                        System.out.println("新商品已成功加入！");
                        displayTable(names, prices, stocks);
                    }
                    break;
                case 7:
                    System.out.println("程式結束，謝謝使用！");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇，請輸入 1-7。");
            }
            System.out.println();
        }
    }

    public static void displayMenu() {
        System.out.println("========== 商品文字資料管理器 ==========");
        System.out.println("1. 顯示商品表格");
        System.out.println("2. 完整名稱搜尋");
        System.out.println("3. 部分名稱搜尋");
        System.out.println("4. 顯示低庫存商品 (庫存 < 10)");
        System.out.println("5. 顯示庫存總價值");
        System.out.println("6. 輸入一筆新文字資料並驗證");
        System.out.println("7. 結束");
        System.out.println("========================================");
    }

    public static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("請輸入整數！");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); // 清除換行
        return val;
    }

    public static boolean parseRecords(String[] records, String[] names, int[] prices, int[] stocks) {
        for (int i = 0; i < records.length; i++) {
            String[] parts = records[i].split(",");
            if (parts.length != 3) {
                System.out.println("解析失敗：第 " + (i + 1) + " 筆資料欄位數量不正確 → " + records[i]);
                return false;
            }
            names[i] = parts[0].trim();
            try {
                prices[i] = Integer.parseInt(parts[1].trim());
                stocks[i] = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException e) {
                System.out.println("解析失敗：第 " + (i + 1) + " 筆數字格式錯誤 → " + records[i]);
                return false;
            }
            if (names[i].isEmpty() || prices[i] < 0 || stocks[i] < 0) {
                System.out.println("解析失敗：第 " + (i + 1) + " 筆資料內容不合法 → " + records[i]);
                return false;
            }
        }
        return true;
    }

    public static void displayTable(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n商品編號\t商品名稱\t\t單價\t庫存");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d\t\t%-12s\t%d\t%d%n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void searchExact(String query, String[] names, int[] prices, int[] stocks) {
        if (query.isEmpty()) {
            System.out.println("搜尋名稱不可為空。");
            return;
        }
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(query)) {
                System.out.println("找到商品：");
                System.out.println("名稱: " + names[i] + ", 單價: " + prices[i] + ", 庫存: " + stocks[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("找不到完整名稱為 \"" + query + "\" 的商品。");
        }
    }

    public static void searchPartial(String query, String[] names, int[] prices, int[] stocks) {
        if (query.isEmpty()) {
            System.out.println("搜尋關鍵字不可為空。");
            return;
        }
        System.out.println("部分名稱搜尋結果：");
        boolean found = false;
        String lower = query.toLowerCase();
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(lower)) {
                System.out.printf("%d. %s (單價: %d, 庫存: %d)%n",
                        (i + 1), names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("沒有符合的商品。");
        }
    }

    public static void showLowStock(String[] names, int[] stocks) {
        System.out.println("\n【低庫存商品】(庫存 < 10)");
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

    public static boolean addNewRecord(String record, String[] names, int[] prices, int[] stocks, int index) {
        if (record == null || record.trim().isEmpty()) {
            System.out.println("格式錯誤：輸入資料不可為空。");
            return false;
        }

        String[] parts = record.split(",");
        if (parts.length != 3) {
            System.out.println("格式錯誤：欄位數量不正確（應為 名稱,價格,庫存），實際欄位數: " + parts.length);
            return false;
        }

        String name = parts[0].trim();
        if (name.isEmpty()) {
            System.out.println("格式錯誤：商品名稱不可為空。");
            return false;
        }

        int price;
        int stock;
        try {
            price = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("數字轉換錯誤：價格必須為整數 → \"" + parts[1].trim() + "\"");
            return false;
        }
        try {
            stock = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            System.out.println("數字轉換錯誤：庫存必須為整數 → \"" + parts[2].trim() + "\"");
            return false;
        }

        if (price < 0) {
            System.out.println("資料驗證錯誤：價格不可為負數。");
            return false;
        }
        if (stock < 0) {
            System.out.println("資料驗證錯誤：庫存不可為負數。");
            return false;
        }

        for (int i = 0; i < index; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                System.out.println("資料驗證錯誤：商品名稱 \"" + name + "\" 已存在。");
                return false;
            }
        }

        names[index] = name;
        prices[index] = price;
        stocks[index] = stock;
        return true;
    }
}


/**
 * 測試案例1：正常解析初始 records → 應成功建立 5 筆商品並顯示表格
 * 測試案例2：完整名稱搜尋 "Monitor" → 找到 Monitor, 5200, 5
 * 測試案例3：完整名稱搜尋 "  keyboard  " (前後空白+大小寫) → 找到 Keyboard
 * 測試案例4：部分名稱搜尋 "u" → 找到 Mouse, USB Cable
 * 測試案例5：低庫存顯示 → 應顯示 Monitor(5), Headset(8)
 * 測試案例6：庫存總價值計算 → 890*12 + 490*20 + 5200*5 + 250*30 + 1290*8 = 計算正確值
 * 測試案例7：新增合法資料 "Webcam,1590,15" → 成功加入並顯示更新後表格
 * 測試案例8：新增格式錯誤 "InvalidData" → 顯示「格式錯誤：欄位數量不正確」
 */