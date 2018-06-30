package mvc.beans;

public class Mark {
    //    private int score;
//    private String subject;
//
//    public Mark(int score) {
//        this.score = score;
//    }
//
//    public Mark(int score, String subject) {
//        this.score = score;
//        this.subject = subject;
//    }
//
//    public void setSubject(String subject) {
//
//        this.subject = subject;
//    }
//
//    public String getSubject() {
//
//        return subject;
//    }
//
//    public Mark() {
//        super();
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public int getScore() {
//        return score;
//    }
    private int id;
    private int lessonId;
    private int score;
    private int subjectId;
    private int studentId;
    private int lecturerId;

    public Mark(int id, int lessonId, int score, int subjectId, int studentId, int lecturerId) {
        this.id = id;
        this.lessonId = lessonId;
        this.score = score;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.lecturerId = lecturerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }
}
