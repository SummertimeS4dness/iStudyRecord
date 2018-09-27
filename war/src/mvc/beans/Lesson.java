package mvc.beans;

import java.lang.Object;
import java.util.Date;

/**
 * Bean Lesson.
 */
public class Lesson implements Comparable<Lesson> {
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

    /**
     * Instantiates a new Lesson.
     */
    public Lesson() {
    }

    /**
     * Instantiates a new Lesson.
     *
     * @param stringDate lesson's string date
     * @param subjectId  lesson's subject's id
     * @param lecturerId lesson's lecturer's id
     * @param lessonId   lesson's id
     */
    public Lesson(String stringDate, int subjectId, int lecturerId, int lessonId) {
        this.stringDate = stringDate;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
    }

    /**
     * Instantiates a new Lesson.
     *
     * @param stringDate lesson's string date
     * @param subjectId  lesson's subject's id
     * @param lecturerId lesson's lecturer's id
     * @param lessonId   lesson's id
     * @param subject    lesson's subject
     * @param lecturer   lesson's lecturer
     */
    public Lesson(String stringDate, int subjectId, int lecturerId, int lessonId, String subject, String lecturer) {
        this.stringDate = stringDate;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
        this.subject = subject;
        this.lecturer = lecturer;
    }

    /**
     * Instantiates a new Lesson.
     *
     * @param date       lesson's date
     * @param subjectId  lesson's subject's id
     * @param lecturerId lesson's lecturer's id
     * @param lessonId   lesson's id
     * @param subject    lesson's subject
     * @param lecturer   lesson's lecturer
     * @param stringDate lesson's string date
     */
    public Lesson(Date date, int subjectId, int lecturerId, int lessonId, String subject, String lecturer, String stringDate) {
        this.date = date;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
        this.subject = subject;
        this.lecturer = lecturer;
        this.stringDate = stringDate;
    }

    /**
     * Instantiates a new Lesson.
     *
     * @param date       lesson's date
     * @param subjectId  lesson's subject's id
     * @param lecturerId lesson's lecturer's id
     * @param lessonId   lesson's id
     * @param subject    lesson's subject
     * @param lecturer   lesson's lecturer
     */
    public Lesson(Date date, int subjectId, int lecturerId, int lessonId, String subject, String lecturer) {
        this.date = date;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.lessonId = lessonId;
        this.subject = subject;
        this.lecturer = lecturer;
    }

    /**
     * Instantiates a new Lesson.
     *
     * @param stringDate lesson's string date
     * @param subject    lesson's subject
     */
    public Lesson(String stringDate, String subject) {
        this.stringDate = stringDate;
        this.subject = subject;
    }

    /**
     * Instantiates a new Lesson.
     *
     * @param date    lesson's date
     * @param subject lesson's subject
     */
    public Lesson(Date date, String subject) {
        this.date = date;
        this.subject = subject;
    }

    /**
     * Instantiates a new Lesson.
     *
     * @param date       lesson's date
     * @param subject    lesson's subject
     * @param stringDate lesson's string date
     */
    public Lesson(Date date, String subject, String stringDate) {
        this.date = date;
        this.subject = subject;
        this.stringDate = stringDate;
    }

    /**
     * Sets lesson's time.
     *
     * @param time lesson's time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets lesson's time.
     *
     * @return lesson's time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets lesson's day.
     *
     * @param day lesson's day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Gets lesson's day.
     *
     * @return lesson's day
     */
    public String getDay() {
        return day;
    }

    /**
     * Gets lesson's string date.
     *
     * @return lesson's string date
     */
    public String getStringDate() {
        return stringDate;
    }

    /**
     * Gets lesson's date.
     *
     * @return lesson's date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets lesson's string date.
     *
     * @param stringDate lesson's string date
     */
    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    /**
     * Sets lesson's date.
     *
     * @param date lesson's date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets lesson's subject's id.
     *
     * @return lesson's subject's id
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * Sets lesson's subject's id.
     *
     * @param subjectId lesson's subject's id
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Gets lesson's lecturer's id.
     *
     * @return lesson's lecturer's id
     */
    public int getLecturerId() {
        return lecturerId;
    }

    /**
     * Sets lesson's lecturer's id.
     *
     * @param lecturerId lesson's lecturer's id
     */
    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    /**
     * Gets lesson's lesson's id.
     *
     * @return lesson's lesson's id
     */
    public int getLessonId() {
        return lessonId;
    }

    /**
     * Sets lesson's lesson's id.
     *
     * @param lessonId lesson's lesson's id
     */
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * Gets lesson's lecturer.
     *
     * @return lesson's lecturer
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Sets lesson's subject.
     *
     * @param subject lesson's subject
     */
    public void setSubject(String subject) {

        this.subject = subject;
    }

    /**
     * Sets lesson's lecturer.
     *
     * @param lecturer lesson's lecturer
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * Gets lesson's subject.
     *
     * @return lesson's subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets lesson's year.
     *
     * @return lesson's year
     */
    public String getYear() {
        return year;
    }

    /**
     * Gets lesson's month.
     *
     * @return lesson's month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets lesson's year.
     *
     * @param year lesson's year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Sets lesson's month.
     *
     * @param month lesson's month
     */
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
