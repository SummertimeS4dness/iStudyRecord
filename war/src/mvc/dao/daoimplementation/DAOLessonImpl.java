package mvc.dao.daoimplementation;

import mvc.beans.Lecturer;
import mvc.beans.Lesson;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;
import mvc.dao.daointerfaces.DAOLesson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;

/**
 * Class for work with Lesson object in database
 */
public class DAOLessonImpl implements DAOLesson {
private final static Logger logger = Logger.getLogger(DAOLessonImpl.class);

public static final String updateLessonDate = "UPDATE LESSONS SET LESSON_DATE =TO_DATE(TO_CHAR(?),'YYYY-MM-DD HH24:MI') WHERE LESSON_ID=?";
public static final String addLesson = "INSERT INTO LESSONS VALUES (LESSON_SEQUENCE.nextval,TO_DATE(TO_CHAR(?),'YYYY-MM-DD HH24:MI'),?,?)";
public static final String removeLesson = "DELETE FROM LESSONS WHERE LESSON_ID=?";
public static final String allLessons = "SELECT * FROM LESSONS";
public static final String getLessonForStudent = "SELECT * FROM LESSONS JOIN STUDENT_SUBJECT_LISTS ON" +
        " (LESSONS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) JOIN SUBJECTS ON (STUDENT_SUBJECT_LISTS.SUBJECT_ID=SUBJECTS.SUBJECT_ID)" +
        " JOIN LECTURERS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID) WHERE STUDENT_ID=?";
public static final String getLessonForLecturer = "SELECT * FROM LESSONS WHERE LESSONS.LECTURER_ID=?";
public static final String getLessonForSubject = "SELECT * FROM LESSONS WHERE LESSONS.SUBJECT_ID=?";
public static final String getLessonForGroup = "SELECT * FROM LESSONS  JOIN STUDENT_SUBJECT_LISTS  ON (LESSONS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID )" +
        "JOIN LECTURERS ON (LECTURERS.LECTURER_ID=LESSONS.LECTURER_ID) " +
        "JOIN SUBJECTS ON (LESSONS.SUBJECT_ID=SUBJECTS.SUBJECT_ID)" +
        " WHERE STUDENT_ID IN (SELECT OBJECT_ID FROM OBJECTS WHERE PARENT_ID=?)";
private JdbcTemplate template;


/**
 * set jdbc template
 * @param template template to set
 */
public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

/**
 * add lesson to database
  * @param lesson lesson to add
 */
@Override
public void addLesson(Lesson lesson) {
        template.update(addLesson, lesson.getStringDate(), lesson.getSubjectId(), lesson.getLecturerId());
    }

/**
 * update date of the lesson in database
  * @param lesson lesson with new date to update
 */
@Override
public void updateLessonDate(Lesson lesson) {
        template.update(updateLessonDate,lesson.getStringDate(),lesson.getLessonId());
    }

/**
 * remove lesson from database
  * @param lesson lesson to remove
 */
@Override
public void removeLesson(Lesson lesson) {
        template.update(removeLesson, lesson.getLessonId());
    }

/**
 * get all lesson from database
  * @return all lessons
 */
@Override
public List<Lesson> allLessons() {
        List<Lesson> lessons = template.query(allLessons, new LessonMapper());
        return lessons;
    }

/**
 * get all lessons for student
  * @param student student to get lessons for
 * @return all lessons for student
 */
@Override
public List<Lesson> getLessonForStudent(Student student) {
        List<Lesson> lessons = template.query(getLessonForStudent, new LessonMapper1(),student.getId());
        return lessons;
    }

/**
 * get all lessons for lecturer
 * @param lecturer lecturer to get lessons for
 * @return all lessons for lecturer
 */
@Override
public List<Lesson> getLessonForLecturer(Lecturer lecturer) {
        List<Lesson> lessons = template.query(getLessonForLecturer, new LessonMapper2(),lecturer.getId());
        return lessons;
    }

/**
 * get all lessons for subject
  * @param subject subject to get lessons for
 * @return all lessons for subject
 */
@Override
public List<Lesson> getLessonForSubject(Subject subject) {
        List<Lesson> lessons = template.query(getLessonForSubject, new LessonMapper2(),subject.getId());
        return lessons;
    }


/**
 * get all lessons for group
  * @param object group to get lessons for
 * @return all lessons for group
 */
@Override
public List<Lesson> getLessonForGroup(Object object) {
    List<Lesson> lessons = template.query(getLessonForGroup,new LessonMapperWithSubjectAndLecturer(),object.getId());
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
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            Date parsed = null;
            try {
                parsed = format.parse(lessonDate);
            } catch (ParseException e) {
                logger.error("Error in parsing lesson's date: " + e);
            }
            format.applyPattern("EEEE dd-MM-yyyy HH:mm");
            lessonDate = format.format(parsed);
            format.applyPattern("HH:mm");
            String time = format.format(parsed);
            format.applyPattern("EEEE dd-MM-yyyy");
            String day = format.format(parsed);
            int subjectId = rs.getInt("subject_id");
            int lecturerId = rs.getInt("lecturer_id");
            String subject = rs.getString("subject_full_name");
            String lecturer = rs.getString("lecturer_name");
            Lesson lesson = new Lesson(parsed, subjectId, lecturerId, lessonId, subject, lecturer, lessonDate);
            lesson.setTime(time);
            lesson.setDay(day);
            return lesson;
        }
    }
class LessonMapper2 implements RowMapper<Lesson> {
        public Lesson mapRow(ResultSet rs, int arg1) throws SQLException {
            String lessonDate = rs.getString("lesson_date");
            String lessonId = rs.getString("lesson_id");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            Date parsed = null;
            try {
                parsed = format.parse(lessonDate);
            } catch (ParseException e) {
                logger.error("Error in parsing lesson's date: " + e);
            }
            format.applyPattern("EEEE dd-MM-yyyy HH:mm");
            lessonDate = format.format(parsed);
            format.applyPattern("HH:mm");
            String time = format.format(parsed);
            format.applyPattern("EEEE dd-MM-yyyy");
            String day = format.format(parsed);
            format.applyPattern("yyyy");
            String year = format.format(parsed);
            format.applyPattern("MMMM");
            String month = format.format(parsed);
            int id = rs.getInt("subject_id");
            Lesson lesson = new Lesson();
            lesson.setDate(parsed);
            lesson.setStringDate(lessonDate);
            lesson.setSubjectId(id);
            lesson.setTime(time);
            lesson.setDay(day);
            lesson.setYear(year);
            lesson.setMonth(month);
            lesson.setLessonId(Integer.parseInt(lessonId));
            return lesson;
        }
    }
class LessonMapperWithSubjectAndLecturer implements RowMapper<Lesson>{
        @Override
        public Lesson mapRow(ResultSet resultSet, int i) throws SQLException {
            int lessonId = resultSet.getInt("lesson_id");
            int subId= resultSet.getInt("subject_id");
            String dateString = resultSet.getString("lesson_date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = null;
            try {
                date = format.parse(dateString);
            } catch (ParseException e) {
                logger.error("Error in parsing lesson's date: " + e);
            }
            int lecturerId=resultSet.getInt("lecturer_id");
            String lecturerName = resultSet.getString("lecturer_name");
            String subjectName = resultSet.getString("subject_short_name");
            Lesson lesson = new Lesson(date, subId, lecturerId, lessonId, subjectName, lecturerName);
            lesson.setStringDate(dateString);
            return lesson;
        }
    }

}
