public class Order {
    private String id;
    private String customer;
    private int amount;
    private boolean processed;

    public Order(String id, String customer, int amount) {
        this.id = id.trim();
        this.customer = customer.trim();
        this.amount = amount;
        this.processed = false;
    }

    public String getId() { return id; }
    public String getCustomer() { return customer; }
    public int getAmount() { return amount; }
    public boolean isProcessed() { return processed; }
    public void setProcessed(boolean processed) { this.processed = processed; }

    @Override
    public String toString() {
        return id + " | " + customer + " | 金額:" + amount + (processed ? " [已處理]" : " [待處理]");
    }
}
