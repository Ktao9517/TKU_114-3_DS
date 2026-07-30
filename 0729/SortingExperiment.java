import java.util.Arrays;
import java.util.Random;

public class SortingExperiment {
    public static void main(String[] args) {
        int n = 20;
        int[] sorted = new int[n];
        int[] reverse = new int[n];
        int[] random = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = i + 1;
            reverse[i] = n - i;
            random[i] = new Random(42).nextInt(100);
        }

        System.out.println("===== 排序操作統計 =====");
        runTest("已排序", sorted);
        runTest("反向", reverse);
        runTest("隨機", random);
    }

    private static void runTest(String label, int[] original) {
        System.out.println("\n--- " + label + " 資料 ---");
        int[] a = original.clone();
        int[] b = original.clone();
        System.out.println("Selection: " + selectionStats(a));
        System.out.println("Insertion: " + insertionStats(b));
    }

    private static String selectionStats(int[] arr) {
        int cmp = 0, swap = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                cmp++;
                if (arr[j] < arr[min]) min = j;
            }
            if (min != i) {
                int t = arr[i]; arr[i] = arr[min]; arr[min] = t;
                swap++;
            }
        }
        return "比較=" + cmp + ", 交換=" + swap;
    }

    private static String insertionStats(int[] arr) {
        int cmp = 0, shift = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                cmp++;
                arr[j + 1] = arr[j];
                shift++;
                j--;
            }
            if (j >= 0) cmp++;
            arr[j + 1] = key;
        }
        return "比較=" + cmp + ", 右移=" + shift;
    }
}
