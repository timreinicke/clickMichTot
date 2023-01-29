package presentation.scenes.playlistMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PlaylistMenuView extends BorderPane {
    HBox list;
    Button back;
    public PlaylistMenuView(){
        list = new HBox();
        back = new Button("Back to Main Menu");
        back.getStyleClass().add("backButton");

        this.setTop(back);
        back.setAlignment(Pos.TOP_LEFT);
        this.setCenter(list);

        list.setAlignment(Pos.CENTER);
        list.setMinWidth(this.getMinWidth());
        list.setMinHeight(this.getMinHeight());
    }
}
