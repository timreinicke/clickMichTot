package presentation.scenes.settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import presentation.uicomponents.volume.VolumeSliderController;

/*
 * settings view
 */

public class SettingsView extends BorderPane {
    TextField dirField;
    Label dirLabel;
    Button submitButton;
    Label title;
    Button toMainMenu;
    VBox settingsContainer;
    Label audioInput;

    VBox centerPane;
    HBox topPane;

    public SettingsView(){
        topPane = new HBox();
        title = new Label("Einstellungen");
        title.getStyleClass().addAll("title");

        toMainMenu = new Button("Back to Main Menu");
        toMainMenu.getStyleClass().add("return-button");
        topPane.setPadding(new Insets(20));

        centerPane = new VBox();

        settingsContainer = new VBox();


        HBox inputContainer = new HBox();
        dirLabel = new Label("Setze den Playlistpfad");
        dirField = new TextField();
        submitButton = new Button("Setzen");

        dirLabel.getStyleClass().addAll("settings-label", "settings");
        dirField.getStyleClass().addAll("settings-field", "settings");
        submitButton.getStyleClass().addAll("settings-button", "settings");


        inputContainer.getChildren().addAll(dirLabel, dirField, submitButton);
        inputContainer.setSpacing(10);
        inputContainer.getStyleClass().addAll("center");

        audioInput = new Label("Set Volume:");
        audioInput.getStyleClass().addAll("settings-label", "settings");

        settingsContainer.getChildren().addAll(inputContainer, audioInput);
        settingsContainer.setSpacing(10);
        settingsContainer.getStyleClass().add("center");

        centerPane.getChildren().addAll(title, settingsContainer);
        centerPane.setPadding(new Insets(25, 10, 10, 25));
        centerPane.setSpacing(10);

        topPane.getChildren().add(toMainMenu);

        this.getStyleClass().add("container");
        this.setCenter(centerPane);
        this.setTop(topPane);
    }
}
