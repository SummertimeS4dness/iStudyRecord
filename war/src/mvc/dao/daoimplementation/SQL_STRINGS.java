package mvc.dao.daoimplementation;

public abstract class SQL_STRINGS {

public static final String selectID = "(SELECT OBJECT_SEQUENCE.nextval FROM DUAL)";
public static final String objectInsert = "insert into OBJECTS values(?,?,?,?,?,?)";
public static final String createLecturer = "INSERT INTO LECTURERS VALUES (?,?,?,?,?,?,?,?)";
public static final String removeLecturer = "DELETE FROM OBJECTS WHERE OBJECT_ID=?";
public static final String getLecturerForSubject = "SELECT * FROM LECTURERS JOIN SUBJECTS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
		"WHERE SUBJECT_ID=?";
public static final String getGetLecturersForSubject ="SELECT * FROM LECTURERS JOIN SUBJECTS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
		"WHERE SUBJECT_ID=?";
public static final String getLecturerForMark = "SELECT * FROM LECTURERS JOIN MARKS ON (MARKS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
		"WHERE MARKS.LECTURER_ID=?";
public static final String getLecturerForLesson = "SELECT * FROM LECTURERS JOIN LESSONS ON (LESSONS.LECTURER_ID=LECTURERS.LECTURER_ID)" +
		"WHERE LESSON_ID=?";
public static final String getLecturersForStudent = "SELECT * FROM LECTURERS JOIN STUDENT_SUBJECT_LISTS on(LECTURERS.LECTURER_ID= STUDENT_SUBJECT_LISTS.LECTURER_ID)" +
		"WHERE STUDENT_ID = ?";
public static final String getlecturers = "SELECT * FROM LECTURERS";
public static final String validateLecturer  = "SELECT * FROM LECTURERS WHERE LECTURER_LOGIN = ? AND LECTURER_PASSWORD=?";
public static final String updateLecturer = "UPDATE LECTURERS SET LECTURER_LOGIN =?,LECTURER_PASSWORD=?,LECTURER_NAME=?,LECTURER_INFO=?,LECTURER_DEGREE=?,LECTURER_WORKS=?,LECTURER_INTERESTS=? WHERE LECTURER_ID=?";
public static final String getLecturerById = "SELECT * FROM LECTURERS WHERE LECTURER_ID=?";
public static final String validateAdmin = "SELECT * FROM OBJECTS WHERE ADMIN_LOGIN = ? AND ADMIN_PASSWORD=?";


public static final String updateLessonDate = "UPDATE LESSONS SET LESSON_DATE =TO_DATE(TO_CHAR(?),'YYYY-MM-DD HH24:MI') WHERE LESSON_ID=?";
public static final String addLesson = "INSERT INTO LESSONS VALUES (LESSON_SEQUENCE.nextval,TO_DATE(TO_CHAR(?),'YYYY-MM-DD HH24:MI'),?,?)";
public static final String removeLesson = "DELETE FROM LESSONS WHERE LESSON_ID=?";
public static final String allLessons = "SELECT * FROM LESSONS";
public static final String getLessonForStudent = "SELECT * FROM LESSONS JOIN STUDENT_SUBJECT_LISTS ON" +
		" (LESSONS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) JOIN SUBJECTS ON (STUDENT_SUBJECT_LISTS.SUBJECT_ID=SUBJECTS.SUBJECT_ID)" +
		" JOIN LECTURERS ON (SUBJECTS.LECTURER_ID=LECTURERS.LECTURER_ID) WHERE STUDENT_ID=?";
public static final String getLessonForLecturer = "SELECT * FROM LESSONS WHERE LESSONS.LECTURER_ID=?";
public static final String getLessonForSubject = "SELECT * FROM LESSONS WHERE LESSONS.SUBJECT_ID=?";
public static final String getLessonForGroup = "SELECT * FROM LESSONS  JOIN STUDENT_SUBJECT_LISTS  ON (LESSONS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID )" +
		"JOIN LECTURERS ON (LECTURERS.LECTURER_ID=LESSONS.LECTURER_ID) " +
		"JOIN SUBJECTS ON (LESSONS.SUBJECT_ID=SUBJECTS.SUBJECT_ID)" +
		" WHERE STUDENT_ID IN (SELECT OBJECT_ID FROM OBJECTS WHERE PARENT_ID=?)";

public static final String createMark = "INSERT INTO MARKS VALUES (MARK_SEQUENCE.nextval,?,?,?,?,?)";
public static final String removeMark = "DELETE FROM MARKS WHERE MARK_ID=?";
public static final String getMarksForGroup = "SELECT * from MARKS WHERE SUBJECT_ID=?";
public static final String getMarksForStudent = "SELECT * from MARKS JOIN LESSONS ON (MARKS.LESSON_ID=LESSONS.LESSON_ID) " +
		"JOIN SUBJECTS ON (LESSONS.SUBJECT_ID = SUBJECTS.SUBJECT_ID) WHERE STUDENT_ID=?";
public static final String getMarksForLecturer = "SELECT * from MARKS WHERE LECTURER_ID=?";
public static final String getMarksForStudentAndSubject = "SELECT * from MARKS WHERE SUBJECT_ID=? AND STUDENT_ID=?";
public static final String getMarksForLecturerAndSubject = "SELECT * from MARKS WHERE SUBJECT_ID=? AND LECTURER_ID=?";
public static final String getMarksForGroupAndSubject = "SELECT * from MARKS JOIN OBJECTS ON (MARKS.STUDENT_ID=OBJECTS.OBJECT_ID) JOIN STUDENT_INFO ON" +
		" (OBJECTS.OBJECT_ID=STUDENT_INFO.STUDENT_ID) JOIN LESSONS ON (MARKS.LESSON_ID=LESSONS.LESSON_ID) WHERE " +
		"MARKS.SUBJECT_ID=? AND PARENT_ID=?";
public static final String updateMark = "UPDATE MARKS SET SCORE=? WHERE MARK_ID=?";

public static final String getGroups = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='group' ORDER BY OBJECT_ID";
public static final String getStudentsObjects = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='student' ORDER BY OBJECT_ID";
public static final String getLecturers = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='lecturer'ORDER BY OBJECT_ID";
public static final String getCathedras = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='cathedra'ORDER BY OBJECT_ID";
public static final String getFaculties = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='faculty'ORDER BY OBJECT_ID";
public static final String getUniversities = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE='university'ORDER BY OBJECT_ID";
public static final String createObject = "INSERT INTO OBJECTS VALUES (OBJECT_SEQUENCE.nextval,?,?,?,?,?)";
public static final String removeObject = "DELETE FROM OBJECTS WHERE OBJECT_ID=?";
public static final String getObjects = "SELECT a.*, b.OBJECT_DESCRIPTOIN FROM OBJECTS a, OBJECTS b WHERE a.PARENT_ID=b.OBJECT_ID ORDER BY a.OBJECT_ID";
public static final String getParent = "SELECT * FROM OBJECTS  WHERE OBJECT_ID=(SELECT PARENT_ID FROM OBJECTS  WHERE OBJECT_ID=?) ORDER BY OBJECT_ID";
public static final String getChildObjects = "SELECT * FROM OBJECTS WHERE PARENT_ID=? ORDER BY OBJECT_ID";
public static final String updateObject = "UPDATE OBJECTS SET PARENT_ID=?, OBJECT_DESCRIPTOIN = ? WHERE OBJECT_ID=?";
public static final String showGroupsForSubject = "SELECT * FROM OBJECTS JOIN STUDENT_SUBJECT_LISTS ON (OBJECTS.OBJECT_ID = STUDENT_SUBJECT_LISTS.STUDENT_ID) " +
		"JOIN SUBJECTS ON (SUBJECTS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) WHERE SUBJECTS.SUBJECT_ID=?";

public static final String createStudent = "insert into STUDENT_INFO values(?,?,?,?,?)";
public static final String removeStudent = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";
public static final String getStudents = "SELECT * FROM STUDENT_INFO ORDER BY STUDENT_ID";
public static final String validateStudent = "SELECT * FROM STUDENT_INFO WHERE STUDENT_LOGIN = ? AND STUDENT_PASSWORD=? ORDER BY STUDENT_ID";
public static final String registerStudentForSubject = "INSERT INTO STUDENT_SUBJECT_LISTS VALUES(?,?,?)";
public static final String getStudentsOnSubject = "select * from STUDENT_INFO JOIN STUDENT_SUBJECT_LISTS ON " +
		"(STUDENT_INFO.STUDENT_ID=STUDENT_SUBJECT_LISTS.STUDENT_ID) WHERE SUBJECT_ID=?";
public static final String getStudentForGroup = "SELECT * FROM STUDENT_INFO JOIN OBJECTS ON " +
		"(STUDENT_INFO.STUDENT_ID=OBJECTS.OBJECT_ID) WHERE PARENT_ID=?";
public static final String removeStudentFromSubject = "DELETE FROM STUDENT_SUBJECT_LISTS WHERE STUDENT_ID=? AND SUBJECT_ID=?";
public static final String updateStudent = "UPDATE STUDENT_INFO SET STUDENT_NAME=?, STUDENT_LOGIN=?,STUDENT_PASSWORD=? WHERE STUDENT_ID=?";
public static final String setStarosta1 = "UPDATE STUDENT_INFO SET  IS_STAROSTA = 0 WHERE STUDENT_ID IN (SELECT OBJECT_ID FROM OBJECTS WHERE PARENT_ID=?)";
public static final String setStarosta2 = "UPDATE STUDENT_INFO SET IS_STAROSTA=? WHERE STUDENT_ID=?";
public static final String selectParentForStudent = "SELECT * FROM OBJECTS WHERE OBJECT_ID=(SELECT PARENT_ID FROM OBJECTS WHERE OBJECT_ID=?)ORDER BY OBJECT_ID";
public static final String showStarostaForStudent = "SELECT * FROM STUDENT_INFO JOIN OBJECTS ON (STUDENT_INFO.STUDENT_ID = OBJECTS.OBJECT_ID) " +
		"WHERE OBJECTS.PARENT_ID=(SELECT PARENT_ID FROM OBJECTS WHERE OBJECT_ID=?) AND STUDENT_INFO.IS_STAROSTA=1";
public static final String getStudentById = "SELECT * FROM STUDENT_INFO WHERE STUDENT_ID=?";

public static final String createSubject = "INSERT INTO SUBJECTS VALUES (SUBJECT_SEQUENCE.nextval,?,?,?,?)";
public static final String removeSubject = "DELETE FROM SUBJECTS WHERE SUBJECT_ID=?";
public static final String getSubjects = "SELECT * FROM SUBJECTS";
public static final String showSubjectsForStudent = "SELECT * FROM SUBJECTS JOIN STUDENT_SUBJECT_LISTS " +
		"ON (SUBJECTS.SUBJECT_ID=STUDENT_SUBJECT_LISTS.SUBJECT_ID) WHERE STUDENT_ID=?";
public static final String showSubjectFroLecturer = "SELECT * FROM SUBJECTS WHERE LECTURER_ID=?";
public static final String showSubjectsForGroup = "SELECT * FROM SUBJECTS JOIN STUDENT_SUBJECT_LISTS ON (SUBJECTS.SUBJECT_ID = STUDENT_SUBJECT_LISTS.SUBJECT_ID) " +
		"JOIN OBJECTS ON (STUDENT_SUBJECT_LISTS.STUDENT_ID=OBJECTS.OBJECT_ID) WHERE PARENT_ID=?";
public static final String getSubjectById = "SELECT * FROM SUBJECTS WHERE SUBJECT_ID=?";
public static final String getLecturerName = "SELECT LECTURER_NAME FROM LECTURERS WHERE LECTURER_ID=?";
public static final String updateSubject = "UPDATE SUBJECTS SET SUBJECT_SHORT_NAME=?,SUBJECT_FULL_NAME=?,SUBJECT_INFO =?,LECTURER_ID=? WHERE SUBJECT_ID=?";
}
