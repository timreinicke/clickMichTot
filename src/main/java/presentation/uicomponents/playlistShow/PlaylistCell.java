package presentation.uicomponents.playlistShow;

import application.GameApplication;
import business.Playlist;
import business.PlaylistManager;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PlaylistCell extends ListCell<Playlist> {
    private Pane view;
    private Label playlistLabel;
    private GameApplication application;
    private PlaylistManager manager;
    public PlaylistCell(){
        view = new HBox();

        this.manager = manager;
        this.application = application;

        playlistLabel = new Label();

        view.getChildren().add(playlistLabel);
    }

    protected void updateItem(Playlist p, boolean empty){
        super.updateItem(p, empty);

        setText(null);
        setGraphic(null);

        if(p != null){
            this.playlistLabel.setText(p.getPlaylistName());
            this.view.setPrefWidth(30);
            this.view.setPrefHeight(40);
            this.setGraphic(view);
        }else {
            setGraphic(null);
        }
    }
}
