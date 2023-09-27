package ku.cs.services;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ThemeMode {
    private static boolean isDarkMode;

    public static void setThemeMode(AnchorPane root) {
        if (isDarkMode) {
            setDarkMode(root);
        } else {
            setLightMode(root);
        }
    }

    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static void toggleTheme() {
        isDarkMode = !isDarkMode;
    }

    public static void setLightMode(AnchorPane root) {
        root.getStylesheets().clear();
        root.getStylesheets().add(ThemeMode.class.getResource("/ku/cs/forButton/light.css").toExternalForm());
    }

    public static void setDarkMode(AnchorPane root) {
        root.getStylesheets().clear();
        root.getStylesheets().add(ThemeMode.class.getResource("/ku/cs/forButton/dark.css").toExternalForm());
    }

    public static void setLightMode(AnchorPane root, Button mode) {
        setLightMode(root);
        mode.setText("Dark");
    }

    public static void setDarkMode(AnchorPane root, Button mode) {
        setDarkMode(root);
        mode.setText("Light");
    }
}