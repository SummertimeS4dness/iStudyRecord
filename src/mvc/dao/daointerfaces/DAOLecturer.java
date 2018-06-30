package mvc.dao.daointerfaces;

import mvc.beans.*;

import java.util.List;

public interface DAOLecturer {
	public void createLecturer(Lecturer lecturer);
	public void removeLecturer(Lecturer lecturer);
	public Lecturer getLecturerForSubject(Subject subject);
	public Lecturer getLecturerForMark(Mark mark);
	public Lecturer getLecturerForLesson(Lesson lesson);
	public List<Lecturer> getLecturersForStudent(Student student);
	public List<Lecturer> getLecturers();
}
