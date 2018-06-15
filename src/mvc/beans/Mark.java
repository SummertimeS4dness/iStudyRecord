package mvc.beans;

public class Mark {
    private int score;

    public Mark(int score) {
        this.score = score;
    }

    public Mark() {
        super();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
