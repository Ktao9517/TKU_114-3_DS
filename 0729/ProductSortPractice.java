public class ProductSortPractice {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P01", "Keyboard", 890, 12),
            new Product("P02", "Mouse", 490, 20),
            new Product("P03", "Monitor", 5200, 5),
            new Product("P04", "USB Cable", 250, 30),
            new Product("P05", "Headset", 1290, 8),
            new Product("P06", "Webcam", 890, 15),  // 同價
            new Product("P07", "SSD", 2500, 10),
            new Product("P08", "RAM", 1290, 6)     // 同價
        };

        System.out.println("===== 商品物件排序（價格升冪，穩定）=====");
        System.out.println("排序前:");
        for (Product p : products) System.out.println(p);

        insertionSortByPrice(products);

        System.out.println("\n排序後:");
        for (Product p : products) System.out.println(p);
    }

    // 穩定 Insertion Sort（使用 >= 會破壞穩定，這裡用 > 保持穩定）
    public static void insertionSortByPrice(Product[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Product key = arr[i];
            int pos = i - 1;
            while (pos >= 0 && arr[pos].getPrice() > key.getPrice()) {
                arr[pos + 1] = arr[pos];
                pos--;
            }
            arr[pos + 1] = key;
        }
    }
}
