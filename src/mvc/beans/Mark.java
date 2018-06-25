package mvc.beans;

public class Mark {
    private int score;
    private String subject;

    public Mark(int score) {
        this.score = score;
    }

    public Mark(int score, String subject) {
        this.score = score;
        this.subject = subject;
    }

    public void setSubject(String subject) {

        this.subject = subject;
    }

    public String getSubject() {

        return subject;
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
