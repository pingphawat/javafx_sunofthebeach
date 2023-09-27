package ku.cs.models.account;

import java.util.ArrayList;

public class AdminList  {
    private ArrayList<Admin> admins;

    public AdminList() {
        admins = new ArrayList<>();
    }

    public void addAdmin(Admin member) {
        admins.add(member);
    }

    public ArrayList<Admin> getAllAdmins() {
        return admins;
    }

    public Admin findAccount(String username) {
        Admin found = null;
        for (Admin admin : admins) {
            if (username.equals(admin.getUsername())) {
                found = admin;
                return found;
            }
        }
        return found;
    }
}
