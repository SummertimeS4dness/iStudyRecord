package mvc.beans;

public class Student {
    
    private int id;
    private String login;
    private String password;
    private String name;
    
    private String group;
    private int groupId;

public String getGroup() {
    return group;
}

public void setGroup(String group) {
    this.group = group;
}

public int getGroupId() {
    return groupId;
}

public void setGroupId(int groupId) {
    this.groupId = groupId;
}

public Student(int id, String login, String password, String name) {
        this.id = id;
        this.login=login;
        this.password = password;
        this.name = name;
    }

    public Student(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Student() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
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

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

}
