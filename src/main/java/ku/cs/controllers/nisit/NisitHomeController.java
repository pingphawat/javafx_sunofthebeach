package ku.cs.controllers.nisit;

import animatefx.animation.FadeIn;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.services.ThemeMode;
import ku.cs.models.account.Nisit;
import ku.cs.models.account.NisitList;
import ku.cs.services.NisitListDataSource;
import javafx.scene.input.MouseEvent;
import ku.cs.services.Information;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.IOException;

public class NisitHomeController {
    private Information information;
    private Nisit nisit;
    private NisitListDataSource nisitListDataSource;
    private NisitList nisitList;
    @FXML private AnchorPane root;
    @FXML private Label usernameLabel;
    @FXML private ImageView profile ;

    @FXML public void initialize(){
        information = (Information) FXRouter.getData();
        nisit = information.getNisit();
        nisitListDataSource = new NisitListDataSource("data","register.csv");
        nisitList = nisitListDataSource.readData();

        usernameLabel.setText(nisit.getUsername());
        String directoryName = "data" + File.separator + "images" + File.separator + "avatar";
        String filepath = directoryName + File.separator + nisit.getUsername() + ".png";
        profile.setImage(new Image(new File(filepath).toURI().toString()));

        new FadeIn(root).play();
        ThemeMode.setThemeMode(root);
    }

    @FXML public void handleAboutProgramButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("nisitAboutProgram");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitAboutProgram ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePicAboutProgramButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("nisitAboutProgram");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitAboutProgram ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleOrganizerButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("nisitOrganizer");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitOrganizer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePicOrganizerButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("nisitOrganizer");
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitOrganizer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleProfileButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("nisitProfile", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitProfile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleLogOutButton(ActionEvent actionEvent) {
        nisit = nisitList.findAccount(nisit.getUsername());
        nisit.setLoginStatus(false);
        nisitListDataSource.writeData(nisitList);

        try{
            FXRouter.goTo("home");
        } catch(IOException e){
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleReportButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("nisitReport", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReport ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePicReportButton(MouseEvent mouseEvent){
        try{
            FXRouter.goTo("nisitReport", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReport ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handleReportListButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("nisitReportList", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReportList ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML public void handlePicReportListButton(MouseEvent mouseEvent){
        try{
           FXRouter.goTo("nisitReportList", information);
        } catch(IOException e){
            System.err.println("ไปที่หน้า nisitReportList ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
