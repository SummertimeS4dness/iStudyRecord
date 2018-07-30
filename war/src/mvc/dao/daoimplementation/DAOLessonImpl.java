package mvc.dao.daoimplementation;

import mvc.beans.Lecturer;
import mvc.beans.Lesson;
import mvc.beans.Student;
import mvc.beans.Subject;
import mvc.dao.daointerfaces.DAOLesson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOLessonImpl implements DAOLesson {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void addLesson(Lesson lesson) {
        String sql = "INSERT INTO LESSONS VALUES (LESSON_SEQUENCE.nextval,TO_DATE(TO_CHAR(?),'YYYY-MM-DD HH24:MI'),?,?)";
        template.update(sql, lesson.getStringDate(), lesson.getSubjectId(), lesson.getLecturerId());
    }

    @Override
    public void removeLesson(Lesson lesson) {
        String sql = "DELETE FROM LESSONS WHERE LESSON_ID=?";
        template.update(sql, lesson.getLessonId());
    }

    @Override
    public List<Lesson> allLessons() {
        String sql = "SELECT * FROM LESSONS";
        List<Lesson> lessons = template.query(sql, new LessonMapper());
        return lessons;
    }

    @Override
    public List<Lesson> getLessonForStudent(Student student) {
        String sql = "SELECT * FROM LESSONS JOIN STUDENT_SUBJECT_LISTS ON" +
                " (LESSONS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) JOIN SUBJECTS ON (STUDENT_SUBJECT_LISTS.SUBJECT_ID=SUBJECTS.SUBJECT_ID)" +
                " JOIN LECTURERS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID) WHERE STUDENT_ID=" + student.getId();
        List<Lesson> lessons = template.query(sql, new LessonMapper1());
        return lessons;
    }

    @Override
    public List<Lesson> getLessonForLecturer(Lecturer lecturer) {
        String sql = "SELECT * FROM LESSONS WHERE LESSONS.LECTURER_ID=" + lecturer.getId();
        List<Lesson> lessons = template.query(sql, new LessonMapper2());
        /*List<Lesson> toReturn = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (Lesson les: lessons) {
            if(!numbers.contains(les.getLessonId())) {
                numbers.add(les.getLessonId());
                toReturn.add(les);
            }
        }*/
        return lessons;
    }

    @Override
    public List<Lesson> getLessonForSubject(Subject subject) {
        String sql = "SELECT * FROM LESSONS JOIN STUDENT_SUBJECT_LISTS ON" +
                " (LESSONS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) WHERE LESSONS.SUBJECT_ID=" + subject.getId();
        List<Lesson> lessons = template.query(sql, new LessonMapper());
        return lessons;
    }

    class LessonMapper implements RowMapper<Lesson> {
        public Lesson mapRow(ResultSet rs, int arg1) throws SQLException {
            int lessonId = rs.getInt("lesson_id");
            String lessonDate = rs.getString("lesson_date");
            int subjectId = rs.getInt("subject_id");
            int lecturerId = rs.getInt("lecturer_id");
            Lesson lesson = new Lesson(lessonDate, subjectId, lecturerId, lessonId);
            return lesson;
        }
    }
    class LessonMapper1 implements RowMapper<Lesson> {
        public Lesson mapRow(ResultSet rs, int arg1) throws SQLException {
            int lessonId = rs.getInt("lesson_id");
            String lessonDate = rs.getString("lesson_date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsed = null;
            try {
                parsed = format.parse(lessonDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int subjectId = rs.getInt("subject_id");
            int lecturerId = rs.getInt("lecturer_id");
            String subject = rs.getString("subject_full_name");
            String lecturer = rs.getString("lecturer_name");
            Lesson lesson = new Lesson(parsed, subjectId, lecturerId, lessonId, subject, lecturer, lessonDate);
            return lesson;
        }
    }
    class LessonMapper2 implements RowMapper<Lesson> {
        public Lesson mapRow(ResultSet rs, int arg1) throws SQLException {
            String lessonDate = rs.getString("lesson_date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parsed = null;
            try {
                parsed = format.parse(lessonDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int id = rs.getInt("subject_id");
            Lesson lesson = new Lesson();
            lesson.setDate(parsed);
            lesson.setStringDate(lessonDate);
            lesson.setSubjectId(id);
            return lesson;
        }
    }

}
