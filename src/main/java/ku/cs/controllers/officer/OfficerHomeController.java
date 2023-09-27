package ku.cs.controllers.officer;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.OfficerList;
import ku.cs.services.OfficerListDataSource;
import ku.cs.services.Filterer;
import ku.cs.services.ReportListDataSource;
import ku.cs.models.account.Officer;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import javafx.scene.layout.AnchorPane;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import java.io.File;
import java.io.IOException;

public class OfficerHomeController {
    private Information information;
    private Officer officer;
    private OfficerListDataSource officerListDataSource;
    private DataSource<ReportList> reportListDataSource;
    private ReportList reportList;
    private ObservableList<Report> reports;
    private OfficerList officerList;
    @FXML private TableView<Report> reportTableView;
    @FXML private TableColumn titleColumn;
    @FXML private TableColumn statusColumn;
    @FXML private ImageView header;
    @FXML private Label usernameLabel;
    @FXML private ImageView profile ;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        String urlHeader = getClass().getResource("/ku/cs/images/staff/header.png").toExternalForm();
        header.setImage(new Image(urlHeader));
        String urlTopic = getClass().getResource("/ku/cs/images/staff/topic1.png").toExternalForm();

        information = (Information) FXRouter.getData();
        officer = information.getOfficer();
        officerListDataSource = new OfficerListDataSource("data","officer.csv");
        reportListDataSource = new ReportListDataSource("data", "allReport.csv");
        reportList = reportListDataSource.readData();
        reports = FXCollections.observableArrayList();

        usernameLabel.setText(officer.getUsername());
        String directoryName = "data" + File.separator + "images" + File.separator + "avatar";
        String filepath = directoryName + File.separator + officer.getUsername() + ".png";
        profile.setImage(new Image(new File(filepath).toURI().toString()));

        titleColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("title"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("status"));

        setTable();
        handleSelectedReport();

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    private void setTable(){
        reportList = reportList.filterBy(new Filterer<Report>() {
            @Override
            public boolean filter(Report report) {
                return officer.checkManagement(report.getType());
            }
        });

        reports.clear();
        reports.addAll(reportList.getAllReports());

        reportTableView.setItems(reports);
        reportTableView.getColumns().clear();
        reportTableView.getColumns().addAll(titleColumn, statusColumn);
    }

    @FXML public void handleSelectedReport() {
        reportTableView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Report>() {
                    @Override
                    public void changed(ObservableValue<? extends Report> observable, Report oldValue, Report newValue) {
                        showSelectedReport(newValue);
                    }
                });
    }

    private void showSelectedReport(Report report){
        information.setReport(report);

        try{
            FXRouter.goTo("officerReportDetail", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า officerReportDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleOrganizerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("officerOrganizer");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า officerOrganizer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML public void handlePicOrganizerButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("officerOrganizer");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า officerOrganizer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML public void handleAboutProgramButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("officerAboutProgram");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า officerAboutProgram ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML public void handlePicAboutProgramButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("officerAboutProgram");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า officerAboutProgram ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML public void handleProfileButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("officerProfile", information);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า officerProfile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML public void handleLogOutButton(ActionEvent actionEvent){
        officerList = officerListDataSource.readData();

        officer = officerList.findAccount(officer.getUsername());
        officer.setLoginStatus(false);
        officerListDataSource.writeData(officerList);

        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
