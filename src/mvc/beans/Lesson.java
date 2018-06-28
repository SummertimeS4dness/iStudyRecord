package mvc.beans;

import java.util.Date;

public class Lesson {
    private String date;
    private int subjectId;
    private int lecturerId;
    private int lessonId;

public Lesson(String date, int subjectId, int lecturerId, int lessonId) {
    this.date = date;
    this.subjectId = subjectId;
    this.lecturerId = lecturerId;
    this.lessonId = lessonId;
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
}
