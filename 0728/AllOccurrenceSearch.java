public class AllOccurrenceSearch {
    public static void main(String[] args) {
        int[] data = {15, 22, 8, 22, 41, 22, 7, 33, 22, 19};

        System.out.println("===== 搜尋全部相同資料 =====");
        System.out.print("陣列: ");
        for (int n : data) System.out.print(n + " ");
        System.out.println();

        searchAll(data, 22);
        System.out.println();
        searchAll(data, 99); // 不存在
        System.out.println();
        searchAll(data, 15); // 第一筆
    }

    public static void searchAll(int[] data, int target) {
        System.out.println("搜尋目標: " + target);
        int comparisons = 0;
        int count = 0;
        StringBuilder indexes = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            comparisons++;
            if (data[i] == target) {
                if (count > 0) indexes.append(", ");
                indexes.append(i);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("找不到目標 " + target);
        } else {
            System.out.println("出現索引: " + indexes.toString());
            System.out.println("出現次數: " + count);
        }
        System.out.println("比較次數: " + comparisons);
    }
}
