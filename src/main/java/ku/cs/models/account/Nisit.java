package ku.cs.models.account;

import ku.cs.services.NisitListDataSource;

import java.awt.image.BufferedImage;
import java.io.File;

public class Nisit extends User {
    private boolean nisitStatus;
    private int loginAttempt;

    public Nisit(String name, String surname, String username, String password){
        super(name, surname, username, password);
        nisitStatus = true;
        loginAttempt = 0;
    }

    public Nisit(String name, String surname, String username, String password, boolean loginStatus, String lastLoginDateTime) {
        super(name, surname, username, password, loginStatus, lastLoginDateTime);
        nisitStatus = true;
        loginAttempt = 0;
    }

    public Nisit(String name, String surname, String username, String password, boolean loginStatus, String lastLoginDateTime, boolean nisitStatus, int loginAttempt) {
        super(name, surname, username, password, loginStatus, lastLoginDateTime);
        this.nisitStatus = nisitStatus;
        this.loginAttempt = loginAttempt;
    }

    @Override
    public void setImage(BufferedImage bi){
        String directoryName = "data" + File.separator +
                "images" + File.separator +
                "avatar";
        String filepath = directoryName + File.separator +
                getUsername() + ".png";
        NisitListDataSource ulData = new NisitListDataSource(false);
        ulData.setImageFile(bi, directoryName, getUsername() + ".png");
        this.picture = ulData.getImage(filepath);
    }

    public boolean isAvailable(){
        return nisitStatus;
    }

    public void unBanAccount(){
        nisitStatus = true;
        loginAttempt = 0;
    }

    public void banAccount(){
        nisitStatus = false;
    }

    public int getLoginAttempt() {
        return loginAttempt;
    }

    public void tryToLogin(){
        loginAttempt += 1;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + nisitStatus + "," + loginAttempt;
    }
}
