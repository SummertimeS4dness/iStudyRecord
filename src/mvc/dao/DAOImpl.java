package mvc.dao;

import mvc.beans.Lecturer;
import mvc.beans.Lesson;
import mvc.beans.Mark;
import mvc.beans.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOImpl implements DAO {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Student> getStudents() {
        return template.query("select * from ANOTHERSTUDENTS", new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                return student;
            }
        });
    }

    @Override
    public List<Mark> getMarks(String name) {
        return template.query("select * from MARK where MARK_STUDENT='" + name + "'", new MarkMapper());
    }

    class MarkMapper implements RowMapper<Mark> {
        public Mark mapRow(ResultSet rs, int arg1) throws SQLException {
            Mark mark = new Mark();

            mark.setScore(Integer.parseInt(rs.getString("mark_score")));
            mark.setSubject(rs.getString("mark_subject"));

            return mark;
        }
    }

    @Override
    public void setMark() {

    }

    @Override
    public void createLesson() {
        Lesson lesson = new Lesson();
    }

    @Override
    public void createStudent() {

    }

    @Override
    public void createLecturer() {
        Lecturer lecturer = new Lecturer();
    }

    @Override
    public void createSubject() {

    }

    @Override
    public void registerStudentForSubject() {

    }

    @Override
    public void registerLecturerForSubject() {

    }

    @Override
    public void registerStudent(Student student) {
        String sql = "insert into STUDENTS values(?,?,?,?,?)";
        template.update(sql, student.getStudentLogin(), student.getPassword(), student.getName(),
                student.getGroup(), student.getStarosta());
    }

    @Override
    public void registerLecturer(Lecturer lecturer) {
        String sql = "insert into LECTURER values(?,?,?,?)";
        template.update(sql, lecturer.getLecturerID(), lecturer.getLecturerLogin(), lecturer.getPassword(),
                lecturer.getName());
    }

    @Override
    public Student validateStudent(String login, String password) {
        String sql = "select * from STUDENTS where STUDENTLOGIN='" + login + "' and PASSWORD='" + password + "'";

        List<Student> users = template.query(sql, new StudentMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

    class StudentMapper implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int arg1) throws SQLException {
            Student student = new Student();

            student.setStudentLogin(rs.getString("studentlogin"));
            student.setPassword(rs.getString("password"));
            student.setName(rs.getString("name"));
            student.setGroup(rs.getString("groupname"));
            student.setStarosta(rs.getInt("isstarosta"));

            return student;
        }
    }

    @Override
    public Lecturer validateLecturer(String login, String password) {
        String sql = "select * from LECTURER where LECTURER_LOGIN='" + login + "' and LECTURER_PASSWORD='" + password + "'";

        List<Lecturer> users = template.query(sql, new LecturerMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

    class LecturerMapper implements RowMapper<Lecturer> {
        public Lecturer mapRow(ResultSet rs, int arg1) throws SQLException {
            Lecturer lecturer = new Lecturer();

            lecturer.setLecturerID(rs.getString("lecturer_id"));
            lecturer.setLecturerLogin(rs.getString("lecturer_login"));
            lecturer.setPassword(rs.getString("lecturer_password"));
            lecturer.setName(rs.getString("lecturer_name"));

            return lecturer;
        }
    }
}
