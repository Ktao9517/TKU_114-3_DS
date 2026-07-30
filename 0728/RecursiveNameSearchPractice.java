public class RecursiveNameSearchPractice {

    public static int search(String[] names, String target, int index) {
        
        if (names == null || index >= names.length) {
            return -1;
        }
        
        if (names[index] != null && names[index].equals(target)) {
            return index;
        }
        
        return search(names, target, index + 1);
    }

    public static void main(String[] args) {
        System.out.println("===== 遞迴版文字搜尋 =====");

        String[] names = {"Amy", "Ben", "Cara", "Dan", "Eve", "Frank"};
        String[] empty = {};
        
        System.out.println("空陣列搜尋 Amy: " + search(empty, "Amy", 0));

        System.out.println("搜尋 Amy: " + search(names, "Amy", 0));

        System.out.println("搜尋 Frank: " + search(names, "Frank", 0));
       
        System.out.println("搜尋 Cara: " + search(names, "Cara", 0));

        System.out.println("搜尋 Zoe: " + search(names, "Zoe", 0));
    }
}
