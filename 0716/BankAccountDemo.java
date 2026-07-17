public class BankAccountDemo {
    public static void main(String[] args) {
        System.out.println("========== 銀行帳戶示範 ==========\n");

        BankAccount acc1 = new BankAccount("A001", "王小明", 5000);
        BankAccount acc2 = new BankAccount("A002", "陳小美", 3000);

        System.out.println("初始狀態:");
        System.out.println(acc1);
        System.out.println(acc2);

        System.out.println("\n--- 存款測試 ---");
        System.out.println("acc1 存款 2000: " + acc1.deposit(2000));
        System.out.println("acc1 目前餘額: " + acc1.getBalance());
        System.out.println("acc1 嘗試存款 -100: " + acc1.deposit(-100));
        System.out.println("acc1 目前餘額: " + acc1.getBalance());

        System.out.println("\n--- 提款測試 ---");
        System.out.println("acc2 提款 1000: " + acc2.withdraw(1000));
        System.out.println("acc2 目前餘額: " + acc2.getBalance());
        System.out.println("acc2 嘗試提款 5000 (超過餘額): " + acc2.withdraw(5000));
        System.out.println("acc2 目前餘額: " + acc2.getBalance());
        System.out.println("acc2 嘗試提款 0: " + acc2.withdraw(0));

        System.out.println("\n--- 成功轉帳測試 ---");
        System.out.println("轉帳前:");
        System.out.println("  " + acc1);
        System.out.println("  " + acc2);
        boolean success = acc1.transferTo(acc2, 1500);
        System.out.println("acc1 轉帳 1500 給 acc2: " + success);
        System.out.println("轉帳後:");
        System.out.println("  " + acc1);
        System.out.println("  " + acc2);

        System.out.println("\n--- 失敗轉帳測試（餘額不足）---");
        System.out.println("轉帳前:");
        System.out.println("  " + acc1);
        System.out.println("  " + acc2);
        boolean fail1 = acc2.transferTo(acc1, 10000);
        System.out.println("acc2 轉帳 10000 給 acc1: " + fail1);
        System.out.println("轉帳後（餘額應不變）:");
        System.out.println("  " + acc1);
        System.out.println("  " + acc2);

        System.out.println("\n--- 失敗轉帳測試（金額非法）---");
        boolean fail2 = acc1.transferTo(acc2, -500);
        System.out.println("acc1 轉帳 -500 給 acc2: " + fail2);
        System.out.println("餘額應不變: " + acc1.getBalance() + " / " + acc2.getBalance());

        System.out.println("\n========== 最終狀態 ==========");
        System.out.println(acc1);
        System.out.println(acc2);
    }
}