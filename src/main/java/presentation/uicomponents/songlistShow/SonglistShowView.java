package presentation.uicomponents.songlistShow;

import business.Playlist;
import business.Song;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SonglistShowView extends BorderPane {

    Label headerLabel;
    ListView<Song> allSongs;
    VBox songContainer;
    public SonglistShowView(){
        headerLabel = new Label("All Songs, so much to choose from!");
        headerLabel.getStyleClass().add("list-cell_label");
        this.setTop(headerLabel);
        songContainer = new VBox();

        allSongs = new ListView<>();
        songContainer.getChildren().add(allSongs);
        this.setCenter(songContainer);

        BorderPane.setAlignment(headerLabel, Pos.CENTER);
        BorderPane.setMargin(headerLabel, new Insets(10));

        BorderPane.setAlignment(songContainer, Pos.CENTER);
        BorderPane.setMargin(songContainer, new Insets(10));
    }
}