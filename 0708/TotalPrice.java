public class TotalPrice {
    public static void main(String[] args) {
        
        int price1 = 30;
        int price2 = 45;
        int price3 = 60;

        int totalPrice = 0;

        totalPrice += price1; 
        totalPrice += price2;
        totalPrice += price3;

        System.out.println("Total price: " + totalPrice);
    }
}

