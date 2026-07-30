import java.util.ArrayList;

public class LibraryManagementSystem {
    private ArrayList<Book> books = new ArrayList<>();

    public boolean addBook(Book book) {
        if (book == null || book.getId().isEmpty()) return false;
        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(book.getId())) {
                System.out.println("重複編號: " + book.getId());
                return false;
            }
        }
        books.add(book);
        return true;
    }

    public static void main(String[] args) {
        LibraryManagementSystem lib = new LibraryManagementSystem();
        lib.addBook(new Book("B003", "資料結構", "資訊", 15));
        lib.addBook(new Book("B001", "演算法", "資訊", 22));
        lib.addBook(new Book("B005", "文學史", "文學", 8));
        lib.addBook(new Book("B002", "作業系統", "資訊", 12));
        lib.addBook(new Book("B004", "現代詩", "文學", 5));

        Book[] arr = lib.books.toArray(new Book[0]);

        System.out.println("===== 圖書借閱資料管理系統 =====");
        System.out.println("\n依編號升冪:");
        BookAlgorithms.mergeSortById(arr);
        for (Book b : arr) System.out.println(b);

        System.out.println("\n依借閱次數降冪:");
        Book[] arr2 = lib.books.toArray(new Book[0]);
        BookAlgorithms.mergeSortByBorrowDesc(arr2);
        for (Book b : arr2) System.out.println(b);

        System.out.println("\nBinary Search B002: " + BookAlgorithms.binarySearchById(arr, "B002"));
        System.out.println("分類「資訊」: " + BookAlgorithms.findByCategory(arr, "資訊"));
        System.out.println("空搜尋: " + BookAlgorithms.binarySearchById(arr, "B999"));
    }
}
