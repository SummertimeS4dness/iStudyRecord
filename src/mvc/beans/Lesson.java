package mvc.beans;

import java.util.Date;

public class Lesson implements Comparable<Lesson>{
    private String stringDate;
    private Date date;
    private int subjectId;
    private int lecturerId;
    private int lessonId;
    private String subject;
    private String lecturer;

    public Lesson() { }

    public Lesson(String stringDate, int subjectId, int lecturerId, int lessonId) {
        this.stringDate = stringDate;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
    }

    public Lesson(String stringDate, int subjectId, int lecturerId, int lessonId, String subject, String lecturer) {
        this.stringDate = stringDate;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
        this.subject = subject;
        this.lecturer = lecturer;
    }

    public Lesson(Date date, int subjectId, int lecturerId, int lessonId, String subject, String lecturer, String stringDate) {
        this.date = date;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
        this.subject = subject;
        this.lecturer = lecturer;
        this.stringDate = stringDate;
    }

    public Lesson(Date date, int subjectId, int lecturerId, int lessonId, String subject, String lecturer) {
        this.date = date;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
        this.subject = subject;
        this.lecturer = lecturer;
    }

    public Lesson(String stringDate, String subject) {
        this.stringDate = stringDate;
        this.subject = subject;
    }

    public Lesson(Date date, String subject) {
        this.date = date;
        this.subject = subject;
    }

    public Lesson(Date date, String subject, String stringDate) {
        this.date = date;
        this.subject = subject;
        this.stringDate = stringDate;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setSubject(String subject) {

        this.subject = subject;
    }
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public int compareTo(Lesson o) {
        return getDate().compareTo(o.getDate());
    }
}
