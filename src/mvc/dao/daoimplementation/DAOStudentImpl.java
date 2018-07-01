package mvc.dao.daoimplementation;

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

public class DAOStudentImpl implements DAOStudent {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void createStudent(Student student, Object object) {
        String selectID="(SELECT OBJECT_SEQUENCE.nextval FROM DUAL)";
        int id = template.query(selectID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        }).get(0);
        String objectInsert = "insert into OBJECTS values(?,?,?,?)";
        int parentId=object.getParentId();
        if(parentId==0)
        template.update(objectInsert,id,object.getDescription(),object.getType(), null);
        else
        template.update(objectInsert,object.getDescription(),object.getType(), object.getParentId());
       // String studentInsert = "insert into STUDENT_INFO values(OBJECT_SEQUENCE.nextval-1,?,?,?)";
       
        System.out.println(id);
        String studentInsert = "insert into STUDENT_INFO values(?,?,?,?)";
        template.update(studentInsert,id, student.getName(), student.getLogin(), student.getPassword());
    }


    @Override
    public void removeStudent(Student student) {
        String objectRemove = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";
        template.update(objectRemove, student.getId());

        String studentRemove = "DELETE FROM STUDENT_INFO WHERE STUDENT_ID = ?";
        template.update(studentRemove, student.getId());
    }

    @Override
    public List<Student> getStudents() {
        String sql = "SELECT * FROM STUDENT_INFO ";
        List<Student> users = template.query(sql, new StudentMapper());
        return users;
    }

    @Override
    public Student validateStudent(Login login) {
        String studentValidate = "SELECT * FROM STUDENT_INFO WHERE STUDENT_LOGIN = ? AND STUDENT_PASSWORD=?";
        Student student = template.query(studentValidate, new StudentMapper(),login.getNickname(),login.getPassword()).get(0);
        return student;
    }

    @Override
    public void registerStudentForSubject(Student student, Subject subject) {
        String registerForSubject = "INSERT INTO STUDENT_SUBJECT_LISTS VALUES(?,?,?)";
        template.update(registerForSubject, subject.getId(), student.getId(), subject.getLecturerId());
    }

    @Override
    public List<Student> getStudentsOnSubject(Subject subject) {
        String sql = "select * from STUDENT_INFO JOIN STUDENT_SUBJECT_LISTS ON " +
                "(STUDENT_INFO.STUDENT_ID=STUDENT_SUBJECT_LISTS.STUDENT_ID) WHERE SUBJECT_ID=" + subject.getId();
        List<Student> users = template.query(sql, new StudentMapper());
        return users;
    }

@Override
public void updateStudent(Student student) {
    String sql = "UPDATE STUDENT_INFO SET STUDENT_NAME=?, STUDENT_LOGIN=?,STUDENT_PASSWORD=? WHERE STUDENT_ID=?";
    template.update(sql,student.getName(),student.getLogin(),student.getPassword(),student.getId());
}

class StudentMapper implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int arg1) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("student_id"));
            student.setLogin(rs.getString("student_login"));
            student.setPassword(rs.getString("student_password"));
            student.setName(rs.getString("student_name"));
            return student;
        }
    }
}
