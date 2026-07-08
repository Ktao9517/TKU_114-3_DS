public class SubtotalCalculator {
     public static void main(String[] args) {
        int result = calculateSubtotal(3, 2);
        System.out.println("商品小計: " + result);
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
}
