package MVC.controllers;


import MVC.beans.Lesson;
import MVC.beans.Mark;
import MVC.beans.Student;
import MVC.dao.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

@Controller
public class Controllers {
    @Autowired
    private DAOImpl dao;

    /*private ArrayList <Student> studentList = new ArrayList<>();
    private ArrayList <Mark> markList = new ArrayList<>();*/

    @RequestMapping("/viewAll")
    public ModelAndView showMarksForStudent(){
        ArrayList <Student> studentList = new ArrayList<>();
        ArrayList <Mark> markList = new ArrayList<>();
        Lesson lesson = new Lesson();
        Mark mark = new Mark();
        studentList.add(new Student());
        //List<Student> studentList = dao.getAllStudents();
        return new ModelAndView("viewAllStudents", "list", studentList);
    }

    @RequestMapping("/viewAll")
    public ModelAndView showMarksForGroup(){
        return null;
    }

    @RequestMapping("/viewAll")
    public void setMark(){

    }

    @RequestMapping("/viewAll")
    public void createLesson(){}

    @RequestMapping("/viewAll")
    public void createStudent(){}

    @RequestMapping("/viewAll")
    public void createLecturer(){}

    @RequestMapping("/viewAll")
    public void createSubject(){}

    @RequestMapping("/viewAll")
    public void registerStudentForSubject(){}

    @RequestMapping("/viewAll")
    public void registerLecturerForSubject(){}
}
