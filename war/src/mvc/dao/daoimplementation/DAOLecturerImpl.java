package mvc.dao.daoimplementation;

import mvc.beans.*;
import mvc.beans.Object;
import mvc.dao.daointerfaces.DAOLecturer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOLecturerImpl implements DAOLecturer {
private JdbcTemplate template;


public static final String selectID = "(SELECT OBJECT_SEQUENCE.nextval FROM DUAL)";
public static final String objectInsert = "insert into OBJECTS values(?,?,?,?)";
public static final String createLecturer = "INSERT INTO LECTURERS VALUES (?,?,?,?,?,?,?,?)";
public static final String removeLecturer = "DELETE FROM OBJECTS WHERE OBJECT_ID=?";
public static final String getLecturerForSubject = "SELECT * FROM LECTURERS JOIN SUBJECTS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
		"WHERE SUBJECT_ID=?";
public static final String getGetLecturersForSubject ="SELECT * FROM LECTURERS JOIN SUBJECTS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
		"WHERE SUBJECT_ID=?";
public static final String getLecturerForMark = "SELECT * FROM LECTURERS JOIN MARKS ON (MARKS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
		"WHERE MARKS.LECTURER_ID=?";
public static final String getLecturerForLesson = "SELECT * FROM LECTURERS JOIN LESSONS ON (LESSONS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
		"WHERE LESSON_ID=?";
public static final String getLecturersForStudent = "SELECT * FROM LECTURERS JOIN STUDENT_SUBJECT_LISTS on(LECTURERS.LECTURER_ID= STUDENT_SUBJECT_LISTS.LECTURER_ID)" +
		"WHERE STUDENT_ID = ?";
public static final String getlecturers = "SELECT * FROM LECTURERS";
public static final String validateLecturer  = "SELECT * FROM LECTURERS WHERE LECTURER_LOGIN = ? AND LECTURER_PASSWORD=?";
public static final String updateLecturer = "UPDATE LECTURERS SET LECTURER_LOGIN =?,LECTURER_PASSWORD=?,LECTURER_NAME=?,LECTURER_INFO=?,LECTURER_DEGREE=?,LECTURER_WORKS=?,LECTURER_INTERESTS=? WHERE LECTURER_ID=?";



public void setTemplate(JdbcTemplate template) {
	this.template = template;
}
@Override
public void createLecturer(Lecturer lecturer, Object object) {
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
	template.update(createLecturer,id, lecturer.getLogin(), lecturer.getPassword(), lecturer.getName()
			, lecturer.getInfo(), lecturer.getDegree(), lecturer.getWorks(), lecturer.getInterests());
	
	
}
@Override
public void removeLecturer(Lecturer lecturer) {
	template.update(removeLecturer, lecturer.getId());
}
@Override
public Lecturer getLecturerForSubject(Subject subject) {
	List<Lecturer> lecturers = template.query(getLecturerForSubject, new LecturerMapper(),subject.getId());
	return lecturers.get(0);
}
@Override
public List<Lecturer> getLecturersForSubject(Subject subject) {
	List<Lecturer> lecturers = template.query(getGetLecturersForSubject, new LecturerMapper(), subject.getId());
	return lecturers;
}
@Override
public Lecturer getLecturerForMark(Mark mark) {
	List<Lecturer> lecturers = template.query(getLecturerForMark, new LecturerMapper(),mark.getId());
	return lecturers.get(0);
}
@Override
public Lecturer getLecturerForLesson(Lesson lesson) {
	List<Lecturer> lecturers = template.query(getLecturerForLesson, new LecturerMapper(),lesson.getLessonId());
	return lecturers.get(0);
}
@Override
public List<Lecturer> getLecturersForStudent(Student student) {
	List<Lecturer> lecturers = template.query(getLecturersForStudent, new LecturerMapper(),student.getId());
	return lecturers;
}
@Override
public List<Lecturer> getLecturers() {
	List<Lecturer> lecturers = template.query(getlecturers , new LecturerMapper());
	return lecturers;
}
class LecturerMapper implements RowMapper<Lecturer> {
	public Lecturer mapRow(ResultSet rs, int arg1) throws SQLException {
		int id = rs.getInt("lecturer_id");
		String login = rs.getString("lecturer_login");
		String password = rs.getString("lecturer_password");
		String name = rs.getString("lecturer_name");
		String info = rs.getString("lecturer_info");
		String degree = rs.getString("lecturer_degree");
		String works = rs.getString("lecturer_works");
		String interests = rs.getString("lecturer_interests");
		Lecturer lecturer = new Lecturer(id, login, password, name, info, degree, works, interests);
		String sql = "SELECT * FROM OBJECTS WHERE OBJECT_ID=(SELECT PARENT_ID FROM OBJECTS WHERE OBJECT_ID=" + rs.getInt("lecturer_id")+")ORDER BY OBJECT_ID";
		List<Object> objects = template.query(sql, new DAOObjectImpl.ObjectMapper());
		lecturer.setCathedra(objects.get(0).getDescription());
		lecturer.setCathedraId(objects.get(0).getId());
		return lecturer;
	}
}
@Override
public Lecturer validateLecturer(Login login) {
	List<Lecturer> list = template.query(validateLecturer, new LecturerMapper(),login.getNickname(),login.getPassword());
	Lecturer lecturer = null;
	if(list.size() != 0) {
		lecturer = list.get(0);
	}
	return lecturer;
}
@Override
public void updateLecturer(Lecturer lecturer) {
	template.update(updateLecturer,lecturer.getLogin(),lecturer.getPassword(),lecturer.getName(),lecturer.getInfo(),lecturer.getDegree(),lecturer.getWorks(),lecturer.getInterests(),lecturer.getId());
}
}