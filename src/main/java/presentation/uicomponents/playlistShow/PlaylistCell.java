package presentation.uicomponents.playlistShow;

import business.Playlist;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PlaylistCell extends ListCell<Playlist> {
    private Pane view;
    private Label playlistLabel;
    public PlaylistCell(){
        view = new HBox();

        playlistLabel = new Label();
        playlistLabel.getStyleClass().add("list-cell_label");
        view.getChildren().add(playlistLabel);
    }

    protected void updateItem(Playlist p, boolean empty){
        super.updateItem(p, empty);

        setText(null);
        setGraphic(null);

        if(p != null){
            this.playlistLabel.setText(p.getPlaylistName());
            this.view.minWidth(1000);
            this.view.setPrefHeight(40);
            this.setGraphic(view);
        }else {
            setGraphic(null);
        }
    }
}
