import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Catalog {
    private static final List<Track> trackList = new ArrayList<>();
    private static final List<Album> albumsList = new ArrayList<>();
    private static final List<Compilation> compilationsList = new ArrayList<>();
    private static final List<Artist> artistsList = new ArrayList<>();

    public static void releaseAlbum(Album album) {
        album.getArtist().releaseAlbum(album);
        albumsList.add(album);
        trackList.addAll(album.getTracks());
    }

    public static void releaseCompilation(Compilation compilation) {
        compilationsList.add(compilation);
    }

    public static void addArtist(Artist artist) {
        artistsList.add(artist);
    }

    public static List<Track> findTracks(Predicate<Track> trackPredicate) {
        return trackList.stream().filter(trackPredicate).collect(Collectors.toList());
    }

    public static List<Album> findAlbums(Predicate<Album> albumPredicate) {
        return albumsList.stream().filter(albumPredicate).collect(Collectors.toList());
    }

    public static List<Compilation> findCompilations(Predicate<Compilation> compilationPredicate) {
        return compilationsList.stream().filter(compilationPredicate).collect(Collectors.toList());
    }

    public static List<Artist> findArtists(Predicate<Artist> artistPredicate) {
        return artistsList.stream().filter(artistPredicate).collect(Collectors.toList());
    }

    public static List<Compilation> getCompilationsList() {
        return Collections.unmodifiableList(compilationsList);
    }

    public static List<Track> getTrackList() {
        return Collections.unmodifiableList(trackList);
    }

    public static List<Album> getAlbumsList() {
        return Collections.unmodifiableList(albumsList);
    }

    public static List<Artist> getArtistsList() {
        return Collections.unmodifiableList(artistsList);
    }
}
