package presentation.uicomponents.playlistShow;

import application.GameApplication;
import business.Playlist;
import business.PlaylistManager;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import exceptions.PlaylistNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import presentation.uicomponents.songlistShow.SonglistController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;


public class PlaylistShowController{
    PlaylistManager manager;
    ListView<Playlist> showPlaylist;
    GameApplication application;
    PlaylistShowView playlistWindow;

    ArrayList<Playlist> allPlaylists;
    ListView<Playlist> allPlaylistView;
    public PlaylistShowController(PlaylistManager manager,  GameApplication application){

        this.manager = manager;
        this.application = application;

        this.playlistWindow = new PlaylistShowView();

        showPlaylist = new ListView<>();

        allPlaylistView = playlistWindow.playlistListView;

        allPlaylists = new ArrayList<>();

        initialize();
    }

    public void initialize() {

        allPlaylists = manager.getPlaylistManager();

        /*allPlaylistView.setCellFactory(
                p -> new PlaylistCell()
        );*/

        allPlaylistView.setOnMouseClicked(e -> {
            System.out.println(allPlaylistView.getSelectionModel().getSelectedItems());

            try {
                manager.setPlaylist(allPlaylistView.getSelectionModel().getSelectedItem());
            } catch (PlaylistNotFoundException | IOException | InvalidDataException | UnsupportedTagException ex) {
                throw new RuntimeException(ex);
            }

            try {
                application.switchScene("SonglistScreen", "SongList");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        ObservableList<Playlist> playlistContent = FXCollections.observableArrayList();
        playlistContent.clear();
        playlistContent.addAll(allPlaylists);

        Predicate<Playlist> filter = playlist -> playlist.getSongs().size()>0;

        playlistContent = playlistContent.filtered(filter);

        allPlaylistView.setPrefHeight(playlistContent.size()*40);

        allPlaylistView.setItems(playlistContent);
        allPlaylistView.setEditable(true);
        allPlaylistView.setCellFactory(
                p -> new PlaylistCell()
        );

        playlistContent.addListener((ListChangeListener<Playlist>) change -> {
            while(change.next()){
                if(change.wasPermutated()){
                    for(int from = change.getFrom(); from<change.getTo();++from){
                        int to = change.getPermutation(from);
                    }
                }else if (change.wasUpdated()){

                }else {
                    for(Playlist item : change.getRemoved());
                    for(Playlist item : change.getAddedSubList());
                }
            }
});
    }

    public PlaylistShowView getPlaylistWindow() {return playlistWindow;}
}
