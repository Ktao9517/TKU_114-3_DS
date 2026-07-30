public class RepairAlgorithms {
    public static void mergeSortByPriorityDesc(RepairTask[] tasks) {
        if (tasks == null || tasks.length <= 1) return;
        RepairTask[] temp = new RepairTask[tasks.length];
        mergeSort(tasks, temp, 0, tasks.length - 1);
    }

    private static void mergeSort(RepairTask[] arr, RepairTask[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(RepairTask[] arr, RepairTask[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) temp[i] = arr[i];
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            
            if (temp[i].getPriority() >= temp[j].getPriority()) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }
}
