package mvc.dao.daoimplementation;

import mvc.beans.Lecturer;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;
import mvc.dao.daointerfaces.DAOSubject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static mvc.dao.daoimplementation.SQL_STRINGS.*;


/**
 * Class for work with Subject object in database
 */
public class DAOSubjectImpl implements DAOSubject {

private JdbcTemplate template;

/**
 * set jdbc template
 * @param template template to set
 */
public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

/**
 * create subject in database
 * @param subject subject to create
 */
@Override
public void createSubject(Subject subject) {
        template.update(createSubject,  subject.getShortName(), subject.getFullName(), subject.getInfo(), subject.getLecturerId());
    }

/**
 * remove subject from database
 * @param subject subject to remove
 */
@Override
public void removeSubject(Subject subject) {
        template.update(removeSubject, subject.getId());
    }

/**
 * get all subjects
 * @return all subjects
 */
@Override
public List<Subject> getSubjects() {
        List<Subject> subjects = template.query(getSubjects, new SubjecttMapper());
        return subjects;
    }

/**
 * get all subjects for student
 * @param student students to get subject for
 * @return all subjects for student
 */
@Override
public List<Subject> showSubjectsForStudent(Student student) {
        List<Subject> subjects = template.query(showSubjectsForStudent, new SubjecttMapper(),student.getId());
        return subjects;
    }

/**
 * get all subjects for lecturer
 * @param lecturer lecturer to get subject for
 * @return all subjects for lecturer
 */
@Override
public List<Subject> showSubjectsForLecturer(Lecturer lecturer) {
        List<Subject> subjects = template.query(showSubjectFroLecturer, new SubjecttMapper(), lecturer.getId());
        return subjects;
    }

/**
 * get all subjects for group
 * @param object group to get subjects for
 * @return
 */
@Override
public List<Subject> showSubjectsForGroup(Object object) {
        List<Subject> subjects = template.query(showSubjectsForGroup, new SubjecttMapper(),object.getId());
        return subjects;
    }

/**
 * get subject by specific id
 * @param id id for subject to get
 * @return subject with id
 */
@Override
public String getSubjectById(int id) {
        List<Subject> subject = template.query(getSubjectById, new SubjecttMapper(), id);
        return subject.get(0).getFullName();
    }

class SubjecttMapper implements RowMapper<Subject> {
        public static final String getStudentsAmount = "SELECT COUNT(STUDENT_ID) FROM STUDENT_SUBJECT_LISTS WHERE SUBJECT_ID = ? GROUP BY SUBJECT_ID";
    
        public Subject mapRow(ResultSet rs, int arg1) throws SQLException {
            Subject subject = new Subject();
            subject.setId(rs.getInt("subject_id"));
            subject.setLecturerId(rs.getInt("lecturer_id"));
            subject.setShortName(rs.getString("subject_short_name"));
            subject.setFullName(rs.getString("subject_full_name"));
            subject.setInfo(rs.getString("subject_info"));
            String lecturer = template.query(getLecturerName, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("lecturer_name");
                }
            },subject.getLecturerId()).get(0);
            List<Integer> am = template.query(getStudentsAmount,new RowMapper<Integer>(){

                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(1);
                }
            },subject.getId());
            subject.setLecturerName(lecturer);
            if(am.size()>0)
            subject.setAmount(am.get(0));
            return subject;
        }
    }

/**
 * update subject in database
 * @param subject subject to update
 */
@Override
public void updateSubject(Subject subject) {
    template.update(updateSubject,subject.getShortName(),subject.getFullName(),subject.getInfo(),subject.getLecturerId(),subject.getId());
}
}
