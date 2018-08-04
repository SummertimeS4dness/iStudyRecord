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

public class DAOSubjectImpl implements DAOSubject {
public static final String createSubject = "INSERT INTO SUBJECTS VALUES (SUBJECT_SEQUENCE.nextval,?,?,?,?)";
public static final String removeSubject = "DELETE FROM SUBJECTS WHERE SUBJECT_ID=?";
public static final String getSubjects = "SELECT * FROM SUBJECTS";
public static final String showSubjectsForStudent = "SELECT * FROM SUBJECTS JOIN STUDENT_SUBJECT_LISTS " +
        "ON (SUBJECTS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) WHERE STUDENT_ID=?";
public static final String showSubjectFroLecturer = "SELECT * FROM SUBJECTS WHERE LECTURER_ID=?";
public static final String showSubjectsForGroup = "SELECT * FROM SUBJECTS JOIN STUDENT_SUBJECT_LISTS ON (SUBJECTS.SUBJECT_ID = STUDENT_SUBJECT_LISTS.SUBJECT_ID) " +
        "JOIN OBJECTS ON (STUDENT_SUBJECT_LISTS.STUDENT_ID=OBJECTS.OBJECT_ID) WHERE PARENT_ID=?";
public static final String getSubjectById = "SELECT * FROM SUBJECTS WHERE SUBJECT_ID=?";
public static final String getLecturerName = "SELECT LECTURER_NAME FROM LECTURERS WHERE LECTURER_ID=?";
public static final String updateStudent = "UPDATE SUBJECTS SET SUBJECT_SHORT_NAME=?,SUBJECT_FULL_NAME=?,SUBJECT_INFO =?,LECTURER_ID=? WHERE SUBJECT_ID=?";
private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void createSubject(Subject subject) {
        template.update(createSubject,  subject.getShortName(), subject.getFullName(), subject.getInfo(), subject.getLecturerId());
    }

    @Override
    public void removeSubject(Subject subject) {
        template.update(removeSubject, subject.getId());
    }

    @Override
    public List<Subject> getSubjects() {
        List<Subject> subjects = template.query(getSubjects, new SubjecttMapper());
        return subjects;
    }

    @Override
    public List<Subject> showSubjectsForStudent(Student student) {
        List<Subject> subjects = template.query(showSubjectsForStudent, new SubjecttMapper(),student.getId());
        return subjects;
    }

    @Override
    public List<Subject> showSubjectsForLecturer(Lecturer lecturer) {
        List<Subject> subjects = template.query(showSubjectFroLecturer, new SubjecttMapper(), lecturer.getId());
        return subjects;
    }
    @Override
    public List<Subject> showSubjectsForGroup(Object object) {
        List<Subject> subjects = template.query(showSubjectsForGroup, new SubjecttMapper(),object.getId());
        return subjects;
    }

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
@Override
public void updateSubject(Subject subject) {
    template.update(updateStudent,subject.getShortName(),subject.getFullName(),subject.getInfo(),subject.getLecturerId(),subject.getId());
}
}
