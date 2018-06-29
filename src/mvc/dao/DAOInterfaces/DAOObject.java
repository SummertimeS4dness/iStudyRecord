package mvc.dao.DAOInterfaces;

import mvc.beans.Object;

import java.util.List;

public interface DAOObject {
	public void createObject(Object object);
	public void removeObject(Object object);
	public List<Object> getObjects();
	public Object getParent(Object object);
	public List<Object> getChildObjects(Object object);
}
