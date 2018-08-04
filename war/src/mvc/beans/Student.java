package mvc.beans;

import java.lang.Object;

public class Student {
    
    private int id;
    private String login;
    private String password;
    private String name;
    private int isStarosta;
    private String group;
    private int groupId;

public int getIsStarosta() {
    return isStarosta;
}

public Student(int id, int isStarosta,int groupId) {
    this.id = id;
    this.isStarosta = isStarosta;
    this.groupId=groupId;
}

public void setIsStarosta(int isStarosta) {
    this.isStarosta = isStarosta;
}

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
