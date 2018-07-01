package mvc.dao.daointerfaces;

import mvc.beans.Login;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;

import java.util.List;

public interface DAOStudent {
	public void createStudent(Student student, Object object);
	public void removeStudent(Student student);
	public List<Student> getStudents();
	public Student validateStudent(Login login);
	public void registerStudentForSubject(Student student,Subject subject);
	public List<Student> getStudentsOnSubject(Subject subject);
	public void updateStudent(Student student);
}
