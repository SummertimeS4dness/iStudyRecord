package mvc.beans;

public class Lecturer {
    private String lecturerLogin;
    private String password;
    private String name;

    public Lecturer(String lecturerLogin, String password, String name) {
        this.lecturerLogin = lecturerLogin;
        this.password = password;
        this.name = name;
    }

    public Lecturer() {
        super();
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
    }
}
