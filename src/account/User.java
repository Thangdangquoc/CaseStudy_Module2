package account;

import java.io.Serializable;

public class User implements Serializable {
    private static int ID_USER = 1;
    private int id;
    private String account;
    private String password;

    public User() {
    }

    public User(String account, String password) {
        this.id = ID_USER++;
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                "}";
    }
}
