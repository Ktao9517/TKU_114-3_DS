public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public PlaylistLinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    
    public boolean addLast(String code, String name) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("新增失敗：代碼不可空白");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("新增失敗：歌曲名稱不可空白");
            return false;
        }
        if (findByCode(code) != null) {
            System.out.println("新增失敗：歌曲代碼已存在 → " + code);
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(code, name);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        System.out.println("尾端新增成功: " + newNode);
        return true;
    }

    public PlaylistNode findByCode(String code) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.getCode().equalsIgnoreCase(code)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    
    public boolean removeByCode(String code) {
        if (head == null) {
            System.out.println("刪除失敗：播放清單為空");
            return false;
        }

        
        if (head.getCode().equalsIgnoreCase(code)) {
            System.out.println("成功刪除第一首: " + head);
            head = head.getNext();
            size--;
            return true;
        }

        PlaylistNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getCode().equalsIgnoreCase(code)) {
                System.out.println("成功刪除: " + current.getNext());
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }

        System.out.println("刪除失敗：找不到代碼 " + code);
        return false;
    }

    public void printPlaylist() {
        if (head == null) {
            System.out.println("(播放清單為空)");
            return;
        }
        System.out.println("===== 播放順序（共 " + size + " 首）=====");
        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current);
            current = current.getNext();
            index++;
        }
    }
}