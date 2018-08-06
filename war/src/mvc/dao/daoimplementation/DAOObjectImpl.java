package mvc.dao.daoimplementation;

import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;
import mvc.dao.daointerfaces.DAOObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * Class for work with Object object in database
 */
public class DAOObjectImpl implements DAOObject {
public static final String getGroups = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='group' ORDER BY OBJECT_ID";
public static final String getStudents = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='student' ORDER BY OBJECT_ID";
public static final String getLecturers = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='lecturer'ORDER BY OBJECT_ID";
public static final String getCathedras = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='cathedra'ORDER BY OBJECT_ID";
public static final String getFaculties = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='faculty'ORDER BY OBJECT_ID";
public static final String getUniversities = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='university'ORDER BY OBJECT_ID";
public static final String createObject = "INSERT INTO OBJECTS VALUES (OBJECT_SEQUENCE.nextval,?,?,?)";
public static final String removeObject = "DELETE FROM OBJECTS WHERE OBJECT_ID=?";
public static final String getObjects = "SELECT a.*, b.OBJECT_DESCRIPTOIN FROM OBJECTS a, OBJECTS b WHERE a.PARENT_ID=b.OBJECT_ID ORDER BY a.OBJECT_ID";
public static final String getParent = "SELECT * FROM OBJECTS  WHERE OBJECT_ID=(SELECT PARENT_ID FROM OBJECTS  WHERE OBJECT_ID=?) ORDER BY OBJECT_ID";
public static final String getChildObjects = "SELECT * FROM OBJECTS WHERE PARENT_ID=? ORDER BY OBJECT_ID";
public static final String updateObject = "UPDATE OBJECTS SET PARENT_ID=?, OBJECT_DESCRIPTOIN = ? WHERE OBJECT_ID=?";
public static final String showGroupsForSubject = "SELECT * FROM OBJECTS JOIN STUDENT_SUBJECT_LISTS ON (OBJECTS.OBJECT_ID = STUDENT_SUBJECT_LISTS.STUDENT_ID) " +
        "JOIN SUBJECTS ON (SUBJECTS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) WHERE SUBJECTS.SUBJECT_ID=?";

private JdbcTemplate template;

/**
 * set jdbc template
 * @param template template to set
 */
public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

/**
 * get all groups
  * @return all groups
 */
@Override
public List<Object> getGrops() {
    List<Object> objects = template.query(getGroups, new ObjectMapper());
    return objects;
}

/**
 * get all students
 * @return all students
 */
@Override
public List<Object> getStudents() {
    List<Object> objects = template.query(getStudents, new ObjectMapper());
    return objects;
}

/**
 * get all lecturers
 * @return all lecturers
 */
@Override
public List<Object> getLecturers() {
    List<Object> objects = template.query(getLecturers, new ObjectMapper());
    return objects;
}

/**
 * get all cathedras
 * @return all cathedras
 */
@Override
public List<Object> getCathedras() {
    List<Object> objects = template.query(getCathedras, new ObjectMapper());
    return objects;
}

/**
 * get all faculties
 * @return all faculties
 */
@Override
public List<Object> getFaculties() {
    List<Object> objects = template.query(getFaculties, new ObjectMapper());
    return objects;
}

/**
 * get all universities
 * @return all universities
 */
@Override
public List<Object> getUniversities() {
    List<Object> objects = template.query(getUniversities, new ObjectMapper());
    return objects;
}

/**
 * create object in database
 * @param object object to create
 */
@Override
public void createObject(Object object) {
    if(object.getParentId()==0)
        template.update(createObject,object.getDescription(), object.getType(), null);
    else
        template.update(createObject,object.getDescription(), object.getType(), object.getParentId());
    }

/**
 * remove object from database
 * @param object object to remove
 */
@Override
public void removeObject(Object object) {
        template.update(removeObject, object.getId());
    }

/**
 * get all objects
 * @return all objects
 */
@Override
public List<Object> getObjects() {
        List<Object> objects = template.query(getObjects, new ObjectMapper());
        return objects;
    }

/**
 * get parent object for object
 * @param object object to get parent for
 * @return parent object
 */
@Override
public Object getParent(Object object) {
        List<Object> objects = template.query(getParent, new ObjectMapper(),object.getId());
        return objects.get(0);
    }

/**
 * get child objects for object
 * @param object object to get childs for
 * @return shild object
 */
@Override
public List<Object> getChildObjects(Object object) {
        List<Object> objects = template.query(getChildObjects, new ObjectMapper(),object.getId());
        return objects;
    }

/**
 * update object
 * @param object object to update
 */
@Override
public void updateObject(Object object) {
    template.update(updateObject,object.getParentId(),object.getDescription(), object.getId());
}

/**
 * show all groups on subject
 * @param subject subject to get groups for
 * @return all groups on subject
 */
@Override
public List<Object> showGroupsForSubject(Subject subject) {
        List<Object> students = template.query(showGroupsForSubject, new ObjectMapper(),subject.getId());
        List<Object> groups = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (Object obj: students) {
            if(!numbers.contains(obj.getParentId())) {
                numbers.add(obj.getParentId());
                Object group = getParent(obj);
                groups.add(group);
            }
        }
        return groups;
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
