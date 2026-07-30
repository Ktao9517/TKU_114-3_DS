public class Contestant {
    private String id;
    private String name;
    private int score;
    private int finishSeconds;

    public Contestant(String id, String name, int score, int finishSeconds) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.finishSeconds = finishSeconds;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getScore() { return score; }
    public int getFinishSeconds() { return finishSeconds; }

    @Override
    public String toString() {
        return id + " | " + name + " | 分數:" + score + " | 秒數:" + finishSeconds;
    }
}
