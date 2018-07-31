package mvc.dao.daointerfaces;

import mvc.beans.Lecturer;
import mvc.beans.Lesson;
import mvc.beans.Object;
import mvc.beans.Student;
import mvc.beans.Subject;

import java.util.List;

public interface DAOLesson {
	public void addLesson(Lesson lesson);
	public void removeLesson(Lesson lesson);
	public List<Lesson> allLessons();
	public List<Lesson> getLessonForStudent(Student student);
	public List<Lesson> getLessonForLecturer(Lecturer lecturer);
	public List<Lesson> getLessonForSubject(Subject subject);
	public List<Lesson> getLessonForGroup(Object object);
	public void updateLessonDate(Lesson lesson);
}
