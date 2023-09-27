package ku.cs.controllers.main;

import animatefx.animation.*;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.models.account.AdminList;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.AdminListDataSource;
import ku.cs.services.NisitListDataSource;
import ku.cs.models.account.OfficerList;
import ku.cs.services.OfficerListDataSource;
import ku.cs.services.DataSource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import ku.cs.services.ThemeMode;

public class RegisterController {
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField surnameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    @FXML private ImageView profile = null;
    private BufferedImage bi = null;
    private DataSource<NisitList> nisitListDataSource;
    private DataSource<AdminList> adminListDataSource;
    private DataSource<OfficerList> officerListDataSource;
    private NisitList nisitList;
    private AdminList adminList;
    private OfficerList officerList;
    @FXML private AnchorPane root;
    @FXML private Button VBox;

    @FXML public void initialize(){
        errorLabel.setText("");
        successLabel.setText("");
        nisitListDataSource = new NisitListDataSource("data","register.csv");
        adminListDataSource = new AdminListDataSource("data","admin.csv");
        officerListDataSource = new OfficerListDataSource("data","officer.csv");
        nisitList = nisitListDataSource.readData();
        adminList = adminListDataSource.readData();
        officerList = officerListDataSource.readData();

        new FadeInLeftBig(VBox).play();
        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void registerButton(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String confirm = confirmField.getText();
        Nisit nisit = new Nisit(name, surname, username, password);

        if ((name.isEmpty() == true || username.isEmpty() == true || password.isEmpty() == true || confirm.isEmpty() == true || surname.isEmpty() == true)) {
            successLabel.setText("");
            errorLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        } else if (nisitList.findAccount(username) != null || adminList.findAccount(username) != null || officerList.findAccount(username) != null) {
            errorLabel.setText("ชื่อบัญชีนี้เคยถูกลงทะเบียนแล้ว");
            successLabel.setText("");
        } else if (!nisit.isValidUsername(username)) {
            successLabel.setText("");
            errorLabel.setText("ไม่สามารถใช้ชื่อบัญชีได้");
        } else if (!nisit.isValidPassword(password)) {
            successLabel.setText("");
            errorLabel.setText("ไม่สามารถใช้รหัสผ่านนี้ได้");
        } else if (password.equals(confirm) == false) {
            successLabel.setText("");
            errorLabel.setText("password ไม่ตรงกัน");
        } else {
            nisit.setImage(bi);
            nisitList.addNisit(nisit);
            nisitListDataSource.writeData(nisitList);
            errorLabel.setText("");
            successLabel.setText("ลงทะเบียนสำเร็จ");
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
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("home");
        } catch(IOException e){
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
