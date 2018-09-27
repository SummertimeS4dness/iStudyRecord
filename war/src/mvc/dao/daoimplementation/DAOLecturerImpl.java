package mvc.dao.daoimplementation;

import mvc.beans.*;
import mvc.beans.Object;
import mvc.dao.daointerfaces.DAOLecturer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static mvc.dao.daoimplementation.SQL_STRINGS.*;

/**
 * Class for work with Lecturer object in database
 */
public class DAOLecturerImpl implements DAOLecturer {
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
     * create lecturer and object in database
     *
     * @param lecturer lecturer to create
     * @param object   object to create
     */
    @Override
    public void createLecturer(Lecturer lecturer, Object object) {
        int id = template.query(selectID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        }).get(0);
        int parentId = object.getParentId();
        if (parentId == 0)
            template.update(objectInsert, id, object.getDescription(), object.getType(), null, null, null);
        else
            template.update(objectInsert, id, object.getDescription(), object.getType(), object.getParentId(), null, null);
        template.update(createLecturer, id, lecturer.getLogin(), lecturer.getPassword(), lecturer.getName()
                , lecturer.getInfo(), lecturer.getDegree(), lecturer.getWorks(), lecturer.getInterests());


    }

    /**
     * remove student from database
     *
     * @param lecturer lecturer to remove
     */
    @Override
    public void removeLecturer(Lecturer lecturer) {
        template.update(removeLecturer, lecturer.getId());
    }

    /**
     * get lecturer on subject
     *
     * @param subject subject to get lecturer on
     * @return lecturer on subject
     */
    @Override
    public Lecturer getLecturerForSubject(Subject subject) {
        List<Lecturer> lecturers = template.query(getLecturerForSubject, new LecturerMapper(), subject.getId());
        return lecturers.get(0);
    }

    /**
     * get all lecturers on subject
     *
     * @param subject subject to get lecturers on
     * @return all lecturers on subject
     */
    @Override
    public List<Lecturer> getLecturersForSubject(Subject subject) {
        List<Lecturer> lecturers = template.query(getGetLecturersForSubject, new LecturerMapper(), subject.getId());
        return lecturers;
    }

    /**
     * get lecturer which putted mark
     *
     * @param mark mark which lecturer putted
     * @return lecturer which putted mark
     */
    @Override
    public Lecturer getLecturerForMark(Mark mark) {
        List<Lecturer> lecturers = template.query(getLecturerForMark, new LecturerMapper(), mark.getId());
        return lecturers.get(0);
    }

    /**
     * get lecturer for the lesson
     *
     * @param lesson lesson which lecturer do
     * @return lecturer which do lesson
     */
    @Override
    public Lecturer getLecturerForLesson(Lesson lesson) {
        List<Lecturer> lecturers = template.query(getLecturerForLesson, new LecturerMapper(), lesson.getLessonId());
        return lecturers.get(0);
    }

    /**
     * get all lecturers for student
     *
     * @param student student for which we take all lecturers
     * @return all lecturers for student
     */
    @Override
    public List<Lecturer> getLecturersForStudent(Student student) {
        List<Lecturer> lecturers = template.query(getLecturersForStudent, new LecturerMapper(), student.getId());
        return lecturers;
    }

    /**
     * get all lecturers
     *
     * @return all lecturers
     */
    @Override
    public List<Lecturer> getLecturers() {
        List<Lecturer> lecturers = template.query(getlecturers, new LecturerMapper());
        return lecturers;
    }

    /**
     * get lecturer with specific id
     *
     * @param id id for lecturer
     * @return lecturer with id
     */
    @Override
    public Lecturer getLecturerById(int id) {
        List<Lecturer> lecturer = template.query(getLecturerById, new LecturerMapper(), Integer.toString(id));
        return lecturer.get(0);
    }


    class LecturerMapper implements RowMapper<Lecturer> {
        public Lecturer mapRow(ResultSet rs, int arg1) throws SQLException {
            int id = rs.getInt("lecturer_id");
            String login = rs.getString("lecturer_login");
            String password = rs.getString("lecturer_password");
            String name = rs.getString("lecturer_name");
            String info = rs.getString("lecturer_info");
            String degree = rs.getString("lecturer_degree");
            String works = rs.getString("lecturer_works");
            String interests = rs.getString("lecturer_interests");
            Lecturer lecturer = new Lecturer(id, login, password, name, info, degree, works, interests);
            String sql = "SELECT * FROM OBJECTS WHERE OBJECT_ID=(SELECT PARENT_ID FROM OBJECTS WHERE OBJECT_ID=" + rs.getInt("lecturer_id") + ")ORDER BY OBJECT_ID";
            List<Object> objects = template.query(sql, new DAOObjectImpl.ObjectMapper());
            lecturer.setCathedra(objects.get(0).getDescription());
            lecturer.setCathedraId(objects.get(0).getId());
            return lecturer;
        }
    }

    /**
     * get lecturer with specific login and password
     *
     * @param login bean which contain login and password
     * @return lecturer with login and password
     */
    @Override
    public Lecturer validateLecturer(Login login) {
        List<Lecturer> list = template.query(validateLecturer, new LecturerMapper(), login.getNickname(), login.getPassword());
        Lecturer lecturer = null;
        if (list.size() != 0) {
            lecturer = list.get(0);
        }
        return lecturer;
    }

    /**
     * update lecturer in database
     *
     * @param lecturer lecturer to update
     */
    @Override
    public void updateLecturer(Lecturer lecturer) {
        template.update(updateLecturer, lecturer.getLogin(), lecturer.getPassword(), lecturer.getName(), lecturer.getInfo(), lecturer.getDegree(), lecturer.getWorks(), lecturer.getInterests(), lecturer.getId());
    }
}