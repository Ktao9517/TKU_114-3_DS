import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-6): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    addContact(sc, contacts);
                    break;
                case 2:
                    searchContact(sc, contacts);
                    break;
                case 3:
                    updatePhone(sc, contacts);
                    break;
                case 4:
                    deleteContact(sc, contacts);
                    break;
                case 5:
                    listAllContacts(contacts);
                    break;
                case 6:
                    System.out.println("程式結束。感謝使用聯絡人管理系統！");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇，請輸入 1-6。");
            }
            System.out.println();
        }
    }

    
    private static void displayMenu() {
        System.out.println("========== 聯絡人管理系統 ==========");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 完整清單");
        System.out.println("6. 結束");
        System.out.println("====================================");
    }

   
    private static void addContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.print("請輸入代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        if (code.isEmpty()) {
            System.out.println("新增失敗：代碼不可為空白。");
            return;
        }

        if (findByCode(contacts, code) != null) {
            System.out.println("新增失敗：代碼已存在。");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("新增失敗：姓名不可為空白。");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = sc.nextLine().trim();

        System.out.print("請輸入電子郵件: ");
        String email = sc.nextLine().trim();

        contacts.add(new Contact(code, name, phone, email));
        System.out.println("新增成功：" + name + "（代碼：" + code + "）");
    }

    
    private static void searchContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.print("請輸入要搜尋的代碼或姓名: ");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            keyword = sc.nextLine().trim();
        }

        ArrayList<Contact> results = findByKeyword(contacts, keyword);
        if (results.isEmpty()) {
            System.out.println("找不到符合的聯絡人。");
        } else {
            System.out.println("找到 " + results.size() + " 筆：");
            for (Contact c : results) {
                System.out.println("  " + c);
            }
        }
    }

    
    private static void updatePhone(Scanner sc, ArrayList<Contact> contacts) {
        System.out.print("請輸入聯絡人代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        Contact contact = findByCode(contacts, code);
        if (contact == null) {
            System.out.println("修改失敗：找不到該代碼。");
            return;
        }

        System.out.print("請輸入新電話: ");
        String newPhone = sc.nextLine().trim();
        contact.setPhone(newPhone);
        System.out.println("電話修改成功：" + contact.getName() + " → " + newPhone);
    }

    
    private static void deleteContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.print("請輸入要刪除的代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        Contact contact = findByCode(contacts, code);
        if (contact == null) {
            System.out.println("刪除失敗：找不到該代碼。");
            return;
        }

        contacts.remove(contact);
        System.out.println("刪除成功：" + contact.getName());
    }

    
    private static void listAllContacts(ArrayList<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("目前沒有任何聯絡人。");
            return;
        }
        System.out.println("===== 完整聯絡人清單（共 " + contacts.size() + " 人）=====");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }

    
    private static Contact findByCode(ArrayList<Contact> contacts, String code) {
        for (Contact c : contacts) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

   
    private static ArrayList<Contact> findByKeyword(ArrayList<Contact> contacts, String keyword) {
        ArrayList<Contact> results = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getCode().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                results.add(c);
            }
        }
        return results;
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