package mvc.dao.DAOInterfaces;

import mvc.beans.*;

import java.util.List;

public interface DAOMark {
	public void createMark(Mark mark);
	public void removeMark(Mark mark);
	public List<Mark> getMarksForSubject(Subject subject);
	public List<Mark> getMarksForStudent(Student student);
	public List<Mark> getMarksForLecturer(Lecturer lecturer);
	public List<Mark> getMarksForStudentAndSubject(Student student,Subject subject);
	public List<Mark> getMarksForLecturerAndSubject(Lecturer lecturer,Subject subject);
	
}
