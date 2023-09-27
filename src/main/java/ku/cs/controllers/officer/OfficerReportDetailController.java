package ku.cs.controllers.officer;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Officer;
import ku.cs.services.Information;
import ku.cs.models.report.Report;
import java.io.IOException;

public class OfficerReportDetailController {
    private Information information;
    private Report report;
    private Officer officer;
    @FXML private Label dateTimeLabel;
    @FXML private Label titleLabel;
    @FXML private Label typeLabel;
    @FXML private Label typeDetailLabel;
    @FXML private Label detailLabel;
    @FXML private Label statusLabel;
    @FXML private Label officerLabel;
    @FXML private Label fixDetailLabel;
    @FXML private Label errorLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        report = information.getReport();
        officer = information.getOfficer();

        errorLabel.setText("");
        dateTimeLabel.setText(report.getDateTime());
        titleLabel.setText(report.getTitle());
        titleLabel.setWrapText(true);
        typeLabel.setText(report.getType());
        typeDetailLabel.setText(report.getTypeDetail());
        detailLabel.setText(report.getDetail());
        detailLabel.setWrapText(true);
        statusLabel.setText(report.getStatus());
        officerLabel.setText(report.getOfficerUsername());
        fixDetailLabel.setText(report.getFixedDetail());
        fixDetailLabel.setWrapText(true);

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("officerHome");
        } catch(IOException e){
            System.err.println("ไปที่หน้า officerHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleFixReportButton(ActionEvent actionEvent){
        if(officer.checkManageReport(report)){
            try{
                FXRouter.goTo("officerFixReport", information);
            } catch(IOException e){
                System.err.println("ไปที่หน้า officerFixReport ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        } else{
            if(report.isFinished()){
                errorLabel.setText("เรื่องร้องเรียนนี้ถูกจัดการแล้ว");
            }
            else if(report.isOnProgress()){
                errorLabel.setText("เรื่องร้องเรียนนี้กำลังจัดการโดย " + report.getOfficerUsername());
            }
        }
    }
}
