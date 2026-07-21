public class LinkedListReverse {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        
        System.out.println("===== 多節點反轉 =====");
        Node head1 = buildList(new int[]{10, 20, 30, 40, 50});
        System.out.print("反轉前: ");
        printList(head1);
        head1 = reverse(head1);
        System.out.print("反轉後: ");
        printList(head1);

        
        System.out.println("\n===== 單一節點反轉 =====");
        Node head2 = new Node(100);
        System.out.print("反轉前: ");
        printList(head2);
        head2 = reverse(head2);
        System.out.print("反轉後: ");
        printList(head2);

       
        System.out.println("\n===== 空串列反轉 =====");
        Node head3 = null;
        System.out.print("反轉前: ");
        printList(head3);
        head3 = reverse(head3);
        System.out.print("反轉後: ");
        printList(head3);

        
        System.out.println("\n===== 再次多節點 =====");
        Node head4 = buildList(new int[]{1, 2, 3});
        System.out.print("反轉前: ");
        printList(head4);
        head4 = reverse(head4);
        System.out.print("反轉後: ");
        printList(head4);
    }

    
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;   
            current.next = prev;   
            prev = current;        
            current = next;
        }

        return prev; 
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