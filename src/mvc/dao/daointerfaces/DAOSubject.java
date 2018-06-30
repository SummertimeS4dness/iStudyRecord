package mvc.dao.daointerfaces;

import mvc.beans.Lecturer;
import mvc.beans.Student;
import mvc.beans.Subject;

import java.util.List;

public interface DAOSubject {
	public void createSubject(Subject subject);
	public void removeSubject(Subject subject);
	public List<Subject> getSublects();
	public List<Subject> showSubjectsForStudent(Student student);
	public List<Subject> showSubjectsForLecturer(Lecturer lecturer);
}
