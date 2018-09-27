package mvc.beans;

import java.lang.Object;
import java.util.Date;

/**
 * Bean Mark.
 */
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

    /**
     * Instantiates a new Mark.
     */
    public Mark() {
        super();
    }

    /**
     * Instantiates a new Mark.
     *
     * @param id         mark's id
     * @param lessonId   mark's lesson's id
     * @param score      mark's score
     * @param subjectId  mark's subject's id
     * @param studentId  mark's student's id
     * @param lecturerId mark's lecturer's id
     */
    public Mark(int id, int lessonId, int score, int subjectId, int studentId, int lecturerId) {
        this.id = id;
        this.lessonId = lessonId;
        this.score = score;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.lecturerId = lecturerId;
    }

    /**
     * Instantiates a new Mark.
     *
     * @param id          mark's id
     * @param lessonId    mark's lesson's id
     * @param score       mark's score
     * @param subjectId   mark's subject's id
     * @param studentId   mark's student's id
     * @param lecturerId  mark's lecturer's id
     * @param subjectName mark's subject's name
     * @param stringDate  mark's string date
     */
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

    /**
     * Instantiates a new Mark.
     *
     * @param score     mark's score
     * @param subjectId mark's subject's id
     */
    public Mark(int score, int subjectId) {
        this.score = score;
        this.subjectId = subjectId;
    }

    /**
     * Gets mark's student name.
     *
     * @return mark's student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets mark's student name.
     *
     * @param studentName mark's student name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Gets mark's id.
     *
     * @return mark's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets mark's id.
     *
     * @param id mark's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets mark's lesson id.
     *
     * @return mark's lesson id
     */
    public int getLessonId() {
        return lessonId;
    }

    /**
     * Sets mark's lesson id.
     *
     * @param lessonId mark's lesson id
     */
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * Gets mark's score.
     *
     * @return mark's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets mark's score.
     *
     * @param score mark's score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets mark's subject's id.
     *
     * @return mark's subject's id
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * Sets mark's subject's id.
     *
     * @param subjectId mark's subject's id
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Gets mark's student's id.
     *
     * @return mark's student's id
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Sets mark's student's id.
     *
     * @param studentId mark's student's id
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets mark's lecturer's id.
     *
     * @return mark's lecturer's id
     */
    public int getLecturerId() {
        return lecturerId;
    }

    /**
     * Sets mark's lecturer's id.
     *
     * @param lecturerId mark's lecturer's id
     */
    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    /**
     * Sets mark's subject's name.
     *
     * @param subjectName mark's subject's name
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Gets mark's subject's name.
     *
     * @return mark's subject's name
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Gets mark's string date.
     *
     * @return mark's string date
     */
    public String getStringDate() {
        return stringDate;
    }

    /**
     * Gets mark's date.
     *
     * @return mark's date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets mark's string date.
     *
     * @param stringDate mark's string date
     */
    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    /**
     * Sets mark's date.
     *
     * @param date mark's date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Mark o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", score=" + score +
                ", subjectId=" + subjectId +
                ", studentId=" + studentId +
                ", lecturerId=" + lecturerId +
                ", subjectName='" + subjectName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", stringDate='" + stringDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark = (Mark) o;

        if (id != mark.id) return false;
        if (lessonId != mark.lessonId) return false;
        if (score != mark.score) return false;
        if (subjectId != mark.subjectId) return false;
        if (studentId != mark.studentId) return false;
        return lecturerId == mark.lecturerId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + lessonId;
        result = 31 * result + score;
        result = 31 * result + subjectId;
        result = 31 * result + studentId;
        result = 31 * result + lecturerId;
        return result;
    }
}
