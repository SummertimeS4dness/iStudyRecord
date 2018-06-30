package mvc.dao.daoimplementation;

import mvc.beans.Object;
import mvc.dao.daointerfaces.DAOObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOObjectImpl implements DAOObject {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void createObject(Object object) {
        String sql = "INSERT INTO OBJECTS VALUES (OBJECT_SEQUENCE.nextval,?,?,?)";
        template.update(sql, object.getId(), object.getDescription(), object.getType(), object.getParentId());
    }

    @Override
    public void removeObject(Object object) {
        String sql = "DELETE FROM OBJECTS WHERE OBJECT_ID=?";
        template.update(sql, object.getId());
    }

    @Override
    public List<Object> getObjects() {
        String sql = "SELECT * FROM OBJECTS";
        List<Object> objects = template.query(sql, new ObjectMapper());
        return objects;
    }

    @Override
    public Object getParent(Object object) {
        String sql = "SELECT * FROM OBJECTS WHERE OBJECT_ID=" + object.getParentId();
        List<Object> objects = template.query(sql, new ObjectMapper());
        return objects.get(0);
    }

    @Override
    public List<Object> getChildObjects(Object object) {
        String sql = "SELECT * FROM OBJECTS WHERE PARENT_ID=" + object.getId();
        List<Object> objects = template.query(sql, new ObjectMapper());
        return objects;
    }

    class ObjectMapper implements RowMapper<Object> {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            int id = rs.getInt("object_id");
            String description = rs.getString("object_description");
            String type = rs.getString("object_type");
            int parentId = rs.getInt("parent_id");
            Object object = new Object(id, description, type, parentId);
            return object;
        }
    }
}
