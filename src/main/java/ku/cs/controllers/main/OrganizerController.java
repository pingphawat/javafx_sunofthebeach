package ku.cs.controllers.main;

import animatefx.animation.FadeInUpBig;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import java.io.IOException;

public class OrganizerController {
    @FXML private AnchorPane root;

    @FXML public void initialize(){
        new FadeInUpBig(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
