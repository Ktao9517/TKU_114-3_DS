import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        while (true) {
            displayMenu();
            System.out.print("請選擇功能 (1-7): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    addCourse(sc, courses);
                    break;
                case 2:
                    enrollStudent(sc, courses);
                    break;
                case 3:
                    dropStudent(sc, courses);
                    break;
                case 4:
                    deleteCourse(sc, courses);
                    break;
                case 5:
                    searchCourse(sc, courses);
                    break;
                case 6:
                    showSummary(courses);
                    break;
                case 7:
                    System.out.println("程式結束。感謝使用選課管理系統！");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇，請輸入 1-7。");
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("========== 選課管理系統 ==========");
        System.out.println("1. 新增課程");
        System.out.println("2. 選課");
        System.out.println("3. 退選");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程");
        System.out.println("6. 顯示統計摘要");
        System.out.println("7. 結束");
        System.out.println("==================================");
    }

    private static void addCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入課程代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        if (code.isEmpty()) {
            System.out.println("新增失敗：代碼不可為空白。");
            return;
        }

        if (findByCode(courses, code) != null) {
            System.out.println("新增失敗：代碼已存在。");
            return;
        }

        System.out.print("請輸入課程名稱: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("新增失敗：名稱不可為空白。");
            return;
        }

        System.out.print("請輸入容量: ");
        int capacity = readPositiveInt(sc);
        if (capacity <= 0) {
            System.out.println("容量必須大於 0。");
            return;
        }

        courses.add(new Course(code, name, capacity));
        System.out.println("新增成功：" + name + "（代碼：" + code + "，容量：" + capacity + "）");
    }

    private static void enrollStudent(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入要選課的課程代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        Course course = findByCode(courses, code);
        if (course == null) {
            System.out.println("選課失敗：找不到該課程。");
            return;
        }

        if (course.isFull()) {
            System.out.println("選課失敗：課程已額滿（" + course.getEnrolled() + "/" + course.getCapacity() + "）。");
            return;
        }

        course.enroll();
        System.out.println("選課成功：" + course.getName() + "，目前人數: " + course.getEnrolled());
    }

    private static void dropStudent(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入要退選的課程代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        Course course = findByCode(courses, code);
        if (course == null) {
            System.out.println("退選失敗：找不到該課程。");
            return;
        }

        if (!course.drop()) {
            System.out.println("退選失敗：目前無人選修此課程。");
            return;
        }

        System.out.println("退選成功：" + course.getName() + "，目前人數: " + course.getEnrolled());
    }

    private static void deleteCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入要刪除的課程代碼: ");
        String code = sc.nextLine().trim();
        if (code.isEmpty()) {
            code = sc.nextLine().trim();
        }

        Course course = findByCode(courses, code);
        if (course == null) {
            System.out.println("刪除失敗：找不到該課程。");
            return;
        }

        courses.remove(course);
        System.out.println("刪除成功：" + course.getName());
    }

    private static void searchCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入要搜尋的課程代碼或名稱: ");
        String keyword = sc.nextLine().trim();
        if (keyword.isEmpty()) {
            keyword = sc.nextLine().trim();
        }

        boolean found = false;
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                System.out.println("找到課程：" + c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到符合的課程。");
        }
    }

    private static void showSummary(ArrayList<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("目前沒有任何課程。");
            return;
        }

        int totalCourses = courses.size();
        int totalEnrollments = 0;
        ArrayList<Course> fullCourses = new ArrayList<>();

        for (Course c : courses) {
            totalEnrollments += c.getEnrolled();
            if (c.isFull()) {
                fullCourses.add(c);
            }
        }

        System.out.println("===== 統計摘要 =====");
        System.out.println("總課程數: " + totalCourses);
        System.out.println("總選課人次: " + totalEnrollments);
        System.out.println("額滿課程數: " + fullCourses.size());

        if (!fullCourses.isEmpty()) {
            System.out.println("額滿課程清單：");
            for (Course c : fullCourses) {
                System.out.println("  - " + c);
            }
        }
    }

    private static Course findByCode(ArrayList<Course> courses, String code) {
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
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
        return val > 0 ? val : 0;
    }
}