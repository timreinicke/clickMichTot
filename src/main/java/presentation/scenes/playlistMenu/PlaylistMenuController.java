package presentation.scenes.playlistMenu;

import application.GameApplication;
import business.PlaylistManager;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import presentation.uicomponents.AlertBox;
import presentation.uicomponents.playlistShow.PlaylistShowController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class PlaylistMenuController {
    private PlaylistMenuView view;
    GameApplication application;
    PlaylistManager manager;
    PlaylistShowController playlistView;
    public PlaylistMenuController(GameApplication application, PlaylistManager manager){
        this.manager = manager;
        this.application = application;
        this.view = new PlaylistMenuView();
        playlistView = new PlaylistShowController(manager, application);

        view.getChildren().add(playlistView.getPlaylistWindow());
        initialize();
    }
    public void initialize(){

        view.setOnDragOver((EventHandler<DragEvent>) event -> {

            boolean dropSupported = true;
            Dragboard dragboard = null;
            Set<TransferMode> modes;

            dragboard = event.getDragboard();

            if (!(event.getGestureSource() == view) && !dragboard.hasFiles()){
                dropSupported = false;
                AlertBox.display("ALREADY IMPLEMENTED", "Your file is already implemented, please check and try again");
            }

            if(dropSupported){
                event.acceptTransferModes(TransferMode.COPY);
            }
                }
        );
        view.setOnDragDetected(event -> {
                Dragboard dragboard;

                dragboard = view.startDragAndDrop(TransferMode.COPY);
                List<File> fileList;
                List<File> supportedFiles = null;
                fileList = dragboard.getFiles();



                ClipboardContent content = new ClipboardContent();
                content.putFiles(supportedFiles);
                dragboard.setContent(content);

                event.consume();
        });

        view.setOnDragDropped(e ->{
            Dragboard dragboard = e.getDragboard();

            List<File> fileList;
            List<File> supportedFiles = null;
            fileList = dragboard.getFiles();
            try {
                for (File f : fileList) {
                    if (f.getName().endsWith(".m3u")) {
                        manager.addPlaylist(f);
                        reload();
                    }else {
                        AlertBox.display("INVALID DATATYPE", "Your file has an invalid datatype, please check and try again");
                    }
                }
            } catch (InvalidDataException ex) {
                throw new RuntimeException(ex);
            } catch (UnsupportedTagException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } finally {
                e.consume();
            }
        });
    }

    private void reload(){
        view.getChildren().clear();
        playlistView = new PlaylistShowController(manager, application);
        view.getChildren().add(playlistView.getPlaylistWindow());
    }
    public PlaylistMenuView getView(){return view;}
}