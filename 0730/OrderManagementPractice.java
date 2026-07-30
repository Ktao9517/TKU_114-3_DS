import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class OrderManagementPractice {
    private ArrayList<Order> allOrders = new ArrayList<>();
    private Deque<Order> waiting = new ArrayDeque<>();
    private Deque<Order> completed = new ArrayDeque<>();

    public boolean addOrder(Order order) {
        if (order == null || order.getId().isEmpty()) return false;
        for (Order o : allOrders) {
            if (o.getId().equalsIgnoreCase(order.getId())) {
                System.out.println("重複編號，無法新增: " + order.getId());
                return false;
            }
        }
        allOrders.add(order);
        waiting.offer(order);
        System.out.println("新增訂單成功: " + order);
        return true;
    }

    public Order processNext() {
        if (waiting.isEmpty()) {
            System.out.println("目前沒有待處理訂單");
            return null;
        }
        Order o = waiting.poll();
        o.setProcessed(true);
        completed.push(o);
        System.out.println("處理完成: " + o);
        return o;
    }

    public Order peekNext() {
        if (waiting.isEmpty()) {
            System.out.println("沒有下一筆待處理訂單");
            return null;
        }
        return waiting.peek();
    }

    public void showStatus() {
        System.out.println("等待中: " + waiting.size() + "，已完成: " + completed.size());
        System.out.println("全部訂單數: " + allOrders.size());
    }

    public static void main(String[] args) {
        OrderManagementPractice system = new OrderManagementPractice();

        system.addOrder(new Order("O001", "Amy", 1500));
        system.addOrder(new Order("O002", "Ben", 3200));
        system.addOrder(new Order("O003", "Amy", 800));
        system.addOrder(new Order("O001", "Dup", 100)); // 重複測試

        System.out.println("下一筆: " + system.peekNext());
        system.processNext();
        system.processNext();
        system.showStatus();

        Order[] arr = system.allOrders.toArray(new Order[0]);
        OrderAlgorithms.mergeSortByAmountDesc(arr);
        System.out.println("\n依金額降冪:");
        for (Order o : arr) System.out.println(o);

        System.out.println("\nAmy 的訂單: " + OrderAlgorithms.findByCustomer(arr, "Amy"));
    }
}
