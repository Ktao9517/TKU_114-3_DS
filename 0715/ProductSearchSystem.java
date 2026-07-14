import java.util.Scanner;

public class ProductSearchSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-6): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    System.out.print("請輸入完整商品名稱: ");
                    String fullName = sc.nextLine().trim();
                    searchExactName(fullName, names, prices, stocks);
                    break;
                case 3:
                    System.out.print("請輸入部分商品名稱關鍵字: ");
                    String partial = sc.nextLine().trim();
                    searchPartialName(partial, names, prices, stocks);
                    break;
                case 4:
                    displayLongestNameProduct(names, prices, stocks);
                    break;
                case 5:
                    System.out.print("請輸入商品名稱: ");
                    String targetName = sc.nextLine().trim();
                    System.out.print("請輸入要搜尋的關鍵字: ");
                    String keyword = sc.nextLine().trim();
                    showKeywordPosition(targetName, keyword, names);
                    break;
                case 6:
                    System.out.println("程式結束，謝謝使用！");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇，請輸入 1-6。");
            }
            System.out.println();
        }
    }

    public static void displayMenu() {
        System.out.println("========== 商品名稱搜尋系統 ==========");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
        System.out.println("3. 部分名稱搜尋 (可顯示多筆結果)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品名稱與搜尋關鍵字第一次出現的位置");
        System.out.println("6. 結束");
        System.out.println("======================================");
    }

    public static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("請輸入整數！");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); 
        return val;
    }

    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n商品編號\t商品名稱\t\t單價\t庫存");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d\t\t%-12s\t%d\t%d%n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void searchExactName(String query, String[] names, int[] prices, int[] stocks) {
        if (query.isEmpty()) {
            System.out.println("搜尋關鍵字不可為空。");
            return;
        }
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(query)) {
                System.out.println("找到商品：");
                System.out.println("名稱: " + names[i]);
                System.out.println("單價: " + prices[i] + " 元");
                System.out.println("庫存: " + stocks[i] + " 件");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("找不到名稱為 \"" + query + "\" 的商品。");
        }
    }

    public static void searchPartialName(String query, String[] names, int[] prices, int[] stocks) {
        if (query.isEmpty()) {
            System.out.println("搜尋關鍵字不可為空。");
            return;
        }
        System.out.println("部分名稱搜尋結果 (關鍵字: \"" + query + "\"):");
        boolean found = false;
        String lowerQuery = query.toLowerCase();
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(lowerQuery)) {
                System.out.printf("%d. %s (單價: %d, 庫存: %d)%n",
                        (i + 1), names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("沒有符合的商品。");
        }
    }

    public static void displayLongestNameProduct(String[] names, int[] prices, int[] stocks) {
        if (names.length == 0) {
            System.out.println("沒有商品資料。");
            return;
        }
        int maxIdx = 0;
        for (int i = 1; i < names.length; i++) {
            if (names[i].length() > names[maxIdx].length()) {
                maxIdx = i;
            }
        }
        System.out.println("名稱最長的商品：");
        System.out.println("名稱: " + names[maxIdx] + " (長度: " + names[maxIdx].length() + ")");
        System.out.println("單價: " + prices[maxIdx] + " 元");
        System.out.println("庫存: " + stocks[maxIdx] + " 件");
    }

    public static void showKeywordPosition(String targetName, String keyword, String[] names) {
        if (targetName.isEmpty() || keyword.isEmpty()) {
            System.out.println("商品名稱與關鍵字皆不可為空。");
            return;
        }

        String actualName = null;
        for (String n : names) {
            if (n.equalsIgnoreCase(targetName)) {
                actualName = n;
                break;
            }
        }
        if (actualName == null) {
            System.out.println("找不到商品 \"" + targetName + "\"。");
            return;
        }

        int pos = indexOfIgnoreCase(actualName, keyword);
        if (pos == -1) {
            System.out.println("商品名稱 \"" + actualName + "\" 中找不到關鍵字 \"" + keyword + "\"。");
        } else {
            System.out.println("商品名稱: " + actualName);
            System.out.println("關鍵字 \"" + keyword + "\" 第一次出現的位置: " + pos);
        }
    }

    public static int indexOfIgnoreCase(String source, String target) {
        if (source == null || target == null || target.isEmpty()) {
            return -1;
        }
        String srcLower = source.toLowerCase();
        String tgtLower = target.toLowerCase();
        return srcLower.indexOf(tgtLower);
    }
}

/**
 * 測試案例1：完整名稱搜尋 "Keyboard" → 應找到 Keyboard, 890, 12
 * 測試案例2：完整名稱搜尋 "  mouse  " (含前後空白、大小寫) → 應找到 Mouse
 * 測試案例3：完整名稱搜尋 "Laptop" → 應顯示找不到
 * 測試案例4：部分名稱搜尋 "o" → 應找到 Mouse, Monitor
 * 測試案例5：部分名稱搜尋 "usb" → 應找到 USB Cable
 * 測試案例6：顯示名稱最長商品 → USB Cable (9字元，含空白) 或 Headset 等
 * 測試案例7：關鍵字位置 "Monitor" 中找 "ito" → 位置 2
 * 測試案例8：關鍵字位置 "USB Cable" 中找 "cable" (忽略大小寫) → 位置 4
 */