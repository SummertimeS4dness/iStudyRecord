package mvc.beans;

public class Student {
    private int id;
    private String studentLogin;
    private String password;
    private String name;
    private String group;
    private int starosta;


    public Student(int id, String studentLogin, String password, String name, String group, int starosta) {
        this.id = id;
        this.studentLogin = studentLogin;
        this.password = password;
        this.name = name;
        this.group = group;
        this.starosta = starosta;
    }

    public Student() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentLogin(String studentLogin) {
        this.studentLogin = studentLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setStarosta(int starosta) {
        this.starosta = starosta;
    }

    public int getId() {
        return id;
    }

    public String getStudentLogin() {
        return studentLogin;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getStarosta() {
        return starosta;
    }
}
