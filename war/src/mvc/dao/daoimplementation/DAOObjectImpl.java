package mvc.dao.daoimplementation;

import mvc.beans.Login;
import mvc.beans.Object;
import mvc.beans.Subject;
import mvc.dao.daointerfaces.DAOObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static mvc.dao.daoimplementation.SQL_STRINGS.*;

/**
 * Class for work with Object object in database
 */
public class DAOObjectImpl implements DAOObject {


    private JdbcTemplate template;

    /**
     * set jdbc template
     *
     * @param template template to set
     */
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * get all groups
     *
     * @return all groups
     */
    @Override
    public List<Object> getGrops() {
        List<Object> objects = template.query(getGroups, new ObjectMapper());
        return objects;
    }

    /**
     * get all students
     *
     * @return all students
     */
    @Override
    public List<Object> getStudents() {
        List<Object> objects = template.query(getStudentsObjects, new ObjectMapper());
        return objects;
    }

    /**
     * get all lecturers
     *
     * @return all lecturers
     */
    @Override
    public List<Object> getLecturers() {
        List<Object> objects = template.query(getLecturers, new ObjectMapper());
        return objects;
    }

    /**
     * get all cathedras
     *
     * @return all cathedras
     */
    @Override
    public List<Object> getCathedras() {
        List<Object> objects = template.query(getCathedras, new ObjectMapper());
        return objects;
    }

    /**
     * get all faculties
     *
     * @return all faculties
     */
    @Override
    public List<Object> getFaculties() {
        List<Object> objects = template.query(getFaculties, new ObjectMapper());
        return objects;
    }

    /**
     * get all universities
     *
     * @return all universities
     */
    @Override
    public List<Object> getUniversities() {
        List<Object> objects = template.query(getUniversities, new ObjectMapper());
        return objects;
    }

    /**
     * create object in database
     *
     * @param object object to create
     */
    @Override
    public void createObject(Object object) {
        if (object.getParentId() == 0)
            template.update(createObject, object.getDescription(), object.getType(), null, null, null);
        else
            template.update(createObject, object.getDescription(), object.getType(), object.getParentId(), null, null);
    }

    /**
     * remove object from database
     *
     * @param object object to remove
     */
    @Override
    public void removeObject(Object object) {
        template.update(removeObject, object.getId());
    }

    /**
     * get all objects
     *
     * @return all objects
     */
    @Override
    public List<Object> getObjects() {
        List<Object> objects = template.query(getObjects, new ObjectMapper());
        return objects;
    }

    /**
     * get parent object for object
     *
     * @param object object to get parent for
     * @return parent object
     */
    @Override
    public Object getParent(Object object) {
        List<Object> objects = template.query(getParent, new ObjectMapper(), object.getId());
        return objects.get(0);
    }

    /**
     * get child objects for object
     *
     * @param object object to get childs for
     * @return shild object
     */
    @Override
    public List<Object> getChildObjects(Object object) {
        List<Object> objects = template.query(getChildObjects, new ObjectMapper(), object.getId());
        return objects;
    }

    /**
     * update object
     *
     * @param object object to update
     */
    @Override
    public void updateObject(Object object) {
        template.update(updateObject, object.getParentId(), object.getDescription(), object.getId());
    }

    /**
     * show all groups on subject
     *
     * @param subject subject to get groups for
     * @return all groups on subject
     */
    @Override
    public List<Object> showGroupsForSubject(Subject subject) {
        List<Object> students = template.query(showGroupsForSubject, new ObjectMapper(), subject.getId());
        List<Object> groups = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (Object obj : students) {
            if (!numbers.contains(obj.getParentId())) {
                numbers.add(obj.getParentId());
                Object group = getParent(obj);
                groups.add(group);
            }
        }
        return groups;
    }

    /**
     * get admin by specific login and password
     *
     * @param login bean which contain login and password
     * @return object of admin
     */
    @Override
    public Object validateAdmin(Login login) {
        List<Object> list = template.query(validateAdmin, new ObjectMapper(), login.getNickname(), login.getPassword());
        Object object = null;
        if (list.size() != 0) {
            object = list.get(0);
        }
        return object;
    }

    static class ObjectMapper implements RowMapper<Object> {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            int id = rs.getInt("object_id");
            String description = rs.getString("object_descriptoin");
            String type = rs.getString("object_type");
            int parentId = rs.getInt("parent_id");
            String parentDescr = rs.getString("object_descriptoin");
            Object object = new Object(id, description, type, parentId);
            object.setParentDescription(parentDescr);
            return object;
        }
    }
}
