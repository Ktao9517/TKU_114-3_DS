import java.util.ArrayList;

public class OrderAlgorithms {

    public static void mergeSortByAmountDesc(Order[] orders) {
        if (orders == null || orders.length <= 1) return;
        Order[] temp = new Order[orders.length];
        mergeSort(orders, temp, 0, orders.length - 1);
    }

    private static void mergeSort(Order[] arr, Order[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(Order[] arr, Order[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) temp[i] = arr[i];
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i].getAmount() >= temp[j].getAmount()) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }

    public static ArrayList<Order> findByCustomer(Order[] orders, String name) {
        ArrayList<Order> result = new ArrayList<>();
        if (name == null) return result;
        for (Order o : orders) {
            if (o.getCustomer().equalsIgnoreCase(name)) {
                result.add(o);
            }
        }
        return result;
    }
}
