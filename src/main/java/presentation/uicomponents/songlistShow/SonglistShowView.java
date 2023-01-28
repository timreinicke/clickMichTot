package presentation.uicomponents.songlistShow;

import business.Playlist;
import business.Song;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class SonglistShowView extends BorderPane {

    Label headerLabel;
    ListView<Song> allSongs;
    public SonglistShowView(){
        headerLabel = new Label("All Songs, so much to choose from!");
        this.setTop(headerLabel);

        allSongs = new ListView<>();
        this.setCenter(allSongs);

        BorderPane.setAlignment(headerLabel, Pos.CENTER);
        BorderPane.setMargin(headerLabel, new Insets(10));

        BorderPane.setAlignment(allSongs, Pos.CENTER);
        BorderPane.setMargin(allSongs, new Insets(10));
    }
}