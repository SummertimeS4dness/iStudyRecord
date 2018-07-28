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

public void setTemplate(JdbcTemplate template) {
	this.template = template;
}

@Override
public void createLecturer(Lecturer lecturer, Object object) {
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
		template.update(objectInsert,id,object.getDescription(),object.getType(), object.getParentId());
	String sql = "INSERT INTO LECTURERS VALUES (?,?,?,?,?,?,?,?)";
	template.update(sql,id, lecturer.getLogin(), lecturer.getPassword(), lecturer.getName()
			, lecturer.getInfo(), lecturer.getDegree(), lecturer.getWorks(), lecturer.getInterests());
	
	
}

@Override
public void removeLecturer(Lecturer lecturer) {
	String sql = "DELETE FROM OBJECTS WHERE OBJECT_ID=?";
	template.update(sql, lecturer.getId());
}

@Override
public Lecturer getLecturerForSubject(Subject subject) {
	String sql = "SELECT * FROM LECTURERS JOIN SUBJECTS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
			"WHERE SUBJECT_ID=" + subject.getId();
	List<Lecturer> lecturers = template.query(sql, new LecturerMapper());
	return lecturers.get(0);
}

@Override
public Lecturer getLecturerForMark(Mark mark) {
	String sql = "SELECT * FROM LECTURERS JOIN MARKS ON (MARKS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
			"WHERE MARKS.LECTURER_ID=" + mark.getId();
	List<Lecturer> lecturers = template.query(sql, new LecturerMapper());
	return lecturers.get(0);
}

@Override
public Lecturer getLecturerForLesson(Lesson lesson) {
	String sql = "SELECT * FROM LECTURERS JOIN LESSONS ON (LESSONS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
			"WHERE LESSON_ID=" + lesson.getLessonId();
	List<Lecturer> lecturers = template.query(sql, new LecturerMapper());
	return lecturers.get(0);
}

@Override
public List<Lecturer> getLecturersForStudent(Student student) {
	String sql = "SELECT * FROM LECTURERS JOIN STUDENT_SUBJECT_LISTS on(LECTURERS.LECTURER_ID= STUDENT_SUBJECT_LISTS.LECTURER_ID)" +
			"WHERE STUDENT_ID = " + student.getId();
	List<Lecturer> lecturers = template.query(sql, new LecturerMapper());
	return lecturers;
}

@Override
public List<Lecturer> getLecturers() {
	String sql = "SELECT * FROM LECTURERS";
	List<Lecturer> lecturers = template.query(sql, new LecturerMapper());
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
	String lecturerValidate = "SELECT * FROM LECTURERS WHERE LECTURER_LOGIN = ? AND LECTURER_PASSWORD=?";
	List<Lecturer> list = template.query(lecturerValidate, new LecturerMapper(),login.getNickname(),login.getPassword());
	Lecturer lecturer = null;
	if(list.size() != 0) {
		lecturer = list.get(0);
	}
	return lecturer;
}

public void updateLecturer(Lecturer lecturer) {
	String sql = "UPDATE LECTURERS SET LECTURER_LOGIN =?,LECTURER_PASSWORD=?,LECTURER_NAME=?,LECTURER_INFO=?,LECTURER_DEGREE=?,LECTURER_WORKS=?,LECTURER_INTERESTS=? WHERE LECTURER_ID=?";
	template.update(sql,lecturer.getLogin(),lecturer.getPassword(),lecturer.getName(),lecturer.getInfo(),lecturer.getDegree(),lecturer.getWorks(),lecturer.getInterests(),lecturer.getId());
}
}