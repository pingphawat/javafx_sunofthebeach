package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.report.InappropriateReport;
import ku.cs.models.report.InappropriateReportList;
import ku.cs.services.InappropriateReportListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import java.io.IOException;

public class InappropriateContentReportListController {
    private Information information;
    @FXML private ListView inappropriateContentReportListView;
    private DataSource<InappropriateReportList> inappropriateContentReportListDatasource;
    private InappropriateReportList inappropriateContentReportList;

    @FXML private AnchorPane root;
    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        inappropriateContentReportListDatasource = new InappropriateReportListDataSource("data", "inappropriateContentReport.csv");
        inappropriateContentReportList = inappropriateContentReportListDatasource.readData();

        showListView();
        handleSelectedInappropriateContentReport();

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    private void showListView(){
        inappropriateContentReportListView.getItems().addAll(inappropriateContentReportList.getAllInappropriateReports());
        inappropriateContentReportListView.refresh();
    }

    @FXML public void handleSelectedInappropriateContentReport(){
        inappropriateContentReportListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<InappropriateReport>() {
                    @Override
                    public void changed(ObservableValue<? extends InappropriateReport> observable, InappropriateReport oldValue, InappropriateReport newValue) {
                        readInappropriateReport(newValue);
                    }
                }
        );
    }

    private void readInappropriateReport(InappropriateReport inappropriateContentReport){
        information.setInappropriateReport(inappropriateContentReport);

        try{
            FXRouter.goTo("inappropriateContentReportListDetail", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า inappropriateContentReportListDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try {
            FXRouter.goTo("inappropriate");
        } catch(IOException e){
            System.out.println("ไปที่หน้า inappropriate ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
