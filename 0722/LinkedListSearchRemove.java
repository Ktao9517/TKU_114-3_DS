public class LinkedListSearchRemove {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        
        Node head = buildList(new int[]{10, 20, 30, 40, 50});

        System.out.println("===== 初始串列 =====");
        printList(head);

        
        System.out.println("\ncontains(30): " + contains(head, 30));
        System.out.println("contains(99): " + contains(head, 99));
        System.out.println("contains(10): " + contains(head, 10));

        
        System.out.println("\n===== 刪除 head (10) =====");
        head = removeValue(head, 10);
        printList(head);

        
        System.out.println("\n===== 刪除中間 (30) =====");
        head = removeValue(head, 30);
        printList(head);

        
        System.out.println("\n===== 刪除最後 (50) =====");
        head = removeValue(head, 50);
        printList(head);

        
        System.out.println("\n===== 刪除找不到的值 (99) =====");
        head = removeValue(head, 99);
        printList(head);

        
        System.out.println("\n===== 空串列刪除 =====");
        Node empty = null;
        empty = removeValue(empty, 10);
        printList(empty);
        System.out.println("空串列 contains(10): " + contains(empty, 10));
    }

    public static boolean contains(Node head, int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static Node removeValue(Node head, int value) {
        if (head == null) {
            System.out.println("串列為空，無法刪除。");
            return null;
        }

        
        if (head.data == value) {
            System.out.println("成功刪除 head: " + value);
            return head.next;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == value) {
                System.out.println("成功刪除: " + value);
                current.next = current.next.next;
                return head;
            }
            current = current.next;
        }

        System.out.println("找不到值: " + value + "，無法刪除。");
        return head;
    }

    public static void printList(Node head) {
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

    private static Node buildList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }
}