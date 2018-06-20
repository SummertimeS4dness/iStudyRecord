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
    public List<Mark> getMarks() {
        return template.query("select * from ANOTHERSTUDENTS", new RowMapper<Mark>() {
            @Override
            public Mark mapRow(ResultSet resultSet, int i) throws SQLException {
                Mark student = new Mark();
                return student;
            }
        });
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

    }

    @Override
    public Student validateStudent(String login, String password) {
        String sql = "select * from STUDENTS where STUDENTLOGIN='" + login + "' and PASSWORD='" + password + "'";

        List<Student> users = template.query(sql, new StudentMapper());

        return users.size() > 0 ? users.get(0) : null;
    }
    class StudentMapper implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int arg1) throws SQLException {
            Student user = new Student();

            user.setStudentLogin(rs.getString("studentlogin"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setGroup(rs.getString("groupname"));
            user.setStarosta(rs.getInt("isstarosta"));

            return user;
        }
    }
    @Override
    public Lecturer validateLecturer(String login, String password) {
        return null;
    }
}
