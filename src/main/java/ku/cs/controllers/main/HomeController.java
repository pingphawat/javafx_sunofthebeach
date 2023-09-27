package ku.cs.controllers.main;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInDownBig;
import animatefx.animation.FadeInRightBig;
import animatefx.animation.Flash;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Admin;
import ku.cs.models.account.AdminList;
import ku.cs.services.AdminListDataSource;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.NisitListDataSource;
import ku.cs.models.account.Officer;
import ku.cs.models.account.OfficerList;
import ku.cs.services.OfficerListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeController {
    private Information information;
    @FXML private TextField userID;
    @FXML private TextField hiddenPassword;
    @FXML private PasswordField userPassword;
    @FXML private CheckBox showPassword;
    @FXML private Label errorLabel;
    private DataSource<NisitList> nisitListDataSource;
    private DataSource<OfficerList> officerListDataSource;
    private DataSource<AdminList> adminListDataSource;
    private NisitList nisitList;
    private OfficerList officerList;
    private AdminList adminList;
    @FXML private Button mode;
    @FXML private AnchorPane root;
    @FXML private VBox vVbox;
    @FXML private VBox vboxLogin;
    @FXML private ImageView username;
    @FXML private ImageView password;
    @FXML private ImageView login;

    @FXML public void initialize(){
        ThemeMode.setThemeMode(root);
        setTextImage();

        errorLabel.setText("");
        nisitListDataSource = new NisitListDataSource("data","register.csv");
        officerListDataSource = new OfficerListDataSource("data","officer.csv");
        adminListDataSource = new AdminListDataSource("data","admin.csv");
        nisitList = nisitListDataSource.readData();
        adminList = adminListDataSource.readData();
        officerList = officerListDataSource.readData();
        information = new Information();

        new FadeInRightBig(vVbox).play();
        new FadeIn(root).play();
        new FadeInDownBig(vboxLogin).play();
    }

    private void setTextImage() {
        String usernameUrl;
        String passwordUrl;
        String loginUrl;

        if (ThemeMode.isDarkMode()) {
            usernameUrl = getClass().getResource("/ku/cs/images/white-username.png").toExternalForm();
            passwordUrl = getClass().getResource("/ku/cs/images/white-password.png").toExternalForm();
            loginUrl = getClass().getResource("/ku/cs/images/white-login.png").toExternalForm();
        } else {
            usernameUrl = getClass().getResource("/ku/cs/images/username.png").toExternalForm();
            passwordUrl = getClass().getResource("/ku/cs/images/password.png").toExternalForm();
            loginUrl = getClass().getResource("/ku/cs/images/login.png").toExternalForm();
        }

        username.setImage(new Image(usernameUrl));
        password.setImage(new Image(passwordUrl));
        login.setImage(new Image(loginUrl));
    }

    @FXML public void handleDarkMode(ActionEvent event) {
        ThemeMode.toggleTheme();
        ThemeMode.setThemeMode(root);
        setTextImage();
        new Flash(root).play();
    }

    @FXML public void handleLoginButton(ActionEvent actionEvent) {
        String username = userID.getText();
        String password;
        if(showPassword.isSelected()){
            password = hiddenPassword.getText();
        } else{
            password = userPassword.getText();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String lastLoginDateTime = dtf.format(LocalDateTime.now());

        if (username.isEmpty() == true || password.isEmpty() == true) {
            errorLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        } else if(nisitList.findAccount(username) != null){
            Nisit nisit = nisitList.findAccount(username);
            if(nisit.isCorrectPassword(password)){
                if(nisit.isAvailable()) {
                    nisit.setLoginStatus(true);
                    nisit.setLastLoginDateTime(lastLoginDateTime);
                    nisitListDataSource.writeData(nisitList);
                    information.setNisit(nisit);
                    try {
                        FXRouter.goTo("nisitHome", information);
                    } catch (IOException e) {
                        System.err.println("ไปที่หน้า nisitHome ไม่ได้");
                        System.err.println("ให้ตรวจสอบการกําหนด route");
                        e.printStackTrace();
                    }
                } else{
                    nisit.tryToLogin();
                    nisitListDataSource.writeData(nisitList);
                    information.setNisit(nisit);
                    try {
                        FXRouter.goTo("claiming", information);
                    } catch (IOException e) {
                        System.err.println("ไปที่หน้า claiming ไม่ได้");
                        System.err.println("ให้ตรวจสอบการกําหนด route");
                        e.printStackTrace();
                    }
                }
            } else{
                errorLabel.setText("รหัสผ่านไม่ถูกต้อง");
            }
        } else if(adminList.findAccount(username) != null){
            Admin admin = adminList.findAccount(username);
            if(admin.isCorrectPassword(password)){
                admin.setLoginStatus(true);
                admin.setLastLoginDateTime(lastLoginDateTime);
                adminListDataSource.writeData(adminList);
                information.setAdmin(admin);
                try {
                    FXRouter.goTo("adminHome", information);
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า adminHome ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกําหนด route");
                    e.printStackTrace();
                }
            } else{
                errorLabel.setText("รหัสผ่านไม่ถูกต้อง");
            }
        } else if(officerList.findAccount(username) != null){
            Officer officer = officerList.findAccount(username);
           if(officer.isCorrectPassword(password)){
               officer.setLoginStatus(true);
               officer.setLastLoginDateTime(lastLoginDateTime);
               officerListDataSource.writeData(officerList);
               information.setOfficer(officer);
               try {
                   FXRouter.goTo("officerHome", information);
               } catch (IOException e) {
                   System.err.println("ไปที่หน้า officerHome ไม่ได้");
                   System.err.println("ให้ตรวจสอบการกําหนด route");
                   e.printStackTrace();
               }
           } else{
               errorLabel.setText("รหัสผ่านไม่ถูกต้อง");
           }
        } else{
            errorLabel.setText("ชื่อบัญชีไม่ถูกต้อง");
        }
    }

    @FXML public void handleRegisterButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML public void handlePasswordCheckbox(ActionEvent actionEvent){
        if(showPassword.isSelected()){
            hiddenPassword.setText(userPassword.getText());
            hiddenPassword.setVisible(true);
            userPassword.setVisible(false);
            return;
        }
        userPassword.setText(hiddenPassword.getText());
        hiddenPassword.setVisible(false);
        userPassword.setVisible(true);
    }

    @FXML public void handleOrganizerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("organizer");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า organizer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML public void handleAboutProgramButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("aboutProgram");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า aboutProgram ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}