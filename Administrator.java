public class Administrator extends User {

    public Administrator(int userId, String name,
                         String email, String password,
                         String phone) {

        super(userId, name, email, password, phone);
    }

    public void manageUser() {
        System.out.println("Managing users...");
    }

    public void manageDoctor() {
        System.out.println("Managing doctors...");
    }

    public void manageAppointment() {
        System.out.println("Managing appointments...");
    }
}