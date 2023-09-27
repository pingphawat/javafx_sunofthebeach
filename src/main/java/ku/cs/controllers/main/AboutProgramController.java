package ku.cs.controllers.main;

import animatefx.animation.FadeInDownBig;
import animatefx.animation.FadeInUpBig;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ku.cs.services.ThemeMode;
import java.io.IOException;

public class AboutProgramController {
    @FXML private VBox howTo;
    @FXML private VBox centerBackground;
    @FXML private AnchorPane root;

    @FXML public void initialize() {
        new FadeInDownBig(howTo).play();
        new FadeInDownBig(centerBackground).play();
        new FadeInUpBig(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("home");
        } catch(IOException e){
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
