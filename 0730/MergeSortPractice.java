import java.util.Arrays;

public class MergeSortPractice {
    public static void main(String[] args) {
        int[] data = {41, 12, 35, 8, 27, 19, 50, 3};
        System.out.println("===== Merge Sort 追蹤 =====");
        System.out.println("原始: " + Arrays.toString(data));
        mergeSort(data, new int[data.length], 0, data.length - 1);
        System.out.println("最終: " + Arrays.toString(data));

        int[] empty = {};
        mergeSort(empty, new int[0], 0, -1);
        System.out.println("空陣列: " + Arrays.toString(empty));

        int[] single = {99};
        mergeSort(single, new int[1], 0, 0);
        System.out.println("單筆: " + Arrays.toString(single));
    }

    public static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        System.out.println("拆分: [" + left + ".." + mid + "] 與 [" + (mid + 1) + ".." + right + "]");
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
        System.out.print("合併 [" + left + ".." + right + "]: ");
        for (int i = left; i <= right; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) temp[i] = arr[i];
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) arr[k++] = temp[i++];
            else arr[k++] = temp[j++];
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }
}
