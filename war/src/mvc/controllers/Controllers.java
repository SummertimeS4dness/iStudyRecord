package mvc.controllers;

import mvc.beans.*;
import mvc.beans.Object;
import mvc.dao.daointerfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * The type Controllers.
 */
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

    /**
     * get marks for student
     *
     * @return marks for current student
     */
    @RequestMapping(value = "/marks", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Mark> showMarksForStudent() {
        List<Mark> list = daoMark.getMarksForStudent(new Student(ID, login.getNickname(), login.getPassword()));
        Collections.sort(list);
        return list;
    }

    /**
     * get name of loginning user
     *
     * @return return name
     */
    @RequestMapping(value = "/getName", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public String showName() {
        return login.getNickname();
    }

    /**
     * get student by specific id
     *
     * @param id id of student
     * @return student with id
     */
    @RequestMapping(value = "/getStudentProfile", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public Student getStudentProfile(@RequestParam("id") int id) {
        Student student = daoStudent.getStudentById(id);
        return student;
    }

    /**
     * update student in database
     *
     * @param student student to update
     */
    @RequestMapping(value = "/updateStudentProfile", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateStudentProfile(@RequestBody Student student) {
        daoStudent.updateStudent(student);
    }

    /**
     * get lecturer by specific if
     *
     * @param id if of lecturer
     * @return lecturer with id
     */
    @RequestMapping(value = "/getLecturerProfile", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public Lecturer getLecturerProfile(@RequestParam("id") int id) {
        Lecturer lecturer = daoLecturer.getLecturerById(id);
        return lecturer;
    }

    /**
     * update lecturer in database
     *
     * @param lecturer lecturer by id
     */
    @RequestMapping(value = "/updateLecturerProfile", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateLecturerProfile(@RequestBody Lecturer lecturer) {
        daoLecturer.updateLecturer(lecturer);
    }

    /**
     * update mark in database
     *
     * @param mark mark to update
     */
    @RequestMapping(value = "/updateMark", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateMark(@RequestBody Mark mark) {
        daoMark.updateMark(mark);
    }

    /**
     * get all lessons for student
     *
     * @return all lessons for student
     */
    @RequestMapping(value = "/studentSchedule", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lesson> showScheduleForStudent() {
        List<Lesson> list = daoLesson.getLessonForStudent(new Student(ID, login.getNickname(), login.getPassword()));
        Collections.sort(list);
        return list;
    }

    /**
     * get all lessons for lecturer
     *
     * @return all lessons for lecturer
     */
    @RequestMapping(value = "/getSubjectsForLecturer", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Subject> showSubjectsForLecturer() {
        return daoSubject.showSubjectsForLecturer(new Lecturer(ID, login.getNickname(), login.getPassword()));
    }

    /**
     * get lecturers for subject with specific id
     *
     * @param id id of subject
     * @return lecturers for subject with id
     */
    @RequestMapping(value = "/getLecturersForSubject", method = RequestMethod.GET, produces = {"application/json"}, consumes = "application/json", headers = "Accept=*/*")
    @ResponseBody
    public List<Lecturer> getLecturersForSubject(@RequestParam("id") int id) {
        return daoLecturer.getLecturersForSubject(new Subject(id));
    }

    /**
     * get subject for group with specific id
     *
     * @param id id of group
     * @return subjects for group with id
     */
    @RequestMapping(value = "/getSubjectsForGroup", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<Subject> getSubjectsForGroup(@RequestParam int id) {
        Object object = new Object(id);
        return daoSubject.showSubjectsForGroup(object);
    }

    /**
     * get groups for subject
     *
     * @param subject subject to het groups on
     * @return all groups on subject
     */
    @RequestMapping(value = "/getGroupsForSubject", method = RequestMethod.POST, consumes = "application/json", produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> getGroupsForSubject(@RequestBody Subject subject) {
        return daoObject.showGroupsForSubject(subject);
    }

    /**
     * get all marks for group and subject
     *
     * @param objId  group if
     * @param subjId subject id
     * @return all marks for group and subject
     */
    @RequestMapping(value = "/getMarksForGroupAndSubject", method = RequestMethod.GET, consumes = "application/json", produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Mark> getMarksForGroupAndSubject(@RequestParam("objId") int objId, @RequestParam("subjId") int subjId) {
        Object object = new Object(objId);
        Subject subject = new Subject(subjId);
        List<Mark> list = daoMark.getMarksForGroupAndSubject(subject, object);
        Collections.sort(list);
        return list;
    }

    /**
     * get all students for subject
     *
     * @param subject subjects to get students on
     * @return all students on subject
     */
    @RequestMapping(value = "/getStudentsForSubject", method = RequestMethod.POST, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Student> showStudentsForSubjects(@RequestBody Subject subject) {
        return daoStudent.getStudentsOnSubject(subject);
    }

    /**
     * get all students in group
     *
     * @param group group to get students in
     * @return all students in group
     */
    @RequestMapping(value = "/getStudentsForGroup", method = RequestMethod.POST, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Student> getStudentsForGroup(@RequestBody Object group) {
        return daoStudent.getStudentsForGroup(group);
    }

    /**
     * get all lessons for subject
     *
     * @param subject subject to get lessons for
     * @return all lessons in subject
     */
    @RequestMapping(value = "/getLessonsForSubject", method = RequestMethod.POST, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lesson> showLessonsForSubjects(@RequestBody Subject subject) {
        return daoLesson.getLessonForSubject(subject);
    }

    /**
     * get all students in group with specific id
     *
     * @param id id of group
     * @return all students is group
     */
    @RequestMapping(value = "/studentsForGroup", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Student> studentsForGroup(@RequestParam int id) {
        Object object = new Object(id);
        return daoStudent.getStudentsForGroup(object);
    }

    /**
     * get lessons for lecturer
     *
     * @return lessons for lecturer
     */
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

    /**
     * get all students
     *
     * @return all students
     */
    @RequestMapping(value = "/students", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Student> showStudents() {
        return daoStudent.getStudents();
    }

    /**
     * get all objects with type 'lecturer'
     *
     * @return all 'lecturer' type objects
     */
    @RequestMapping(value = "/lecturerObjects", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> getLcturerOjects() {
        return daoObject.getLecturers();
    }

    /**
     * get all objects with type 'student'
     *
     * @return all 'student' type objects
     */
    @RequestMapping(value = "/studentObjects", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> getStudentOjects() {
        return daoObject.getStudents();
    }

    /**
     * get all lecturers
     *
     * @return all lecturers
     */
    @RequestMapping(value = "/lecturers", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lecturer> showLecturers() {
        return daoLecturer.getLecturers();
    }

    /**
     * get all objects with type 'group'
     *
     * @return all 'group' type objects
     */
    @RequestMapping(value = "/getGroup", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> showGroups() {
        return daoObject.getGrops();
    }

    /**
     * get all objects with type 'faculties'
     *
     * @return all 'faculties' type objects
     */
    @RequestMapping(value = "/getFaculty", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> showFaculties() {
        return daoObject.getFaculties();
    }

    /**
     * get all objects with type 'universities'
     *
     * @return all 'universities' type objects
     */
    @RequestMapping(value = "/getUniversity", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> showUniversities() {
        return daoObject.getUniversities();
    }

    /**
     * get all objects
     *
     * @return all objects
     */
    @RequestMapping(value = "/objects", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> getObjects() {
        return daoObject.getObjects();
    }

    /**
     * get all objects with type 'cathedra'
     *
     * @return all 'cathedra' type objects
     */
    @RequestMapping(value = "/getCathedra", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Object> showCathedras() {
        return daoObject.getCathedras();
    }

    /**
     * get all objects with type 'lecturer'
     *
     * @return all 'lecturer' type objects
     */
    @RequestMapping(value = "/getLecturers", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lecturer> showLecturersObjects() {
        return daoLecturer.getLecturers();
    }

    /**
     * get lecturer for subject
     *
     * @param subject subject to get lecturer on
     * @return lecturer for subject
     */
    @RequestMapping(value = "/getLecturerBySubject", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public Lecturer showLecturerBySubject(@RequestBody Subject subject) {
        return daoLecturer.getLecturerForSubject(subject);
    }

    /**
     * get all subjects
     *
     * @return all subjects
     */
    @RequestMapping(value = "/subjects", method = RequestMethod.GET, produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Subject> showSubjects() {
        return daoSubject.getSubjects();
    }

    /**
     * Sets student as starosta.
     *
     * @param student new starosta
     */
    @RequestMapping(value = "/setStarosta", method = RequestMethod.POST, consumes = "application/json")
    public void setStarosta(@RequestBody Student student) {
        daoStudent.setStarosta(student);
    }

    /**
     * Login's model and view.
     *
     * @return login's model and view
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());

        return mav;
    }

    /**
     * Login's process model and view.
     *
     * @param login login
     * @param type  type of visitor
     * @return page's model and view
     */
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
            Object object = daoObject.validateAdmin(login);
            if (object != null) {
                mav = new ModelAndView("redirect:/adminPage");
            } else {
                mav = new ModelAndView("login");
                mav.addObject("message", "Username or Password is wrong!");
            }
        }
        return mav;
    }

    /**
     * Destination admin page.
     *
     * @return path of admin's page
     */
    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String destinationAdminPage() {
        return "adminPage";
    }

    /**
     * Destination student's page model and view.
     *
     * @return student's page model and view
     */
    @RequestMapping(value = "/studentPage", method = RequestMethod.GET)
    public ModelAndView destinationStudentPage() {
        Object obj = new Object();
        obj.setId(ID);
        String group = daoObject.getParent(obj).getDescription();
        String starosta = daoStudent.showStarostaForStudent(ID).getName();
        ModelAndView mav = new ModelAndView("studentPage", "name", login.getNickname());
        mav.addObject("studentID", ID);
        mav.addObject("group", group);
        mav.addObject("starosta", starosta);
        return mav;
    }

    /**
     * Destination lecturer's page model and view.
     *
     * @return lecturer's page model and view
     */
    @RequestMapping(value = "/lecturerPage", method = RequestMethod.GET)
    public ModelAndView destinationLecturerPage() {
        ModelAndView mav = new ModelAndView("lecturerPage", "name", login.getNickname());
        mav.addObject("lecturerID", ID);
        return mav;
    }

    /**
     * Updates student.
     *
     * @param student student to update
     */
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateStudent(@RequestBody Student student) {
        daoStudent.updateStudent(student);
        daoObject.updateObject(new Object(student.getId(), student.getName(), "student", student.getGroupId()));
    }

    /**
     * Deletes student.
     *
     * @param student student to delete
     */
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteStudent(@RequestBody Student student) {
        System.out.println(student.getName() + " " + student.getId() + " " + student.getLogin() + " " + student.getPassword());
        daoStudent.removeStudent(student);
    }

    /**
     * Creates student.
     *
     * @param student student to create
     */
    @RequestMapping(value = "/createStudent", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void newStudentForm(@RequestBody Student student) {
        Object object = new Object();
        object.setDescription(student.getName());
        object.setType("student");
        object.setParentId(student.getGroupId());
        daoStudent.createStudent(student, object);
    }

    /**
     * Creates lecturer.
     *
     * @param lecturer lecturer to create
     */
    @RequestMapping(value = "/createLecturer", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void newLecturerForm(@RequestBody Lecturer lecturer) {
        Object object = new Object();
        object.setDescription(lecturer.getName());
        object.setType("lecturer");
        object.setParentId(lecturer.getCathedraId());
        daoLecturer.createLecturer(lecturer, object);
    }

    /**
     * Updates lecturer.
     *
     * @param lecturer lecturer to update
     */
    @RequestMapping(value = "/updateLecturer", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateLecturer(@RequestBody Lecturer lecturer) {
        daoLecturer.updateLecturer(lecturer);
        daoObject.updateObject(new Object(lecturer.getId(), lecturer.getName(), "lecturer", lecturer.getCathedraId()));
    }

    /**
     * Updates object.
     *
     * @param object object to update
     */
    @RequestMapping(value = "/updateObject", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateObject(@RequestBody Object object) {
        daoObject.updateObject(object);
    }

    /**
     * Deletes lecturer.
     *
     * @param lecturer lecturer to delete
     */
    @RequestMapping(value = "/deleteLecturer", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteLecturer(@RequestBody Lecturer lecturer) {
        daoLecturer.removeLecturer(lecturer);
    }

    /**
     * Deletes object.
     *
     * @param object object to delete
     */
    @RequestMapping(value = "/deleteObject", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteObject(@RequestBody Object object) {
        daoObject.removeObject(object);
    }

    /**
     * Deletes subject.
     *
     * @param subject subject to delete
     */
    @RequestMapping(value = "/deleteSubject", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteSubject(@RequestBody Subject subject) {
        daoSubject.removeSubject(subject);
    }

    /**
     * Creates subject.
     *
     * @param subject the subject to create
     */
    @RequestMapping(value = "/createSubject", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void createSubject(@RequestBody Subject subject) {
        daoSubject.createSubject(subject);
    }

    /**
     * Creates lesson.
     *
     * @param lesson the lesson
     */
    @RequestMapping(value = "/createLesson", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void createLesson(@RequestBody Lesson lesson) {
        String res = lesson.getStringDate();
        res = res.replaceAll("\"", "");
        res = res.replaceAll("T", " ");
        lesson.setStringDate(res);
        daoLesson.addLesson(lesson);
    }

    /**
     * Creates mark.
     *
     * @param mark mark to create
     * @return response entity with status
     */
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

    /**
     * Updates subject.
     *
     * @param subject subject to update
     */
    @RequestMapping(value = "/updateSubject", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateSubject(@RequestBody Subject subject) {
        daoSubject.updateSubject(subject);
    }

    /**
     * Gets student for subject.
     *
     * @param id subject's id
     * @return student for subject
     */
    @RequestMapping(value = "/studentsForSubject", method = RequestMethod.GET, consumes = "application/json", produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Student> getStudentForSubject(@RequestParam("id") int id) {
        Subject subject = new Subject(id, null, null, null, 0);
        return daoStudent.getStudentsOnSubject(subject);
    }

    /**
     * Remove students from subject.
     *
     * @param students students to remove from subjects
     */
    @RequestMapping(value = "/deleteStudentsFromSubject", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeStudentsFromSubject(@RequestBody Student[] students) {
        Subject subject = new Subject(students[0].getId());
        for (int i = 1; i < students.length; i++) {
            daoStudent.removeStudentFromSubject(subject, students[i]);
        }
    }

    /**
     * Add students for subject.
     *
     * @param students students to add for subjects
     */
    @RequestMapping(value = "/addStudentForSubject", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void addStudentsForSubject(@RequestBody Student[] students) {
        Subject subject = new Subject(students[0].getId());
        subject.setLecturerId(Integer.parseInt(students[0].getPassword()));
        for (int i = 1; i < students.length; i++) {
            daoStudent.registerStudentForSubject(students[i], subject);
        }
    }

    /**
     * Creates object.
     *
     * @param object object to create
     */
    @RequestMapping(value = "/addObject", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void createObject(@RequestBody Object object) {
        daoObject.createObject(object);
    }

    /**
     * Schedule for group.
     *
     * @param id group's id
     * @return schedule for group
     */
    @RequestMapping(value = "/lessonsForGroup", method = RequestMethod.GET, consumes = "application/json", produces = {"application/json"}, headers = "Accept=*/*")
    @ResponseBody
    public List<Lesson> scheduleForGroup(@RequestParam("id") int id) {
        return daoLesson.getLessonForGroup(new Object(id));
    }

    /**
     * Updates lesson's date.
     *
     * @param lesson lesson to update date
     */
    @RequestMapping(value = "/updateLessonDate", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateLessonDate(@RequestBody Lesson lesson) {
        daoLesson.updateLessonDate(lesson);
    }
}
