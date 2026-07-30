import java.util.Arrays;

public class ScoreRankingPractice {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 92, 65, 88, 70, 95, 78};
        System.out.println("===== 成績降冪排名 =====");
        System.out.println("原始成績: " + Arrays.toString(scores));

        int[] sorted = selectionSortDescending(scores.clone());
        System.out.println("\n排名結果:");
        int rank = 1;
        for (int i = 0; i < sorted.length; i++) {
            if (i > 0 && sorted[i] != sorted[i - 1]) {
                rank = i + 1;
            }
            String pass = sorted[i] >= 60 ? "及格" : "不及格";
            System.out.println("第 " + rank + " 名: " + sorted[i] + " 分 (" + pass + ")");
        }
    }

    public static int[] selectionSortDescending(int[] arr) {
        for (int start = 0; start < arr.length - 1; start++) {
            int maxIndex = start;
            for (int i = start + 1; i < arr.length; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            if (maxIndex != start) {
                int temp = arr[start];
                arr[start] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
        return arr;
    }
}
