package mvc.beans;

import java.lang.Object;

/**
 * Bean Subject.
 */
public class Subject {
    private int id;
    private String shortName;
    private String fullName;
    private String info;
    private int lecturerId;
    private int amount;
    private String lecturerName;


    /**
     * Instantiates a new Subject.
     *
     * @param id         subject's id
     * @param shortName  subject's short name
     * @param fullName   subject'ss full name
     * @param info       subject's info
     * @param lecturerId subject's lecturer's id
     */
    public Subject(int id, String shortName, String fullName, String info, int lecturerId) {
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
        this.info = info;
        this.lecturerId = lecturerId;
    }

    /**
     * Instantiates a new Subject.
     *
     * @param id subject's id
     */
    public Subject(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new Subject.
     */
    public Subject() { super();}
    /**
     * Gets subject's lecturer's name.
     *
     * @return subject's lecturer's name
     */
    public String getLecturerName() {
        return lecturerName;
    }

    /**
     * Sets subject's lecturer's name.
     *
     * @param lecturerName subject's lecturer's name
     */
    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    /**
     * Gets subject's amount.
     *
     * @return subject's amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount subject's amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    /**
     * Gets subject's id.
     *
     * @return subject's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets subject's id.
     *
     * @param id subject's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets subject's short name.
     *
     * @return subject's short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets short name.
     *
     * @param shortName subject's short name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Gets subject's full name.
     *
     * @return subject's full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets subject's full name.
     *
     * @param fullName subject's full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets subject's info.
     *
     * @return subject's info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets subject's info.
     *
     * @param info subject's info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Gets subject's lecturer's id.
     *
     * @return subject's lecturer id
     */
    public int getLecturerId() {
        return lecturerId;
    }

    /**
     * Sets subject's lecturer's id.
     *
     * @param lecturerId subject's lecturer's id
     */
    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

@Override
public String toString() {
    return "Subject{" +
            "id=" + id +
            ", shortName='" + shortName + '\'' +
            ", fullName='" + fullName + '\'' +
            ", info='" + info + '\'' +
            ", lecturerId=" + lecturerId +
            ", amount=" + amount +
            ", lecturerName='" + lecturerName + '\'' +
            '}';
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Subject subject = (Subject) o;
    
    if (id != subject.id) return false;
    return lecturerId == subject.lecturerId;
}

@Override
public int hashCode() {
    int result = id;
    result = 31 * result + lecturerId;
    return result;
}
}
