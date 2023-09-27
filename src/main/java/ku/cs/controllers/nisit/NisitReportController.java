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
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Nisit;
import ku.cs.services.ReportListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NisitReportController {
    private Information information;
    private Nisit nisit;
    private DataSource<ReportList> reportListDataSource;
    private ReportList reportList;
    @FXML private TextField titleTextField;
    @FXML private TextField detailTextField;
    private ObservableList<String> typeList;
    @FXML private ChoiceBox typeBox;
    private ObservableList<String> typeLifeList;
    private ObservableList<String> typeStudyList;
    private ObservableList<String> typeDocumentList;
    private ObservableList<String> typeActivityList;
    private ObservableList<String> typeLocationList;
    @FXML private ChoiceBox typeDetailBox;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize() {
        information = (Information) FXRouter.getData();
        nisit = information.getNisit();
        reportListDataSource = new ReportListDataSource("data","allReport.csv");
        typeList = FXCollections.observableArrayList("คุณภาพชีวิตในมหาวิทยาลัย", "การเรียน", "เอกสารและงานธุรการ", "กิจกรรมและสันทนาการ", "อาคารและสถานที่");
        typeLifeList = FXCollections.observableArrayList("เว็บไซต์และระบบ", "การรักษาพยาบาล", "ค่าครองชีพในมหาวิทยาลัย", "อื่นๆ");
        typeStudyList = FXCollections.observableArrayList("การ Add/Drop รายวิชา", "รายวิชาเรียน", "หลักสูตร", "อื่นๆ");
        typeDocumentList = FXCollections.observableArrayList("การกู้ยืม", "การขอเอกสาร", "อื่นๆ");
        typeActivityList = FXCollections.observableArrayList("กีฬา", "กิจกรรม", "อื่นๆ");
        typeLocationList = FXCollections.observableArrayList("พื้นที่สาธารณะ", "อาคาร", "ยานพาหนะ", "อื่นๆ");

        errorLabel.setText("");
        successLabel.setText("");
        setChoiceBox();
        handleSelectedTypeBox();

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    private void setChoiceBox(){
        typeBox.setValue("คุณภาพชีวิตในมหาวิทยาลัย");
        typeBox.setItems(typeList);

        typeDetailBox.setValue("เว็บไซต์และระบบ");
        typeDetailBox.setItems(typeLifeList);
    }

    @FXML public void handleSelectedTypeBox(){
        typeBox.setOnAction((event) -> {
            String selected = (String) typeBox.getSelectionModel().getSelectedItem();
            if(selected.equals("คุณภาพชีวิตในมหาวิทยาลัย")){
                typeDetailBox.setValue("เว็บไซต์และระบบ");
                typeDetailBox.setItems(typeLifeList);
            }
            else if(selected.equals("การเรียน")){
                typeDetailBox.setValue("การ Add/Drop รายวิชา");
                typeDetailBox.setItems(typeStudyList);
            }
            else if(selected.equals("เอกสารและงานธุรการ")){
                typeDetailBox.setValue("การกู้ยืม");
                typeDetailBox.setItems(typeDocumentList);
            }
            else if(selected.equals("กิจกรรมและสันทนาการ")){
                typeDetailBox.setValue("กีฬา");
                typeDetailBox.setItems(typeActivityList);
            }
            else if(selected.equals("อาคารและสถานที่")){
                typeDetailBox.setValue("พื้นที่สาธารณะ");
                typeDetailBox.setItems(typeLocationList);
            }
        });
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("nisitHome");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePostButton(MouseEvent mouseEvent) {
        reportList = reportListDataSource.readData();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dateTime = dtf.format(LocalDateTime.now());
        String title = titleTextField.getText();
        String detail = detailTextField.getText();
        String type = (String) typeBox.getSelectionModel().getSelectedItem();
        String typeDetail = (String) typeDetailBox.getSelectionModel().getSelectedItem();

        if (title.isEmpty() || detail.isEmpty()) {
            successLabel.setText("");
            errorLabel.setText("กรุณากรอกข้อมูลให้ครบถ้วน");
        } else {
            Report report = new Report(title, detail, type, typeDetail, dateTime, nisit.getUsername());
            reportList.addReport(report);
            reportListDataSource.writeData(reportList);

            titleTextField.clear();
            detailTextField.clear();
            errorLabel.setText("");
            successLabel.setText("เขียนเรื่องร้องเรียนสำเร็จ");
        }
    }
}