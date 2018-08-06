package mvc.dao.daoimplementation;

import mvc.beans.*;
import mvc.beans.Object;
import mvc.dao.daointerfaces.DAOMark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;

import static mvc.dao.daoimplementation.SQL_STRINGS.*;


public class DAOMarkImpl implements DAOMark {
    private final static Logger logger = Logger.getLogger(DAOMarkImpl.class);



private JdbcTemplate template;

/**
 * set jdbc template
 * @param template template to set
 */
public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

/**
 * create mark in database
 * @param mark mark to create
 * @return result of creating
 */
@Override
public String createMark(Mark mark) {
        String toReturn = "OK";
        try {
            template.update(createMark, mark.getLessonId(), mark.getScore(), mark.getSubjectId(), mark.getStudentId(), mark.getLecturerId());
        } catch (Exception e) {
            toReturn = e.getMessage();
            printLog(e, "Error in creating new mark:\n");
        }
        return toReturn;
    }

/**
 * remove mark from database
 * @param mark mark to remove
 */
@Override
public void removeMark(Mark mark) {
        template.update(removeMark, mark.getId());
    }

/**
 * update mark in database
 * @param mark mark to update
 */
@Override
public void updateMark(Mark mark) {
        template.update(updateMark, mark.getScore(), mark.getId());
    }

/**
 * get all marks for subject
 * @param subject subjects to get marks on
 * @return all marks for subject
 */
@Override
public List<Mark> getMarksForSubject(Subject subject) {
        List<Mark> marks = template.query(getMarksForGroup, new MarkMapper(),subject.getId());
        return marks;
    }

/**
 * get all marks for student
 * @param student student to get marks for
 * @return all marks for student
 */
@Override
public List<Mark> getMarksForStudent(Student student) {
        System.out.println(student.getId());
        List<Mark> marks = template.query(getMarksForStudent, new MarkMapper1(),student.getId());
        return marks;
    }

/**
 * get all marks putted by lecturer
 * @param lecturer lecturer to get all marks
 * @return all marks putted by lecturer
 */
@Override
public List<Mark> getMarksForLecturer(Lecturer lecturer) {
        List<Mark> marks = template.query(getMarksForLecturer, new MarkMapper(),lecturer.getId());
        return marks;
    }

/**
 * get all marks for student on subject
 * @param student student to get marks for
 * @param subject subject to get marks for
 * @return marks for student on subject
 */
@Override
public List<Mark> getMarksForStudentAndSubject(Student student, Subject subject) {
        List<Mark> marks = template.query(getMarksForStudentAndSubject, new MarkMapper(),subject.getId(),student.getId());
        return marks;
    }

/**
 * get all marks putted by lecturer on subject
 * @param lecturer lecturer to get marks
 * @param subject subject to get marks for
 * @return marks putted by lecturer on subject
 */
@Override
public List<Mark> getMarksForLecturerAndSubject(Lecturer lecturer, Subject subject) {
        List<Mark> marks = template.query(getMarksForLecturerAndSubject, new MarkMapper(),subject.getId(),lecturer.getId());
        return marks;
    }

/**
 * get all marks for group on subject
 * @param subject subject to get marks on
 * @param object group to get marks for
 * @return all marks for group on subject
 */
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
                printLog(e, "Error in parsing mark's date:\n");
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
                printLog(e,"Error in parsing mark's date:\n");
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
    private void printLog(Exception e, String mes) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        logger.error(mes);
        logger.error(result.toString());
    }
}
