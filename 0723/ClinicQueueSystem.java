import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ClinicQueueSystem {
    private Queue<Patient> queue = new LinkedList<>();
    private Set<Integer> usedNumbers = new HashSet<>();
    private int nextNumber = 1;
    private int totalServed = 0;

    public void register(String name, String department) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("掛號失敗：姓名不可空白");
            return;
        }
        if (department == null || department.trim().isEmpty()) {
            System.out.println("掛號失敗：科別不可空白");
            return;
        }

        int number = nextNumber++;
        while (usedNumbers.contains(number)) {
            number = nextNumber++;
        }
        usedNumbers.add(number);

        Patient p = new Patient(number, name, department);
        queue.offer(p);
        System.out.println("掛號成功: " + p + "（目前等待: " + queue.size() + " 人）");
    }

    public void callNext() {
        if (queue.isEmpty()) {
            System.out.println("目前沒有等待病患，無法叫號。");
            return;
        }
        Patient p = queue.poll();
        totalServed++;
        System.out.println("請 " + p + " 進入診間。");
    }

    public void peekNext() {
        if (queue.isEmpty()) {
            System.out.println("目前沒有下一位病患。");
            return;
        }
        System.out.println("下一位: " + queue.peek());
    }

    public void showWaitingList() {
        if (queue.isEmpty()) {
            System.out.println("目前沒有等待病患。");
            return;
        }
        System.out.println("===== 等待清單（共 " + queue.size() + " 人）=====");
        for (Patient p : queue) {
            System.out.println(p);
        }
    }

    public void showDepartmentStats() {
        Map<String, Integer> deptCount = new HashMap<>();
        for (Patient p : queue) {
            deptCount.put(p.getDepartment(),
                    deptCount.getOrDefault(p.getDepartment(), 0) + 1);
        }

        System.out.println("===== 各科別等待人數 =====");
        if (deptCount.isEmpty()) {
            System.out.println("目前沒有等待病患。");
        } else {
            for (Map.Entry<String, Integer> e : deptCount.entrySet()) {
                System.out.println(e.getKey() + ": " + e.getValue() + " 人");
            }
        }
        System.out.println("總服務人數: " + totalServed);
        System.out.println("目前總等待人數: " + queue.size());
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("===== 診所叫號系統 =====\n");

        clinic.register("王小明", "內科");
        clinic.register("陳小美", "外科");
        clinic.register("林大同", "內科");
        clinic.register("張三", "小兒科");

        clinic.showWaitingList();
        clinic.peekNext();
        clinic.showDepartmentStats();

        System.out.println();
        clinic.callNext();
        clinic.callNext();
        clinic.showDepartmentStats();

        clinic.callNext();
        clinic.callNext();
        clinic.callNext(); 

        clinic.register("李四", "皮膚科");
        clinic.showWaitingList();
        clinic.showDepartmentStats();
    }
}