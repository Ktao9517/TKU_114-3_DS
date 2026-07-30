public class SearchEfficiencyReport {
    public static void main(String[] args) {
        System.out.println("===== 搜尋效率分析 =====\n");

        int[] sizes = {16, 128, 1024};
        for (int size : sizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = (i + 1) * 10; 
            }

            System.out.println("資料量: " + size);
            
            int first = data[0];
            System.out.println("  搜尋第一筆 " + first +
                " → Sequential: " + sequentialChecks(data, first) +
                " 次, Binary: " + binaryChecks(data, first) + " 次");

            
            int last = data[size - 1];
            System.out.println("  搜尋最後一筆 " + last +
                " → Sequential: " + sequentialChecks(data, last) +
                " 次, Binary: " + binaryChecks(data, last) + " 次");

            
            int missing = last + 999;
            System.out.println("  搜尋不存在 " + missing +
                " → Sequential: " + sequentialChecks(data, missing) +
                " 次, Binary: " + binaryChecks(data, missing) + " 次");
            System.out.println();
        }

        System.out.println("===== 觀察結果 =====");
        System.out.println("1. Sequential Search 最壞情況比較次數約等於資料量 n，屬於 O(n)。");
        System.out.println("2. Binary Search 比較次數約為 log2(n)，資料量從 16→1024 時比較次數增加緩慢。");
        System.out.println("3. 當資料已排序且資料量較大時，Binary Search 明顯較有效率。");
        System.out.println("4. 若資料未排序，只能使用 Sequential Search。");
    }

    public static int sequentialChecks(int[] data, int target) {
        int checks = 0;
        for (int value : data) {
            checks++;
            if (value == target) return checks;
        }
        return checks;
    }

    public static int binaryChecks(int[] data, int target) {
        int left = 0, right = data.length - 1, checks = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            checks++;
            if (data[mid] == target) return checks;
            else if (data[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return checks;
    }
}
