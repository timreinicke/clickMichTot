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
 * erstellt Playlisten, die dann in PlaylistManager verwaltet werden.
 * Implementiert Cloneable um eine ShufflePlaylist erstellen zu können
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
	 * Laed die Playlist indem ein BufferedReader ueber ein File iteriert und dann die Methode addTrack aufruft
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
	 * iteriert ueber Playlist Liste um einen uebergebenen Song zu suchen
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
