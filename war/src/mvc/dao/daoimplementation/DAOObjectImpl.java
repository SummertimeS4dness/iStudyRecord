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

public class DAOObjectImpl implements DAOObject {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

@Override
public List<Object> getGrops() {
    String sql = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='group' ORDER BY OBJECT_ID";
    List<Object> objects = template.query(sql, new ObjectMapper());
    return objects;
}
@Override
public List<Object> getStudents() {
    String sql = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='student' ORDER BY OBJECT_ID";
    List<Object> objects = template.query(sql, new ObjectMapper());
    return objects;
}
@Override
public List<Object> getLecturers() {
    String sql = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='lecturer'ORDER BY OBJECT_ID";
    List<Object> objects = template.query(sql, new ObjectMapper());
    return objects;
}
@Override
public List<Object> getCathedras() {
    String sql = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='cathedra'ORDER BY OBJECT_ID";
    List<Object> objects = template.query(sql, new ObjectMapper());
    return objects;
}
@Override
public List<Object> getFaculties() {
    String sql = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='faculty'ORDER BY OBJECT_ID";
    List<Object> objects = template.query(sql, new ObjectMapper());
    return objects;
}
@Override
public List<Object> getUniversities() {
    String sql = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='university'ORDER BY OBJECT_ID";
    List<Object> objects = template.query(sql, new ObjectMapper());
    return objects;
}
@Override
    public void createObject(Object object) {
        String sql = "INSERT INTO OBJECTS VALUES (OBJECT_SEQUENCE.nextval,?,?,?)";
    if(object.getParentId()==0)
        template.update(sql,object.getDescription(), object.getType(), null);
    else
        template.update(sql,object.getDescription(), object.getType(), object.getParentId());
    }

    @Override
    public void removeObject(Object object) {
        String sql = "DELETE FROM OBJECTS WHERE OBJECT_ID=?";
        template.update(sql, object.getId());
    }

    @Override
    public List<Object> getObjects() {
        String sql = "SELECT * FROM OBJECTS ORDER BY OBJECT_ID";
        List<Object> objects = template.query(sql, new ObjectMapper());
        return objects;
    }

    @Override
    public Object getParent(Object object) {
        String sql = "SELECT * FROM OBJECTS  WHERE OBJECT_ID=(SELECT PARENT_ID FROM OBJECTS  WHERE OBJECT_ID="+object.getId()+") ORDER BY OBJECT_ID";
        List<Object> objects = template.query(sql, new ObjectMapper());
        return objects.get(0);
    }

    @Override
    public List<Object> getChildObjects(Object object) {
        String sql = "SELECT * FROM OBJECTS WHERE PARENT_ID=" + object.getId()+" ORDER BY OBJECT_ID";
        List<Object> objects = template.query(sql, new ObjectMapper());
        return objects;
    }

    @Override
    public void updateObject(Object object) {
    String sql = "UPDATE OBJECTS SET PARENT_ID=?, OBJECT_DESCRIPTOIN = ? WHERE OBJECT_ID=?";
    template.update(sql,object.getParentId(),object.getDescription(), object.getId());
}
    @Override
    public List<Object> showGroupsForSubject(Subject subject) {
        String sql = "SELECT * FROM OBJECTS JOIN STUDENT_SUBJECT_LISTS ON (OBJECTS.OBJECT_ID = STUDENT_SUBJECT_LISTS.STUDENT_ID) " +
                "JOIN SUBJECTS ON (SUBJECTS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) WHERE SUBJECTS.SUBJECT_ID=" + subject.getId();
        List<Object> students = template.query(sql, new ObjectMapper());
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
            Object object = new Object(id, description, type, parentId);
            return object;
        }
    }
}
