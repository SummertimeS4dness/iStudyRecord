package mvc.controllers;


import mvc.beans.Lecturer;
import mvc.beans.Login;
import mvc.beans.Mark;
import mvc.beans.Student;
import mvc.dao.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controllers {
    @Autowired
    private DAOImpl dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ArrayList <Student> studentList = new ArrayList<>();
    private ArrayList <Mark> markList = new ArrayList<>();
    private Login login;

    /*@RequestMapping("/studentPage")
    public ModelAndView showMarksForStudent(){
        List<Mark> marks = dao.getMarks(login.getNickname());
        return new ModelAndView("studentPage", "list", marks);
    }*/

    @RequestMapping("/viewAll1")
    public ModelAndView showMarksForGroup(){
        return null;
    }

    @RequestMapping("/viewAll1")
    public void setMark(){

    }

    @RequestMapping("/viewAll1")
    public void registerStudent(Student student) {

    }

    @RequestMapping("/viewAll1")
    public void registerLecturer(Lecturer lecturer) {

    }

    @RequestMapping("/viewAll1")
    public Student validateStudent(String login, String password) {
        return null;
    }

    @RequestMapping("/viewAll1")
    public Lecturer validateLecturer(String login, String password) {
        return null;
    }

    @RequestMapping("/viewAll1")
    public void createLesson(){}

    @RequestMapping(value = "/registerStudent", method = RequestMethod.GET)
    public ModelAndView registerStuden() {
        ModelAndView mav = new ModelAndView("registerStudent");
        mav.addObject("student", new Student());

        return mav;
    }

    @RequestMapping(value = "/registerStudentProcess", method = RequestMethod.POST)
    public ModelAndView registerStudentProcess(@ModelAttribute("student") Student student) {
        dao.registerStudent(student);

        return new ModelAndView("welcome", "firstname", student.getName());
    }

    @RequestMapping(value = "/registerLecturer", method = RequestMethod.GET)
    public ModelAndView registerLecturer() {
        ModelAndView mav = new ModelAndView("registerLecturer");
        mav.addObject("lecturer", new Lecturer());

        return mav;
    }

    @RequestMapping(value = "/registerLecturerProcess", method = RequestMethod.POST)
    public ModelAndView registerLecturerProcess(@ModelAttribute("lecturer") Lecturer lecturer) {
        dao.registerLecturer(lecturer);

        return new ModelAndView("welcome", "firstname", lecturer.getName());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());

        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute("login") Login login, @RequestParam("radioName") String type) {
        ModelAndView mav = null;
        Student student = null;
        Lecturer lecturer = null;
        this.login = new Login(login.getNickname(), login.getPassword());
        if ("student".equals(type)) {
            student = dao.validateStudent(login.getNickname(), login.getPassword());
            if (student != null) {
                mav = new ModelAndView("studentPage");
                List<Mark> marks = dao.getMarks(login.getNickname());
                mav.addObject("firstname", student.getName());
                mav.addObject("list", marks);
            } else {
                mav = new ModelAndView("login");
                mav.addObject("message", "Username or Password is wrong!!");
            }
        }
        else if ("lecturer".equals(type)) {
            lecturer = dao.validateLecturer(login.getNickname(), login.getPassword());
            if (lecturer != null) {
                int y = 0;
                mav = new ModelAndView("welcome");
                mav.addObject("firstname", lecturer.getName());
            } else {
                mav = new ModelAndView("login");
                mav.addObject("message", "Username or Password is wrong!!");
            }
        }

        return mav;
    }
}
