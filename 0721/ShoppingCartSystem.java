import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();

        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-5): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    addItem(sc, cart);
                    break;
                case 2:
                    updateQuantity(sc, cart);
                    break;
                case 3:
                    removeItem(sc, cart);
                    break;
                case 4:
                    showCart(cart);
                    break;
                case 5:
                    System.out.println("程式結束。感謝使用購物車系統！");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇，請輸入 1-5。");
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("========== 購物車系統 ==========");
        System.out.println("1. 加入商品");
        System.out.println("2. 修改數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 顯示購物車與總額");
        System.out.println("5. 結束");
        System.out.println("================================");
    }

    private static void addItem(Scanner sc, ArrayList<CartItem> cart) {
        System.out.print("請輸入商品代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        if (code.isEmpty()) {
            System.out.println("加入失敗：代碼不可為空白。");
            return;
        }

        CartItem existing = findByCode(cart, code);
        if (existing != null) {
            System.out.print("此商品已在購物車中，請輸入要增加的數量: ");
            int qty = readPositiveInt(sc);
            if (qty <= 0) {
                System.out.println("數量必須大於 0。");
                return;
            }
            existing.addQuantity(qty);
            System.out.println("已增加數量：" + existing.getName() + "，目前數量: " + existing.getQuantity());
            return;
        }

        System.out.print("請輸入商品名稱: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("加入失敗：名稱不可為空白。");
            return;
        }

        System.out.print("請輸入單價: ");
        int price = readNonNegativeInt(sc);

        System.out.print("請輸入數量: ");
        int quantity = readPositiveInt(sc);
        if (quantity <= 0) {
            System.out.println("數量必須大於 0。");
            return;
        }

        cart.add(new CartItem(code, name, price, quantity));
        System.out.println("加入成功：" + name + " x " + quantity);
    }

    private static void updateQuantity(Scanner sc, ArrayList<CartItem> cart) {
        System.out.print("請輸入要修改的商品代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        CartItem item = findByCode(cart, code);
        if (item == null) {
            System.out.println("修改失敗：找不到該商品。");
            return;
        }

        System.out.print("請輸入新數量（必須 > 0）: ");
        int newQty = readPositiveInt(sc);
        if (newQty <= 0) {
            System.out.println("修改失敗：數量必須大於 0。");
            return;
        }

        item.setQuantity(newQty);
        System.out.println("數量修改成功：" + item.getName() + " → " + newQty);
    }

    private static void removeItem(Scanner sc, ArrayList<CartItem> cart) {
        System.out.print("請輸入要移除的商品代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        CartItem item = findByCode(cart, code);
        if (item == null) {
            System.out.println("移除失敗：找不到該商品。");
            return;
        }

        cart.remove(item);
        System.out.println("移除成功：" + item.getName());
    }

    private static void showCart(ArrayList<CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("購物車目前是空的。");
            return;
        }

        System.out.println("===== 購物車內容 =====");
        int total = 0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            System.out.println((i + 1) + ". " + item);
            total += item.getSubtotal();
        }
        System.out.println("----------------------");
        System.out.println("總額: " + total + " 元");
    }

    private static CartItem findByCode(ArrayList<CartItem> cart, String code) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
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

    private static int readPositiveInt(Scanner sc) {
        int val = readInt(sc);
        return val;
    }

    private static int readNonNegativeInt(Scanner sc) {
        int val = readInt(sc);
        return Math.max(val, 0);
    }
}