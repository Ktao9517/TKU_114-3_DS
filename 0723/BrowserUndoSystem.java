import java.util.Stack;

public class BrowserUndoSystem {
    private Stack<String> history = new Stack<>();
    private String currentPage = "首頁 (Home)";

    public void openPage(String page) {
        history.push(currentPage);
        currentPage = page;
        System.out.println("開啟新頁面: " + page);
    }

    public void goBack() {
        if (history.isEmpty()) {
            System.out.println("沒有上一頁可以返回。");
            return;
        }
        currentPage = history.pop();
        System.out.println("返回上一頁: " + currentPage);
    }

    public void showCurrent() {
        System.out.println("目前頁面: " + currentPage);
    }

    public void showHistorySize() {
        System.out.println("歷史紀錄數量: " + history.size());
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("===== 瀏覽操作復原系統（至少 8 次操作）=====\n");

        
        browser.showCurrent();
        
        browser.openPage("Google 搜尋");
        
        browser.openPage("YouTube");
    
        browser.openPage("維基百科");
       
        browser.showCurrent();
       
        browser.goBack();
        
        browser.goBack();
        
        browser.showCurrent();
        
        System.out.println("\n--- 額外測試 ---");
        browser.goBack();
        browser.goBack(); 
        browser.openPage("GitHub");
        browser.showCurrent();
        browser.showHistorySize();
    }
}