import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Genre classical = new Genre(Genre.basicGenre, "Classical");
        Genre nonClassical = new Genre(Genre.basicGenre, "NonClassical");
        Genre rock = new Genre(nonClassical, "Rock");
        Genre hardRock = new Genre(rock, "HardRock");
        Genre piano = new Genre(classical, "Piano");
        assert(Genre.getGenres().containsAll(new ArrayList<>(Arrays.asList(classical, nonClassical, rock, hardRock, piano))));

        Artist a1 = new Artist("Artist1", 26);
        Artist a2 = new Artist("Artist2", 14);
        Artist a3 = new Artist("Artist3", 146);
        Catalog.addArtist(a1);
        Catalog.addArtist(a2);
        Catalog.addArtist(a3);
        assert (Catalog.getArtistsList().containsAll(new ArrayList<>(Arrays.asList(a1, a2, a3))));

        Track classicalTrack1 = new Track(a1, "Classical", classical);
        Track classicalTrack2 = new Track(a1, "Piano", piano);
        Album classicalAlbum = null;
        try {
            classicalAlbum =  new Album("ClassicalAlbum", LocalDate.of(1999, 3, 12), Arrays.asList(classicalTrack1, classicalTrack2));
            Catalog.releaseAlbum(classicalAlbum);
        } catch (Exception e) {
            assert(false);
        }

        Track rockTrack1 = new Track(a2, "Rock", rock);
        Track rockTrack2 = new Track(a2, "HardRock", hardRock);
        Album rockAlbum = null;
        try {
            rockAlbum = new Album("RickAlbum", LocalDate.of(2019, 9, 25), Arrays.asList(rockTrack1, rockTrack2));
            Catalog.releaseAlbum(rockAlbum);
        } catch (Exception e) {
            assert(false);
        }

        try{
            Album errorAlbum = new Album("ErrorAlbum", LocalDate.of(1999, 1, 14), Arrays.asList(classicalTrack1, rockTrack1));
            assert(false);
        } catch (Exception e) {
            assert(true);
        }

        Compilation mixedCompilation = new Compilation(Arrays.asList(classicalTrack1, rockTrack1, rockTrack2));
        Catalog.releaseCompilation(mixedCompilation);


        assert (piano.isSubGenre(classical));
        assert (!piano.isSubGenre(rock));
        assert (piano.isSubGenre(piano));

        assert (Catalog.findAlbums(album -> album.getTracks().stream().anyMatch(track -> track.getGenre() == piano)).get(0) == classicalAlbum);
        assert (Catalog.findArtists(artist -> artist.getAge() > 100).get(0) == a3);
        assert (Catalog.findTracks(track -> track.getGenre().isSubGenre(classical)).size() == 2);
        assert (Catalog.findCompilations(compilation -> compilation.getTracks().size() > 2).get(0) == mixedCompilation);
    }
}
