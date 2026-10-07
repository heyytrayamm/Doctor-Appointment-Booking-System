package model;

public class User {

    public int id;
    public String name;
    public String email;
    public String password;
    public String phone;

    
    public User(int id, String name, String email, String password, String phone) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    // Login
    public void login() {

        System.out.println(name + " logged in.");
    }
}
