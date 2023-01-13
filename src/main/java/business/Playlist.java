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

/*
 * erstellt Playlisten, die dann in PlaylistManager verwaltet werden.
 * Implementiert Cloneable um eine ShufflePlaylist erstellen zu können
 */

public class Playlist implements Cloneable{

	List<Song> playlist;
	int next;
	File f;

	public Playlist(File f) throws IOException, InvalidDataException, UnsupportedTagException {
		playlist = new ArrayList<>();
		this.f = f;
		loadPlaylist();
	}

	private void addTrack(String fileName) throws IOException {
		try{
		Song track = new Song(fileName);
		playlist.add(track);
		}catch (IOException | UnsupportedTagException | InvalidDataException ex){
			System.out.println("Error reading Song");
		}
	}

	/*
	 * Laed die Playlist indem ein BufferedReader ueber ein File iteriert und dann die Methode addTrack aufruft
	 */

	public void loadPlaylist() throws IOException, InvalidDataException, UnsupportedTagException {
		try (BufferedReader br = new BufferedReader(new FileReader(f))){
			String line;
			while((line = br.readLine()) != null){
				addTrack(line);
			}
		}catch (IOException ex){
			throw new IOException("Error reading Playlistfile");
		}
		next =-1;
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
		}throw new SongNotFoundException(this);
	}

	public Song getNextSong(Song aktSong) throws SongNotFoundException, IOException, InvalidDataException, UnsupportedTagException {
		if (searchSong(aktSong) == playlist.size() - 1){
		    return playlist.get(0);
		}
		return playlist.get(searchSong(aktSong) + 1);
	}

	public Song getPrevSong(Song aktSong) throws SongNotFoundException, IOException, InvalidDataException, UnsupportedTagException {
		if (searchSong(aktSong) == 0){
		    return playlist.get(playlist.size() - 1);
		}
		return playlist.get(searchSong(aktSong) - 1);
	}

	public Song getAktSong(Song aktSong) throws SongNotFoundException, IOException, InvalidDataException, UnsupportedTagException {
		return playlist.get(searchSong(aktSong));
	}

	public List<Song> getPlaylist() {
		return playlist;
	}

	/*
	 * implementiert clone aus dem Interface und erschafft ein identisches Array, welches spaeter deep cloned wird
	 */

	@Override
	public Playlist clone() {
		try {
			Playlist clone = (Playlist) super.clone();
			clone.playlist = new ArrayList<>(playlist);
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
