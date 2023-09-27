package ku.cs.models.account;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList(){
        users = new ArrayList<>();
    }

    public void addUser(User user){
        users.add(user);
    }

    public ArrayList<User> getAllUsers(){
        return users;
    }

    public User findAccount(String username) {
        User found = null;
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                found = user;
                return found;
            }
        }
        return found;
    }
}