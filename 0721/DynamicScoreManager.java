import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("===== 動態成績管理 =====");
        System.out.println("請持續輸入成績 (0-100)，輸入 -1 結束");

        while (true) {
            System.out.print("請輸入成績: ");
            if (!sc.hasNextInt()) {
                System.out.println("請輸入整數！");
                sc.next();
                continue;
            }
            int score = sc.nextInt();

            if (score == -1) {
                break;
            }

            if (score < 0 || score > 100) {
                System.out.println("成績必須在 0 到 100 之間，請重新輸入。");
                continue;
            }

            scores.add(score);
            System.out.println("已加入成績: " + score);
        }

        if (scores.isEmpty()) {
            System.out.println("\n沒有輸入任何成績。");
            sc.close();
            return;
        }

        System.out.println("\n===== 統計結果 =====");
        System.out.println("筆數: " + scores.size());
        System.out.printf("平均: %.2f%n", calculateAverage(scores));
        System.out.println("最高: " + findMax(scores));
        System.out.println("最低: " + findMin(scores));

        ArrayList<Integer> passList = getPassList(scores);
        System.out.print("及格名單 (>=60): ");
        if (passList.isEmpty()) {
            System.out.println("無");
        } else {
            for (int i = 0; i < passList.size(); i++) {
                System.out.print(passList.get(i));
                if (i < passList.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        sc.close();
    }

    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int s : scores) {
            sum += s;
        }
        return (double) sum / scores.size();
    }

    public static int findMax(ArrayList<Integer> scores) {
        int max = scores.get(0);
        for (int s : scores) {
            if (s > max) {
                max = s;
            }
        }
        return max;
    }

    public static int findMin(ArrayList<Integer> scores) {
        int min = scores.get(0);
        for (int s : scores) {
            if (s < min) {
                min = s;
            }
        }
        return min;
    }

    public static ArrayList<Integer> getPassList(ArrayList<Integer> scores) {
        ArrayList<Integer> pass = new ArrayList<>();
        for (int s : scores) {
            if (s >= 60) {
                pass.add(s);
            }
        }
        return pass;
    }
}