package ku.cs.models.account;

import javafx.scene.image.Image;
import ku.cs.services.NisitListDataSource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    protected String username;
    private String password;
    private String name;
    private String surname;
    private boolean loginStatus;
    private String lastLoginDateTime;
    protected Image picture;

    public User(String name, String surname, String username, String password){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        loginStatus = false;
        lastLoginDateTime = "-";
    }

    public User(String name, String surname, String username, String password, boolean loginStatus, String lastLoginDateTime){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.loginStatus = loginStatus;
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean isOnline(){
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLastLoginDateTime(){
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(String lastLoginDateTime){
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public LocalDateTime getLastLoginLocalDateTime() {
        lastLoginDateTime = getLastLoginDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime lastLoginLocalDateTime = LocalDateTime.parse(lastLoginDateTime, dtf);
        return lastLoginLocalDateTime;
    }

    public void setImage(BufferedImage bi) {
        String directoryName = "data" + File.separator + "images";
        String filepath = directoryName + File.separator + getUsername() + ".png";

        NisitListDataSource ulData = new NisitListDataSource(false);
        ulData.setImageFile(bi, directoryName, getUsername() + ".png");

        this.picture = ulData.getImage(filepath);
    }

    public boolean isValidPassword(String password) {
        return ((password!= null)
                && (!password.equals(""))
                && (password.matches("^[a-zA-Z0-9]{3,20}$")));
    }

    public boolean isValidUsername(String username) {
        return ((username!= null)
                && (!username.equals(""))
                && (username.matches("^[a-zA-Z]+(.+){3,20}$")));
    }

    public boolean isCorrectPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return username;
    }

    public String toCSV() {
        return name + "," + surname + "," + username + "," + password + "," + loginStatus + "," + lastLoginDateTime;
    }
}
