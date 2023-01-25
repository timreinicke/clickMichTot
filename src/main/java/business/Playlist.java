package business;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import exceptions.SongNotFoundException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * erstellt Playlisten, die dann in PlaylistManager verwaltet werden.
 * Implementiert Cloneable um eine ShufflePlaylist erstellen zu können
 */

public class Playlist implements Cloneable {

	List<Song> playlist;
	int next;
	File f;
	String playlistName;

	public Playlist(File f) throws IOException, InvalidDataException, UnsupportedTagException {
		playlist = new ArrayList<>();
		this.f = f;
		loadPlaylist();
	}

	private void addTrack(String fileName) throws IOException {
		try {
			Song track = new Song(fileName);
			playlist.add(track);
			playlistName = this.f.getName();
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

	public List<Song> getSongs() {
		return playlist;
	}

	/*
	 * iteriert ueber Playlist Liste um einen uebergebenen Song zu suchen
	 */

	public int searchSong(Song aktSong) throws SongNotFoundException, IOException, InvalidDataException, UnsupportedTagException {
		int i = 0;
		for (Song akt : this.playlist) {
			if (akt == aktSong) {

				return i;
			}
			i++;
		}
		throw new SongNotFoundException(this);
	}

	public List<Song> getPlaylist() {
		return playlist;
	}

	public String getPlaylistName() {return playlistName;}
}
