package mvc.dao.daointerfaces;

import mvc.beans.*;
import mvc.beans.Object;

import java.util.List;

public interface DAOLecturer {
	public void createLecturer(Lecturer lecturer, Object object);
	public void removeLecturer(Lecturer lecturer);
	public Lecturer getLecturerForSubject(Subject subject);
	public Lecturer getLecturerForMark(Mark mark);
	public Lecturer getLecturerForLesson(Lesson lesson);
	public List<Lecturer> getLecturersForStudent(Student student);
	public List<Lecturer> getLecturers();
	public Lecturer validateLecturer(Login login);
	public void updateLecturer(Lecturer lecturer);
}
