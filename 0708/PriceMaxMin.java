public class PriceMaxMin {
    public static void main(String[] args) {
        int price1 = 90;
        int price2 = 55;
        int price3 = 30;

        int max = price1;
        int min = price1;

        if (price2 > max) {
            max = price2;
        }

        if (price3 > max) {
            max = price3;
        }

        if (price2 < min) {
            min = price2;
        }

        if (price3 < min) {
            min = price3;
        }

        System.out.println("最高價格: " + max);
        System.out.println("最低價格: " + min);
    }
}
