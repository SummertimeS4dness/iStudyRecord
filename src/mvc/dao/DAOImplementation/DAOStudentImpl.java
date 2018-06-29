package mvc.dao.DAOImplementation;

import mvc.beans.Login;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;
import mvc.dao.DAOInterfaces.DAOStudent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import mvc.dao.DAOImplementation.*;
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
	String objectInsert = "insert into OBJECTS values(OBJECT_SEQUENCE.nextval,?,?,?)";
	template.update(objectInsert,object.getId(),object.getDesctiption(),object.getType(),object.getParentId());
	
	String studentInsert = "insert into STUDENT_INFO values(OBJECT_SEQUENCE.currval,?,?,?)";
	template.update(studentInsert, object.getId(), student.getName(), student.getLogin());
}



@Override
public void removeStudent(Student student) {
	String objectRemove = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";
	template.update(objectRemove,student.getId());
	
	String studentRemove= "DELETE FROM STUDENT_INFO WHERE STUDENT_ID = ?";
	template.update(studentRemove,student.getId());
}

@Override
public List<Student> getStudents() {
	String sql = "select * from STUDENT_INFO ";
	List<Student> users = template.query(sql, new StudentMapper());
	return users;
}

@Override
public Student validateStudent(Login login) {
	String studentValidate= "SELECT * FROM STUDENT_INFO WHERE STUDENT_LOGIN = ? AND STUDENT_PASSWORD=?";
	Student student = template.query(studentValidate,new StudentMapper()).get(0);
	return student;
}

@Override
public void registerStudentForSubject(Student student,Subject subject) {
	String registerForSubject= "INSERT INTO STUDENT_SUBJECT_LISTS VALUES(?,?,?)";
	template.update(registerForSubject,subject.getId(),student.getId(),subject.getLecturerId());
}

@Override
public List<Student> getStudentsOnSubject(Subject subject) {
	String sql = "select * from STUDENT_INFO JOIN STUDENT_SUBJECT_LISTS ON " +
			"(STUDENT_INFO.STUDENT_ID=STUDENT_SUBJECT_LISTS.STUDENT_ID) WHERE SUBJECT_ID="+subject.getId();
	List<Student> users = template.query(sql, new StudentMapper());
	return users;
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
