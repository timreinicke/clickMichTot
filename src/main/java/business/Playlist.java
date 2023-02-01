package business;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
 * creates Playlists that are managed by PlaylistManager
 */

public class Playlist implements Cloneable {

	ArrayList<Song> playlist;
	int next;
	File f;
	String playlistName;

	@Override
	public String toString() {
		return this.playlistName;
	}

	public Playlist(File f) throws IOException, InvalidDataException, UnsupportedTagException {
		playlist = new ArrayList<>();
		this.f = f;
		loadPlaylist();
	}

	private void addTrack(String fileName) throws IOException {
		try {
			String nameOfFile = this.f.getName();
			Song track = new Song(fileName);
			playlist.add(track);
			playlistName = nameOfFile.substring(0, nameOfFile.lastIndexOf('.'));
		} catch (IOException | UnsupportedTagException | InvalidDataException ex) {
			System.out.println("Error reading Song");
		}
	}

	/*
	 * Loads playlist
	 */

	public void loadPlaylist() throws IOException, InvalidDataException, UnsupportedTagException {
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String line;
			while ((line = br.readLine()) != null) {
				addTrack(line);
			}
		} catch (IOException ex) {
			throw new IOException("Error reading Playlistfile");
		}
		next = -1;
	}

	public ArrayList<Song> getSongs() {
		return playlist;
	}

	/*
	 * iterates over playlist to search a song
	 */

	public boolean searchSong(Song aktSong){
		for (Song akt : this.playlist) {
			if (akt.isEqual(aktSong)) {
				return true;
			}
		}
		return false;
	}

	public int sizeOf(){
		int i = 0;
		for(Song s : playlist){
			i++;
		}
		return i;
	}

	public String getPlaylistName() {return playlistName;}
}
