package mvc.dao.daoimplementation;

import mvc.beans.*;
import mvc.beans.Object;
import mvc.dao.daointerfaces.DAOMark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;

public class DAOMarkImpl implements DAOMark {
    private final static Logger logger = Logger.getLogger(DAOMarkImpl.class);

public static final String createMark = "INSERT INTO MARKS VALUES (MARK_SEQUENCE.nextval,?,?,?,?,?)";
public static final String removeMark = "DELETE FROM MARKS WHERE MARK_ID=?";
public static final String getMarksForGroup = "SELECT * from MARKS WHERE SUBJECT_ID=?";
public static final String getMarksForStudent = "SELECT * from MARKS JOIN LESSONS ON (MARKS.LESSON_ID=LESSONS.LESSON_ID) " +
        "JOIN SUBJECTS ON (LESSONS.SUBJECT_ID = SUBJECTS.SUBJECT_ID) WHERE STUDENT_ID=?";
public static final String getMarksForLecturer = "SELECT * from MARKS WHERE LECTURER_ID=?";
public static final String getMarksForStudentAndSubject = "SELECT * from MARKS WHERE SUBJECT_ID=? AND STUDENT_ID=?";
public static final String getMarksForLecturerAndSubject = "SELECT * from MARKS WHERE SUBJECT_ID=? AND LECTURER_ID=?";
public static final String getMarksForGroupAndSubject = "SELECT * from MARKS JOIN OBJECTS ON (MARKS.STUDENT_ID=OBJECTS.OBJECT_ID) JOIN STUDENT_INFO ON" +
        " (OBJECTS.OBJECT_ID=STUDENT_INFO.STUDENT_ID) JOIN LESSONS ON (MARKS.LESSON_ID=LESSONS.LESSON_ID) WHERE " +
        "MARKS.SUBJECT_ID=? AND PARENT_ID=?";
    public static final String updateMark = "UPDATE MARKS SET SCORE=? WHERE MARK_ID=?";

private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public String createMark(Mark mark) {
        String toReturn = "OK";
        try {
            template.update(createMark, mark.getLessonId(), mark.getScore(), mark.getSubjectId(), mark.getStudentId(), mark.getLecturerId());
        } catch (Exception e) {
            toReturn = e.getMessage();
            logger.error("Error in creating new mark: " + e);
        }
        return toReturn;
    }

    @Override
    public void removeMark(Mark mark) {
        template.update(removeMark, mark.getId());
    }

    @Override
    public void updateMark(Mark mark) {
        template.update(updateMark, mark.getScore(), mark.getId());
    }

    @Override
    public List<Mark> getMarksForSubject(Subject subject) {
        List<Mark> marks = template.query(getMarksForGroup, new MarkMapper(),subject.getId());
        return marks;
    }

    @Override
    public List<Mark> getMarksForStudent(Student student) {
        System.out.println(student.getId());
        List<Mark> marks = template.query(getMarksForStudent, new MarkMapper1(),student.getId());
        return marks;
    }

    @Override
    public List<Mark> getMarksForLecturer(Lecturer lecturer) {
        List<Mark> marks = template.query(getMarksForLecturer, new MarkMapper(),lecturer.getId());
        return marks;
    }

    @Override
    public List<Mark> getMarksForStudentAndSubject(Student student, Subject subject) {
        List<Mark> marks = template.query(getMarksForStudentAndSubject, new MarkMapper(),subject.getId(),student.getId());
        return marks;
    }

    @Override
    public List<Mark> getMarksForLecturerAndSubject(Lecturer lecturer, Subject subject) {
        List<Mark> marks = template.query(getMarksForLecturerAndSubject, new MarkMapper(),subject.getId(),lecturer.getId());
        return marks;
    }

    @Override
    public List<Mark> getMarksForGroupAndSubject(Subject subject, Object object) {
        List<Mark> marks = template.query(getMarksForGroupAndSubject, new MarkMapper2(),subject.getId(),object.getId());
        return marks;
    }

    class MarkMapper implements RowMapper<Mark> {
        public Mark mapRow(ResultSet rs, int arg1) throws SQLException {
            int id = rs.getInt("mark_id");
            int lessonId = rs.getInt("lesson_id");
            int score = rs.getInt("score");
            int subjectID = rs.getInt("subject_id");
            int studentID = rs.getInt("student_id");
            int lecturerID = rs.getInt("lecturer_id");
            Mark mark = new Mark(id, lessonId, score, subjectID, studentID, lecturerID);
            return mark;
        }
    }

    class MarkMapper1 implements RowMapper<Mark> {
        public Mark mapRow(ResultSet rs, int arg1) throws SQLException {
            int id = rs.getInt("mark_id");
            int lessonId = rs.getInt("lesson_id");
            int score = rs.getInt("score");
            int subjectID = rs.getInt("subject_id");
            int studentID = rs.getInt("student_id");
            int lecturerID = rs.getInt("lecturer_id");
            String subjectName = rs.getString("subject_full_name");
            String date = rs.getString("lesson_date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            Date parsed = null;
            try {
                parsed = format.parse(date);
            } catch (ParseException e) {
                logger.error("Error in parsing mark's date: " + e);
            }
            format.applyPattern("EEEE dd-MM-yyyy HH:mm");
            date = format.format(parsed);
            Mark mark = new Mark(id, lessonId, score, subjectID, studentID, lecturerID, subjectName, date);
            mark.setDate(parsed);
            return mark;
        }
    }
    class MarkMapper2 implements RowMapper<Mark> {
        public Mark mapRow(ResultSet rs, int arg1) throws SQLException {
            int id = rs.getInt("mark_id");
            int lessonId = rs.getInt("lesson_id");
            int score = rs.getInt("score");
            int subjectID = rs.getInt("subject_id");
            int studentID = rs.getInt("student_id");
            int lecturerID = rs.getInt("lecturer_id");
            String studentName = rs.getString("student_name");
            String date = rs.getString("lesson_date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            Date parsed = null;
            try {
                parsed = format.parse(date);
            } catch (ParseException e) {
                logger.error("Error in parsing mark's date: " + e);
            }
            format.applyPattern("EEEE dd-MM-yyyy HH:mm");
            date = format.format(parsed);
            Mark mark = new Mark(id, lessonId, score, subjectID, studentID, lecturerID);
            mark.setStringDate(date);
            mark.setStudentName(studentName);
            mark.setDate(parsed);
            return mark;
        }
    }
}
