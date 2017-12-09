package transactions.entity;

/**
 * Created by andrei on 26.11.17.
 */
public class User {
    private int id_user;
    private int role_id;
    private String name;
    private String password;

    public User() {
    }

    public User(int role_id, String name, String password) {
        this.role_id = role_id;
        this.name = name;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
