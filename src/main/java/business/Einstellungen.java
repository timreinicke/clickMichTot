package business;
/*
* Path management for our business logic
 */

public class Einstellungen {

    String playlistsDir;
    int difficulty;
    String heroDir;

    public Einstellungen() {

        playlistsDir = "src/main/resources/application/music/playlists/";
        difficulty = 0;
        this.heroDir = "src/main/resources/application/images/hero_female.png";
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getHeroDir() {
        return heroDir;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setHeroDir(String heroDir) {
        this.heroDir = heroDir;
    }

    public void setPlaylistsDir(String playlistsDir) {
        this.playlistsDir = playlistsDir;
    }

    public String getPlaylistsDir() {
        return playlistsDir;
    }
}
