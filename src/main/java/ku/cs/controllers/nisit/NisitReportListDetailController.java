package ku.cs.controllers.nisit;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.services.ReportListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import java.io.IOException;

public class NisitReportListDetailController {
    private Information information;
    private Report report;
    private ReportList reportList;
    private DataSource<ReportList> reportListDataSource;
    @FXML private Label titleLabel;
    @FXML private Label reportDetailLabel;
    @FXML private Label fixDetailLabel;
    @FXML private Label statusLabel;
    @FXML private Label voteLabel;
    @FXML private Label successLabel;
    @FXML private  Label errorLabel;
    @FXML private Label typeLabel;
    @FXML private Label typeDetailLabel;
    @FXML private Label dateTimeLabel;
    @FXML private Label nisitUsernameLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        report = information.getReport();
        reportListDataSource = new ReportListDataSource("data", "allReport.csv");

        titleLabel.setText(report.getTitle());
        titleLabel.setWrapText(true);
        reportDetailLabel.setText(report.getDetail());
        reportDetailLabel.setWrapText(true);
        fixDetailLabel.setText(report.getFixedDetail());
        statusLabel.setText(report.getStatus());
        typeLabel.setText(report.getType());
        typeDetailLabel.setText(report.getTypeDetail());
        nisitUsernameLabel.setText(report.getNisitUsername());
        dateTimeLabel.setText(report.getDateTime());
        voteLabel.setText("" + report.getVote());
        errorLabel.setText("");
        successLabel.setText("");

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleVote(ActionEvent actionEvent){
        if(report.votedBy(information.getNisit().getUsername())) {
            reportList = reportListDataSource.readData();
            for (Report thisReport : reportList.getAllReports()) {
                if (thisReport.equals(report)) {
                    thisReport.votedBy(information.getNisit().getUsername());
                    reportListDataSource.writeData(reportList);

                    voteLabel.setText("" + report.getVote());
                    errorLabel.setText("");
                    successLabel.setText("โหวตสำเร็จ");
                }
            }
        } else{
            successLabel.setText("");
            errorLabel.setText("ไม่สามารถโหวตได้ เนื่องจากเคยได้ทำการโหวตไปแล้ว");
        }
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent) {
        try{
            FXRouter.goTo("nisitReportList");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReportList ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleReportInappropriateContentButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("nisitReportInappropriateContent", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReportInappropriateContent ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleReportInappropriateUserButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("nisitReportInappropriateUser", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReportInappropriateUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
