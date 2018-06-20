package mvc.controllers;


import mvc.beans.Lecturer;
import mvc.beans.Login;
import mvc.beans.Mark;
import mvc.beans.Student;
import mvc.dao.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class Controllers {
    @Autowired
    private DAOImpl dao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private ArrayList <Student> studentList = new ArrayList<>();
    private ArrayList <Mark> markList = new ArrayList<>();

    @RequestMapping("/viewAll")
    public ModelAndView showMarksForStudent(){
        studentList.add(new Student());
        //List<Student> studentList = studentDAO.getAllStudents();
        return new ModelAndView("viewAllStudents", "list", studentList);
    }

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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegisteredStudent() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("student", new Student());

        return mav;
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addStudent(@ModelAttribute("user") Student student) {
        dao.registerStudent(student);

        return new ModelAndView("welcome", "firstname", student.getName());
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoggedStudent() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());

        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcessStudent(@ModelAttribute("login") Login login) {
        ModelAndView mav = null;

        Student user = dao.validateStudent(login.getNickname(), login.getPassword());

        if (null != user) {
            mav = new ModelAndView("welcome");
            mav.addObject("firstname", user.getName());
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
        }

        return mav;
    }
}
