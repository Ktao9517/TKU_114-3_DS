import java.util.Scanner;

public class ProductSortingSystem {
    public static void main(String[] args) {
        StoreProduct[] original = {
            new StoreProduct("P01", "Keyboard", 890, 12),
            new StoreProduct("P02", "Mouse", 490, 25),
            new StoreProduct("P03", "Monitor", 5200, 5),
            new StoreProduct("P04", "USB", 250, 40),
            new StoreProduct("P05", "Headset", 1290, 8),
            new StoreProduct("P06", "Webcam", 890, 15),
            new StoreProduct("P07", "SSD", 2500, 10),
            new StoreProduct("P08", "RAM", 1800, 6),
            new StoreProduct("P09", "Case", 650, 20),
            new StoreProduct("P10", "PSU", 1500, 9)
        };

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== 商品排序選單 =====");
            System.out.println("1. 價格升冪");
            System.out.println("2. 價格降冪");
            System.out.println("3. 庫存降冪");
            System.out.println("4. 結束");
            System.out.print("選擇: ");
            int choice = sc.nextInt();
            if (choice == 4) break;

            StoreProduct[] copy = copyArray(original);
            switch (choice) {
                case 1:
                    insertionSort(copy, "priceAsc");
                    System.out.println("【價格升冪】");
                    break;
                case 2:
                    insertionSort(copy, "priceDesc");
                    System.out.println("【價格降冪】");
                    break;
                case 3:
                    insertionSort(copy, "stockDesc");
                    System.out.println("【庫存降冪】");
                    break;
                default:
                    System.out.println("無效選擇");
                    continue;
            }
            for (StoreProduct p : copy) {
                System.out.println(p);
            }
        }
        sc.close();
    }

    private static StoreProduct[] copyArray(StoreProduct[] src) {
        StoreProduct[] copy = new StoreProduct[src.length];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i];
        }
        return copy;
    }

    private static void insertionSort(StoreProduct[] arr, String mode) {
        for (int i = 1; i < arr.length; i++) {
            StoreProduct key = arr[i];
            int j = i - 1;
            while (j >= 0 && shouldSwap(arr[j], key, mode)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static boolean shouldSwap(StoreProduct a, StoreProduct b, String mode) {
        if (mode.equals("priceAsc")) return a.getPrice() > b.getPrice();
        if (mode.equals("priceDesc")) return a.getPrice() < b.getPrice();
        if (mode.equals("stockDesc")) return a.getStock() < b.getStock();
        return false;
    }
}
