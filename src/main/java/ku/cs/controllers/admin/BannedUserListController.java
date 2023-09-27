package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.Filterer;
import ku.cs.services.NisitListDataSource;
import ku.cs.services.DataSource;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class BannedUserListController {
    @FXML private TableView<Nisit> bannedUserTableView;
    @FXML private TableColumn usernameColumn;
    @FXML private TableColumn loginAttemptColumn;
    private DataSource<NisitList> nisitListDatasource;
    private NisitList nisitList;
    private NisitList filteredNisitList;
    private ObservableList<Nisit> nisits;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Nisit, String>("username"));
        loginAttemptColumn.setCellValueFactory(new PropertyValueFactory<Nisit, Integer>("loginAttempt"));

        nisits = FXCollections.observableArrayList();
        nisitListDatasource = new NisitListDataSource("data", "register.csv");
        nisitList = nisitListDatasource.readData();
        filteredNisitList = nisitList;

        showTable();

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    private void showTable(){
        filteredNisitList = nisitList.filterBy(new Filterer<Nisit>() {
            @Override
            public boolean filter(Nisit nisit) {
                if(!nisit.isAvailable())
                    return true;
                return false;
            }
        });

        nisits.clear();
        nisits.addAll(filteredNisitList.getAllNisits());
        bannedUserTableView.setItems(nisits);
        bannedUserTableView.getColumns().clear();
        bannedUserTableView.getColumns().addAll(usernameColumn, loginAttemptColumn);
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
