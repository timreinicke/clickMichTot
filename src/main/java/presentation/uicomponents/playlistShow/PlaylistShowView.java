package presentation.uicomponents.playlistShow;

import business.Playlist;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class PlaylistShowView extends BorderPane {

    Label headerLabel;
    ListView<Playlist> playlistListView;
    public PlaylistShowView(){
        headerLabel = new Label("All Playlists, you may drop files.m3u to add more");
        this.setTop(headerLabel);

        playlistListView = new ListView<>();
        this.setCenter(playlistListView);

        BorderPane.setAlignment(headerLabel, Pos.CENTER);
        BorderPane.setMargin(headerLabel, new Insets(10));

        BorderPane.setAlignment(playlistListView, Pos.CENTER);
        BorderPane.setMargin(playlistListView, new Insets(10));
    }
}
