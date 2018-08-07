package mvc.dao.daointerfaces;

import mvc.beans.*;
import mvc.beans.Object;

import java.util.List;

public interface DAOMark {
    /**
     * create mark in database
     *
     * @param mark mark to create
     * @return result of creating
     */
    public String createMark(Mark mark);

    /**
     * remove mark from database
     *
     * @param mark mark to remove
     */
    public void removeMark(Mark mark);

    /**
     * get all marks for subject
     *
     * @param subject subjects to get marks on
     * @return all marks for subject
     */
    public List<Mark> getMarksForSubject(Subject subject);

    /**
     * get all marks for student
     *
     * @param student student to get marks for
     * @return all marks for student
     */
    public List<Mark> getMarksForStudent(Student student);

    /**
     * get all marks putted by lecturer
     *
     * @param lecturer lecturer to get all marks
     * @return all marks putted by lecturer
     */
    public List<Mark> getMarksForLecturer(Lecturer lecturer);

    /**
     * get all marks for student on subject
     *
     * @param student student to get marks for
     * @param subject subject to get marks for
     * @return marks for student on subject
     */
    public List<Mark> getMarksForStudentAndSubject(Student student, Subject subject);

    /**
     * get all marks putted by lecturer on subject
     *
     * @param lecturer lecturer to get marks
     * @param subject  subject to get marks for
     * @return marks putted by lecturer on subject
     */
    public List<Mark> getMarksForLecturerAndSubject(Lecturer lecturer, Subject subject);

    /**
     * get all marks for group on subject
     *
     * @param subject subject to get marks on
     * @param object  group to get marks for
     * @return all marks for group on subject
     */
    public List<Mark> getMarksForGroupAndSubject(Subject subject, Object object);

    /**
     * update mark in database
     *
     * @param mark mark to update
     */
    public void updateMark(Mark mark);
}
