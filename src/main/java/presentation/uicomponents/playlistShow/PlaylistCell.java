package presentation.uicomponents.playlistShow;

import application.GameApplication;
import business.Playlist;
import business.PlaylistManager;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import exceptions.PlaylistNotFoundException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import presentation.scenes.menuScreen.MenuScreenController;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class PlaylistCell extends ListCell<Playlist> {
    private Pane view;
    private Label playlistLabel;
    private Button play;
    private GameApplication application;
    private PlaylistManager manager;
    public PlaylistCell(GameApplication application, PlaylistManager manager){
        this.manager = manager;
        this.application = application;
        VBox trackPane = new VBox();
        playlistLabel = new Label();
        trackPane.getChildren().add(playlistLabel);

        view = new HBox();
        play= new Button("auswählen");
        view.getChildren().addAll(trackPane, play);
        play.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                try {

                    application.switchScene("Playlistview");
                    manager.setPlaylist(getItem());

                } catch (PlaylistNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InvalidDataException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedTagException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        this.setGraphic(view);
    }
    protected void updateItem(Playlist p, boolean empty){
        super.updateItem(p, empty);
        setText(null);
        setGraphic(null);
        if(p != null){
            this.playlistLabel.setText(p.getPlaylistName());
            view.setPrefWidth(15);
            setGraphic(view);
        }else {
            setGraphic(null);
        }
    }
}
