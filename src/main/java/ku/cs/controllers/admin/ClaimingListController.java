package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInDownBig;
import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.report.Claiming;
import ku.cs.models.report.ClaimingList;
import ku.cs.services.ClaimingListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import java.io.IOException;

public class ClaimingListController {
    Information information;
    private DataSource<ClaimingList> claimingListDatasource;
    private ClaimingList claimingList;
    private ObservableList<Claiming> claimings;
    @FXML private TableView<Claiming> claimingTableView;
    @FXML private TableColumn usernameColumn;
    @FXML private TableColumn reasonColumn;
    @FXML private AnchorPane root;
    @FXML private ImageView claimingLists;
    @FXML private Button centerBackground;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        claimingListDatasource = new ClaimingListDataSource("data", "claiming.csv");
        claimings = FXCollections.observableArrayList();

        usernameColumn.setCellValueFactory(new PropertyValueFactory<Claiming, String>("username"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<Claiming, String>("reason"));

        claimingList = claimingListDatasource.readData();

        showTable();
        handleSelectedClaiming();

        new FadeIn(root).play();
        new FadeInDownBig(claimingLists).play();
        new FadeInDownBig(centerBackground).play();
        ThemeMode.setThemeMode(root);
    }

    private void showTable(){
        claimings.clear();
        claimings.addAll(claimingList.getAllClaimings());

        claimingTableView.setItems(claimings);
        claimingTableView.getColumns().clear();
        claimingTableView.getColumns().addAll(usernameColumn, reasonColumn);
    }

    @FXML public void handleSelectedClaiming(){
        claimingTableView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Claiming>(){
                    @Override
                    public void changed(ObservableValue<? extends Claiming> observable, Claiming oldValue, Claiming newValue) {
                        readClaiming(newValue);
                    }
                });
    }

    private void readClaiming(Claiming claiming){
        information.setClaiming(claiming);

        try{
            FXRouter.goTo("claimingListDetail", information);

        } catch(IOException e){
            System.err.println("ไปที่หน้า claimingListDetail ไม่ได้");
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
