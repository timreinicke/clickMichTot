package presentation.scenes.playlistMenu;

import application.GameApplication;
import business.Playlist;
import business.PlaylistManager;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import presentation.uicomponents.AlertBox;
import presentation.uicomponents.playlistShow.PlaylistShowController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class playlistMenuController {
    private playlistMenuView view;
    GameApplication application;
    PlaylistManager manager;
    PlaylistShowController playlistView;
    public playlistMenuController(GameApplication application, PlaylistManager manager){
        this.manager = manager;
        this.application = application;
        this.view = new playlistMenuView();
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
                AlertBox.display("INVALID DATATYPE OR ALREADY IMPLEMENTED", "Your file either has an invalid datatype or is already implemented, please check and try agaim");
            }
                /*modes = dragboard.getTransferModes();
                for(TransferMode mode : modes) {
                    TransferMode.COPY = modes;
                }*/

            if(dropSupported){
                System.out.println("hier");
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

            System.out.println("bin");
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
                        System.out.println("ich");
                        manager.addPlaylist(f);
                        reload();
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
    public playlistMenuView getView(){return view;}
}
