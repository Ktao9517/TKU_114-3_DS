public class BuildLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);

        
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        

        Node head = n1;

        System.out.println("===== 鏈結串列內容 =====");
        printList(head);

        System.out.println("節點數: " + countNodes(head));
        System.out.println("總和: " + sumNodes(head));

        
        System.out.println("\n===== 空串列測試 =====");
        Node empty = null;
        printList(empty);
        System.out.println("空串列節點數: " + countNodes(empty));
        System.out.println("空串列總和: " + sumNodes(empty));
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

    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static int sumNodes(Node head) {
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }
}