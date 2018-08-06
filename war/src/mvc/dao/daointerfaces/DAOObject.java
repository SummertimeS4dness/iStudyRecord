package mvc.dao.daointerfaces;

import mvc.beans.Login;
import mvc.beans.Object;
import mvc.beans.Subject;

import java.util.List;

public interface DAOObject {
/**
 * get all groups
 * @return all groups
 */
	public List<Object> getGrops();
/**
 * get all faculties
 * @return all faculties
 */
	public List<Object> getFaculties();
/**
 * get all universities
 * @return all universities
 */
	public List<Object> getUniversities();
/**
 * get all students
 * @return all students
 */
	public List<Object> getStudents();
/**
 * get all lecturers
 * @return all lecturers
 */
	public List<Object> getLecturers();
/**
 * get all cathedras
 * @return all cathedras
 */
	public List<Object> getCathedras();
/**
 * create object in database
 * @param object object to create
 */
	public void createObject(Object object);
/**
 * remove object from database
 * @param object object to remove
 */
	public void removeObject(Object object);
/**
 * get all objects
 * @return all objects
 */
	public List<Object> getObjects();
/**
 * get parent object for object
 * @param object object to get parent for
 * @return parent object
 */
	public Object getParent(Object object);
/**
 * get child objects for object
 * @param object object to get childs for
 * @return shild object
 */
	public List<Object> getChildObjects(Object object);
/**
 * update object
 * @param object object to update
 */
	public void updateObject(Object object);
/**
 * show all groups on subject
 * @param subject subject to get groups for
 * @return all groups on subject
 */
	public List<Object> showGroupsForSubject(Subject subject);

/**
 * get admin by specific login and password
 * @param login bean which contain login and password
 * @return object of admin
 */
public Object validateAdmin(Login login);
}
