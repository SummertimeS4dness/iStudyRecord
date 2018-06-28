package mvc.dao;

import mvc.beans.Lecturer;
import mvc.beans.Lesson;
import mvc.beans.Student;

import java.util.List;

public interface DAOLesson {
	public void addLesson(Lesson lesson);
	public void removeLesson(Lesson lesson);
	public List<Lesson> allLessons();
	public List<Lesson> getLessonForStudent(Student student);
	public List<Lesson> getLessonForLecturer(Lecturer lecturer);
	
}
