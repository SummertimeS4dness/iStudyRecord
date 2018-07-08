package mvc.beans;

import java.util.Date;

public class Mark implements Comparable<Mark> {
    private int id;
    private int lessonId;
    private int score;
    private int subjectId;
    private int studentId;
    private int lecturerId;
    private String subjectName;
    private String studentName;
    private String stringDate;
    private Date date;

    public Mark() {super();}

    public Mark(int id, int lessonId, int score, int subjectId, int studentId, int lecturerId) {
        this.id = id;
        this.lessonId = lessonId;
        this.score = score;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.lecturerId = lecturerId;
    }

    public Mark(int id, int lessonId, int score, int subjectId, int studentId, int lecturerId, String subjectName, String stringDate) {
        this.id = id;
        this.lessonId = lessonId;
        this.score = score;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.lecturerId = lecturerId;
        this.subjectName = subjectName;
        this.stringDate = stringDate;
    }

    public Mark(int score, int subjectId) {
        this.score = score;
        this.subjectId = subjectId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getStringDate() {
        return stringDate;
    }

    public Date getDate() {
        return date;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Mark o) {
        return getDate().compareTo(o.getDate());
    }
}
