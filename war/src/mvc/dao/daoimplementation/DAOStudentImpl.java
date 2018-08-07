package mvc.dao.daoimplementation;

import javafx.scene.Group;
import mvc.beans.Login;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;
import mvc.dao.daointerfaces.DAOStudent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static mvc.dao.daoimplementation.SQL_STRINGS.*;

/**
 * Class for work with Student object in database
 */
public class DAOStudentImpl implements DAOStudent {


    private JdbcTemplate template;

    /**
     * set jdbc template
     *
     * @param template template to set
     */
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * create student in database
     *
     * @param student student to create
     * @param object  object to create
     */
    @Override
    public void createStudent(Student student, Object object) {
        int id = template.query(selectID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        }).get(0);
        int parentId = object.getParentId();
        if (parentId == 0)
            template.update(objectInsert, id, object.getDescription(), object.getType(), null, null);
        else
            template.update(objectInsert, id, object.getDescription(), object.getType(), object.getParentId(), null, null);
        template.update(createStudent, id, student.getName(), student.getLogin(), student.getPassword(), 0);
    }

    /**
     * remove student from database
     *
     * @param student student to remove
     */
    @Override
    public void removeStudent(Student student) {
        template.update(removeStudent, student.getId());
    }

    /**
     * get all students
     *
     * @return all students
     */
    @Override
    public List<Student> getStudents() {
        List<Student> users = template.query(getStudents, new StudentMapper());
        return users;
    }

    /**
     * get student with specific login and password
     *
     * @param login bean which contain login and password
     * @return student with login and password
     */
    @Override
    public Student validateStudent(Login login) {
        List<Student> list = template.query(validateStudent, new StudentMapper(), login.getNickname(), login.getPassword());
        Student student = null;
        if (list.size() != 0) {
            student = list.get(0);
        }
        return student;
    }

    /**
     * register student for subject
     *
     * @param student student to register
     * @param subject subject to register on
     */
    @Override
    public void registerStudentForSubject(Student student, Subject subject) {
        template.update(registerStudentForSubject, subject.getId(), student.getId(), subject.getLecturerId());
    }

    /**
     * get all student on subject
     *
     * @param subject subjects to get students on
     * @return all students on subject
     */
    @Override
    public List<Student> getStudentsOnSubject(Subject subject) {
        List<Student> users = template.query(getStudentsOnSubject, new StudentMapper(), subject.getId());
        return users;
    }

    /**
     * get all students in group
     *
     * @param object group to get students in
     * @return all students in group
     */
    @Override
    public List<Student> getStudentsForGroup(Object object) {
        List<Student> users = template.query(getStudentForGroup, new StudentMapper(), object.getId());
        return users;
    }

    /**
     * remove students from subject
     *
     * @param subject subject from which remove student
     * @param student student to remove from subject
     */
    @Override
    public void removeStudentFromSubject(Subject subject, Student student) {
        template.update(removeStudentFromSubject, student.getId(), subject.getId());
    }

    /**
     * update student in database
     *
     * @param student student to update
     */
    @Override
    public void updateStudent(Student student) {
        template.update(updateStudent, student.getName(), student.getLogin(), student.getPassword(), student.getId());
    }

    /**
     * make student a starosta in group
     *
     * @param student student to make a starosta
     */
    @Override
    public void setStarosta(Student student) {
        template.update(setStarosta1, student.getGroupId());
        template.update(setStarosta2, student.getIsStarosta(), student.getId());
    }

    /**
     * get starosta for student by specific id of student
     *
     * @param id id of student
     * @return starosta for student with id
     */
    @Override
    public Student showStarostaForStudent(int id) {
        List<Student> starosta = template.query(showStarostaForStudent, new StudentMapper(), Integer.toString(id));
        return starosta.get(0);
    }

    /**
     * get student by specific id
     *
     * @param id id of student to get
     * @return student with id
     */
    @Override
    public Student getStudentById(int id) {
        List<Student> student = template.query(getStudentById, new StudentMapper(), Integer.toString(id));
        return student.get(0);
    }

    class StudentMapper implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int arg1) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("student_id"));
            student.setLogin(rs.getString("student_login"));
            student.setPassword(rs.getString("student_password"));
            student.setName(rs.getString("student_name"));
            student.setIsStarosta(rs.getInt("is_starosta"));
            List<Object> objects = template.query(selectParentForStudent, new DAOObjectImpl.ObjectMapper(), rs.getInt("student_id"));
            student.setGroup(objects.get(0).getDescription());
            student.setGroupId(objects.get(0).getId());
            return student;
        }
    }
}
