import java.util.Date;

public class Track {
    private final Artist artistName;
    private final String name;
    private final Genre genre;

    public Track(Artist artistName, String name, Genre genre) {
        this.artistName = artistName;
        this.name = name;
        this.genre = genre;
    }

    public Artist getArtistName() {
        return artistName;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }
}
