package transactions.entity;

/**
 * Created by andrei on 28.11.17.
 */
public class Client {
    private int id_client;
    private int role_id;
    private int rating;
    private String username;
    private String first_name;
    private String last_name;
    private String passport;
    private String address;
    private String phone_number;
    private String email;
    private String password;

    public Client() {
    }

    public Client(int role_id, String first_name, String last_name, String passport, int rating, String address, String phone_number, String email, String password, String username) {
        this.role_id = role_id;
        this.rating = rating;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.passport = passport;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
    }

    public Client(int id_client, int role_id, String first_name, String last_name, String passport, int rating, String address, String phone_number, String email, String password, String username) {
        this.role_id = role_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.passport = passport;
        this.rating = rating;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.username = username;
        this.id_client = id_client;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
