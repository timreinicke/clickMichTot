package presentation.uicomponents.playlistShow;

import business.Playlist;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlaylistShowView extends BorderPane {

    Label headerLabel;
    ListView<Playlist> playlistListView;

    VBox playlistContainer;
    public PlaylistShowView(){
        headerLabel = new Label("All Playlists, you may drop files.m3u to add more");
        this.setTop(headerLabel);
        headerLabel.getStyleClass().add("title");

        playlistContainer = new VBox();

        playlistListView = new ListView<>();
        playlistContainer.getChildren().add(playlistListView);
        this.setCenter(playlistContainer);

        BorderPane.setAlignment(playlistContainer, Pos.CENTER);
        BorderPane.setMargin(playlistContainer, new Insets(10));

        BorderPane.setAlignment(headerLabel, Pos.CENTER);
        BorderPane.setMargin(headerLabel, new Insets(10));


    }
}
