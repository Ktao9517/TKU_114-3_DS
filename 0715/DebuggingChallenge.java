/*
 * 錯誤1：編譯錯誤
 *   位置：原程式最後 if 區塊內 System.out.println("系統結束，年齡：" + age)
 *   錯誤類型：編譯錯誤 (missing semicolon)
 *   原因：println 語句結尾缺少分號 ;
 *   修正方式：在語句結尾加上 ;
 *
 * 錯誤2：陣列越界錯誤
 *   位置：for (int i = 0; i <= scores.length; i++)
 *   錯誤類型：執行時期例外 (ArrayIndexOutOfBoundsException)
 *   原因：迴圈條件使用 <= ，當 i == scores.length (3) 時會存取 scores[3]，超出陣列範圍 0~2
 *   修正方式：改為 i < scores.length
 *   Breakpoint 記錄：當 i=3 時，scores.length=3，嘗試存取 scores[3] → 拋出 ArrayIndexOutOfBoundsException
 *                 當下 total 已累加到 80+75+92=247，但尚未完成迴圈就中斷
 *
 * 錯誤3：字串比較錯誤
 *   位置：if (command == "exit")
 *   錯誤類型：邏輯錯誤 (字串內容比較失敗)
 *   原因：使用 == 比較字串只比較參考位址，不會比較內容。即使輸入 "exit" 也會是 false
 *   修正方式：改為 command.equals("exit") 或 "exit".equals(command)（較安全避免 null）
 *
 * 錯誤4：整數除法造成的邏輯錯誤
 *   位置：double average = total / scores.length;
 *   錯誤類型：邏輯錯誤 (精度損失)
 *   原因：total 與 scores.length 都是 int，整數除法會先截斷小數部分再賦值給 double
 *         例如 total=247, length=3 → 247/3 = 82 (整數) → average=82.0，正確應為 82.333...
 *   修正方式：改為 double average = (double) total / scores.length;
 *   Breakpoint 記錄：total=247, scores.length=3，修正前 average 被設為 82.0
 *
 * 錯誤5：Scanner 換行問題
 *   位置：int age = sc.nextInt(); 之後直接 String command = sc.nextLine();
 *   錯誤類型：邏輯錯誤 / 輸入緩衝區殘留
 *   原因：nextInt() 只讀取數字，不會消耗後面的換行字元 \n。
 *         下一次 nextLine() 會立刻讀到這個殘留的空字串，導致 command 永遠是 ""
 *   修正方式：在 nextInt() 之後立刻呼叫 sc.nextLine(); 清除緩衝區中的換行字元
 *   Breakpoint 記錄：執行 nextInt() 後，輸入緩衝區仍殘留 "\n"，command 被設為空字串 ""
 */

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if ("exit".equals(command)) {
            
            System.out.println("系統結束，年齡：" + age);
        } else {
            System.out.println("指令不是 exit，程式結束。目前年齡：" + age);
        }

        sc.close();
    }
}