import java.util.Scanner;

public class TaskLinkedListSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskLinkedList tasks = new TaskLinkedList();

        
        tasks.addNormal("T001", "寫作業");
        tasks.addUrgent("T002", "準備考試");
        tasks.addNormal("T003", "洗衣服");

        while (true) {
            displayMenu();
            System.out.print("請選擇 (1-7): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    System.out.print("工作代碼: ");
                    String uCode = sc.nextLine().trim();
                    if (uCode.isEmpty()) uCode = sc.nextLine().trim();
                    System.out.print("工作說明: ");
                    String uDesc = sc.nextLine().trim();
                    tasks.addUrgent(uCode, uDesc);
                    break;
                case 2:
                    System.out.print("工作代碼: ");
                    String nCode = sc.nextLine().trim();
                    if (nCode.isEmpty()) nCode = sc.nextLine().trim();
                    System.out.print("工作說明: ");
                    String nDesc = sc.nextLine().trim();
                    tasks.addNormal(nCode, nDesc);
                    break;
                case 3:
                    System.out.print("要完成的工作代碼: ");
                    String cCode = sc.nextLine().trim();
                    if (cCode.isEmpty()) cCode = sc.nextLine().trim();
                    tasks.completeTask(cCode);
                    break;
                case 4:
                    System.out.print("要刪除的工作代碼: ");
                    String dCode = sc.nextLine().trim();
                    if (dCode.isEmpty()) dCode = sc.nextLine().trim();
                    tasks.removeTask(dCode);
                    break;
                case 5:
                    tasks.listIncomplete();
                    break;
                case 6:
                    tasks.printAll();
                    tasks.printStats();
                    break;
                case 7:
                    System.out.println("結束工作項目系統。");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇。");
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("========== 工作項目系統 ==========");
        System.out.println("1. 新增緊急工作（前端）");
        System.out.println("2. 新增一般工作（尾端）");
        System.out.println("3. 完成工作");
        System.out.println("4. 刪除工作");
        System.out.println("5. 列出未完成工作");
        System.out.println("6. 顯示全部與統計");
        System.out.println("7. 結束");
        System.out.println("==================================");
    }

    private static int readInt(Scanner sc) {
        while (true) {
            if (sc.hasNextInt()) {
                int val = sc.nextInt();
                sc.nextLine();
                return val;
            } else {
                System.out.print("請輸入有效整數: ");
                sc.next();
            }
        }
    }
}