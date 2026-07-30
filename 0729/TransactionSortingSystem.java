public class TransactionSortingSystem {
    public static void main(String[] args) {
        Transaction[] list = {
            new Transaction("T01", "A1001", 5000, 3),
            new Transaction("T02", "A1002", 12000, 1),
            new Transaction("T03", "A1001", 5000, 2),
            new Transaction("T04", "A1003", 8000, 4),
            new Transaction("T05", "A1002", 12000, 5),
            new Transaction("T06", "A1004", 3000, 6)
        };

        System.out.println("===== 交易紀錄排序 =====");
        System.out.println("排序前:");
        for (Transaction t : list) System.out.println(t);

        insertionSort(list);

        System.out.println("\n排序後（金額降冪，相同金額則時間序升冪）:");
        for (Transaction t : list) System.out.println(t);
    }

    public static void insertionSort(Transaction[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Transaction key = arr[i];
            int j = i - 1;
            while (j >= 0 && compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static int compare(Transaction a, Transaction b) {
        if (a.getAmount() != b.getAmount()) {
            return b.getAmount() - a.getAmount();
        }
        return a.getTimeSeq() - b.getTimeSeq();
    }
}
