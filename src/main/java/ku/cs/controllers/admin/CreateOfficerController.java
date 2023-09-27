package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.AdminList;
import ku.cs.services.AdminListDataSource;
import ku.cs.models.account.NisitList;
import ku.cs.services.NisitListDataSource;
import ku.cs.models.account.Officer;
import ku.cs.models.account.OfficerList;
import ku.cs.services.OfficerListDataSource;
import ku.cs.services.DataSource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateOfficerController {
    private DataSource<AdminList> adminListDataSource;
    private DataSource<OfficerList> officerListDataSource;
    private DataSource<NisitList> nisitListDataSource;
    private AdminList adminList;
    private OfficerList officerList;
    private NisitList nisitList;
    private ObservableList<String> departmentList;
    @FXML private ChoiceBox departmentBox;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;
    @FXML private ImageView profile = null;
    @FXML private BufferedImage bi = null;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        adminListDataSource = new AdminListDataSource("data","admin.csv");
        officerListDataSource = new OfficerListDataSource("data","officer.csv");
        nisitListDataSource = new NisitListDataSource("data", "register.csv");
        departmentList = FXCollections.observableArrayList("หน่วยงาน", "สำนักงานพัฒนาคุณภาพและบริหารความเสี่ยง", "สำนักงานบริการวิชาการ", "สำนักงานกฏหมาย", "สำนักการกีฬา", "สำนักงานทรัพย์สิน");

        errorLabel.setText("");
        successLabel.setText("");
        setChoiceBox();

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    private void setChoiceBox(){
        departmentBox.setValue("หน่วยงาน");
        departmentBox.setItems(departmentList);
    }

    @FXML public void AddButton(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String confirm = confirmField.getText();
        String department = (String) departmentBox.getSelectionModel().getSelectedItem();
        Officer officer = new Officer(name, surname, username, password, department);
        officerList = officerListDataSource.readData();
        adminList = adminListDataSource.readData();
        nisitList = nisitListDataSource.readData();

       if (name.isEmpty() == true || username.isEmpty() == true || password.isEmpty() == true || confirm.isEmpty() == true || surname.isEmpty() == true || department.equals("หน่วยงาน")) {
           errorLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
           successLabel.setText("");
       } else if (nisitList.findAccount(username) != null ||  adminList.findAccount(username) != null || officerList.findAccount(username) != null){
           errorLabel.setText("ชื่อบัญชีนี้เคยถูกลงทะเบียนแล้ว");
           successLabel.setText("");
       } else if(!passwordField.getText().equals(confirm)) {
           errorLabel.setText("password ไม่ตรงกัน");
           successLabel.setText("");
       } else if (!officer.isValidUsername(username)) {
           successLabel.setText("");
           errorLabel.setText("ไม่สามารถใช้ชื่อบัญชีได้");
       } else if (!officer.isValidPassword(password)) {
           successLabel.setText("");
           errorLabel.setText("ไม่สามารถใช้รหัสผ่านนี้ได้");
       } else {
            officerList.addOfficer(officer);
            officerListDataSource.writeData(officerList);

            successLabel.setText("สร้างบัญชีสำเร็จ");
            errorLabel.setText("");
            nameTextField.setText("");
            surnameTextField.setText("");
            usernameTextField.setText("");
            passwordField.setText("");
            confirmField.setText("");
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
            FXRouter.goTo("adminHome");
        } catch(IOException e){
            System.err.println("ไปที่หน้า adminHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}

