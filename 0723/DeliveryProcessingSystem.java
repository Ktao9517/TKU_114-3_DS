import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeliveryProcessingSystem {
    private Queue<DeliveryTask> waitingQueue = new LinkedList<>();
    private Stack<DeliveryTask> completedStack = new Stack<>();

    public void addTask(String id, String address, String item) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("新增失敗：工作代碼不可空白");
            return;
        }
        
        for (DeliveryTask t : waitingQueue) {
            if (t.getId().equalsIgnoreCase(id)) {
                System.out.println("新增失敗：代碼已存在 → " + id);
                return;
            }
        }
        for (DeliveryTask t : completedStack) {
            if (t.getId().equalsIgnoreCase(id)) {
                System.out.println("新增失敗：代碼已存在於完成紀錄 → " + id);
                return;
            }
        }

        DeliveryTask task = new DeliveryTask(id, address, item);
        waitingQueue.offer(task);
        System.out.println("新增待配送工作: " + task);
    }

    public void completeNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有待配送工作。");
            return;
        }
        DeliveryTask task = waitingQueue.poll();
        completedStack.push(task);
        System.out.println("完成配送: " + task);
    }

    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有下一個待配送工作。");
            return;
        }
        System.out.println("下一個工作: " + waitingQueue.peek());
    }

    
    public void undoLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("沒有可復原的完成紀錄。");
            return;
        }
        DeliveryTask task = completedStack.pop();
        waitingQueue.offer(task);
        System.out.println("已復原工作並放回等待佇列尾端: " + task);
    }

    public void showStatus() {
        System.out.println("===== 配送狀態 =====");
        System.out.println("等待數: " + waitingQueue.size());
        System.out.println("完成數: " + completedStack.size());

        System.out.println("\n--- 待配送佇列 ---");
        if (waitingQueue.isEmpty()) {
            System.out.println("(空)");
        } else {
            for (DeliveryTask t : waitingQueue) {
                System.out.println(t);
            }
        }

        System.out.println("\n--- 完成紀錄（最近完成在上方）---");
        if (completedStack.isEmpty()) {
            System.out.println("(空)");
        } else {
            
            Object[] arr = completedStack.toArray();
            for (int i = arr.length - 1; i >= 0; i--) {
                System.out.println(arr[i]);
            }
        }
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("===== 配送工作流程系統 =====\n");

        system.addTask("D001", "台北市中正區", "文件");
        system.addTask("D002", "新北市板橋區", "包裹");
        system.addTask("D003", "桃園市中壢區", "食品");
        system.peekNext();
        system.showStatus();

        System.out.println();
        system.completeNext();
        system.completeNext();
        system.showStatus();

        System.out.println("\n--- 復原最近完成 ---");
        system.undoLastCompleted();
        system.showStatus();

        System.out.println();
        system.completeNext();
        system.completeNext();
        system.completeNext(); 
        system.undoLastCompleted();
        system.undoLastCompleted();
        system.undoLastCompleted(); 
        system.showStatus();
    }
}