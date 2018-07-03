package mvc.dao.daointerfaces;

import mvc.beans.Object;

import java.util.List;

public interface DAOObject {
	public List<Object> getGrops();
	public List<Object> getStudents();
	public List<Object> getLecturers();
	public List<Object> getCathedras();
	public void createObject(Object object);
	public void removeObject(Object object);
	public List<Object> getObjects();
	public Object getParent(Object object);
	public List<Object> getChildObjects(Object object);
}
