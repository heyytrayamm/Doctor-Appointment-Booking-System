public class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String password;
    protected String phone;

    public User(int userId, String name, String email,
                String password, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public void login() {
        System.out.println(name + " logged in.");
    }

    public void logout() {
        System.out.println(name + " logged out.");
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}