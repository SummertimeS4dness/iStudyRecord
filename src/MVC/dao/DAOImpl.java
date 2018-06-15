package MVC.dao;

import MVC.beans.Lesson;
import MVC.beans.Lecturer;
import MVC.beans.Mark;
import MVC.beans.Student;
import MVC.controllers.Controllers;
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
}
