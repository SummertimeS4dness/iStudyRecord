package MVC.dao;

import MVC.beans.Lesson;
import MVC.beans.Lecturer;
import MVC.beans.Mark;
import MVC.beans.Student;
import MVC.controllers.Controllers;

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
