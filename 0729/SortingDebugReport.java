import java.util.Arrays;

public class SortingDebugReport {
    public static void main(String[] args) {
        System.out.println("===== 排序程式除錯報告 =====\n");

        int[] data1 = {5, 2, 8, 1, 9};
        System.out.println("【錯誤1：內層範圍錯誤】");
        System.out.println("原始: " + Arrays.toString(data1));
        System.out.println("錯誤結果: " + Arrays.toString(buggySelection1(data1.clone())));
        System.out.println("修正結果: " + Arrays.toString(correctSelection(data1.clone())));
        // 錯誤原因：內層迴圈從 0 開始，重複處理已排序區

        int[] data2 = {4, 3, 2, 1};
        System.out.println("\n【錯誤2：key 未保存】");
        System.out.println("原始: " + Arrays.toString(data2));
        System.out.println("錯誤結果: " + Arrays.toString(buggyInsertion2(data2.clone())));
        System.out.println("修正結果: " + Arrays.toString(correctInsertion(data2.clone())));
        // 錯誤原因：沒有先把 key 存起來，右移時覆蓋了原值

        int[] data3 = {3, 1, 4, 2};
        System.out.println("\n【錯誤3：比較方向錯誤】");
        System.out.println("原始: " + Arrays.toString(data3));
        System.out.println("錯誤結果: " + Arrays.toString(buggySelection3(data3.clone())));
        System.out.println("修正結果: " + Arrays.toString(correctSelection(data3.clone())));
        // 錯誤原因：用 > 找最小值，變成降冪
    }

    // 錯誤版本1：內層從 0 開始
    public static int[] buggySelection1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = 0; j < arr.length; j++) { // 錯誤：應從 i+1 開始
                if (arr[j] < arr[min]) min = j;
            }
            int t = arr[i]; arr[i] = arr[min]; arr[min] = t;
        }
        return arr;
    }

    // 錯誤版本2：沒保存 key
    public static int[] buggyInsertion2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) { // 直接用 arr[i]，會被覆蓋
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = arr[i];
        }
        return arr;
    }

    // 錯誤版本3：比較方向錯
    public static int[] buggySelection3(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[min]) min = j; // 錯誤：變成找最大
            }
            int t = arr[i]; arr[i] = arr[min]; arr[min] = t;
        }
        return arr;
    }

    public static int[] correctSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            if (min != i) {
                int t = arr[i]; arr[i] = arr[min]; arr[min] = t;
            }
        }
        return arr;
    }

    public static int[] correctInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 正確保存
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
