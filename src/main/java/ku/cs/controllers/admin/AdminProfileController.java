package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Admin;
import ku.cs.models.account.AdminList;
import ku.cs.services.AdminListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import java.io.IOException;

public class AdminProfileController {
    private Information information;
    private Admin admin;
    private DataSource<AdminList> adminListDataSource;
    private AdminList adminList;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmField;
    @FXML private Label errorLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label successLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        admin = information.getAdmin();
        adminListDataSource = new AdminListDataSource("data", "admin.csv");

        usernameTextField.setText(admin.getUsername());
        errorLabel.setText("");
        successLabel.setText("");
        nameLabel.setText(admin.getName());
        surnameLabel.setText(admin.getSurname());

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void changePasswordButton(ActionEvent actionEvent) {
        adminList = adminListDataSource.readData();

        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirm = confirmField.getText();

        if (usernameTextField.getText().isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirm.isEmpty()) {
            successLabel.setText("");
            errorLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        } else if (!(newPassword.equals(confirm))) {
            successLabel.setText("");
            errorLabel.setText("password ไม่ตรงกัน");
        } else if (!(oldPassword.equals(admin.getPassword()))) {
            successLabel.setText("");
            errorLabel.setText("password ไม่ถูกต้อง");
        } else {
            admin = adminList.findAccount(admin.getUsername());
            if (!admin.isValidPassword(newPassword)) {
                successLabel.setText("");
                errorLabel.setText("ไม่สามารถใช้รหัสผ่านนี้ได้");
            } else {
                admin.setPassword(newPassword);
                adminListDataSource.writeData(adminList);
                information.setAdmin(admin);

                errorLabel.setText("");
                successLabel.setText("เปลี่ยนรหัสผ่านสำเร็จ");
                newPasswordField.setText("");
                confirmField.setText("");
                oldPasswordField.setText("");
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