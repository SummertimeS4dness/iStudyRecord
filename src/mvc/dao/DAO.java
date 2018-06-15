package mvc.dao;

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


}
