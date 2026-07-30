import java.util.Arrays;

public class MergeArrayPractice {
    public static void main(String[] args) {
        int[] a = {1, 4, 7, 10, 15};
        int[] b = {-3, 2, 4, 8, 12, 20};

        System.out.println("===== 合併兩個排序陣列 =====");
        System.out.println("A: " + Arrays.toString(a));
        System.out.println("B: " + Arrays.toString(b));
        System.out.println("合併結果: " + Arrays.toString(merge(a, b)));

        System.out.println("A + 空: " + Arrays.toString(merge(a, new int[0])));
        System.out.println("空 + B: " + Arrays.toString(merge(new int[0], b)));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }
}
