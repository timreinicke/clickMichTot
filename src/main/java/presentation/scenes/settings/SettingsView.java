package presentation.scenes.settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;


public class SettingsView extends BorderPane {
    TextField dirField;
    Label dirLabel;
    Button submitButton;
    Label title;
    Button toMainMenu;
    Button settings;

    public SettingsView(){
        title = new Label("Einstellungen");
        title.getStyleClass().addAll("title", "center");

        toMainMenu = new Button("Main Menu");
        VBox centerPane = new VBox();


        HBox inputContainer = new HBox();
        dirLabel = new Label("Setze den Playlistpfad");
        dirField = new TextField();
        submitButton = new Button("Setzen");

        dirLabel.getStyleClass().addAll("settings-label", "settings");
        dirField.getStyleClass().addAll("settings-field", "settings");
        submitButton.getStyleClass().addAll("settings-button", "settings");


        inputContainer.getChildren().addAll(dirLabel, dirField, submitButton);
        inputContainer.setSpacing(10);
        centerPane.getChildren().addAll(title, toMainMenu, inputContainer);
        centerPane.setPadding(new Insets(25, 10, 10, 25));
        centerPane.setSpacing(10);
        centerPane.getStyleClass().addAll("center-container");

        this.setCenter(centerPane);
        toMainMenu.setAlignment(Pos.TOP_LEFT);
    }
}
