package mvc.controllers;


import com.sun.deploy.net.HttpResponse;
import mvc.beans.*;
import mvc.beans.Object;
import mvc.dao.DAOImpl;
import mvc.dao.daointerfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Controllers {
    @Autowired
    private DAOImpl dao;

    @Autowired
    private DAOStudent daoStudent;

    @Autowired
    private DAOLecturer daoLecturer;

    @Autowired
    private DAOMark daoMark;

    @Autowired
    private DAOLesson daoLesson;

    @Autowired
    private DAOObject daoObject;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Login login;
    private int ID;

    /*@RequestMapping("/studentPage")
    public ModelAndView showMarksForStudent(){
        List<Mark> marks = dao.getMarks(login.getNickname());
        return new ModelAndView("studentPage", "list", marks);
    }*/
    @RequestMapping(value = "/marks", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Mark> showMarksForStudent() {
        return daoMark.getMarksForStudent(new Student(ID, login.getNickname(), login.getPassword()));
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lesson> showScheduleForStudent() {
        return daoLesson.getLessonForStudent(new Student(ID, login.getNickname(), login.getPassword()));
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Student> showStudents() {
        return daoStudent.getStudents();
    }
    @RequestMapping(value = "/getGroups", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> showGroups() {
        return daoObject.getGrops();
    }
    @RequestMapping(value = "/getCathedras", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> showCathedras() {
        return daoObject.getCathedras();
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
    public ModelAndView registerStudentProcess(@ModelAttribute("student") Student student, BindingResult resultStudent,  @ModelAttribute("object") Object object,
                                               BindingResult resultLecturer) {
        //Object object = new Object("student", "student", 0);
        daoStudent.createStudent(student, object);

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
        daoLecturer.createLecturer(lecturer, new Object(0,"lecturer","lecturer",0));

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
            student = daoStudent.validateStudent(login);
            ID = student.getId();
            if (student != null) {
                mav = new ModelAndView("studentPage");
                //List<Mark> marks = dao.getMarks(login.getNickname());
                mav.addObject("firstname", student.getName());
                //mav.addObject("list", marks);
            } else {
                mav = new ModelAndView("login");
                mav.addObject("message", "Username or Password is wrong!!");
            }
        } else if ("lecturer".equals(type)) {
            lecturer = daoLecturer.validateLecturer(login);
            ID = lecturer.getId();
            if (lecturer != null) {
                mav = new ModelAndView("welcome");
                mav.addObject("firstname", lecturer.getName());
            } else {
                mav = new ModelAndView("login");
                mav.addObject("message", "Username or Password is wrong!!");
            }
        } else if ("admin".equals(type)) {
            if (login.getPassword().equals("admin") && login.getNickname().equals("admin")) {
                mav = new ModelAndView("redirect:/adminPage");
            }
        }
        return mav;
    }
@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
public String destination() {
    return "adminPage";
}
    @RequestMapping(value = "/testAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ModelAndView testAdmin(@RequestBody Student student) {
        System.out.println(student.getName() + " " + student.getId() + " " + student.getLogin() + " " + student.getPassword());
        return new ModelAndView("showString", "student", student.getName());
        //return new ModelAndView("login");
    }
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST,consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateStudent(@RequestBody Student student){
        System.out.println(student.getName() + " " + student.getId() + " " + student.getLogin() + " " + student.getPassword());
        daoStudent.updateStudent(student);
    }
    @RequestMapping(value = "/deleteStudent",method = RequestMethod.DELETE,consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteStudent(@RequestBody Student student){
        System.out.println(student.getName() + " " + student.getId() + " " + student.getLogin() + " " + student.getPassword());
        daoStudent.removeStudent(student);
    }
    
    @RequestMapping(value = "/adminPage",method = RequestMethod.POST, params = "st")
    public ModelAndView newAccountForm(@ModelAttribute("student") Student student,   @ModelAttribute("object") Object object){
        System.out.println("STUDENT_____________________");
        object.setDescription(student.getName());
        object.setType("student");
        //daoStudent.createStudent(student,object);
        return new ModelAndView("adminPage");
    }
@RequestMapping(value = "/adminPage",method = RequestMethod.POST)
public ModelAndView newAccountForm(@ModelAttribute("lecturer") Lecturer lecturer,   @ModelAttribute("object1") Object object){
    System.out.println("LECTURER_____________________");
    object.setDescription(lecturer.getName());
    object.setType("lecturer");
    //daoLecturer.createLecturer(lecturer,object);
  //  daoStudent.createStudent(student,object);
    return new ModelAndView("adminPage");
}
}
