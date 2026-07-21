import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();

        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-6): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    addName(sc, names);
                    break;
                case 2:
                    searchName(sc, names);
                    break;
                case 3:
                    modifyName(sc, names);
                    break;
                case 4:
                    deleteName(sc, names);
                    break;
                case 5:
                    listAll(names);
                    break;
                case 6:
                    System.out.println("程式結束。");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇，請輸入 1-6。");
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("========== 名單管理系統 ==========");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部");
        System.out.println("6. 結束");
        System.out.println("==================================");
    }

    private static void addName(Scanner sc, ArrayList<String> names) {
        System.out.print("請輸入要新增的姓名: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            name = sc.nextLine().trim();
        }

        if (name.isEmpty()) {
            System.out.println("新增失敗：姓名不可為空白。");
            return;
        }

        if (findIndexIgnoreCase(names, name) != -1) {
            System.out.println("新增失敗：已存在相同姓名（忽略大小寫）。");
            return;
        }

        names.add(name);
        System.out.println("新增成功：" + name);
    }

    private static void searchName(Scanner sc, ArrayList<String> names) {
        System.out.print("請輸入要搜尋的姓名: ");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            keyword = sc.nextLine().trim();
        }

        int index = findIndexIgnoreCase(names, keyword);
        if (index == -1) {
            System.out.println("找不到姓名：" + keyword);
        } else {
            System.out.println("找到姓名：" + names.get(index) + "（索引：" + index + "）");
        }
    }

    private static void modifyName(Scanner sc, ArrayList<String> names) {
        System.out.print("請輸入要修改的原姓名: ");
        String oldName = sc.nextLine().trim();
        if (oldName.isEmpty()) {
            oldName = sc.nextLine().trim();
        }

        int index = findIndexIgnoreCase(names, oldName);
        if (index == -1) {
            System.out.println("找不到姓名：" + oldName);
            return;
        }

        System.out.print("請輸入新姓名: ");
        String newName = sc.nextLine().trim();
        if (newName.isEmpty()) {
            System.out.println("修改失敗：新姓名不可為空白。");
            return;
        }

        for (int i = 0; i < names.size(); i++) {
            if (i != index && names.get(i).equalsIgnoreCase(newName)) {
                System.out.println("修改失敗：已存在相同姓名。");
                return;
            }
        }

        String original = names.get(index);
        names.set(index, newName);
        System.out.println("修改成功：" + original + " → " + newName);
    }

    private static void deleteName(Scanner sc, ArrayList<String> names) {
        System.out.print("請輸入要刪除的姓名: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            name = sc.nextLine().trim();
        }

        int index = findIndexIgnoreCase(names, name);
        if (index == -1) {
            System.out.println("刪除失敗：找不到姓名「" + name + "」。");
        } else {
            String removed = names.remove(index);
            System.out.println("刪除成功：" + removed);
        }
    }

    private static void listAll(ArrayList<String> names) {
        if (names.isEmpty()) {
            System.out.println("目前名單為空。");
            return;
        }
        System.out.println("===== 全部名單（共 " + names.size() + " 人）=====");
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }

    private static int findIndexIgnoreCase(ArrayList<String> names, String target) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
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