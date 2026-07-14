import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = readNonEmptyText(sc);
        System.out.println("原始字元數: " + text.length());

        String trimmed = text.trim();
        System.out.println("有效字元數 (trim後): " + trimmed.length());

        String[] words = splitWords(trimmed);
        System.out.println("單字數量: " + words.length);

        int vowelCount = countVowels(trimmed);
        System.out.println("母音 a/e/i/o/u 總數: " + vowelCount);

        String longest = findLongestWord(words);
        System.out.println("最長單字: " + longest);

        System.out.print("請輸入關鍵字: ");
        String keyword = sc.nextLine().trim();
        int keywordCount = countKeywordOccurrences(words, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數 (忽略大小寫): " + keywordCount);

        sc.close();
    }

    public static String readNonEmptyText(Scanner sc) {
        while (true) {
            System.out.print("請輸入一行文字: ");
            String input = sc.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            }
            System.out.println("輸入不可為空或全空白，請重新輸入。");
        }
    }

    public static String[] splitWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }
        
        return text.trim().split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }
        return longest;
    }

    public static int countKeywordOccurrences(String[] words, String keyword) {
        if (keyword == null || keyword.isEmpty() || words == null) {
            return 0;
        }
        int count = 0;
        String keyLower = keyword.toLowerCase();
        for (String word : words) {
            if (word.toLowerCase().equals(keyLower)) {
                count++;
            }
        }
        return count;
    }
}