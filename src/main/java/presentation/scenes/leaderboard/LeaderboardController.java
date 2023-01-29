package presentation.scenes.leaderboard;

import application.GameApplication;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LeaderboardController {

    GameApplication application;
    protected LeaderboardView view;
    protected Button backButton;
    protected ListView<String> leaders;

    public LeaderboardController(GameApplication application){
        this.application = application;
        view = new LeaderboardView();
        backButton = new Button();
        leaders = view.leaders;

        try {
            readLeaders();
        }catch (IOException e){
            e.printStackTrace();
        }

        initialize();
    }

    public void initialize(){
        backButton.setOnAction(e->{
            try {
                application.switchScene("MainMenu");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void readLeaders() throws IOException {
        try(Stream<String> stream = Files.lines(Paths.get("leaderboard.txt"))){
            stream.forEach(leaders.getItems()::add);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public LeaderboardView getView(){
        return view;
    }
}
