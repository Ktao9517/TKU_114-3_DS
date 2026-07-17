public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private int balance;

    public BankAccount(String accountNumber, String ownerName, int initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = Math.max(initialBalance, 0);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public boolean deposit(int amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean transferTo(BankAccount target, int amount) {
        if (target == null || amount <= 0 || amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        target.balance += amount;
        return true;
    }

    @Override
    public String toString() {
        return String.format("BankAccount[帳號=%s, 戶名=%s, 餘額=%d]", accountNumber, ownerName, balance);
    }
}