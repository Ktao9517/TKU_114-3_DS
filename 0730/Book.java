public class Book {
    private String id;
    private String title;
    private String category;
    private int borrowCount;

    public Book(String id, String title, String category, int borrowCount) {
        this.id = id.trim();
        this.title = title.trim();
        this.category = category.trim();
        this.borrowCount = Math.max(borrowCount, 0);
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public int getBorrowCount() { return borrowCount; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + category + " | 借閱:" + borrowCount;
    }
}
