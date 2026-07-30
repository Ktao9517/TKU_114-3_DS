import java.util.Scanner;

public class ProductIdSearchPractice {
    public static void main(String[] args) {
        
        String[] productIds = {
            "P205", "P101", "P330", "P150", "P088", "P412", "P003", "P277", "P199"
        };

        System.out.println("===== 商品編號循序搜尋 =====");
        System.out.print("目前商品編號: ");
        for (String id : productIds) {
            System.out.print(id + " ");
        }
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入要搜尋的編號: ");
        String target = sc.nextLine().trim();

        int comparisons = 0;
        int foundIndex = -1;

        for (int i = 0; i < productIds.length; i++) {
            comparisons++;
            if (productIds[i].equalsIgnoreCase(target)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("找到！索引 = " + foundIndex + "，編號 = " + productIds[foundIndex]);
        } else {
            System.out.println("找不到編號「" + target + "」");
        }
        System.out.println("實際比較次數: " + comparisons);

        
        System.out.println("\n建議測試：第一筆 P205、最後一筆 P199、不存在 P999");
        sc.close();
    }
}
