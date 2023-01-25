package business;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import exceptions.PlaylistNotFoundException;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

/*
 * Manager fuer unsere Playlistobjekte. Verwaltet sie und setzt aktuelle Playlist und Shuffleplaylist
 */

public class PlaylistManager {
	public ArrayList<Playlist> playlistManager;
	Playlist aktPlaylist;
	Playlist shufflePlaylist;
	Einstellungen settings;

	public PlaylistManager (Einstellungen settings) throws IOException, InvalidDataException, UnsupportedTagException {
		this.settings = settings;
		getPlaylists();
	}

	/*
	 * laed alle Playlisten als File aus dem gegebenen Verzeichnis aus
	 */

	public void getPlaylists() throws IOException, InvalidDataException, UnsupportedTagException {
		playlistManager = new ArrayList<Playlist>();
		String playlistDir = settings.getPlaylistsDir();
		File folder = new File(playlistDir);

		for(File fileEntry: Objects.requireNonNull(folder.listFiles())) {
			playlistManager.add(new Playlist(fileEntry));
		}
	}

	public ArrayList<Playlist> getPlaylistManager() {
		return playlistManager;
	}

	/*
	 * gibt aktuelle Playlist zurueck
	 * @return aktPlaylist
	 * @return shufflePlaylist
	 */

	public Playlist getAktPlaylist() {
			return aktPlaylist;
	}

	/*
	 * setzt aktuelle Playlist
	 */

	public void setPlaylist(Playlist p) throws PlaylistNotFoundException, IOException, InvalidDataException, UnsupportedTagException {
		this.aktPlaylist = p;
	}
}




