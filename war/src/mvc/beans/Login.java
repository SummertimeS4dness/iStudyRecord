package mvc.beans;

import java.lang.Object;

public class Login {

    private String nickname;
    private String password;
    private String status;

    public Login() {
        super();
    }

    public Login(String nickname, String password, String status) {
        this.nickname = nickname;
        this.password = password;
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

@Override
public String toString() {
    return "Login{" +
            "nickname='" + nickname + '\'' +
            ", password='" + password + '\'' +
            ", status='" + status + '\'' +
            '}';
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Login login = (Login) o;
    
    if (!nickname.equals(login.nickname)) return false;
    return password.equals(login.password);
}

@Override
public int hashCode() {
    int result = nickname.hashCode();
    result = 31 * result + password.hashCode();
    return result;
}
}
