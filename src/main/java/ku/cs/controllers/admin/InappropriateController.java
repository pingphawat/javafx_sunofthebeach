package ku.cs.controllers.admin;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInDownBig;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ku.cs.services.ThemeMode;
import ku.cs.services.Information;
import java.io.IOException;

public class InappropriateController {
    private Information information;
    @FXML private AnchorPane root;
    @FXML private VBox InappropriateReports;
    @FXML private VBox centerBackground;

    @FXML public void initialize() {
        information = (Information) FXRouter.getData();

        new FadeIn(root).play();
        new FadeInDownBig(InappropriateReports).play();
        new FadeInDownBig(centerBackground).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("adminHome");
        } catch (IOException e) {
            System.out.println("ไปที่หน้า adminHome ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleInappropriateUserReportButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("inappropriateUserReportList", information);
        } catch (IOException e) {
            System.out.println("ไปที่หน้า inappropriateUserReportList ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleInappropriateContentReportButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("inappropriateContentReportList", information);
        } catch (IOException e) {
            System.out.println("ไปที่หน้า inappropriateContentReportList ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleBannedUserList(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("bannedUserList");
        } catch (IOException e) {
            System.out.println("ไปที่หน้า bannedUserList ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleClaimingList(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("claimingList", information);
        } catch (IOException e) {
            System.out.println("ไปที่หน้า claimingList ไม่ได้");
            System.out.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}