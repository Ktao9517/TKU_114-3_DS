import java.util.Stack;

public class TextEditorUndoSystem {
    private StringBuilder content = new StringBuilder();
    private Stack<String> history = new Stack<>();

    private void saveState() {
        history.push(content.toString());
    }

    public void appendText(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("新增失敗：文字不可空白");
            return;
        }
        saveState();
        content.append(text);
        System.out.println("新增文字: \"" + text + "\"");
        showContent();
    }

    public void deleteLastChars(int count) {
        if (count <= 0) {
            System.out.println("刪除數量必須大於 0");
            return;
        }
        if (content.length() == 0) {
            System.out.println("目前沒有內容可刪除");
            return;
        }
        saveState();
        int removeCount = Math.min(count, content.length());
        content.delete(content.length() - removeCount, content.length());
        System.out.println("刪除最後 " + removeCount + " 個字元");
        showContent();
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("沒有歷史紀錄，無法 Undo");
            return;
        }
        content = new StringBuilder(history.pop());
        System.out.println("Undo 成功");
        showContent();
    }

    public void showContent() {
        System.out.println("目前內容: \"" + content.toString() + "\"");
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("===== 文字編輯 Undo 系統 =====\n");

        editor.appendText("Hello");
        editor.appendText(" World");
        editor.appendText("!");
        editor.showContent();

        System.out.println("\n--- 刪除最後 1 個字元 ---");
        editor.deleteLastChars(1);

        System.out.println("\n--- 連續 Undo 三次 ---");
        editor.undo(); 
        editor.undo(); 
        editor.undo(); 

        System.out.println("\n--- 再 Undo 一次（應該沒有歷史）---");
        editor.undo();

        System.out.println("\n--- 繼續編輯 ---");
        editor.appendText(" Java");
        editor.deleteLastChars(5);
        editor.undo();
        editor.showContent();
    }
}