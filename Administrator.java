package model;

public class Administrator extends User {

    public static final int ADMIN_ID = 301;

    public static final String ADMIN_PASSWORD = "1234";

    public Administrator(int id, String name, String email, String password, String phone) {

        super(id, name, email, password, phone);
    }

    // Administrator login
    public static boolean login(int id, String password) {

        if (id == ADMIN_ID && password.equals(ADMIN_PASSWORD)) {

            return true;
        }

        return false;
    }
}