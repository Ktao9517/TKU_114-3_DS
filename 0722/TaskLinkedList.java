public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getIncompleteCount() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted()) {
                count++;
            }
            current = current.getNext();
        }
        return count;
    }

    
    public boolean addUrgent(String code, String description) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("新增失敗：代碼不可空白");
            return false;
        }
        if (findByCode(code) != null) {
            System.out.println("新增失敗：代碼已存在 → " + code);
            return false;
        }

        TaskNode newNode = new TaskNode(code, description);
        newNode.setNext(head);
        head = newNode;
        size++;
        System.out.println("緊急工作已加到前端: " + newNode);
        return true;
    }

   
    public boolean addNormal(String code, String description) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("新增失敗：代碼不可空白");
            return false;
        }
        if (findByCode(code) != null) {
            System.out.println("新增失敗：代碼已存在 → " + code);
            return false;
        }

        TaskNode newNode = new TaskNode(code, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        System.out.println("一般工作已加到尾端: " + newNode);
        return true;
    }

    public TaskNode findByCode(String code) {
        TaskNode current = head;
        while (current != null) {
            if (current.getCode().equalsIgnoreCase(code)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

   
    public boolean completeTask(String code) {
        TaskNode task = findByCode(code);
        if (task == null) {
            System.out.println("找不到工作代碼: " + code);
            return false;
        }
        if (task.isCompleted()) {
            System.out.println("此工作已完成: " + code);
            return false;
        }
        task.setCompleted(true);
        System.out.println("工作已標記完成: " + task);
        return true;
    }

   
    public boolean removeTask(String code) {
        if (head == null) {
            System.out.println("刪除失敗：工作清單為空");
            return false;
        }

        if (head.getCode().equalsIgnoreCase(code)) {
            System.out.println("成功刪除: " + head);
            head = head.getNext();
            size--;
            return true;
        }

        TaskNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getCode().equalsIgnoreCase(code)) {
                System.out.println("成功刪除: " + current.getNext());
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }

        System.out.println("刪除失敗：找不到代碼 " + code);
        return false;
    }

    public void listIncomplete() {
        System.out.println("===== 未完成工作 =====");
        boolean found = false;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted()) {
                System.out.println(current);
                found = true;
            }
            current = current.getNext();
        }
        if (!found) {
            System.out.println("(目前沒有未完成工作)");
        }
    }

    public void printAll() {
        if (head == null) {
            System.out.println("(工作清單為空)");
            return;
        }
        System.out.println("===== 全部工作（共 " + size + " 項）=====");
        TaskNode current = head;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }

    public void printStats() {
        System.out.println("工作總數: " + size);
        System.out.println("未完成數量: " + getIncompleteCount());
    }
}