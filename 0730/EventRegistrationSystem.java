import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class EventRegistrationSystem {
    private ArrayList<Registration> allRegistrations = new ArrayList<>(); // 主資料
    private Deque<Registration> waitlist = new ArrayDeque<>();            // 候補 Queue
    private Deque<Registration> cancelHistory = new ArrayDeque<>();       // 最近取消 Stack（支援復原）
    private int capacity;                                                 // 正式名額上限
    private int formalCount = 0;                                          // 目前正式人數

    public EventRegistrationSystem(int capacity) {
        this.capacity = Math.max(capacity, 1);
    }

    /** 新增報名（正式或候補） */
    public boolean register(String id, String name, String phone) {
        if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            System.out.println("編號或姓名不可空白");
            return false;
        }
        // 檢查重複編號
        for (Registration r : allRegistrations) {
            if (r.getId().equalsIgnoreCase(id.trim()) && !r.isCancelled()) {
                System.out.println("重複報名編號，無法新增: " + id);
                return false;
            }
        }

        Registration reg = new Registration(id, name, phone);
        allRegistrations.add(reg);

        if (formalCount < capacity) {
            formalCount++;
            System.out.println("正式報名成功: " + reg);
        } else {
            reg.setWaitlist(true);
            waitlist.offer(reg);
            System.out.println("名額已滿，加入候補: " + reg);
        }
        return true;
    }

    /** 取消報名 */
    public boolean cancel(String id) {
        Registration target = null;
        for (Registration r : allRegistrations) {
            if (r.getId().equalsIgnoreCase(id) && !r.isCancelled()) {
                target = r;
                break;
            }
        }
        if (target == null) {
            System.out.println("找不到可取消的編號: " + id);
            return false;
        }

        target.setCancelled(true);
        cancelHistory.push(target); // 記錄以便復原

        if (target.isWaitlist()) {
            waitlist.remove(target);
            System.out.println("已取消候補: " + target);
        } else {
            formalCount--;
            System.out.println("已取消正式報名: " + target);
            // 從候補遞補
            if (!waitlist.isEmpty()) {
                Registration next = waitlist.poll();
                next.setWaitlist(false);
                formalCount++;
                System.out.println("候補遞補為正式: " + next);
            }
        }
        return true;
    }

    /** 復原最近一次取消 */
    public boolean undoCancel() {
        if (cancelHistory.isEmpty()) {
            System.out.println("沒有可復原的取消紀錄");
            return false;
        }
        Registration last = cancelHistory.pop();
        last.setCancelled(false);

        if (formalCount < capacity) {
            last.setWaitlist(false);
            formalCount++;
            System.out.println("復原為正式報名: " + last);
        } else {
            last.setWaitlist(true);
            waitlist.offer(last);
            System.out.println("復原為候補: " + last);
        }
        return true;
    }

    public void showStatus() {
        System.out.println("\n===== 目前狀態 =====");
        System.out.println("正式名額: " + formalCount + " / " + capacity);
        System.out.println("候補人數: " + waitlist.size());
        System.out.println("全部報名紀錄: " + allRegistrations.size());
        System.out.println("取消歷史可復原筆數: " + cancelHistory.size());
    }

    public void showAll() {
        System.out.println("\n全部報名資料:");
        for (Registration r : allRegistrations) {
            System.out.println(r);
        }
    }

    public void showSortedById() {
        Registration[] arr = allRegistrations.toArray(new Registration[0]);
        RegistrationAlgorithms.mergeSortById(arr);
        System.out.println("\n依報名編號排序:");
        for (Registration r : arr) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
        EventRegistrationSystem system = new EventRegistrationSystem(3); // 正式名額 3 人

        System.out.println("===== 活動報名與候補系統 =====");

        // 測試案例
        system.register("R001", "王小明", "0911111111");
        system.register("R002", "陳小美", "0922222222");
        system.register("R003", "林大同", "0933333333");
        system.register("R004", "張三", "0944444444");   // 候補
        system.register("R005", "李四", "0955555555");   // 候補
        system.register("R001", "重複", "0900000000");   // 重複測試

        system.showStatus();
        system.showAll();

        System.out.println("\n--- 取消正式 R002 ---");
        system.cancel("R002");
        system.showStatus();

        System.out.println("\n--- 取消不存在 ---");
        system.cancel("R999");

        System.out.println("\n--- 空候補 Queue 測試（先清候補）---");
        while (!system.waitlist.isEmpty()) {
            system.waitlist.poll();
        }
        system.showStatus();

        System.out.println("\n--- 復原最近取消 ---");
        system.undoCancel();
        system.showStatus();

        // 搜尋與排序
        system.showSortedById();

        Registration[] arr = system.allRegistrations.toArray(new Registration[0]);
        RegistrationAlgorithms.mergeSortById(arr);
        System.out.println("\nBinary Search R003: " + RegistrationAlgorithms.binarySearchById(arr, "R003"));
        System.out.println("Binary Search 不存在: " + RegistrationAlgorithms.binarySearchById(arr, "R999"));
        System.out.println("依姓名 Sequential 搜尋「張三」: " +
                RegistrationAlgorithms.sequentialSearchByName(arr, "張三"));
    }
}
