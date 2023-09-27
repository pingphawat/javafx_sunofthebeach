package ku.cs.controllers.nisit;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.report.InappropriateReport;
import ku.cs.models.report.InappropriateReportList;
import ku.cs.services.InappropriateReportListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import ku.cs.models.report.Report;
import java.io.IOException;

public class NisitReportInappropriateUserController {
    private Information information;
    private Report report;
    private DataSource<InappropriateReportList> inappropriateUserReportListDatasource;
    private InappropriateReportList inappropriateUserReportList;
    @FXML private ChoiceBox typeBox;
    private ObservableList<String> typeList;
    @FXML private TextField detailTextField;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        report = information.getReport();
        inappropriateUserReportListDatasource = new InappropriateReportListDataSource("data", "inappropriateUserReport.csv");
        typeList = FXCollections.observableArrayList("ประเภท", "สแปมโพสต์", "เป็นบัญชีปลอม", "มีการใช้คำที่ไม่เหมาะสม");

        setChoiceBox();
        errorLabel.setText("");
        successLabel.setText("");

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    private void setChoiceBox(){
        typeBox.setValue("ประเภท");
        typeBox.setItems(typeList);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("nisitReportListDetail");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReportListDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePostButton(MouseEvent mouseEvent){
        String type = (String) typeBox.getSelectionModel().getSelectedItem();
        String detail = detailTextField.getText();

        if (detail.isEmpty() || type.equals("ประเภท")){
            successLabel.setText("");
            errorLabel.setText("กรุณากรอกข้อมูลให้ครบถ้วน");
        } else if (report.getNisitUsername().equals(information.getNisit().getUsername())){
            successLabel.setText("");
            errorLabel.setText("ไม่สามารถรายงานบัญชีของคุณได้");
        } else {
            inappropriateUserReportList = inappropriateUserReportListDatasource.readData();
            inappropriateUserReportList.addInappropriateReport(new InappropriateReport(type, detail, report));
            inappropriateUserReportListDatasource.writeData(inappropriateUserReportList);

            detailTextField.clear();
            errorLabel.setText("");
            successLabel.setText("รายงานบัญชีผู้ใช้สำเร็จ");
        }
    }
}
