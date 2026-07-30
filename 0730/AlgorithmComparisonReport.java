import java.util.Arrays;
import java.util.Random;

public class AlgorithmComparisonReport {
    public static void main(String[] args) {
        System.out.println("===== 演算法比較報告 =====\n");
        int[] sizes = {16, 128, 1024};

        for (int n : sizes) {
            System.out.println("資料量 n = " + n);
            int[] sorted = createSorted(n);
            int[] reverse = createReverse(n);
            int[] random = createRandom(n);

            compare("已排序", sorted);
            compare("反向", reverse);
            compare("亂序", random);
            System.out.println();
        }

        System.out.println("===== 觀察結論 =====");
        System.out.println("1. Selection Sort 比較次數固定約 n²/2，與資料狀態無關。");
        System.out.println("2. Insertion Sort 在已排序時接近 O(n)，反向時最差。");
        System.out.println("3. Merge Sort 比較次數穩定接近 n log n，不受資料順序大幅影響。");
        System.out.println("4. 資料量大時 Merge Sort 明顯優於另外兩者。");
    }

    private static void compare(String label, int[] original) {
        int[] a = original.clone();
        int[] b = original.clone();
        int[] c = original.clone();
        System.out.println("  " + label +
            " → Selection比較:" + selectionCmp(a) +
            ", Insertion比較:" + insertionCmp(b) +
            ", Merge比較:" + mergeCmp(c));
    }

    private static int selectionCmp(int[] arr) {
        int cmp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                cmp++;
                if (arr[j] < arr[min]) min = j;
            }
            int t = arr[i]; arr[i] = arr[min]; arr[min] = t;
        }
        return cmp;
    }

    private static int insertionCmp(int[] arr) {
        int cmp = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                cmp++;
                arr[j + 1] = arr[j];
                j--;
            }
            if (j >= 0) cmp++;
            arr[j + 1] = key;
        }
        return cmp;
    }

    private static int mergeCmp(int[] arr) {
        int[] counter = {0};
        mergeSortCount(arr, new int[arr.length], 0, arr.length - 1, counter);
        return counter[0];
    }

    private static void mergeSortCount(int[] arr, int[] temp, int left, int right, int[] counter) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortCount(arr, temp, left, mid, counter);
        mergeSortCount(arr, temp, mid + 1, right, counter);
        // merge with count
        for (int i = left; i <= right; i++) temp[i] = arr[i];
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            counter[0]++;
            if (temp[i] <= temp[j]) arr[k++] = temp[i++];
            else arr[k++] = temp[j++];
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }

    private static int[] createSorted(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        return a;
    }

    private static int[] createReverse(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = n - i;
        return a;
    }

    private static int[] createRandom(int n) {
        int[] a = new int[n];
        Random r = new Random(42);
        for (int i = 0; i < n; i++) a[i] = r.nextInt(n * 10);
        return a;
    }
}
