package ku.cs.controllers.officer;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.services.ReportListDataSource;
import ku.cs.models.account.Officer;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import java.io.IOException;

public class OfficerFixReportController {
    private Information information;
    private Report report;
    private Officer officer;
    private DataSource<ReportList> reportListDatasource;
    private ReportList reportList;
    @FXML private ChoiceBox statusBox;
    private ObservableList<String> statusList;
    @FXML private TextField fixDetailTextField;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        report = information.getReport();
        officer = information.getOfficer();
        statusList = FXCollections.observableArrayList("กำลังดำเนินการ", "จัดการแล้ว");
        reportListDatasource = new ReportListDataSource("data", "allReport.csv");

        errorLabel.setText("");
        successLabel.setText("");
        setChoiceBox();

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    private void setChoiceBox() {
        statusBox.setValue("กำลังดำเนินการ");
        statusBox.setItems(statusList);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("officerReportDetail", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า officerReportDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePostButton(ActionEvent actionEvent){
        String fixDetail = fixDetailTextField.getText();
        String status = (String) statusBox.getSelectionModel().getSelectedItem();

        if(fixDetail.isEmpty()){
            successLabel.setText("");
            errorLabel.setText("โปรดกรอกข้อมูลให้ครบถ้วน");
        } else{
            reportList = reportListDatasource.readData();

            for (Report thisReport : reportList.getAllReports()) {
                if (report.equals(thisReport)) {
                    if(!thisReport.isFinished()){
                        thisReport.setFixedDetail(fixDetail);
                        thisReport.setStatus(status);
                        thisReport.setOfficerUsername(officer.getUsername());
                        reportListDatasource.writeData(reportList);
                        information.setReport(thisReport);

                        successLabel.setText("การจัดการเรื่องร้องเรียนถูกอัปเดตแล้ว");
                        errorLabel.setText("");
                        fixDetailTextField.setText("");
                    } else{
                        successLabel.setText("");
                        errorLabel.setText("เรื่องร้องเรียนนี้ถูกจัดการแล้ว");
                    }
                }
            }
        }
    }
}
