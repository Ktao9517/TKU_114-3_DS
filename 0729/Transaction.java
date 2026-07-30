public class Transaction {
    private String id;
    private String account;
    private int amount;
    private int timeSeq;

    public Transaction(String id, String account, int amount, int timeSeq) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.timeSeq = timeSeq;
    }

    public String getId() { return id; }
    public String getAccount() { return account; }
    public int getAmount() { return amount; }
    public int getTimeSeq() { return timeSeq; }

    @Override
    public String toString() {
        return id + " | " + account + " | 金額:" + amount + " | 時間序:" + timeSeq;
    }
}
