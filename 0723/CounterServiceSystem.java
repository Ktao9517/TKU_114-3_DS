import java.util.LinkedList;
import java.util.Queue;

public class CounterServiceSystem {

    static class Ticket {
        int number;
        String name;

        Ticket(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public String toString() {
            return "號碼 " + number + " - " + name;
        }
    }

    private Queue<Ticket> waitingQueue = new LinkedList<>();
    private Queue<Ticket> servedHistory = new LinkedList<>(); // 處理紀錄另外保存
    private int nextNumber = 1;

    public void takeNumber(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("取號失敗：姓名不可空白");
            return;
        }
        Ticket ticket = new Ticket(nextNumber++, name.trim());
        waitingQueue.offer(ticket);
        System.out.println("取號成功: " + ticket + "（目前等待人數: " + waitingQueue.size() + "）");
    }

    public void callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待的客人，無法叫號。");
            return;
        }
        Ticket ticket = waitingQueue.poll();
        servedHistory.offer(ticket);
        System.out.println("請 " + ticket + " 到櫃台。");
    }

    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有下一位客人。");
            return;
        }
        System.out.println("下一位: " + waitingQueue.peek());
    }

    public void showWaitingCount() {
        System.out.println("目前等待人數: " + waitingQueue.size());
    }

    public void showServedHistory() {
        if (servedHistory.isEmpty()) {
            System.out.println("尚無服務紀錄。");
            return;
        }
        System.out.println("===== 已服務紀錄 =====");
        for (Ticket t : servedHistory) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        CounterServiceSystem counter = new CounterServiceSystem();

        System.out.println("===== 櫃台叫號系統 =====\n");

        counter.takeNumber("王小明");
        counter.takeNumber("陳小美");
        counter.takeNumber("林大同");
        counter.showWaitingCount();
        counter.peekNext();

        counter.callNext();
        counter.callNext();
        counter.peekNext();
        counter.showWaitingCount();

        counter.callNext();
        counter.callNext(); 

        counter.takeNumber("張三");
        counter.showServedHistory();
        counter.showWaitingCount();
    }
}