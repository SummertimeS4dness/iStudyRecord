package mvc.beans;

import java.lang.Object;

/**
 * Bean Lecturer.
 */
public class Lecturer {
    private int id;
    private String login;
    private String password;
    private String name;
    private String info;
    private String degree;
    private String works;
    private String interests;
    
    
    private String cathedra;
    private int cathedraId;

    /**
     * Gets lecturer's cathedra.
     *
     * @return the cathedra
     */
    public String getCathedra() {
    return cathedra;
}

    /**
     * Sets lecturer's cathedra.
     *
     * @param cathedra lecturer's cathedra
     */
    public void setCathedra(String cathedra) {
    this.cathedra = cathedra;
}

    /**
     * Gets lecturer's cathedra's id.
     *
     * @return lecturer's cathedra's id
     */
    public int getCathedraId() {
    return cathedraId;
}

    /**
     * Sets lecturer's cathedra's id.
     *
     * @param cathedraId lecturer's cathedra's id
     */
    public void setCathedraId(int cathedraId) {
    this.cathedraId = cathedraId;
}


    /**
     * Instantiates a new Lecturer.
     *
     * @param id        lecturer's id
     * @param login     lecturer's login
     * @param password  lecturer's password
     * @param name      lecturer's name
     * @param info      lecturer's info
     * @param degree    lecturer's degree
     * @param works     lecturer's works
     * @param interests lecturer's interests
     */
    public Lecturer(int id, String login, String password, String name, String info, String degree, String works, String interests) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.info = info;
        this.degree = degree;
        this.works = works;
        this.interests = interests;
    }

    /**
     * Instantiates a new Lecturer.
     *
     * @param id       lecturer's id
     * @param login    lecturer's login
     * @param password lecturer's password
     */
    public Lecturer(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    /**
     * Instantiates a new Lecturer.
     *
     * @param login     lecturer's login
     * @param password  lecturer's password
     * @param name      lecturer's name
     * @param info      lecturer's info
     * @param degree    lecturer's degree
     * @param works     lecturer's works
     * @param interests lecturer's interests
     */
    public Lecturer(String login, String password, String name, String info, String degree, String works, String interests) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.info = info;
        this.degree = degree;
        this.works = works;
        this.interests = interests;
    }

    /**
     * Instantiates a new Lecturer.
     */
    public Lecturer() {
        super();
    }

    /**
     * Sets lecturer's id.
     *
     * @param id lecturer's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets lecturer's login.
     *
     * @param login lecturer's login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets lecturer's password.
     *
     * @param password lecturer's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets lecturer's name.
     *
     * @param name lecturer's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets lecturer's info.
     *
     * @param info lecturer's info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Sets lecturer's degree.
     *
     * @param degree lecturer's degree
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * Sets works.
     *
     * @param works the works
     */
    public void setWorks(String works) {
        this.works = works;
    }

    /**
     * Sets lecturer's interests.
     *
     * @param interests lecturer's interests
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     * Gets lecturer's id.
     *
     * @return lecturer's id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets lecturer's login.
     *
     * @return lecturer's login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets lecturer's password.
     *
     * @return lecturer's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets lecturer's name.
     *
     * @return lecturer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets lecturer's info.
     *
     * @return lecturer's info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Gets lecturer's degree.
     *
     * @return lecturer's degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Gets lecturer's works.
     *
     * @return lecturer's works
     */
    public String getWorks() {
        return works;
    }

    /**
     * Gets lecturer's interests.
     *
     * @return lecturer's interests
     */
    public String getInterests() {
        return interests;
    }

@Override
public String toString() {
    return "Lecturer{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", info='" + info + '\'' +
            ", degree='" + degree + '\'' +
            ", works='" + works + '\'' +
            ", interests='" + interests + '\'' +
            ", cathedra='" + cathedra + '\'' +
            ", cathedraId=" + cathedraId +
            '}';
}

@Override
public boolean equals(Object a) {
    Lecturer o=(Lecturer) a;
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Lecturer lecturer = (Lecturer) o;
    
    if (id != lecturer.id) return false;
    return login.equals(lecturer.login);
}

@Override
public int hashCode() {
    int result = id;
    result = 31 * result + login.hashCode();
    return result;
}
}
