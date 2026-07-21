import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipment> equipments = new ArrayList<>();

        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-6): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    addEquipment(sc, equipments);
                    break;
                case 2:
                    searchByCode(sc, equipments);
                    break;
                case 3:
                    borrowEquipment(sc, equipments);
                    break;
                case 4:
                    returnEquipment(sc, equipments);
                    break;
                case 5:
                    listAvailable(equipments);
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
        System.out.println("========== 設備管理系統 ==========");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出可借設備");
        System.out.println("6. 結束");
        System.out.println("==================================");
    }

    private static void addEquipment(Scanner sc, ArrayList<Equipment> equipments) {
        System.out.print("請輸入設備代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        if (code.isEmpty()) {
            System.out.println("新增失敗：代碼不可為空白。");
            return;
        }

        if (findByCode(equipments, code) != null) {
            System.out.println("新增失敗：代碼已存在。");
            return;
        }

        System.out.print("請輸入設備名稱: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("新增失敗：名稱不可為空白。");
            return;
        }

        equipments.add(new Equipment(code, name));
        System.out.println("新增成功：" + code + " - " + name);
    }

    private static void searchByCode(Scanner sc, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要搜尋的代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        Equipment eq = findByCode(equipments, code);
        if (eq == null) {
            System.out.println("找不到代碼為 " + code + " 的設備。");
        } else {
            System.out.println("找到設備：" + eq);
        }
    }

    private static void borrowEquipment(Scanner sc, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要借出的設備代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        Equipment eq = findByCode(equipments, code);
        if (eq == null) {
            System.out.println("借出失敗：找不到該設備。");
            return;
        }

        if (!eq.isAvailable()) {
            System.out.println("借出失敗：設備目前已借出。");
            return;
        }

        eq.borrow();
        System.out.println("借出成功：" + eq.getName());
    }

    private static void returnEquipment(Scanner sc, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要歸還的設備代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        Equipment eq = findByCode(equipments, code);
        if (eq == null) {
            System.out.println("歸還失敗：找不到該設備。");
            return;
        }

        if (eq.isAvailable()) {
            System.out.println("歸還失敗：設備目前未被借出。");
            return;
        }

        eq.returnEquipment();
        System.out.println("歸還成功：" + eq.getName());
    }

    private static void listAvailable(ArrayList<Equipment> equipments) {
        System.out.println("===== 可借用設備清單 =====");
        boolean found = false;
        for (Equipment eq : equipments) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有可借用的設備。");
        }
    }

    private static Equipment findByCode(ArrayList<Equipment> equipments, String code) {
        for (Equipment eq : equipments) {
            if (eq.getCode().equalsIgnoreCase(code)) {
                return eq;
            }
        }
        return null;
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