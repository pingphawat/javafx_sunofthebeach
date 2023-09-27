package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.services.NisitListDataSource;
import ku.cs.models.report.InappropriateReport;
import ku.cs.models.report.InappropriateReportList;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.InappropriateReportListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import java.io.IOException;

public class InappropriateUserReportListDetailController {
    private Information information;
    private InappropriateReport inappropriateUserReport;
    private DataSource<NisitList> nisitListDataSource;
    private DataSource<InappropriateReportList> inappropriateUserReportListDataSource;
    private InappropriateReportList inappropriateUserReportList;
    private NisitList nisitList;
    @FXML private Label usernameLabel;
    @FXML private Label typeLabel;
    @FXML private Label detailLabel;
    @FXML private Label errorLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        inappropriateUserReport = information.getInappropriateReport();
        nisitListDataSource = new NisitListDataSource("data", "register.csv");
        inappropriateUserReportListDataSource = new InappropriateReportListDataSource("data", "inappropriateUserReport.csv");

        usernameLabel.setText(inappropriateUserReport.getReport().getNisitUsername());
        typeLabel.setText(inappropriateUserReport.getType());
        detailLabel.setText(inappropriateUserReport.getDetail());
        detailLabel.setWrapText(true);
        errorLabel.setText("");

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try {
            FXRouter.goTo("inappropriateUserReportList");
        } catch(IOException e){
            System.out.println("ไปที่หน้า inappropriateUserReportList ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleDeleteInappropriateUserReportButton(ActionEvent actionEvent){
        deleteThisInappropriateUserReport();
    }

    @FXML public void handleBanUserButton(ActionEvent actionEvent){
        nisitList = nisitListDataSource.readData();

        for(Nisit nisit : nisitList.getAllNisits()){
            if(inappropriateUserReport.getReport().getNisitUsername().equals(nisit.getUsername())){
                if(nisit.isAvailable()){
                    nisit.banAccount();
                    nisitListDataSource.writeData(nisitList);
                    deleteThisInappropriateUserReport();
                } else{
                    errorLabel.setText("บัญชีผู้ใช้นี้ถูกระงับอยู่แล้ว");
                }
                return;
            }
        }
    }

    private void deleteThisInappropriateUserReport(){
        inappropriateUserReportList = inappropriateUserReportListDataSource.readData();

        for(InappropriateReport thisInappropriateUserReport : inappropriateUserReportList.getAllInappropriateReports()){
            if(thisInappropriateUserReport.equals(inappropriateUserReport)){
                inappropriateUserReportList.getAllInappropriateReports().remove(thisInappropriateUserReport);
                inappropriateUserReportListDataSource.writeData(inappropriateUserReportList);

                try {
                    FXRouter.goTo("inappropriateUserReportList");
                } catch(IOException e){
                    System.out.println("ไปที่หน้า inappropriateUserReportList ไม่ได้");
                    System.out.println("ให้ตรวจสอบการกำหนด route");
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}
