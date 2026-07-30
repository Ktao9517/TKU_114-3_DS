import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class RepairSchedulingSystem {
    private ArrayList<RepairTask> allTasks = new ArrayList<>();
    private Deque<RepairTask> waiting = new ArrayDeque<>();
    private Deque<RepairTask> completed = new ArrayDeque<>();

    public boolean addTask(RepairTask task) {
        if (task == null || task.getId().isEmpty()) return false;
        for (RepairTask t : allTasks) {
            if (t.getId().equalsIgnoreCase(task.getId())) return false;
        }
        allTasks.add(task);
        waiting.offer(task);
        return true;
    }

    public RepairTask processNext() {
        if (waiting.isEmpty()) return null;
        RepairTask t = waiting.poll();
        t.setCompleted(true);
        completed.push(t);
        return t;
    }

    public RepairTask undoLast() {
        if (completed.isEmpty()) return null;
        RepairTask t = completed.pop();
        t.setCompleted(false);
        waiting.offerFirst(t);
        return t;
    }

    public static void main(String[] args) {
        RepairSchedulingSystem system = new RepairSchedulingSystem();
        system.addTask(new RepairTask("R01", "冷氣", 3));
        system.addTask(new RepairTask("R02", "影印機", 5));
        system.addTask(new RepairTask("R03", "網路", 3));
        system.addTask(new RepairTask("R04", "電腦", 4));

        System.out.println("===== 維修工作排程系統 =====");
        RepairTask[] arr = system.allTasks.toArray(new RepairTask[0]);
        RepairAlgorithms.mergeSortByPriorityDesc(arr);
        System.out.println("依優先等級降冪（穩定）:");
        for (RepairTask t : arr) System.out.println(t);

        System.out.println("\n處理兩筆:");
        System.out.println(system.processNext());
        System.out.println(system.processNext());
        System.out.println("等待: " + system.waiting.size() + "，完成: " + system.completed.size());

        System.out.println("復原一筆: " + system.undoLast());
        System.out.println("等待: " + system.waiting.size() + "，完成: " + system.completed.size());
    }
}
