import java.util.Arrays;

public class InventorySearchPractice {
    public static void main(String[] args) {
        int[] ids = {305, 112, 478, 201, 99, 350, 180, 420, 55, 290, 165, 380};
        System.out.println("===== 先排序再搜尋 =====");
        System.out.println("排序前: " + Arrays.toString(ids));

        int[] sorted = ids.clone();
        mergeSort(sorted, new int[sorted.length], 0, sorted.length - 1);
        System.out.println("排序後: " + Arrays.toString(sorted));

        testSearch(sorted, 112);  
        testSearch(sorted, 55);   
        testSearch(sorted, 478);  
        testSearch(sorted, 999);  
    }

    private static void testSearch(int[] data, int target) {
        int idx = binarySearch(data, target);
        System.out.println("搜尋 " + target + " → 索引 = " + idx);
    }

    public static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        int i = left, j = mid + 1, k = left;
        for (int t = left; t <= right; t++) temp[t] = arr[t];
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) arr[k++] = temp[i++];
            else arr[k++] = temp[j++];
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }

    public static int binarySearch(int[] data, int target) {
        int low = 0, high = data.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (data[mid] == target) return mid;
            else if (data[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
