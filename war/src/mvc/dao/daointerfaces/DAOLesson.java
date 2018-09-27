package mvc.dao.daointerfaces;

import mvc.beans.Lecturer;
import mvc.beans.Lesson;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;

import java.util.List;

public interface DAOLesson {
    /**
     * add lesson to database
     *
     * @param lesson lesson to add
     */
    public void addLesson(Lesson lesson);

    /**
     * remove lesson from database
     *
     * @param lesson lesson to remove
     */
    public void removeLesson(Lesson lesson);

    /**
     * get all lesson from database
     *
     * @return all lessons
     */
    public List<Lesson> allLessons();

    /**
     * get all lessons for student
     *
     * @param student student to get lessons for
     * @return all lessons for student
     */
    public List<Lesson> getLessonForStudent(Student student);

    /**
     * get all lessons for lecturer
     *
     * @param lecturer lecturer to get lessons for
     * @return all lessons for lecturer
     */
    public List<Lesson> getLessonForLecturer(Lecturer lecturer);

    /**
     * get all lessons for subject
     *
     * @param subject subject to get lessons for
     * @return all lessons for subject
     */
    public List<Lesson> getLessonForSubject(Subject subject);

    public List<Lesson> getLessonForGroup(Object object);

    /**
     * update date of the lesson in database
     *
     * @param lesson lesson with new date to update
     */
    public void updateLessonDate(Lesson lesson);
}
