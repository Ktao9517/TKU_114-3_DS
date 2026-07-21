public class PlaylistNode {
    private String code;
    private String name;
    private PlaylistNode next;

    public PlaylistNode(String code, String name) {
        this.code = code.trim();
        this.name = name.trim();
        this.next = null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public PlaylistNode getNext() {
        return next;
    }

    public void setNext(PlaylistNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}