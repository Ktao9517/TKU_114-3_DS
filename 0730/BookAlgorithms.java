import java.util.ArrayList;

public class BookAlgorithms {

    public static void mergeSortById(Book[] books) {
        if (books == null || books.length <= 1) return;
        Book[] temp = new Book[books.length];
        mergeSortId(books, temp, 0, books.length - 1);
    }

    private static void mergeSortId(Book[] arr, Book[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortId(arr, temp, left, mid);
        mergeSortId(arr, temp, mid + 1, right);
        mergeId(arr, temp, left, mid, right);
    }

    private static void mergeId(Book[] arr, Book[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) temp[i] = arr[i];
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i].getId().compareToIgnoreCase(temp[j].getId()) <= 0)
                arr[k++] = temp[i++];
            else
                arr[k++] = temp[j++];
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }

    public static void mergeSortByBorrowDesc(Book[] books) {
        if (books == null || books.length <= 1) return;
        Book[] temp = new Book[books.length];
        mergeSortBorrow(books, temp, 0, books.length - 1);
    }

    private static void mergeSortBorrow(Book[] arr, Book[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortBorrow(arr, temp, left, mid);
        mergeSortBorrow(arr, temp, mid + 1, right);
        mergeBorrow(arr, temp, left, mid, right);
    }

    private static void mergeBorrow(Book[] arr, Book[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) temp[i] = arr[i];
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i].getBorrowCount() >= temp[j].getBorrowCount())
                arr[k++] = temp[i++];
            else
                arr[k++] = temp[j++];
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }

    public static Book binarySearchById(Book[] sorted, String id) {
        if (sorted == null || id == null) return null;
        int low = 0, high = sorted.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = sorted[mid].getId().compareToIgnoreCase(id);
            if (cmp == 0) return sorted[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    public static ArrayList<Book> findByCategory(Book[] books, String category) {
        ArrayList<Book> result = new ArrayList<>();
        if (category == null) return result;
        for (Book b : books) {
            if (b.getCategory().equalsIgnoreCase(category)) {
                result.add(b);
            }
        }
        return result;
    }
}
