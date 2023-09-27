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

public class NisitReportInappropriateContentController {
    private Information information;
    private Report report;
    private DataSource<InappropriateReportList> inappropriateContentReportListDatasource;
    private InappropriateReportList inappropriateContentReportList;
    @FXML private ChoiceBox typeBox;
    private ObservableList<String> typeList;
    @FXML private TextField detailTextField;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        report = information.getReport();
        inappropriateContentReportListDatasource = new InappropriateReportListDataSource("data", "inappropriateContentReport.csv");
        typeList = FXCollections.observableArrayList("ประเภท", "ไม่ตรงกับหมวดหมู่", "มีการใช้คำที่ไม่เหมาะสม", "มีการพาดพิงถึงบุคคลที่สาม");

        setChoiceBox();
        successLabel.setText("");
        errorLabel.setText("");

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

        if (detail.isEmpty() || type.compareTo("ประเภท") == 0) {
            successLabel.setText("");
            errorLabel.setText("กรุณากรอกข้อมูลให้ครบถ้วน");
        } else if (report.getNisitUsername().equals(information.getNisit().getUsername())){
            errorLabel.setText("ไม่สามารถรายงานเรื่องร้องเรียนที่คุณเป็นผู้แจ้งได้");
            successLabel.setText("");
        } else{
            inappropriateContentReportList = inappropriateContentReportListDatasource.readData();
            inappropriateContentReportList.addInappropriateReport(new InappropriateReport(type, detail, report));
            inappropriateContentReportListDatasource.writeData(inappropriateContentReportList);

            detailTextField.clear();
            errorLabel.setText("");
            successLabel.setText("รายงานเรื่องร้องเรียนนี้สำเร็จ");
        }
    }
}
