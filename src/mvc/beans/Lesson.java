package mvc.beans;

import java.util.Date;

public class Lesson {
    private String date;
    private int subjectId;
    private int lecturerId;
    private int lessonId;
    private String subject;
    private String lecturer;

    public Lesson() { }

    public Lesson(String date, int subjectId, int lecturerId, int lessonId) {
        this.date = date;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
    }

    public Lesson(String date, int subjectId, int lecturerId, int lessonId, String subject, String lecturer) {
        this.date = date;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
        this.subject = subject;
        this.lecturer = lecturer;
    }

    public Lesson(String date, String subject) {
        this.date = date;
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
