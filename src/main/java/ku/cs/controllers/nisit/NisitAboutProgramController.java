package ku.cs.controllers.nisit;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ku.cs.services.ThemeMode;
import java.io.IOException;

public class NisitAboutProgramController {
    @FXML private AnchorPane root;

    @FXML public void initialize() {
        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML
    public void handleBackButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("nisitHome");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
