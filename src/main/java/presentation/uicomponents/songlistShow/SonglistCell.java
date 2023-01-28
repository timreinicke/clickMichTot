package presentation.uicomponents.songlistShow;

import business.Playlist;
import business.Song;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SonglistCell extends ListCell<Song> {
    private Pane view;
    private Label songLabel;
    private Label artistLabel;

    private Label laengeSong;

    public SonglistCell() {
        view = new HBox();

        songLabel = new Label();
        artistLabel = new Label();
        laengeSong = new Label();

        view.getChildren().addAll(songLabel, artistLabel, laengeSong);
    }
    protected void updateItem(Song s, boolean empty){
        super.updateItem(s, empty);

        setText(null);
        setGraphic(null);

        if(s != null){
            this.songLabel.setText(s.getTitle());
            this.artistLabel.setText(s.getArtist());
            this.laengeSong.setText(Integer.toString(s.getDuration()));
            this.view.setPrefWidth(30);
            this.view.setPrefHeight(40);
            this.setGraphic(view);
        }else {
            setGraphic(null);
        }
    }
}
