package ku.cs.models.account;

import java.util.ArrayList;

public class OfficerList {
    public ArrayList<Officer> officers;

    public OfficerList(){
        officers = new ArrayList<>();
    }

    public void addOfficer(Officer officer){
        officers.add(officer);
    }

    public ArrayList<Officer> getAllOfficers(){
        return officers;
    }

    public Officer findAccount(String username) {
        Officer found = null;
        for (Officer officer : officers) {
            if (username.equals(officer.getUsername())) {
                found = officer;
                return found;
            }
        }
        return found;
    }
}
