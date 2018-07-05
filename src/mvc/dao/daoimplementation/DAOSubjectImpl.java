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
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void createSubject(Subject subject) {
        System.out.println(subject.getLecturerId());
        String sql = "INSERT INTO SUBJECTS VALUES (SUBJECT_SEQUENCE.nextval,?,?,?,?)";
        template.update(sql,  subject.getShortName(), subject.getFullName(), subject.getInfo(), subject.getLecturerId());
    }

    @Override
    public void removeSubject(Subject subject) {
        String sql = "DELETE FROM SUBJECTS WHERE SUBJECT_ID=?";
        template.update(sql, subject.getId());
    }

    @Override
    public List<Subject> getSubjects() {
        String sql = "SELECT * FROM SUBJECTS";
        List<Subject> subjects = template.query(sql, new SubjecttMapper());
        return subjects;
    }

    @Override
    public List<Subject> showSubjectsForStudent(Student student) {
        String sql = "SELECT * FROM SUBJECTS JOIN STUDENT_SUBJECT_LISTS " +
                "ON (SUBJECTS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) WHERE STUDENT_ID=" + student.getId();
        List<Subject> subjects = template.query(sql, new SubjecttMapper());
        return subjects;
    }

    @Override
    public List<Subject> showSubjectsForLecturer(Lecturer lecturer) {
        String sql = "SELECT * FROM SUBJECTS WHERE LECTURER_ID=" + lecturer.getId();
        List<Subject> subjects = template.query(sql, new SubjecttMapper());
        return subjects;
    }
    //HFDKJSHFJKDSHFK
    @Override
    public List<Subject> showSubjectsForGroup(Object object) {
        String sql = "SELECT * FROM SUBJECTS JOIN STUDENT_SUBJECT_LISTS ON (SUBJECTS.SUBJECT_ID = STUDENT_SUBJECT_LISTS.SUBJECT_ID) " +
                "JOIN OBJECTS ON (STUDENT_SUBJECT_LISTS.STUDENT_ID=OBJECTS.OBJECT_ID) WHERE PARENT_ID=" + object.getId();
        List<Subject> subjects = template.query(sql, new SubjecttMapper());
        return subjects;
    }

    class SubjecttMapper implements RowMapper<Subject> {
        public Subject mapRow(ResultSet rs, int arg1) throws SQLException {
            Subject subject = new Subject();
            subject.setId(rs.getInt("subject_id"));
            subject.setLecturerId(rs.getInt("lecturer_id"));
            subject.setShortName(rs.getString("subject_short_name"));
            subject.setFullName(rs.getString("subject_full_name"));
            subject.setInfo(rs.getString("subject_info"));
            String sql = "SELECT LECTURER_NAME FROM LECTURERS WHERE LECTURER_ID="+subject.getLecturerId();
            String lecturer = template.query(sql, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("lecturer_name");
                }
            }).get(0);
            sql="SELECT COUNT(STUDENT_ID) FROM STUDENT_SUBJECT_LISTS WHERE SUBJECT_ID = "+subject.getId()+" GROUP BY SUBJECT_ID";
            List<Integer> am = template.query(sql,new RowMapper<Integer>(){

                @Override
                public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(1);
                }
            });
            subject.setLecturerName(lecturer);
            if(am.size()>0)
            subject.setAmount(am.get(0));
            return subject;
        }
    }
@Override
public void updateSubject(Subject subject) {
    String sql = "UPDATE SUBJECTS SET SUBJECT_SHORT_NAME=?,SUBJECT_FULL_NAME=?,SUBJECT_INFO =?,LECTURER_ID=? WHERE SUBJECT_ID=?";
    template.update(sql,subject.getShortName(),subject.getFullName(),subject.getInfo(),subject.getLecturerId(),subject.getId());
}
}
