package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.report.InappropriateReport;
import ku.cs.models.report.InappropriateReportList;
import ku.cs.services.InappropriateReportListDataSource;
import ku.cs.services.ReportListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import java.io.IOException;

public class InappropriateContentReportListDetailController {
    private Information information;
    private InappropriateReport inappropriateContentReport;
    private DataSource<ReportList> reportListDataSource;
    private DataSource<InappropriateReportList> inappropriateContentReportListDataSource;
    private ReportList reportList;
    private InappropriateReportList inappropriateContentReportList;
    @FXML private Label titleLabel;
    @FXML private Label reportDetailLabel;
    @FXML private Label usernameLabel;
    @FXML private Label detailLabel;
    @FXML private Label typeLabel;
    @FXML private Label errorLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        inappropriateContentReport = information.getInappropriateReport();
        inappropriateContentReportListDataSource = new InappropriateReportListDataSource("data", "inappropriateContentReport.csv");
        reportListDataSource = new ReportListDataSource("data", "allReport.csv");

        titleLabel.setText(inappropriateContentReport.getReport().getTitle());
        reportDetailLabel.setText(inappropriateContentReport.getReport().getDetail());
        reportDetailLabel.setWrapText(true);
        usernameLabel.setText(inappropriateContentReport.getReport().getNisitUsername());
        detailLabel.setText(inappropriateContentReport.getDetail());
        detailLabel.setWrapText(true);
        typeLabel.setText(inappropriateContentReport.getType());
        errorLabel.setText("");

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try {
            FXRouter.goTo("inappropriateContentReportList");
        } catch(IOException e){
            System.out.println("ไปที่หน้า inappropriateContentReportList ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleInappropriateContentReportButton(ActionEvent actionEvent){
        deleteThisInappropriateContentReport();
    }

    @FXML public void handleDeleteReportButton(ActionEvent actionEvent){
        reportList = reportListDataSource.readData();

        for(Report report : reportList.getAllReports()){
            if(inappropriateContentReport.getReport().equals(report)){
                reportList.getAllReports().remove(report);
                reportListDataSource.writeData(reportList);
                deleteThisInappropriateContentReport();
                return;
            }
        }
        errorLabel.setText("เรื่องร้องเรียนนี้ถูกลบไปแล้ว");
    }

    private void deleteThisInappropriateContentReport(){
        inappropriateContentReportList = inappropriateContentReportListDataSource.readData();

        for(InappropriateReport thisInappropriateContentReport : inappropriateContentReportList.getAllInappropriateReports()) {
           if (thisInappropriateContentReport.equals(inappropriateContentReport)) {
                inappropriateContentReportList.getAllInappropriateReports().remove(thisInappropriateContentReport);
                inappropriateContentReportListDataSource.writeData(inappropriateContentReportList);

                try {
                    FXRouter.goTo("inappropriateContentReportList");
                } catch(IOException e){
                    System.out.println("ไปที่หน้า inappropriateContentReportList ไม่ได้");
                    System.out.println("ให้ตรวจสอบการกำหนด route");
                }
                return;
            }
        }
    }
}
