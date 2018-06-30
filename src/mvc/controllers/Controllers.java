package mvc.controllers;


import mvc.beans.Lecturer;
import mvc.beans.Login;
import mvc.beans.Mark;
import mvc.beans.Student;
import mvc.dao.DAOImpl;
import mvc.dao.DAOInterfaces.DAOStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Controllers {
    @Autowired
    private DAOImpl dao;
    
    @Autowired
    private DAOStudent daoStudent;
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
    @RequestMapping(value = "/marks", method = RequestMethod.GET, produces = {"application/json"}, headers="Accept=*/*")
    public @ResponseBody List<Mark>/*String*/ showMarksForStudent(){
        return dao.getMarks(login.getNickname());
    }

    @RequestMapping(value = "/test", produces = "application/json")
    @ResponseBody
    public String test(){
        return "test";
    }
    
    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = {"application/json"}, headers="Accept=*/*")
    @ResponseBody
    public List<Student> showStudents(){
        return daoStudent.getStudents();
    }
    /*@RequestMapping("/viewAll1")
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
    public void createLesson(){}*/

    @RequestMapping(value = "/registerStudent", method = RequestMethod.GET)
    public ModelAndView registerStudent() {
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
        this.login = new Login(login.getNickname(), login.getPassword(), type);
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
        else if(type.equals("admin")){
            if(login.getPassword().equals("admin")&& login.getNickname().equals("admin")){
                mav= new ModelAndView("adminPage");
            }
        }
        return mav;
    }
@RequestMapping(value="/testAdmin", method = RequestMethod.POST,consumes = "application/json")
public ModelAndView testAdmin(@RequestBody Student student) {
    System.out.println(student.getName()+" "+student.getId()+" "+student.getLogin()+" "+student.getPassword());
    return new ModelAndView("showString", "student", student.getName());
    //return new ModelAndView("login");
}
}
