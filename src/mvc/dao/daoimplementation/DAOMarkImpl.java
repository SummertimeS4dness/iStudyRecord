package mvc.dao.daoimplementation;

import mvc.beans.*;
import mvc.dao.daointerfaces.DAOMark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOMarkImpl implements DAOMark {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void createMark(Mark mark) {
        String sql = "INSERT INTO MARKS VALUES (MARK_SEQUENCE.nextval,?,?,?,?,?)";
        template.update(sql, mark.getId(), mark.getLessonId(), mark.getScore(), mark.getSubjectId(), mark.getStudentId(), mark.getLecturerId());


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
        String sql = "SELECT * from MARKS WHERE STUDENT_ID=" + student.getId();
        List<Mark> marks = template.query(sql, new MarkMapper());
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
}
