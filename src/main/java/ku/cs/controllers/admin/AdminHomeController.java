package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Admin;
import ku.cs.models.account.AdminList;
import ku.cs.services.AdminListDataSource;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.NisitListDataSource;
import ku.cs.models.account.Officer;
import ku.cs.models.account.OfficerList;
import ku.cs.services.OfficerListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import ku.cs.models.account.User;
import ku.cs.models.account.UserList;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class AdminHomeController {
    private Information information;
    private Admin admin;
    private DataSource<AdminList> adminListDataSource;
    private AdminList adminList;
    private DataSource<NisitList> nisitListDataSource;
    private NisitList nisitList;
    private DataSource<OfficerList> officerListDataSource;
    private OfficerList officerList;
    private UserList userList;
    private UserList sortedUserList;
    private ObservableList<User> users;
    @FXML private TableView<User> userListTableView;
    @FXML private TableColumn usernameColumn;
    @FXML private TableColumn lastLoginColumn;
    @FXML private ScrollPane reportScrollPane;
    @FXML private ScrollPane usersScrollPane;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        admin = information.getAdmin();
        adminListDataSource = new AdminListDataSource("data", "admin.csv");
        nisitListDataSource = new NisitListDataSource("data", "register.csv");
        officerListDataSource = new OfficerListDataSource("data", "officer.csv");
        users = FXCollections.observableArrayList();

        ThemeMode.setThemeMode(root);
        new FadeIn(root).play();

        reportScrollPane = new ScrollPane();
        reportScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        reportScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        usersScrollPane = new ScrollPane();
        usersScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        usersScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        lastLoginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastLoginDateTime"));

        adminList = adminListDataSource.readData();
        nisitList = nisitListDataSource.readData();
        officerList = officerListDataSource.readData();
        userList = new UserList();

        for(Nisit nisit : nisitList.getAllNisits()){
            userList.addUser(nisit);
        }
        for(Officer officer : officerList.getAllOfficers()){
            userList.addUser(officer);
        }

        sortedUserList = userList;

        showTable();
        handleSelectedUser();
    }

    private void showTable(){
        Collections.sort(sortedUserList.getAllUsers(), new Comparator<User>(){
            @Override
            public int compare(User u1, User u2){
                if(u1.getLastLoginDateTime().equals("-") && !(u2.getLastLoginDateTime().equals("-"))){
                    return 1;
                } else if(!(u1.getLastLoginDateTime().equals("-")) && u2.getLastLoginDateTime().equals("-")){
                    return -1;
                } else if(u1.getLastLoginDateTime().equals("-") && u2.getLastLoginDateTime().equals("-")){
                    return 0;
                } else {
                    return -(u1.getLastLoginLocalDateTime().compareTo(u2.getLastLoginLocalDateTime()));
                }
            }
        });

        users.clear();
        users.addAll(sortedUserList.getAllUsers());

        userListTableView.setItems(users);
        userListTableView.getColumns().clear();
        userListTableView.getColumns().addAll(usernameColumn, lastLoginColumn);
    }

    @FXML public void handleSelectedUser() {
        userListTableView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<User>() {
                    @Override
                    public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                        showSelectedUser(newValue);
                    }
                });
    }

    private void showSelectedUser(User user){
        if(nisitList.findAccount(user.getUsername()) != null){
            Nisit nisit = (Nisit) user;
            information.setNisit(nisit);
            try{
                FXRouter.goTo("nisitDetail", information);
            } catch(IOException e){
                System.err.println("ไปที่หน้า nisitDetail ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
        else if(officerList.findAccount(user.getUsername()) != null){
            Officer officer = (Officer) user;
            information.setOfficer(officer);
            try{
                FXRouter.goTo("officerDetail", information);
            } catch(IOException e){
                System.err.println("ไปที่หน้า officerDetail ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML public void handleInappropriateButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("inappropriate", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า inappropriate ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleCreateOfficerAccountButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("createOfficerAccount");
        } catch(IOException e){
            System.err.println("ไปที่หน้า createOfficer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleLogOutButton(ActionEvent actionEvent){
        admin = adminList.findAccount(admin.getUsername());
        admin.setLoginStatus(false);
        adminListDataSource.writeData(adminList);

        try{
            FXRouter.goTo("home");
        } catch(IOException e){
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleProfileButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("adminProfile", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า adminProfile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePicProfileButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("adminProfile", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า adminProfile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleAdminOrganizerButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("adminOrganizer");
        } catch(IOException e){
            System.err.println("ไปที่หน้า adminOrganizer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePicAdminOrganizerButton(MouseEvent mouseEvent) {
        try{
            FXRouter.goTo("adminOrganizer");
        } catch(IOException e){
            System.err.println("ไปที่หน้า adminOrganizer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleAdminAboutProgramButton(ActionEvent actionEvent) {
        try{
            FXRouter.goTo("adminAboutProgram");
        } catch(IOException e){
            System.err.println("ไปที่หน้า adminAboutProgram ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePicAdminAboutProgramButton(MouseEvent mouseEvent) {
        try{
            FXRouter.goTo("adminAboutProgram");
        } catch(IOException e){
            System.err.println("ไปที่หน้า adminAboutProgram ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
