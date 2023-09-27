package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Nisit;
import ku.cs.services.Information;
import java.io.File;
import java.io.IOException;

public class NisitDetailController {
    private Information information;
    private Nisit nisit;
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label loginStatusLabel;
    @FXML private Label lastLoginDateTimeLabel;
    @FXML private Label nisitStatusLabel;
    @FXML private Label loginAttemptLabel;
    @FXML public  ImageView profile;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        nisit = information.getNisit();

        usernameLabel.setText(nisit.getUsername());
        nameLabel.setText(nisit.getName());
        surnameLabel.setText(nisit.getSurname());
        lastLoginDateTimeLabel.setText(nisit.getLastLoginDateTime());
        if(nisit.isOnline()){
            loginStatusLabel.setText("online");
        } else{
            loginStatusLabel.setText("offline");
        }
        if(nisit.isAvailable()){
            nisitStatusLabel.setText("ไม่ถูกระงับ");
            loginAttemptLabel.setText("-");
        } else{
            nisitStatusLabel.setText("ถูกระงับ");
            loginAttemptLabel.setText(""+nisit.getLoginAttempt());
        }

        String directoryName = "data" + File.separator + "images" + File.separator + "avatar";
        String filepath = directoryName + File.separator + nisit.getUsername() + ".png";
        profile.setImage(new Image(new File(filepath).toURI().toString()));

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("adminHome");
        } catch(IOException e){
            System.err.println("ไปที่หน้า userList ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
