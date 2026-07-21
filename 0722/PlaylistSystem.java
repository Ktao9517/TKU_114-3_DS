import java.util.Scanner;

public class PlaylistSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        
        playlist.addLast("S001", "夜曲");
        playlist.addLast("S002", "青花瓷");
        playlist.addLast("S003", "稻香");
        playlist.addLast("S004", "告白氣球");

        while (true) {
            displayMenu();
            System.out.print("請選擇 (1-5): ");
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    System.out.print("歌曲代碼: ");
                    String code = sc.nextLine().trim();
                    if (code.isEmpty()) code = sc.nextLine().trim();
                    System.out.print("歌曲名稱: ");
                    String name = sc.nextLine().trim();
                    playlist.addLast(code, name);
                    break;
                case 2:
                    System.out.print("搜尋代碼: ");
                    String searchCode = sc.nextLine().trim();
                    if (searchCode.isEmpty()) searchCode = sc.nextLine().trim();
                    PlaylistNode found = playlist.findByCode(searchCode);
                    if (found != null) {
                        System.out.println("找到: " + found);
                    } else {
                        System.out.println("找不到代碼: " + searchCode);
                    }
                    break;
                case 3:
                    System.out.print("刪除代碼: ");
                    String delCode = sc.nextLine().trim();
                    if (delCode.isEmpty()) delCode = sc.nextLine().trim();
                    playlist.removeByCode(delCode);
                    break;
                case 4:
                    playlist.printPlaylist();
                    break;
                case 5:
                    System.out.println("結束播放清單系統。");
                    sc.close();
                    return;
                default:
                    System.out.println("無效選擇。");
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("========== 播放清單系統 ==========");
        System.out.println("1. 尾端新增歌曲");
        System.out.println("2. 依代碼搜尋");
        System.out.println("3. 刪除歌曲");
        System.out.println("4. 顯示完整播放順序");
        System.out.println("5. 結束");
        System.out.println("==================================");
    }

    private static int readInt(Scanner sc) {
        while (true) {
            if (sc.hasNextInt()) {
                int val = sc.nextInt();
                sc.nextLine();
                return val;
            } else {
                System.out.print("請輸入有效整數: ");
                sc.next();
            }
        }
    }
}