package business;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import exceptions.PlaylistNotFoundException;
import exceptions.SongNotFoundException;
import presentation.uicomponents.AlertBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

/*
 * Manager for our playlist objects
 */

public class PlaylistManager {
	public ArrayList<Playlist> playlistManager;
	Playlist aktPlaylist;
	Einstellungen settings;

	public PlaylistManager (Einstellungen settings) throws IOException, InvalidDataException, UnsupportedTagException {
		this.settings = settings;
		getPlaylists();
	}

	/*
	 * loads playlist objects
	 */

	public void getPlaylists() throws IOException, InvalidDataException, UnsupportedTagException {
		playlistManager = new ArrayList<Playlist>();
		String playlistDir = settings.getPlaylistsDir();
		File folder = new File(playlistDir);

		for(File fileEntry: Objects.requireNonNull(folder.listFiles())) {
			playlistManager.add(new Playlist(fileEntry));
		}
	}

	public void addPlaylist(File f) throws InvalidDataException, UnsupportedTagException, IOException {
		Playlist newPL = new Playlist(f);
		if(!(f.length() == 0) && !checkIfExists(newPL)) {
			playlistManager.add(newPL);
		}else {
			AlertBox.display("PLAYLIST EMPTY OR EXISTS ALREADY", "Your file is empty or exists as playlist already");
		}
	}
	public boolean checkIfExists(Playlist added){
		for(Playlist p : playlistManager){
			if(added.sizeOf() != p.sizeOf()) continue;
			boolean exists = true;
			for(Song s : added.getSongs()){
				if(!p.searchSong(s)){
					exists = false;
					break;
				}
			}
			if(exists) return true;
		}
		return false;
	}

	public ArrayList<Playlist> getPlaylistManager() {
		return playlistManager;
	}

	/*
	 * get current playlist
	 * @return aktPlaylist
	 * @return shufflePlaylist
	 */

	public Playlist getAktPlaylist() {
			return aktPlaylist;
	}

	/*
	 * sets current playlist
	 */

	public void setPlaylist(Playlist p) throws PlaylistNotFoundException, IOException, InvalidDataException, UnsupportedTagException {
		this.aktPlaylist = p;
	}
}




