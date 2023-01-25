package presentation.uicomponents.playlistShow;

import application.GameApplication;
import business.Playlist;
import business.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import presentation.scenes.menuScreen.MenuScreenController;


public class PlaylistShowController {
    PlaylistManager manager;
    ListView<Playlist> showPlaylist;
    GameApplication application;

    public PlaylistShowController(PlaylistManager manager,  GameApplication application){

        this.manager = manager;
        this.application = application;

        ObservableList content = FXCollections.observableArrayList();
        content.clear();
        content.setAll(manager.getPlaylistManager());

        showPlaylist = new ListView<>();
        showPlaylist.setItems(content);
        showPlaylist.setEditable(true);

        showPlaylist.setCellFactory(
                new Callback<ListView<Playlist>, ListCell<Playlist>>() {
                    @Override
                    public ListCell<Playlist> call(ListView<Playlist> playlistListView) {
                        return new PlaylistCell(application, manager);
                    }
                }
        );
        content.addListener(new ListChangeListener<Playlist>(){
            @Override
            public void onChanged(
                    ListChangeListener.Change<? extends Playlist> change){
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
            }
        });
    }
}
