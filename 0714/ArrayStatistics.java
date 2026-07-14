import java.util.Scanner;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int count = readCount(sc);
        int[] scores = new int[count];
        
        inputScores(sc, scores);
        
        displayScores(scores);
        
        int total = calculateTotal(scores);
        double avg = (double) total / count;
        int max = findMax(scores);
        int min = findMin(scores);
        int passCount = countPass(scores);
        int failCount = count - passCount;
        
        System.out.println("總分: " + total);
        System.out.println("平均: " + String.format("%.2f", avg));
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("及格人數: " + passCount + "，不及格人數: " + failCount);
        
        System.out.print("請輸入目標成績: ");
        int target = sc.nextInt();
        int index = findIndex(scores, target);
        if (index == -1) {
            System.out.println("找不到該成績。");
        } else {
            System.out.println("第一次出現的索引: " + index);
        }
        
        sc.close();
    }
    
    public static int readCount(Scanner sc) {
        int n;
        while (true) {
            System.out.print("請輸入資料筆數 (1-50): ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 1 && n <= 50) {
                    return n;
                }
            } else {
                sc.next(); 
            }
            System.out.println("輸入錯誤，筆數必須為 1 到 50 的整數，請重新輸入。");
        }
    }
    
    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績 (0-100): ");
                if (sc.hasNextInt()) {
                    int s = sc.nextInt();
                    if (s >= 0 && s <= 100) {
                        scores[i] = s;
                        break;
                    }
                } else {
                    sc.next();
                }
                System.out.println("成績必須為 0 到 100 的整數，請重新輸入。");
            }
        }
    }
    
    public static int calculateTotal(int[] scores) {
        int sum = 0;
        for (int s : scores) {
            sum += s;
        }
        return sum;
    }
    
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int s : scores) {
            if (s > max) {
                max = s;
            }
        }
        return max;
    }
    
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int s : scores) {
            if (s < min) {
                min = s;
            }
        }
        return min;
    }
    
    public static int countPass(int[] scores) {
        int count = 0;
        for (int s : scores) {
            if (s >= 60) {
                count++;
            }
        }
        return count;
    }
    
    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public static void displayScores(int[] scores) {
        System.out.print("全部成績: ");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i]);
            if (i < scores.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
