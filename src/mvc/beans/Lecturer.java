package mvc.beans;

public class Lecturer {
    /*private String lecturerID;
    private String lecturerLogin;
    private String password;
    private String name;

    public Lecturer(String lecturerID, String lecturerLogin, String password, String name) {
        this.lecturerID = lecturerID;
        this.lecturerLogin = lecturerLogin;
        this.password = password;
        this.name = name;
    }

    public Lecturer() {
        super();
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getLecturerID() {

        return lecturerID;
    }

    public void setLecturerLogin(String lecturerLogin) {
        this.lecturerLogin = lecturerLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturerLogin() {
        return lecturerLogin;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }*/
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

public String getCathedra() {
    return cathedra;
}

public void setCathedra(String cathedra) {
    this.cathedra = cathedra;
}

public int getCathedraId() {
    return cathedraId;
}

public void setCathedraId(int cathedraId) {
    this.cathedraId = cathedraId;
}


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

    public Lecturer(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Lecturer(String login, String password, String name, String info, String degree, String works, String interests) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.info = info;
        this.degree = degree;
        this.works = works;
        this.interests = interests;
    }

    public Lecturer() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getDegree() {
        return degree;
    }

    public String getWorks() {
        return works;
    }

    public String getInterests() {
        return interests;
    }
}
