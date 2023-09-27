module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires AnimateFX;

    exports ku.cs;
    opens ku.cs to javafx.fxml;

    exports ku.cs.services;
    opens ku.cs.services to javafx.fxml;

    exports ku.cs.controllers.main;
    opens ku.cs.controllers.main to javafx.fxml;
    exports ku.cs.controllers.admin;
    opens ku.cs.controllers.admin to javafx.fxml;
    exports ku.cs.controllers.nisit;
    opens ku.cs.controllers.nisit to javafx.fxml;
    exports ku.cs.controllers.officer;
    opens ku.cs.controllers.officer to javafx.fxml;

    exports ku.cs.models.report;
    opens ku.cs.models.report to java.base, javafx.base;
    exports ku.cs.models.account;
    opens ku.cs.models.account to java.base, javafx.base;
}