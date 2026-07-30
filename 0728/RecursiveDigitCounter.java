public class RecursiveDigitCounter {

    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            System.out.println("target 必須介於 0~9");
            return -1;
        }
        if (number < 0) {
            number = -number;
        }
        
        if (number < 10) {
            return (number == target) ? 1 : 0;
        }
        int last = number % 10;
        int count = (last == target) ? 1 : 0;
        return count + countDigit(number / 10, target);
    }

    public static void main(String[] args) {
        System.out.println("===== 遞迴統計數字出現次數 =====");

        
        System.out.println("countDigit(122333, 3) = " + countDigit(122333, 3)); // 3
        System.out.println("countDigit(10001, 0) = " + countDigit(10001, 0));   // 3
        System.out.println("countDigit(7, 7) = " + countDigit(7, 7));           // 1
        System.out.println("countDigit(12345, 9) = " + countDigit(12345, 9));   // 0
        System.out.println("countDigit(0, 0) = " + countDigit(0, 0));           // 1
        System.out.println("countDigit(5555, 5) = " + countDigit(5555, 5));     // 4
        System.out.println("countDigit(987654321, 1) = " + countDigit(987654321, 1)); // 1
    }
}
