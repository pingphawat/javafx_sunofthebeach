package ku.cs.controllers.officer;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Officer;
import ku.cs.models.account.OfficerList;
import ku.cs.services.OfficerListDataSource;
import ku.cs.services.Information;
import ku.cs.services.DataSource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OfficerProfileController {
    private Information information;
    private DataSource<OfficerList> officerListDataSource;
    private OfficerList officerList;
    private Officer officer;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmField;
    @FXML private Label errorLabel;
    @FXML private Label successImageLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label departmentLabel;
    @FXML private Label successLabel;
    @FXML private ImageView profile;
    private BufferedImage bi = null;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        officer = information.getOfficer();
        officerListDataSource = new OfficerListDataSource("data","officer.csv");

        usernameTextField.setText(officer.getUsername());
        nameLabel.setText(officer.getName());
        surnameLabel.setText(officer.getSurname());
        departmentLabel.setText(officer.getDepartment());
        errorLabel.setText("");
        successImageLabel.setText("");
        successLabel.setText("");

        String directoryName = "data" + File.separator + "images" + File.separator + "avatar";
        String filepath = directoryName + File.separator + officer.getUsername() + ".png";
        profile.setImage(new Image(new File(filepath).toURI().toString()));

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void changePasswordButton(ActionEvent actionEvent) {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirm = confirmField.getText();

        officerList = officerListDataSource.readData();

        if (oldPassword.isEmpty() == true || newPassword.isEmpty() == true || confirm.isEmpty() == true){
            successLabel.setText("");
            errorLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        } else if (!(newPassword.equals(confirm))){
            successLabel.setText("");
            errorLabel.setText("password ไม่ตรงกัน");
        } else if (!(oldPassword.equals(officer.getPassword()))){
            successLabel.setText("");
            errorLabel.setText("password ไม่ถูกต้อง");
        } else{
            officer = officerList.findAccount(officer.getUsername());
            if (!officer.isValidPassword(newPassword)){
                successLabel.setText("");
                errorLabel.setText("ไม่สามารถใช้รหัสผ่านนี้ได้");
            } else{
                officer.setPassword(newPassword);
                officer.setImage(bi);
                officerListDataSource.writeData(officerList);
                information.setOfficer(officer);

                errorLabel.setText("");
                successLabel.setText("เปลี่ยนรหัสผ่านสำเร็จ");
                oldPasswordField.setText("");
                newPasswordField.setText("");
                confirmField.setText("");
            }
        }
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("officerHome", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า officerHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void uploadButton(ActionEvent actionEvent){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
        File picFile = fileChooser.showOpenDialog(stage);
        if (picFile != null) {
            profile.setImage(new Image(picFile.toURI().toString()));
            try {
                bi = ImageIO.read(picFile);
            } catch (IOException e) {
                System.err.println("Cannot load picture");
            }
        }
        officerList = officerListDataSource.readData();
        officer = officerList.findAccount(officer.getUsername());
        officer.setImage(bi);
        if (bi != null){
            successImageLabel.setText("เปลี่ยนรูปภาพสำเร็จ");
        }
    }
}
