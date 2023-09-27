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
import ku.cs.models.report.Claiming;
import ku.cs.models.report.ClaimingList;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.ClaimingListDataSource;
import ku.cs.services.DataSource;
import ku.cs.services.Information;
import java.io.IOException;

public class ClaimingListDetailController {
    private Information information;
    private Claiming claiming;
    private DataSource<ClaimingList> claimingListDatasource;
    private DataSource<NisitList> nisitListDataSource;
    private ClaimingList claimingList;
    private NisitList nisitList;
    @FXML private Label usernameLabel;
    @FXML private Label reasonLabel;
    @FXML private Label errorLabel;
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        claiming = information.getClaiming();
        claimingListDatasource = new ClaimingListDataSource("data", "claiming.csv");
        nisitListDataSource = new NisitListDataSource("data", "register.csv");

        usernameLabel.setText(claiming.getUsername());
        reasonLabel.setText(claiming.getReason());
        reasonLabel.setWrapText(true);
        errorLabel.setText("");

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try {
            FXRouter.goTo("claimingList");
        } catch(IOException e){
            System.out.println("ไปที่หน้า claimingList ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleDeleteClaimingButton(ActionEvent actionEvent){
        deleteThisClaiming();
    }

    private void deleteThisClaiming(){
        claimingList = claimingListDatasource.readData();

        for(Claiming thisClaiming : claimingList.getAllClaimings()) {
            if (thisClaiming.equals(claiming)) {
                claimingList.getAllClaimings().remove(thisClaiming);
                claimingListDatasource.writeData(claimingList);

                try {
                    FXRouter.goTo("claimingList");
                } catch(IOException e){
                    System.out.println("ไปที่หน้า claimingList ไม่ได้");
                    System.out.println("ให้ตรวจสอบการกำหนด route");
                }
                return;
            }
        }
    }

    @FXML public void handleUnBannedButton(ActionEvent actionEvent) {
        nisitList = nisitListDataSource.readData();

        for(Nisit nisit : nisitList.getAllNisits()){
            if(claiming.getUsername().equals(nisit.getUsername())){
                if(!nisit.isAvailable()){
                    nisit.unBanAccount();
                    nisitListDataSource.writeData(nisitList);
                    deleteThisClaiming();
                } else{
                    errorLabel.setText("บัญชีผู้ใช้นี้ไม่ได้ถูกระงับ");
                }
                return;
            }
        }
    }
}
