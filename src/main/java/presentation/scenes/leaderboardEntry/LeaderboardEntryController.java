package presentation.scenes.leaderboardEntry;

import application.GameApplication;
import presentation.uicomponents.AlertBox;

import java.io.*;
import java.util.Objects;

public class LeaderboardEntryController {
    LeaderboardEntryView view;
    String leaderboardDir;

    GameApplication application;
    public LeaderboardEntryController(GameApplication application){
        view = new LeaderboardEntryView();
        this.application = application;
        leaderboardDir = "src/main/resources/application/leaderboard/leaderboard.txt";
        initialize();
    }

    public void initialize() {
        view.submit.setOnAction(e -> {
            if((view.getPlayerName.getText() != null && !view.getPlayerName.getText().isEmpty())){
                try {
                    if(!checkNameAndScore(view.getPlayerName.getText(), 238338)){
                        addEntry(view.getPlayerName.getText(), 238338);
                        view.getPlayerName.setText("Erfolgreich hinzugefügt!");
                        view.getPlayerName.setEditable(false);
                        view.submit.setDisable(true);
                        application.reloadLeaderboard();
                    } else {
                        AlertBox.display("ERROR!", "Your Name already exists with the same score!");
                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        view.continueOn.setOnAction(e->{
            try {
                application.switchScene("MainMenu");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void addEntry(String name, int points) throws FileNotFoundException {
        try(BufferedReader br = new BufferedReader(new FileReader(this.leaderboardDir));
            PrintWriter writer = new PrintWriter(new FileWriter(this.leaderboardDir, true))){
            String line;
            while((line = br.readLine()) != null){
                //skips through all lines that full of text
            }
            writer.print("\n" + name + " " + points + " Punkte");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkNameAndScore(String name, int score) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(this.leaderboardDir))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] compare = line.split(" ");
                if (name.equals(compare[0]) && Integer.parseInt(compare[1]) == score) {
                    return true;
                }
            }
        } return false;
    }

    public LeaderboardEntryView getView(){
        return view;
    }
}
