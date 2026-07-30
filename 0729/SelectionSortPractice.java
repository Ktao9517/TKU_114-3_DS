import java.util.Arrays;

public class SelectionSortPractice {
    public static void main(String[] args) {
        int[] data = {42, 18, 35, 7, 29, 14};
        System.out.println("===== Selection Sort 每輪追蹤 =====");
        System.out.println("原始: " + Arrays.toString(data));

        int[] result = selectionSortAscending(data.clone());
        System.out.println("最終結果: " + Arrays.toString(result));

        System.out.println("\n空陣列: " + Arrays.toString(selectionSortAscending(new int[0])));
        System.out.println("單一元素: " + Arrays.toString(selectionSortAscending(new int[]{99})));
    }

    public static int[] selectionSortAscending(int[] arr) {
        if (arr == null || arr.length <= 1) return arr == null ? new int[0] : arr.clone();

        int comparisons = 0;
        int swaps = 0;

        for (int start = 0; start < arr.length - 1; start++) {
            int minIndex = start;
            for (int i = start + 1; i < arr.length; i++) {
                comparisons++;
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }
            System.out.println("第 " + (start + 1) + " 輪 → start=" + start +
                               ", 選中索引=" + minIndex + " (值=" + arr[minIndex] + ")");
            if (minIndex != start) {
                int temp = arr[start];
                arr[start] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
            System.out.println("  目前陣列: " + Arrays.toString(arr));
        }
        System.out.println("比較次數: " + comparisons + "，實際交換次數: " + swaps);
        return arr;
    }
}
