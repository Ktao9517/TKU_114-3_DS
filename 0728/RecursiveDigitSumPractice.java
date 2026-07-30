public class RecursiveDigitSumPractice {

    public static int digitSum(int number) {
        if (number < 0) {
            number = -number; 
        }
        if (number < 10) {
            return number; 
        }
        return (number % 10) + digitSum(number / 10);
    }

    public static void main(String[] args) {
        System.out.println("===== 遞迴計算各位數總和 =====");
        int[] tests = {5729, 0, 7, 999, 1001, 12345};
        for (int n : tests) {
            System.out.println("digitSum(" + n + ") = " + digitSum(n));
        }
        
        System.out.println("\n驗證: 5+7+2+9 = 23 → " + (digitSum(5729) == 23 ? "正確" : "錯誤"));
    }
}
