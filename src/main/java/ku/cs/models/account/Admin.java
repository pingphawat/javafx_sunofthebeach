package ku.cs.models.account;

public class Admin extends User {
    public Admin(String name, String surname, String username, String password){
        super(name, surname, username, password);
    }

    public Admin(String name, String surname, String username, String password, boolean loginStatus, String lastLoginDateTime) {
        super(name, surname, username, password, loginStatus, lastLoginDateTime);
    }
}
