package mvc.dao.daointerfaces;

import mvc.beans.Login;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;

import java.util.List;

public interface DAOStudent {
/**
 * create student in database
 * @param student student to create
 * @param object object to create
 */
	public void createStudent(Student student, Object object);
/**
 * remove student from database
 * @param student student to remove
 */
	public void removeStudent(Student student);
/**
 * get all students
 * @return all students
 */
	public List<Student> getStudents();
/**
 * get student with specific login and password
 * @param login bean which contain login and password
 * @return student with login and password
 */
	public Student validateStudent(Login login);
/**
 * register student for subject
 * @param student student to register
 * @param subject subject to register on
 */
	public void registerStudentForSubject(Student student, Subject subject);
/**
 * get all student on subject
 * @param subject subjects to get students on
 * @return all students on subject
 */
	public List<Student> getStudentsOnSubject(Subject subject);
/**
 * update student in database
 * @param student student to update
 */
	public void updateStudent(Student student);
/**
 * remove students from subject
 * @param subject subject from which remove student
 * @param student student to remove from subject
 */
	public void removeStudentFromSubject(Subject subject, Student student);
/**
 * get all students in group
 * @param object group to get students in
 * @return all students in group
 */
	public List<Student> getStudentsForGroup(Object object);
/**
 * make student a starosta in group
 * @param student student to make a starosta
 */
	public void setStarosta(Student student);
/**
 * get starosta for student by specific id of student
 * @param id id of student
 * @return starosta for student with id
 */
	public Student showStarostaForStudent(int id);
/**
 * get student by specific id
 * @param id id of student to get
 * @return student with id
 */
	public Student getStudentById(int id);
}
