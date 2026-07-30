import java.util.ArrayList;

public class RegistrationAlgorithms {

    /** 依報名編號升冪 Merge Sort（穩定） */
    public static void mergeSortById(Registration[] regs) {
        if (regs == null || regs.length <= 1) return;
        Registration[] temp = new Registration[regs.length];
        mergeSort(regs, temp, 0, regs.length - 1);
    }

    private static void mergeSort(Registration[] arr, Registration[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(Registration[] arr, Registration[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i].getId().compareToIgnoreCase(temp[j].getId()) <= 0) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }

    /** Binary Search 依編號查詢（必須先排序） */
    public static Registration binarySearchById(Registration[] sorted, String id) {
        if (sorted == null || id == null || id.trim().isEmpty()) return null;
        int low = 0;
        int high = sorted.length - 1;
        String target = id.trim();
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = sorted[mid].getId().compareToIgnoreCase(target);
            if (cmp == 0) {
                return sorted[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    /** Sequential Search 依姓名找出全部符合者 */
    public static ArrayList<Registration> sequentialSearchByName(Registration[] regs, String name) {
        ArrayList<Registration> result = new ArrayList<>();
        if (regs == null || name == null) return result;
        String target = name.trim();
        for (Registration r : regs) {
            if (r.getName().equalsIgnoreCase(target)) {
                result.add(r);
            }
        }
        return result;
    }
}
