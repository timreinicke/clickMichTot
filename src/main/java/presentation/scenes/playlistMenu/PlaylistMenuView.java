package presentation.scenes.playlistMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PlaylistMenuView extends Pane {
    HBox list;
    Button back;
    public PlaylistMenuView(){
        list = new HBox();
        back = new Button("Back to Main Menu");
        back.getStyleClass().add("backButton");

        this.getChildren().addAll(list, back);

        list.setAlignment(Pos.CENTER);
        list.setMinWidth(this.getMinWidth());
        list.setMinHeight(this.getMinHeight());
        back.setAlignment(Pos.TOP_LEFT);
    }
}
