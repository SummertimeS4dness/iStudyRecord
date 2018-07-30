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

public class DAOMarkImpl implements DAOMark {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public String createMark(Mark mark) {
        String sql = "INSERT INTO MARKS VALUES (MARK_SEQUENCE.nextval,?,?,?,?,?)";
        String toReturn = "OK";
        try {
            template.update(sql, mark.getLessonId(), mark.getScore(), mark.getSubjectId(), mark.getStudentId(), mark.getLecturerId());
        } catch (Exception e) {
            toReturn = e.getMessage();
        }
        return toReturn;
    }

    @Override
    public void removeMark(Mark mark) {
        String sql = "DELETE FROM MARKS WHERE MARK_ID=?";
        template.update(sql, mark.getId());
    }

    @Override
    public List<Mark> getMarksForSubject(Subject subject) {
        String sql = "SELECT * from MARKS WHERE SUBJECT_ID=" + subject.getId();
        List<Mark> marks = template.query(sql, new MarkMapper());
        return marks;
    }

    @Override
    public List<Mark> getMarksForStudent(Student student) {
        System.out.println(student.getId());
        String sql = "SELECT * from MARKS JOIN LESSONS ON (MARKS.LESSON_ID=LESSONS.LESSON_ID) " +
                "JOIN SUBJECTS ON (LESSONS.SUBJECT_ID = SUBJECTS.SUBJECT_ID) WHERE STUDENT_ID=" + student.getId();
        List<Mark> marks = template.query(sql, new MarkMapper1());
        return marks;
    }

    @Override
    public List<Mark> getMarksForLecturer(Lecturer lecturer) {
        String sql = "SELECT * from MARKS WHERE LECTURER_ID=" + lecturer.getId();
        List<Mark> marks = template.query(sql, new MarkMapper());
        return marks;
    }

    @Override
    public List<Mark> getMarksForStudentAndSubject(Student student, Subject subject) {
        String sql = "SELECT * from MARKS WHERE SUBJECT_ID=" + subject.getId() + " AND STUDENT_ID=" + student.getId();
        List<Mark> marks = template.query(sql, new MarkMapper());
        return marks;
    }

    @Override
    public List<Mark> getMarksForLecturerAndSubject(Lecturer lecturer, Subject subject) {
        String sql = "SELECT * from MARKS WHERE SUBJECT_ID=" + subject.getId() + " AND LECTURER_ID=" + lecturer.getId();
        List<Mark> marks = template.query(sql, new MarkMapper());
        return marks;
    }

    @Override
    public List<Mark> getMarksForGroupAndSubject(Subject subject, Object object) {
        String sql = "SELECT * from MARKS JOIN OBJECTS ON (MARKS.STUDENT_ID=OBJECTS.OBJECT_ID) JOIN STUDENT_INFO ON" +
                " (OBJECTS.OBJECT_ID=STUDENT_INFO.STUDENT_ID) JOIN LESSONS ON (MARKS.LESSON_ID=LESSONS.LESSON_ID) WHERE " +
                "MARKS.SUBJECT_ID=" + subject.getId() + " AND PARENT_ID=" + object.getId();
        List<Mark> marks = template.query(sql, new MarkMapper2());
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
                e.printStackTrace();
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
                e.printStackTrace();
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
