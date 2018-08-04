package mvc.controllers;

import mvc.beans.*;
import mvc.beans.Object;
import mvc.dao.daointerfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.jws.WebParam;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class Controllers {

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

@RequestMapping(value = "/marks", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Mark> showMarksForStudent() {
    List<Mark> list = daoMark.getMarksForStudent(new Student(ID, login.getNickname(), login.getPassword()));
    Collections.sort(list);
    return list;
}

@RequestMapping(value = "/getName", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public String showName() {
    return login.getNickname();
}

@RequestMapping(value = "/studentSchedule", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Lesson> showScheduleForStudent() {
    List<Lesson> list = daoLesson.getLessonForStudent(new Student(ID, login.getNickname(), login.getPassword()));
    Collections.sort(list);
    return list;
}

@RequestMapping(value = "/getSubjectsForLecturer", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Subject> showSubjectsForLecturer() {
    return daoSubject.showSubjectsForLecturer(new Lecturer(ID, login.getNickname(), login.getPassword()));
}
@RequestMapping(value = "/getLecturersForSubject", method = RequestMethod.GET, produces = {"application/json"}, consumes = "application/json", headers = "Accept=*/*")
@ResponseBody
public List<Lecturer> getLecturersForSubject(@RequestParam("id") int id){
    return daoLecturer.getLecturersForSubject(new Subject(id));
}
@RequestMapping(value = "/getSubjectsForGroup", method = RequestMethod.GET, produces = {"application/json"})
@ResponseBody
public List<Subject> getSubjectsForGroup(@RequestParam int id) {
    System.out.println("helo");
    Object object = new Object(id);
    return daoSubject.showSubjectsForGroup(object);
}

@RequestMapping(value = "/getGroupsForSubject", method = RequestMethod.POST, consumes = "application/json", produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Object> getGroupsForSubject(@RequestBody Subject subject) {
    return daoObject.showGroupsForSubject(subject);
}

@RequestMapping(value = "/getMarksForGroupAndSubject", method = RequestMethod.GET, consumes = "application/json", produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Mark> getMarksForGroupAndSubject(@RequestParam("objId") int objId, @RequestParam("subjId") int subjId) {
    Object object = new Object(objId);
    Subject subject = new Subject(subjId);
    List<Mark> list = daoMark.getMarksForGroupAndSubject(subject, object);
    Collections.sort(list);
    return list;
}

@RequestMapping(value = "/getStudentsForSubject", method = RequestMethod.POST, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Student> showStudentsForSubjects(@RequestBody Subject subject) {
    return daoStudent.getStudentsOnSubject(subject);
}

@RequestMapping(value = "/getStudentsForGroup", method = RequestMethod.POST, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Student> getStudentsForGroup(@RequestBody Object group) {
    return daoStudent.getStudentsForGroup(group);
}

@RequestMapping(value = "/getLessonsForSubject", method = RequestMethod.POST, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Lesson> showLessonsForSubjects(@RequestBody Subject subject) {
    return daoLesson.getLessonForSubject(subject);
}

@RequestMapping(value = "/studentsForGroup", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Student> studentsForGroup(@RequestParam int id)
{
    Object object = new Object(id);
    return daoStudent.getStudentsForGroup(object);
}





@RequestMapping(value = "/lecturerSchedule", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Lesson> showScheduleForLecturer() {
    List<Lesson> list = daoLesson.getLessonForLecturer(new Lecturer(ID, login.getNickname(), login.getPassword()));
    for (Lesson les : list) {
        String res = daoSubject.getSubjectById(les.getSubjectId());
        les.setSubject(res);
    }
    Collections.sort(list);
    return list;
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

@RequestMapping(value = "/getGroup", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Object> showGroups() {
    return daoObject.getGrops();
}

@RequestMapping(value = "/getFaculty", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Object> showFaculties() {
    return daoObject.getFaculties();
}

@RequestMapping(value = "/getUniversity", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Object> showUniversities() {
    return daoObject.getUniversities();
}

@RequestMapping(value = "/objects", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Object> getObjects() {
    return daoObject.getObjects();
}

@RequestMapping(value = "/getCathedra", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
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
public List<Subject> showSubjects() throws IOException {
    throw new IOException("Folder not found!");
   //return daoSubject.getSubjects();
   
   
}

@RequestMapping(value = "/registerStudent", method = RequestMethod.GET)
public ModelAndView registerStudent() {
    ModelAndView mav = new ModelAndView("registerStudent");
    mav.addObject("student", new Student());
    
    return mav;
}

@RequestMapping(value = "/registerStudentProcess", method = RequestMethod.POST)
public ModelAndView registerStudentProcess(@ModelAttribute("student") Student student, BindingResult resultStudent, @ModelAttribute("object") Object object,
                                           BindingResult resultLecturer) {
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
@RequestMapping(value = "/setStarosta", method = RequestMethod.POST, consumes = "application/json")
public void setStarosta(@RequestBody Student student) {
    daoStudent.setStarosta(student);
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
        if (student != null) {
            ID = student.getId();
            mav = new ModelAndView("redirect:/studentPage");
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!");
        }
    } else if ("lecturer".equals(type)) {
        lecturer = daoLecturer.validateLecturer(login);
        if (lecturer != null) {
            ID = lecturer.getId();
            mav = new ModelAndView("redirect:/lecturerPage");
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!");
        }
    } else if ("admin".equals(type)) {
        if (login.getPassword().equals("admin") && login.getNickname().equals("admin")) {
            mav = new ModelAndView("redirect:/adminPage");
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!");
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
    daoStudent.updateStudent(student);
    daoObject.updateObject(new Object(student.getId(), student.getName(), "student", student.getGroupId()));
}

@RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void deleteStudent(@RequestBody Student student) {
    System.out.println(student.getName() + " " + student.getId() + " " + student.getLogin() + " " + student.getPassword());
    daoStudent.removeStudent(student);
}

@RequestMapping(value = "/createStudent", method = RequestMethod.POST)
@ResponseStatus(value = HttpStatus.OK)
public void newStudentForm(@RequestBody Student student) {
    Object object = new Object();
    object.setDescription(student.getName());
    object.setType("student");
    object.setParentId(student.getGroupId());
    daoStudent.createStudent(student, object);
}

@RequestMapping(value = "/createLecturer", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void newLecturerForm(@RequestBody Lecturer lecturer) {
    Object object = new Object();
    object.setDescription(lecturer.getName());
    object.setType("lecturer");
    object.setParentId(lecturer.getCathedraId());
    daoLecturer.createLecturer(lecturer, object);
}

@RequestMapping(value = "/updateLecturer", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void updateLecturer(@RequestBody Lecturer lecturer) {
    daoLecturer.updateLecturer(lecturer);
    daoObject.updateObject(new Object(lecturer.getId(), lecturer.getName(), "lecturer", lecturer.getCathedraId()));
}

@RequestMapping(value = "/updateObject", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void updateObject(@RequestBody Object object) {
    daoObject.updateObject(object);
}

@RequestMapping(value = "/deleteLecturer", method = RequestMethod.DELETE, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void deleteLecturer(@RequestBody Lecturer lecturer) {
    daoLecturer.removeLecturer(lecturer);
}

@RequestMapping(value = "/deleteObject", method = RequestMethod.DELETE, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void deleteObject(@RequestBody Object object) {
    daoObject.removeObject(object);
}

@RequestMapping(value = "/deleteSubject", method = RequestMethod.DELETE, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void deleteSubject(@RequestBody Subject subject) {
    daoSubject.removeSubject(subject);
}

@RequestMapping(value = "/createSubject", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void createSubject(@RequestBody Subject subject) {
    daoSubject.createSubject(subject);
}

@RequestMapping(value = "/createLesson", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void createLesson(@RequestBody Lesson lesson) {
    String res = lesson.getStringDate();
    res = res.replaceAll("\"", "");
    res = res.replaceAll("T", " ");
    lesson.setStringDate(res);
    daoLesson.addLesson(lesson);
}

@RequestMapping(value = "/createMark", method = RequestMethod.POST, consumes = "application/json")
@ResponseBody
public ResponseEntity<?> createMark(@RequestBody Mark mark) {
    String check = daoMark.createMark(mark);
    if ("OK".equals(check)) {
        return new ResponseEntity(HttpStatus.OK);
    } else {
        return new ResponseEntity(check, HttpStatus.BAD_REQUEST);
    }
}

@RequestMapping(value = "/updateSubject", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void updateSubject(@RequestBody Subject subject) {
    daoSubject.updateSubject(subject);
}

@RequestMapping(value = "/studentsForSubject", method = RequestMethod.GET, consumes = "application/json", produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Student> getStudentForSubject(@RequestParam("id") int id) {
    Subject subject = new Subject(id, null, null, null, 0);
    return daoStudent.getStudentsOnSubject(subject);
}

@RequestMapping(value = "/deleteStudentsFromSubject", method = RequestMethod.DELETE, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void removeStudentsFromSubject(@RequestBody Student[] students) {
    Subject subject = new Subject(students[0].getId());
    for (int i = 1; i < students.length; i++) {
        daoStudent.removeStudentFromSubject(subject, students[i]);
    }
}

@RequestMapping(value = "/addStudentForSubject", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void addStudentsForSubject(@RequestBody Student[] students) {
    Subject subject = new Subject(students[0].getId());
    subject.setLecturerId(Integer.parseInt(students[0].getPassword()));
    for (int i = 1; i < students.length; i++) {
        daoStudent.registerStudentForSubject(students[i], subject);
    }
}

@RequestMapping(value = "/addObject", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void createObject(@RequestBody Object object) {
    daoObject.createObject(object);
}

//    @RequestMapping(value = "/aaa", method = RequestMethod.GET, consumes = "application/json" ,headers = "Accept=*/*")
//    @ResponseBody
//    public List<Lesson> scheduleForGroup(@RequestParam("groupID") int id) {
//        System.out.println("here");
//        List<Lesson> lessonForGroup = daoLesson.getLessonForGroup(new Object(id));
//        return lessonForGroup;
//    }
@RequestMapping(value = "/lessonsForGroup", method = RequestMethod.GET, consumes = "application/json", produces = {"application/json"}, headers = "Accept=*/*")
@ResponseBody
public List<Lesson> scheduleForGroup(@RequestParam("id") int id) {
    List<Lesson> lessonForGroup = daoLesson.getLessonForGroup(new Object(id));
    return lessonForGroup;
}

@RequestMapping(value = "/updateLessonDate", method = RequestMethod.POST, consumes = "application/json")
@ResponseStatus(value = HttpStatus.OK)
public void updateLessonDate(@RequestBody Lesson lesson) {
    daoLesson.updateLessonDate(lesson);
}
}
