package mvc.beans;

import java.lang.Object;

/**
 * Bean Student.
 */
public class Student {
    
    private int id;
    private String login;
    private String password;
    private String name;
    private int isStarosta;
    private String group;
    private int groupId;


    /**
     * Instantiates a new Student.
     *
     * @param id         student's id
     * @param isStarosta student's starosta's status
     * @param groupId    student's group's id
     */
    public Student(int id, int isStarosta,int groupId) {
    this.id = id;
    this.isStarosta = isStarosta;
    this.groupId=groupId;
}
    /**
     * Instantiates a new Student.
     *
     * @param id       student's id
     * @param login    student's login
     * @param password student's password
     * @param name     student's name
     */
    public Student(int id, String login, String password, String name) {
        this.id = id;
        this.login=login;
        this.password = password;
        this.name = name;
    }

    /**
     * Instantiates a new Student.
     *
     * @param id       student's id
     * @param login    student's login
     * @param password student's password
     */
    public Student(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    /**
     * Instantiates a new Student.
     */
    public Student() {
        super();
    }
    /**
     * Gets student's starosta's status.
     *
     * @return student's starosta status
     */
    public int getIsStarosta() {
        return isStarosta;
    }

    /**
     * Sets student's starosta's status.
     *
     * @param isStarosta student's starosta's status
     */
    public void setIsStarosta(int isStarosta) {
    this.isStarosta = isStarosta;
}

    /**
     * Gets student's group.
     *
     * @return student's group
     */
    public String getGroup() {
    return group;
}

    /**
     * Sets student's group.
     *
     * @param group student's group
     */
    public void setGroup(String group) {
    this.group = group;
}

    /**
     * Gets student's group's id.
     *
     * @return student's group's id
     */
    public int getGroupId() {
    return groupId;
}

    /**
     * Sets student's group's id.
     *
     * @param groupId student's group's id
     */
    public void setGroupId(int groupId) {
    this.groupId = groupId;
}



    /**
     * Sets student's id.
     *
     * @param id student's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets student's login.
     *
     * @return student's login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets student's login.
     *
     * @param login student's login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets student's password.
     *
     * @param password student's password
     */
    public void setPassword(String password) {
            this.password = password;
        }

    /**
     * Sets student's name.
     *
     * @param name student's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets student's id.
     *
     * @return student's id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets student's password.
     *
     * @return student's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets student's name.
     *
     * @return student's name
     */
    public String getName() {
        return name;
    }

@Override
public String toString() {
    return "Student{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", isStarosta=" + isStarosta +
            ", group='" + group + '\'' +
            ", groupId=" + groupId +
            '}';
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Student student = (Student) o;
    
    if (id != student.id) return false;
    return login.equals(student.login);
}

@Override
public int hashCode() {
    int result = id;
    result = 31 * result + login.hashCode();
    return result;
}
}
