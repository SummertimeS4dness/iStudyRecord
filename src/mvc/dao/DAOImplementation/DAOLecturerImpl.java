package mvc.dao.DAOImplementation;

import mvc.beans.*;
import mvc.dao.DAOInterfaces.DAOLecturer;
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
public void createLecturer(Lecturer lecturer) {
String sql= "INSERT INTO LECTURERS VALUES (LECTURER_SEQUENCE.nextval,?,?,?,?,?,?,?)";
template.update(sql,lecturer.getId(),lecturer.getLogin(),lecturer.getPassword(),lecturer.getName()
,lecturer.getInfo(),lecturer.getDegree(),lecturer.getWorks(),lecturer.getInterests());

}

@Override
public void removeLecturer(Lecturer lecturer) {
String sql = "DELETE FROM LECTURERS WHERE LECTURER_ID=?";
template.update(sql,lecturer.getId());
}

@Override
public Lecturer getLecturerForSubject(Subject subject) {
	String sql = "SELECT * FROM LECTURERS JOIN SUBJECTS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
			"WHERE SUBJECT_ID="+subject.getId();
	List<Lecturer> lecturers = template.query(sql,new LecturerMapper());
	return lecturers.get(0);
}

@Override
public Lecturer getLecturerForMark(Mark mark) {
	String sql = "SELECT * FROM LECTURERS JOIN MARKS ON (MARKS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
			"WHERE MARKS.LECTURER_ID="+mark.getId();
	List<Lecturer> lecturers = template.query(sql,new LecturerMapper());
	return lecturers.get(0);
}

@Override
public Lecturer getLecturerForLesson(Lesson lesson) {
	String sql = "SELECT * FROM LECTURERS JOIN LESSONS ON (LESSONS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
			"WHERE LESSON_ID="+lesson.getLessonId();
	List<Lecturer> lecturers = template.query(sql,new LecturerMapper());
	return lecturers.get(0);
}

@Override
public List<Lecturer> getLecturersForStudent(Student student) {
	String sql = "SELECT * FROM LECTURERS JOIN STUDENT_SUBJECT_LISTS on(LECTURERS.LECTURER_ID= STUDENT_SUBJECT_LISTS.LECTURER_ID)" +
			"WHERE STUDENT_ID = "+student.getId();
	List<Lecturer> lecturers = template.query(sql,new LecturerMapper());
	return lecturers;
}

@Override
public List<Lecturer> getLecturers() {
	String sql = "SELECT * FROM LECTURERS";
	List<Lecturer> lecturers = template.query(sql,new LecturerMapper());
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
		Lecturer lecturer = new Lecturer(id,login,password,name,info,degree,works,interests);
		return lecturer;
	}
}
}
