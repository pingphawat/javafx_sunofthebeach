package ku.cs.controllers.nisit;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.services.NisitListDataSource;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.Filterer;
import ku.cs.services.ReportListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import java.io.IOException;
import java.util.*;

public class NisitReportListController {
    private Information information;
    private DataSource<ReportList> reportListDataSource;
    private DataSource<NisitList> nisitListDataSource;
    private ReportList reportList;
    private NisitList nisitList;
    private ReportList filteredSortedReportList;
    private ObservableList<Report> reports;
    private ObservableList<String> departmentList;
    @FXML private ChoiceBox departmentBox;
    private ObservableList<String> statusList;
    @FXML private ChoiceBox statusBox;
    private ObservableList<String> sortList;
    @FXML private ChoiceBox sortBox;
    @FXML private CheckBox sortLoginUser;
    @FXML private TableView<Report> reportTableView;
    @FXML private TableColumn titleColumn;
    @FXML private TableColumn statusColumn;
    @FXML private TableColumn dateTimeColumn;
    @FXML private TableColumn voteColumn;
    @FXML private TextField minTextField;
    @FXML private TextField maxTextField;
    @FXML private Label errorLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        reportListDataSource = new ReportListDataSource("data", "allReport.csv");
        nisitListDataSource = new NisitListDataSource("data", "register.csv");
        reports = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList("หมวดหมู่ทั้งหมด", "คุณภาพชีวิตในมหาวิทยาลัย", "การเรียน", "เอกสารและงานธุรการ", "กิจกรรมและสันทนาการ", "อาคารและสถานที่");
        statusList = FXCollections.observableArrayList("สถานะเรื่องร้องเรียน", "จัดการแล้ว", "กำลังดำเนินการ", "ยังไม่จัดการ");
        sortList = FXCollections.observableArrayList("เรียงลำดับ", "เรียงจากเวลาที่แจ้ง-เก่าสุด", "เรียงจากเวลาที่แจ้ง-ล่าสุด", "เรียงจากจำนวนโหวต-มากสุด", "เรียงจากจำนวนโหวต-น้อยสุด");

        titleColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("title"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("status"));
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("dateTime"));
        voteColumn.setCellValueFactory(new PropertyValueFactory<Report, Integer>("vote"));

        errorLabel.setText("");
        reportList = reportListDataSource.readData();
        filteredSortedReportList = reportList;

        setChoiceBox();
        setTable();
        handleSelectedReport();

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    private void setChoiceBox(){
        departmentBox.setValue("หมวดหมู่ทั้งหมด");
        departmentBox.setItems(departmentList);
        statusBox.setValue("สถานะเรื่องร้องเรียน");
        statusBox.setItems(statusList);
        sortBox.setValue("เรียงลำดับ");
        sortBox.setItems(sortList);
    }

    private void setTable(){
        reports.clear();
        reports.addAll(reportList.getAllReports());

        reportTableView.setItems(reports);
        reportTableView.getColumns().clear();
        reportTableView.getColumns().addAll(titleColumn, statusColumn, dateTimeColumn, voteColumn);
    }

    @FXML public void handleSelectedReport() {
        reportTableView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Report>() {
                    @Override
                    public void changed(ObservableValue<? extends Report> observable, Report oldValue, Report newValue) {
                        readReport(newValue);
                    }
                });
    }

    private void readReport(Report report){
        information.setReport(report);

        try{
            FXRouter.goTo("nisitReportListDetail", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReportListDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("nisitHome");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleSortLoginUser(){}

    @FXML public void handleSearchButton(){
        String department = (String) departmentBox.getSelectionModel().getSelectedItem();
        String status = (String) statusBox.getSelectionModel().getSelectedItem();
        String sort = (String) sortBox.getSelectionModel().getSelectedItem();
        String min = minTextField.getText();
        String max = maxTextField.getText();

        if((min.isEmpty() || isPositiveInteger(min)) && (max.isEmpty() || isPositiveInteger(max))) {
            filteredSortedReportList = reportList.filterBy(new Filterer<Report>() {
                @Override
                public boolean filter(Report report) {
                    boolean checkDepartment = department.equals("หมวดหมู่ทั้งหมด") || department.equals(report.getType());
                    boolean checkStatus = status.equals("สถานะเรื่องร้องเรียน") || status.equals(report.getStatus());
                    return checkDepartment && checkStatus && checkVote(min, max, report);
                }
            });

            Collections.sort(filteredSortedReportList.getAllReports(), new Comparator<Report>() {
                @Override
                public int compare(Report r1, Report r2) {
                    if (sort.equals("เรียงจากเวลาที่แจ้ง-เก่าสุด"))
                        return r1.getLocalDateTime().compareTo(r2.getLocalDateTime());
                    if (sort.equals("เรียงจากเวลาที่แจ้ง-ล่าสุด"))
                        return -(r1.getLocalDateTime().compareTo(r2.getLocalDateTime()));
                    if (sort.equals("เรียงจากจำนวนโหวต-มากสุด"))
                        return -(r1.getVote() - r2.getVote());
                    if (sort.equals("เรียงจากจำนวนโหวต-น้อยสุด"))
                        return r1.getVote() - r2.getVote();
                    return 0;
                }
            });

            if (sortLoginUser.isSelected()){
                filteredSortedReportList = filteredSortedReportList.filterBy(new Filterer<Report>() {
                    @Override
                    public boolean filter(Report report) {
                        nisitList = nisitListDataSource.readData();
                        for (Nisit nisit : nisitList.getAllNisits()) {
                            if (nisit.getUsername().equals(report.getNisitUsername())) {
                                if (nisit.isOnline()) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                });
            }
            errorLabel.setText("");

            reports.clear();
            reports.addAll(filteredSortedReportList.getAllReports());

            reportTableView.setItems(reports);
            reportTableView.getColumns().clear();
            reportTableView.getColumns().addAll(titleColumn, statusColumn, dateTimeColumn, voteColumn);
        } else{
            errorLabel.setText("กรุณากรอกข้อมูลให้ถูกต้อง");
        }
    }

    private boolean isPositiveInteger(String input){
        try{
            if(Integer.parseInt(input) >= 0)
                return true;
            return false;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private boolean checkVote(String min, String max, Report report){
        if(min.isEmpty() && max.isEmpty()){
            return true;
        }
        if(min.isEmpty() && !max.isEmpty()){
            if (report.getVote() <= Integer.parseInt(max)) {
                return true;
            }
            return false;
        }
        if(!min.isEmpty() && max.isEmpty()){
            if(report.getVote() >= Integer.parseInt(min)){
                return true;
            }
            return false;
        }
        if(!min.isEmpty() && !max.isEmpty()){
            if(Integer.parseInt(min) > Integer.parseInt(max)){
                return false;
            }
            if(report.getVote() >= Integer.parseInt(min) && report.getVote() <= Integer.parseInt(max)){
                return true;
            }
            return false;
        }
        return false;
    }
}