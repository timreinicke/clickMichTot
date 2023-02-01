package presentation.scenes.leaderboard;

import application.GameApplication;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

public class LeaderboardController {

    /*
     * Reads leaderboard.txt and sorts the listview
     */

    GameApplication application;
    protected LeaderboardView view;
    protected Button backButton;
    protected Button settingsButton;
    protected ListView<String> leaders;

    public LeaderboardController(GameApplication application){
        this.application = application;
        view = new LeaderboardView();
        backButton = view.backButton;
        settingsButton = view.settingsButton;
        leaders = view.leaders;

        try {
            readLeaders();
        }catch (IOException e){
            e.printStackTrace();
        }
        sortList();
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

        settingsButton.setOnAction(e->{
            try {
                application.switchScene("Settings");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void readLeaders() throws IOException {
        try(Stream<String> stream = Files.lines(Paths.get("src/main/resources/application/leaderboard/leaderboard.txt"))){
            stream.forEach(leaders.getItems()::add);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sortList(){
        ObservableList<String> items = leaders.getItems();
        Collections.sort(items, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] parts1 = o1.split(" ");
                String[] parts2 = o2.split(" ");

                int points1 = Integer.parseInt(parts1[1]);
                int points2 = Integer.parseInt(parts2[1]);

                return Integer.compare(points2, points1);
            }
        });
    }
    public LeaderboardView getView(){
        return view;
    }
}
