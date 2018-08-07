package mvc.beans;

import java.lang.Object;

/**
 * Bean Login.
 */
public class Login {

    private String nickname;
    private String password;
    private String status;

    /**
     * Instantiates a new Login.
     */
    public Login() {
        super();
    }

    /**
     * Instantiates a new Login.
     *
     * @param nickname login's nickname
     * @param password login's password
     * @param status   login's status
     */
    public Login(String nickname, String password, String status) {
        this.nickname = nickname;
        this.password = password;
        this.status = status;
    }

    /**
     * Gets login's nickname.
     *
     * @return login's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets login's nickname.
     *
     * @param nickname login's nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Gets login's password.
     *
     * @return login's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets login's password.
     *
     * @param password login's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets login's status.
     *
     * @return login's status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets login's status.
     *
     * @param status login's status
     */
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
