package ku.cs.controllers.nisit;

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
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.NisitListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NisitProfileController {
    private Information information;
    private Nisit nisit;
    private DataSource<NisitList> nisitListDataSource;
    private NisitList nisitList;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmField;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label errorLabel;
    @FXML private Label successImageLabel;
    @FXML private ImageView profile ;
    @FXML private Label successLabel;
    @FXML private AnchorPane root;
    private BufferedImage bi = null;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        nisit = information.getNisit();
        nisitListDataSource = new NisitListDataSource("data", "register.csv");

        usernameTextField.setText(nisit.getUsername());
        errorLabel.setText("");
        successLabel.setText("");
        successImageLabel.setText("");
        nameLabel.setText(nisit.getName());
        surnameLabel.setText(nisit.getSurname());
        String directoryName = "data" + File.separator + "images" + File.separator + "avatar";
        String filepath = directoryName + File.separator + nisit.getUsername() + ".png";
        profile.setImage(new Image(new File(filepath).toURI().toString()));

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void changePasswordButton(ActionEvent actionEvent) {
        nisitList = nisitListDataSource.readData();

        String newPassword = newPasswordField.getText();
        String oldPassword = oldPasswordField.getText();
        String confirm = confirmField.getText();

        if (usernameTextField.getText().isEmpty() == true || oldPassword.isEmpty() == true || newPassword.isEmpty() == true || confirm.isEmpty() == true) {
            successLabel.setText("");
            errorLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        } else if (!(newPassword.equals(confirm))){
            successLabel.setText("");
            errorLabel.setText("password ไม่ตรงกัน");
        } else if (!(oldPassword.equals(nisit.getPassword()))){
            successLabel.setText("");
            errorLabel.setText("password ไม่ถูกต้อง");
        } else{
            nisit = nisitList.findAccount(nisit.getUsername());
            if (!nisit.isValidPassword(newPassword)){
                successLabel.setText("");
                errorLabel.setText("ไม่สามารถใช้รหัสผ่านนี้ได้");
            } else {
                nisit.setPassword(newPassword);
                nisitListDataSource.writeData(nisitList);
                information.setNisit(nisit);

                errorLabel.setText("");
                successLabel.setText("เปลี่ยนรหัสผ่านสำเร็จ");
                newPasswordField.setText("");
                confirmField.setText("");
                oldPasswordField.setText("");
            }
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
        nisitList = nisitListDataSource.readData();
        nisit = nisitList.findAccount(nisit.getUsername());
        nisit.setImage(bi);
        if (bi != null){
            successImageLabel.setText("เปลี่ยนรูปภาพสำเร็จ");
        }
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("nisitHome", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
