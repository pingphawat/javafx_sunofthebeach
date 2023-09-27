package ku.cs;

import javafx.application.Application;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "ระบบร้องเรียนมหาวิทยาลัยเกษตรศาสตร์",800,512 );
        configRoute();
        stage.setResizable(false);
        FXRouter.goTo("loading");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        //Main
        FXRouter.when("loading", packageStr+"loading.fxml");
        FXRouter.when("register", packageStr+"register.fxml");
        FXRouter.when("home", packageStr+"home.fxml");
        FXRouter.when("claiming", packageStr+"claiming.fxml");
        FXRouter.when("organizer", packageStr+"organizer.fxml");
        FXRouter.when("aboutProgram", packageStr+"aboutProgram.fxml");
        //Nisit
        FXRouter.when("nisitReport", packageStr+"nisitReport.fxml");
        FXRouter.when("nisitHome", packageStr+"nisitHome.fxml");
        FXRouter.when("nisitAboutProgram", packageStr+"nisitAboutProgram.fxml");
        FXRouter.when("nisitOrganizer", packageStr+"nisitOrganizer.fxml");
        FXRouter.when("nisitProfile", packageStr+"nisitProfile.fxml");
        FXRouter.when("nisitReportList", packageStr+"nisitReportList.fxml");
        FXRouter.when("nisitReportListDetail", packageStr+"nisitReportListDetail.fxml");
        FXRouter.when("nisitReportInappropriateContent", packageStr+"nisitReportInappropriateContent.fxml");
        FXRouter.when("nisitReportInappropriateUser", packageStr+"nisitReportInappropriateUser.fxml");
        //Officer
        FXRouter.when("officerHome", packageStr+"officerHome.fxml");
        FXRouter.when("officerOrganizer", packageStr+"officerOrganizer.fxml");
        FXRouter.when("officerAboutProgram", packageStr+"officerAboutProgram.fxml");
        FXRouter.when("officerProfile", packageStr+"officerProfile.fxml");
        FXRouter.when("officerReportDetail", packageStr+"officerReportDetail.fxml");
        FXRouter.when("officerFixReport", packageStr+"officerFixReport.fxml");
        //Admin
        FXRouter.when("adminHome", packageStr+"adminHome.fxml");
        FXRouter.when("adminProfile", packageStr+"adminProfile.fxml");
        FXRouter.when("createOfficerAccount", packageStr+"createOfficerAccount.fxml");
        FXRouter.when("adminAboutProgram",packageStr+"adminAboutProgram.fxml");
        FXRouter.when("adminOrganizer",packageStr+"adminOrganizer.fxml");
        FXRouter.when("inappropriate", packageStr+"inappropriate.fxml");
        FXRouter.when("inappropriateUserReportList", packageStr+"inappropriateUserReportList.fxml");
        FXRouter.when("inappropriateContentReportList", packageStr+"inappropriateContentReportList.fxml");
        FXRouter.when("claimingList", packageStr+"claimingList.fxml");
        FXRouter.when("claimingListDetail", packageStr+"claimingListDetail.fxml");
        FXRouter.when("inappropriateUserReportListDetail", packageStr+"inappropriateUserReportListDetail.fxml");
        FXRouter.when("inappropriateContentReportListDetail", packageStr+"inappropriateContentReportListDetail.fxml");
        FXRouter.when("nisitDetail", packageStr+"nisitDetail.fxml");
        FXRouter.when("officerDetail", packageStr+"officerDetail.fxml");
        FXRouter.when("bannedUserList", packageStr+"bannedUserList.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}