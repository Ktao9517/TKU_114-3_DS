import java.util.Arrays;

public class InsertionSortPractice {
    public static void main(String[] args) {
        int[] data = {30, 10, 20, 50, 40, 5};
        System.out.println("===== Insertion Sort 移動追蹤 =====");
        System.out.println("原始: " + Arrays.toString(data));
        insertionSortAscending(data.clone());

        System.out.println("\n--- 已排序資料測試 ---");
        insertionSortAscending(new int[]{1, 2, 3, 4, 5});

        System.out.println("\n--- 反向排序資料測試 ---");
        insertionSortAscending(new int[]{50, 40, 30, 20, 10, 5});
        System.out.println("\n觀察：反向排序資料的右移次數最多。");
    }

    public static void insertionSortAscending(int[] arr) {
        int comparisons = 0;
        int shifts = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int pos = i - 1;
            System.out.println("第 " + i + " 輪 → key=" + key);

            while (pos >= 0 && arr[pos] > key) {
                comparisons++;
                arr[pos + 1] = arr[pos];
                shifts++;
                pos--;
            }
            if (pos >= 0) comparisons++; 
            arr[pos + 1] = key;
            System.out.println("  插入位置=" + (pos + 1) + "，陣列=" + Arrays.toString(arr));
        }
        System.out.println("比較次數: " + comparisons + "，右移次數: " + shifts);
    }
}
