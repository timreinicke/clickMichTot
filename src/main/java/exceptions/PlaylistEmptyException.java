package exceptions;

import business.Playlist;
import business.PlaylistManager;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

/*
 *  Exception falls Playlist leer ist
 */

public class PlaylistEmptyException extends Exception {
    PlaylistManager manager;

    public PlaylistEmptyException (PlaylistManager manager, Playlist playlist) throws IOException, PlaylistNotFoundException, InvalidDataException, UnsupportedTagException {
        super("Playlist: " + manager.getAktPlaylist().getPlaylistName() + "ist leer.");
        this.manager = manager;

    }
}
