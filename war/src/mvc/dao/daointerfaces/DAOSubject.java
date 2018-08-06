package mvc.dao.daointerfaces;

import mvc.beans.Lecturer;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;

import java.util.List;

public interface DAOSubject {
/**
 * create subject in database
 * @param subject subject to create
 */
	public void createSubject(Subject subject);
/**
 * remove subject from database
 * @param subject subject to remove
 */
	public void removeSubject(Subject subject);
/**
 * get all subjects
 * @return all subjects
 */
	public List<Subject> getSubjects();
/**
 * get all subjects for student
 * @param student students to get subject for
 * @return all subjects for student
 */
	public List<Subject> showSubjectsForStudent(Student student);
/**
 * get all subjects for lecturer
 * @param lecturer lecturer to get subject for
 * @return all subjects for lecturer
 */
	public List<Subject> showSubjectsForLecturer(Lecturer lecturer);
/**
 * get all subjects for group
 * @param object group to get subjects for
 * @return
 */
	public List<Subject> showSubjectsForGroup(Object object);
/**
 * update subject in database
 * @param subject subject to update
 */
	public void updateSubject(Subject subject);
/**
 * get subject by specific id
 * @param id id for subject to get
 * @return subject with id
 */
	public String getSubjectById(int id);
}
