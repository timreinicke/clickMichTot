package presentation.scenes.playlistMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PlaylistMenuView extends BorderPane {
    HBox list;
    HBox topContainer;
    Button back;
    public PlaylistMenuView(){
        list = new HBox();
        topContainer = new HBox();

        back = new Button("Back to Main Menu");
        back.getStyleClass().add("return-button");

        topContainer.getChildren().add(back);
        topContainer.setPadding(new Insets(10));
        this.setTop(topContainer);
        back.setAlignment(Pos.TOP_LEFT);
        this.setCenter(list);

        list.setAlignment(Pos.CENTER);
        list.setPrefWidth(500);

        list.getStyleClass().add("list");

        this.getStyleClass().add("container");
    }
}
