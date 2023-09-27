package ku.cs.controllers.main;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.report.Claiming;
import ku.cs.models.report.ClaimingList;
import ku.cs.models.account.Nisit;
import ku.cs.services.ClaimingListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import java.io.IOException;

public class ClaimingController {
    private Information information;
    private Nisit nisit;
    private DataSource<ClaimingList> claimingListDataSource;
    private ClaimingList claimingList;
    @FXML private TextField usernameTextField;
    @FXML private TextField reasonTextField;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        nisit = information.getNisit();
        claimingListDataSource = new ClaimingListDataSource("data", "claiming.csv");

        errorLabel.setText("");
        successLabel.setText("");
        usernameTextField.setText(nisit.getUsername());

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handlePostButton(MouseEvent mouseEvent){
        claimingList = claimingListDataSource.readData();

        if(usernameTextField.getText().isEmpty() || reasonTextField.getText().isEmpty()){
            successLabel.setText("");
            errorLabel.setText("กรุณากรอกข้อมูลให้ครบถ้วน");
        } else if(!usernameTextField.getText().equals(nisit.getUsername())){
            successLabel.setText("");
            errorLabel.setText("username ไม่ถูกต้อง");
        } else{
            Claiming claiming = new Claiming(usernameTextField.getText(), reasonTextField.getText());
            claimingList.addClaiming(claiming);
            claimingListDataSource.writeData(claimingList);

            reasonTextField.setText("");
            errorLabel.setText("");
            successLabel.setText("เขียนเรื่องขอคืนสิทธิ์สำเร็จ");
        }
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
