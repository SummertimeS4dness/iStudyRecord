package mvc.dao;

import mvc.beans.Lecturer;
import mvc.beans.Mark;
import mvc.beans.Student;

import java.util.List;

public interface DAO {
    public List<Student> getStudents();
    public List<Mark> getMarks();
    public void setMark();
    public void createLesson();
    public void createStudent();
    public void createLecturer();
    public void createSubject();
    public void registerStudentForSubject();
    public void registerLecturerForSubject();

    public void registerStudent(Student student);
    public void registerLecturer(Lecturer lecturer);
    public Student validateStudent(String login, String password);
    public Lecturer validateLecturer(String login, String password);
}
