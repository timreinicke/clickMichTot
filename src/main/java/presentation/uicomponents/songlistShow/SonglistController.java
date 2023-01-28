package presentation.uicomponents.songlistShow;

import application.GameApplication;
import business.MP3;
import business.Playlist;
import business.PlaylistManager;
import business.Song;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import presentation.uicomponents.playlistShow.PlaylistCell;
import presentation.uicomponents.playlistShow.PlaylistShowView;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class SonglistController {
    PlaylistManager manager;
    ListView<Playlist> showPlaylist;
    GameApplication application;

    MP3 player;

    SonglistShowView songView;

    ArrayList<Song> allSongs;
    ListView<Song> allSongsView;
    public SonglistController(PlaylistManager manager, GameApplication application, MP3 player){

        this.manager = manager;
        this.application = application;
        this.player = player;

        this.songView = new SonglistShowView();

        allSongsView = songView.allSongs;

        allSongs = new ArrayList<>();

        initialize();
    }

    public void initialize() {

        allSongs = manager.getAktPlaylist().getSongs();
        allSongsView.setCellFactory(
                p -> new SonglistCell()
        );

        allSongsView.setOnMouseClicked(e -> {
            try {
                application.switchScene("GameApplication", "GameScreen", allSongsView.getSelectionModel().getSelectedItem().getFilename());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            player.setAktSong(allSongsView.getSelectionModel().getSelectedItem());
        });

        ObservableList<Song> songsContent = FXCollections.observableArrayList();
        songsContent.clear();
        songsContent.addAll(allSongs);

        allSongsView.setItems(songsContent);
        allSongsView.setEditable(true);

        songsContent.addListener((ListChangeListener<Song>) change -> {
            while(change.next()){
                if(change.wasPermutated()){
                    for(int from = change.getFrom(); from<change.getTo();++from){
                        int to = change.getPermutation(from);
                    }
                }else if (change.wasUpdated()){

                }else {
                    for(Song item : change.getRemoved());
                    for(Song item : change.getAddedSubList());
                }
            }
        });
    }

    public SonglistShowView getSongView() {return songView;}
}



