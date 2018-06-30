package mvc.beans;

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
}
