package mvc.beans;

import java.lang.Object;
import java.util.Date;

public class Lesson implements Comparable<Lesson>{
    private String stringDate;
    private Date date;
    private int subjectId;
    private int lecturerId;
    private int lessonId;
    private String subject;
    private String lecturer;
    private String time;
    private String day;
    private String year;
    private String month;

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

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
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

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public int compareTo(Lesson o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "stringDate='" + stringDate + '\'' +
                ", date=" + date +
                ", subjectId=" + subjectId +
                ", lecturerId=" + lecturerId +
                ", lessonId=" + lessonId +
                ", subject='" + subject + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", time='" + time + '\'' +
                ", day='" + day + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                '}';
    }

    @Override
public boolean equals(Object a) {
        Lesson o = (Lesson) a;
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Lesson lesson = (Lesson) o;
    
    if (subjectId != lesson.subjectId) return false;
    if (lecturerId != lesson.lecturerId) return false;
    return lessonId == lesson.lessonId;
}

@Override
public int hashCode() {
    int result = subjectId;
    result = 31 * result + lecturerId;
    result = 31 * result + lessonId;
    return result;
}
}
