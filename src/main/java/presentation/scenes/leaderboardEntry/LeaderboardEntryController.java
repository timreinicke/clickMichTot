package presentation.scenes.leaderboardEntry;

public class LeaderboardEntryController {

    LeaderboardEntryView view;
    public LeaderboardEntryController(){
        view = new LeaderboardEntryView();
    }

    public LeaderboardEntryView getView(){
        return view;
    }
}
