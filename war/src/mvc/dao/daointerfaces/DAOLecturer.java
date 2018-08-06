package mvc.dao.daointerfaces;

import mvc.beans.*;
import mvc.beans.Object;

import java.util.List;
/**
 * Class for work with Lecturer object in database
 */
public interface DAOLecturer {
/**
 * create lecturer and object in database
 * @param lecturer lecturer to create
 * @param object object to create
 */
	public void createLecturer(Lecturer lecturer, Object object);
/**
 * remove student from database
 * @param lecturer lecturer to remove
 */
	public void removeLecturer(Lecturer lecturer);
/**
 * get lecturer on subject
 * @param subject subject to get lecturer on
 * @return lecturer on subject
 */
	public Lecturer getLecturerForSubject(Subject subject);
/**
 * get lecturer which putted mark
 * @param mark mark which lecturer putted
 * @return lecturer which putted mark
 */
	public Lecturer getLecturerForMark(Mark mark);
/**
 * get lecturer for the lesson
 * @param lesson lesson which lecturer do
 * @return lecturer which do lesson
 */
	public Lecturer getLecturerForLesson(Lesson lesson);
/**
 * get all lecturers for student
 * @param student student for which we take all lecturers
 * @return all lecturers for student
 */
	public List<Lecturer> getLecturersForStudent(Student student);
/**
 * get all lecturers
 * @return all lecturers
 */
	public List<Lecturer> getLecturers();
/**
 * get lecturer with specific login and password
 * @param login bean which contain login and password
 * @return lecturer with login and password
 */
	public Lecturer validateLecturer(Login login);
/**
 * update lecturer in database
 * @param lecturer lecturer to update
 */
	public void updateLecturer(Lecturer lecturer);
/**
 * get all lecturers on subject
 * @param subject subject to get lecturers on
 * @return all lecturers on subject
 */
	public List<Lecturer> getLecturersForSubject(Subject subject);
/**
 * get lecturer with specific id
 * @param id id for lecturer
 * @return lecturer with id
 */
	public Lecturer getLecturerById(int id);
}
