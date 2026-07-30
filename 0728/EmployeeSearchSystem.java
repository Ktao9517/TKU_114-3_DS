import java.util.Scanner;

public class EmployeeSearchSystem {
    public static void main(String[] args) {
        // 依編號排序的員工陣列
        Employee[] employees = {
            new Employee("E001", "王小明", "資訊部", "101"),
            new Employee("E015", "陳小美", "人資部", "205"),
            new Employee("E028", "林大同", "財務部", "312"),
            new Employee("E042", "張三", "行銷部", "408"),
            new Employee("E055", "李四", "資訊部", "115"),
            new Employee("E067", "黃五", "業務部", "520")
        };

        System.out.println("===== 員工編號查詢系統 =====");
        System.out.println("目前員工（已依編號排序）:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\n請輸入員工編號: ");
        String targetId = sc.nextLine().trim();

        int index = binarySearchById(employees, targetId);
        if (index != -1) {
            System.out.println("找到員工：");
            System.out.println(employees[index]);
        } else {
            System.out.println("找不到編號「" + targetId + "」的員工。");
        }

        // 空陣列測試
        System.out.println("\n空陣列測試: " + binarySearchById(new Employee[0], "E001"));
        sc.close();
    }

    public static int binarySearchById(Employee[] employees, String targetId) {
        if (employees == null || employees.length == 0 || targetId == null) {
            return -1;
        }
        int low = 0;
        int high = employees.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = employees[mid].getId().compareToIgnoreCase(targetId);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
