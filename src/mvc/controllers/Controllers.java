package mvc.controllers;

import mvc.beans.*;
import mvc.beans.Object;
import mvc.dao.DAOImpl;
import mvc.dao.daointerfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;
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
    private DAOSubject daoSubject;
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

    @RequestMapping(value = "/getName", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public String showName() {
        return login.getNickname();
    }

    @RequestMapping(value = "/studentSchedule", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lesson> showScheduleForStudent() {
        return daoLesson.getLessonForStudent(new Student(ID, login.getNickname(), login.getPassword()));
    }

    @RequestMapping(value = "/getSubjectsForLecturer", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Subject> showSubjectsForLecturer() {
        return daoSubject.showSubjectsForLecturer(new Lecturer(ID, login.getNickname(), login.getPassword()));
    }

    @RequestMapping(value = "/getStudentsForSubject", method = RequestMethod.POST, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Student> showStudentsForSubjects(@RequestBody Subject subject) {
        return daoStudent.getStudentsOnSubject(subject);
    }

    @RequestMapping(value = "/getLessonsForSubject", method = RequestMethod.POST, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lesson> showLessonsForSubjects(@RequestBody Subject subject) {
        return daoLesson.getLessonForSubject(subject);
    }

    @RequestMapping(value = "/lecturerSchedule", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lesson> showScheduleForLecturer() {
        return daoLesson.getLessonForLecturer(new Lecturer(ID, login.getNickname(), login.getPassword()));
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Student> showStudents() {
        return daoStudent.getStudents();
    }

    @RequestMapping(value = "/lecturerObjects", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> getLcturerOjects() {
        return daoObject.getLecturers();
    }

    @RequestMapping(value = "/studentObjects", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> getStudentOjects() {
        return daoObject.getStudents();
    }

    @RequestMapping(value = "/lecturers", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lecturer> showLecturers() {
        return daoLecturer.getLecturers();
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

    @RequestMapping(value = "/getLecturers", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lecturer> showLecturersObjects() {
        return daoLecturer.getLecturers();
    }

    @RequestMapping(value = "/getLecturerBySubject", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public Lecturer showLecturerBySubject(@RequestBody Subject subject) {
        return daoLecturer.getLecturerForSubject(subject);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Subject> showSubjects() {
        return daoSubject.getSubjects();
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
    public ModelAndView registerStudentProcess(@ModelAttribute("student") Student student, BindingResult resultStudent, @ModelAttribute("object") Object object,
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
        daoLecturer.createLecturer(lecturer, new Object(0, "lecturer", "lecturer", 0));

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
                mav = new ModelAndView("redirect:/studentPage");
            } else {
                mav = new ModelAndView("login");
                mav.addObject("message", "Username or Password is wrong!!");
            }
        } else if ("lecturer".equals(type)) {
            lecturer = daoLecturer.validateLecturer(login);
            ID = lecturer.getId();
            if (lecturer != null) {
                mav = new ModelAndView("redirect:/lecturerPage");
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
    public String destinationAdminPage() {
        return "adminPage";
    }

    @RequestMapping(value = "/studentPage", method = RequestMethod.GET)
    public ModelAndView destinationStudentPage() {
        ModelAndView mav = new ModelAndView("studentPage", "name", login.getNickname());
        mav.addObject("studentID", ID);
        return mav;
    }

    @RequestMapping(value = "/lecturerPage", method = RequestMethod.GET)
    public ModelAndView destinationLecturerPage() {
        ModelAndView mav = new ModelAndView("lecturerPage", "name", login.getNickname());
        mav.addObject("lecturerID", ID);
        return mav;
    }

    @RequestMapping(value = "/testAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ModelAndView testAdmin(@RequestBody Student student) {
        System.out.println(student.getName() + " " + student.getId() + " " + student.getLogin() + " " + student.getPassword());
        return new ModelAndView("showString", "student", student.getName());
        //return new ModelAndView("login");
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateStudent(@RequestBody Student student) {
        System.out.println(student.getName() + " " + student.getId() + " " + student.getLogin() + " " + student.getPassword());
        daoStudent.updateStudent(student);
        daoObject.updateObject(new Object(student.getId(), student.getGroup(), "", student.getGroupId()));
    }

    @RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteStudent(@RequestBody Student student) {
        System.out.println(student.getName() + " " + student.getId() + " " + student.getLogin() + " " + student.getPassword());
        daoStudent.removeStudent(student);
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ModelAndView newStudentForm(@ModelAttribute("student") Student student, @ModelAttribute("object") Object object) {
        System.out.println("STUDENT_____________________");
        object.setDescription(student.getName());
        object.setType("student");
        daoStudent.createStudent(student, object);
        //return new ModelAndView("adminPage");
        return new ModelAndView(new RedirectView("adminPage"));
    }

    @RequestMapping(value = "/addLecturer", method = RequestMethod.POST)
    public ModelAndView newLecturerForm(@ModelAttribute("lecturer") Lecturer lecturer, @ModelAttribute("object1") Object object) {
        System.out.println("LECTURER_____________________");
        object.setDescription(lecturer.getName());
        object.setType("lecturer");
        daoLecturer.createLecturer(lecturer, object);
        //  daoStudent.createStudent(student,object);
        //return new ModelAndView("adminPage");
        return new ModelAndView(new RedirectView("adminPage"));
    }

    @RequestMapping(value = "/updateLecturer", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateLecturer(@RequestBody Lecturer lecturer) {
        daoLecturer.updateLecturer(lecturer);
        daoObject.updateObject(new Object(lecturer.getId(), lecturer.getName(), "lecturer", lecturer.getCathedraId()));
    }

    @RequestMapping(value = "/deleteLecturer", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteLecturer(@RequestBody Lecturer lecturer) {
        daoLecturer.removeLecturer(lecturer);
    }

    @RequestMapping(value = "/createSubject", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void createSubject(@RequestBody Subject subject) {
        daoSubject.createSubject(subject);
    }

    @RequestMapping(value = "/createLesson", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void createLesson(@RequestBody Lesson lesson) {
        daoLesson.addLesson(lesson);
    }

    @RequestMapping(value = "/createMark", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void createMark(@RequestBody Mark mark) {
        daoMark.createMark(mark);
    }


}
