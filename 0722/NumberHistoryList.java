public class NumberHistoryList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public NumberHistoryList() {
        head = null;
        size = 0;
    }

    public void addFront(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("前端新增: " + value);
    }

    public void addBack(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("尾端新增: " + value);
    }

    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean remove(int value) {
        if (head == null) {
            System.out.println("串列為空，無法刪除 " + value);
            return false;
        }

        if (head.data == value) {
            head = head.next;
            size--;
            System.out.println("成功刪除: " + value);
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                size--;
                System.out.println("成功刪除: " + value);
                return true;
            }
            current = current.next;
        }

        System.out.println("找不到: " + value + "，無法刪除");
        return false;
    }

    public void printList() {
        if (head == null) {
            System.out.println("(空串列)");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public int getSum() {
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }

    public int getMax() {
        if (head == null) {
            return Integer.MIN_VALUE; 
        }
        int max = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data > max) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }

    public int getMin() {
        if (head == null) {
            return Integer.MAX_VALUE; 
        }
        int min = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }

    public void printStats() {
        System.out.println("size: " + getSize());
        if (size == 0) {
            System.out.println("總和: 0 (空串列)");
            System.out.println("最大值: 無 (空串列)");
            System.out.println("最小值: 無 (空串列)");
        } else {
            System.out.println("總和: " + getSum());
            System.out.println("最大值: " + getMax());
            System.out.println("最小值: " + getMin());
        }
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("===== 至少 8 次操作測試 =====\n");

        
        System.out.println("--- 操作1: 空串列統計 ---");
        list.printStats();
        list.printList();

        
        System.out.println("\n--- 操作2: 尾端新增 10 ---");
        list.addBack(10);
        list.printList();

       
        System.out.println("\n--- 操作3: 尾端新增 20 ---");
        list.addBack(20);
        list.printList();

        
        System.out.println("\n--- 操作4: 前端新增 5 ---");
        list.addFront(5);
        list.printList();

        
        System.out.println("\n--- 操作5: 尾端新增 30 ---");
        list.addBack(30);
        list.printList();

        
        System.out.println("\n--- 操作6: 搜尋 20 ---");
        System.out.println("contains(20): " + list.contains(20));
        System.out.println("contains(99): " + list.contains(99));

        
        System.out.println("\n--- 操作7: 刪除 20 ---");
        list.remove(20);
        list.printList();

        
        System.out.println("\n--- 操作8: 刪除 5 ---");
        list.remove(5);
        list.printList();

       
        System.out.println("\n--- 額外: 刪除不存在 ---");
        list.remove(99);
        list.printList();

        System.out.println("\n--- 最終統計 ---");
        list.printStats();
        list.printList();
    }
}