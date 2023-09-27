package ku.cs.controllers.main;

import animatefx.animation.Flash;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import java.io.IOException;

public class LoadingController {
    @FXML private AnchorPane root;

    @FXML public void handleHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }

        new Flash(root).play();
        ThemeMode.setThemeMode(root);
    }
}
