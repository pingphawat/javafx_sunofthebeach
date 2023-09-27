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
import ku.cs.models.account.Officer;
import ku.cs.services.Information;
import java.io.File;
import java.io.IOException;

public class OfficerDetailController {
    private Information information;
    private Officer officer;
    @FXML public Label usernameLabel;
    @FXML public Label nameLabel;
    @FXML public Label surnameLabel;
    @FXML public Label departmentLabel;
    @FXML public Label statusLabel;
    @FXML public Label lastLoginDateTimeLabel;
    @FXML public ImageView profile;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        officer = information.getOfficer();

        usernameLabel.setText(officer.getUsername());
        nameLabel.setText(officer.getName());
        surnameLabel.setText(officer.getSurname());
        departmentLabel.setText(officer.getDepartment());
        lastLoginDateTimeLabel.setText(officer.getLastLoginDateTime());
        if(officer.isOnline()){
            statusLabel.setText("online");
        } else{
            statusLabel.setText("offline");
        }

        String directoryName = "data" + File.separator + "images" + File.separator + "avatar";
        String filepath = directoryName + File.separator + officer.getUsername() + ".png";
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
