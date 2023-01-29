package presentation.scenes.settings;

import application.GameApplication;
import business.Einstellungen;
import business.MP3;
import business.PlaylistManager;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SettingsController {
    SettingsView view;
    TextField dirField;
    Label dirLabel;
    Button submitButton;
    PlaylistManager manager;
    GameApplication application;
    Button toMainMenu;
    Einstellungen settings;

    public SettingsController(GameApplication application, MP3 player, PlaylistManager manager, Einstellungen settings){
        view = new SettingsView();
        this.dirField = view.dirField;
        this.dirLabel = view.dirLabel;
        this.submitButton = view.submitButton;
        this.manager = manager;
        this.application = application;
        this.toMainMenu = view.toMainMenu;
        this.settings = settings;

        initialize();
    }
    public void initialize(){
        submitButton.setOnAction(mouseEvent -> {
            if((dirField.getText() != null && !dirField.getText().isEmpty() && validPath(dirField.getText()))){
                dirLabel.setText("Pfad erfolgreich geändert!");
                try {
                    settings.setPlaylistsDir(dirField.getText());
                    manager.getPlaylistManager();
                    manager.getPlaylists();
                    application.reloadPlaylists(); // setzt playlist noch nicht richtig
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InvalidDataException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedTagException e) {
                    throw new RuntimeException(e);
                }
            }else {
                dirLabel.setText("Feld leer oder Pfad ungültig");
            }
        });

        toMainMenu.setOnAction(e->{
            try {
                application.switchScene("MainMenu");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public boolean validPath(String toCheck) {
        Path path = Path.of(toCheck);
        return Files.exists(path);
    }
    public SettingsView getView(){
        return view;
    }
}
