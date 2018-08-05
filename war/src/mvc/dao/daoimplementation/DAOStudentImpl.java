package mvc.dao.daoimplementation;

import javafx.scene.Group;
import mvc.beans.Login;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;
import mvc.dao.daointerfaces.DAOObject;
import mvc.dao.daointerfaces.DAOStudent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOStudentImpl implements DAOStudent {
public static final String selectID = "(SELECT OBJECT_SEQUENCE.nextval FROM DUAL)";
public static final String objectInsert = "insert into OBJECTS values(?,?,?,?)";
public static final String createStudent = "insert into STUDENT_INFO values(?,?,?,?)";
public static final String removeStudent = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";
public static final String getStudents = "SELECT * FROM STUDENT_INFO ORDER BY STUDENT_ID";
public static final String validateStudent = "SELECT * FROM STUDENT_INFO WHERE STUDENT_LOGIN = ? AND STUDENT_PASSWORD=? ORDER BY STUDENT_ID";
public static final String registerStudentForSubject = "INSERT INTO STUDENT_SUBJECT_LISTS VALUES(?,?,?)";
public static final String getStudentsOnSubject = "select * from STUDENT_INFO JOIN STUDENT_SUBJECT_LISTS ON " +
        "(STUDENT_INFO.STUDENT_ID=STUDENT_SUBJECT_LISTS.STUDENT_ID) WHERE SUBJECT_ID=?";
public static final String getStudentForGroup = "SELECT * FROM STUDENT_INFO JOIN OBJECTS ON " +
        "(STUDENT_INFO.STUDENT_ID=OBJECTS.OBJECT_ID) WHERE PARENT_ID=?";
public static final String removeStudentFromSubject = "DELETE FROM STUDENT_SUBJECT_LISTS WHERE STUDENT_ID=? AND SUBJECT_ID=?";
public static final String updateStudent = "UPDATE STUDENT_INFO SET STUDENT_NAME=?, STUDENT_LOGIN=?,STUDENT_PASSWORD=? WHERE STUDENT_ID=?";
public static final String setStarosta1 = "UPDATE STUDENT_INFO SET  IS_STAROSTA = 0 WHERE STUDENT_ID IN (SELECT OBJECT_ID FROM OBJECTS WHERE PARENT_ID=?)";
public static final String setStarosta2 = "UPDATE STUDENT_INFO SET IS_STAROSTA=? WHERE STUDENT_ID=?";
public static final String selectParentForStudent = "SELECT * FROM OBJECTS WHERE OBJECT_ID=(SELECT PARENT_ID FROM OBJECTS WHERE OBJECT_ID=?)ORDER BY OBJECT_ID";
    public static final String showStarostaForStudent = "SELECT * FROM STUDENT_INFO JOIN OBJECTS ON (STUDENT_INFO.STUDENT_ID = OBJECTS.OBJECT_ID) " +
            "WHERE OBJECTS.PARENT_ID=(SELECT PARENT_ID FROM OBJECTS WHERE OBJECT_ID=?) AND STUDENT_INFO.IS_STAROSTA=1";
    public static final String getStudentById = "SELECT * FROM STUDENT_INFO WHERE STUDENT_ID=?";


private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void createStudent(Student student, Object object) {
        int id = template.query(selectID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        }).get(0);
        int parentId=object.getParentId();
        if(parentId==0)
        template.update(objectInsert,id,object.getDescription(),object.getType(), null);
        else
        template.update(objectInsert,id,object.getDescription(),object.getType(), object.getParentId());
        template.update(createStudent,id, student.getName(), student.getLogin(), student.getPassword());
    }


    @Override
    public void removeStudent(Student student) {
        template.update(removeStudent, student.getId());
    }
    
    @Override
    public List<Student> getStudents() {
        List<Student> users = template.query(getStudents, new StudentMapper());
        return users;
    }

    @Override
    public Student validateStudent(Login login) {
        List<Student> list = template.query(validateStudent, new StudentMapper(),login.getNickname(),login.getPassword());
        Student student = null;
        if(list.size() != 0) {
            student = list.get(0);
        }
        return student;
    }

    @Override
    public void registerStudentForSubject(Student student, Subject subject) {
        template.update(registerStudentForSubject, subject.getId(), student.getId(), subject.getLecturerId());
    }

    @Override
    public List<Student> getStudentsOnSubject(Subject subject) {
        List<Student> users = template.query(getStudentsOnSubject, new StudentMapper(),subject.getId());
        return users;
    }

    @Override
    public List<Student> getStudentsForGroup(Object object) {
        List<Student> users = template.query(getStudentForGroup, new StudentMapper(),object.getId());
        return users;
    }

@Override
public void removeStudentFromSubject(Subject subject, Student student) {
    template.update(removeStudentFromSubject,student.getId(),subject.getId());
}
@Override
public void updateStudent(Student student) {
    template.update(updateStudent,student.getName(),student.getLogin(),student.getPassword(),student.getId());
}
@Override
public void setStarosta(Student student) {
    template.update(setStarosta1,student.getGroupId());
    template.update(setStarosta2,student.getIsStarosta(),student.getId());
}

    @Override
    public Student showStarostaForStudent(int id) {
        List<Student> starosta = template.query(showStarostaForStudent, new StudentMapper(), Integer.toString(id));
        return starosta.get(0);
    }

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
            List<Object> objects = template.query(selectParentForStudent, new DAOObjectImpl.ObjectMapper(),rs.getInt("student_id"));
            student.setGroup(objects.get(0).getDescription());
            student.setGroupId(objects.get(0).getId());
            return student;
        }
    }
}
