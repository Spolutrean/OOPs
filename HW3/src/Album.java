import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Album {
    private final Artist artist;
    private final String name;
    private final LocalDate releaseDate;
    private final List<Track> tracks;

    public Album(String name, LocalDate releaseDate, List<Track> tracks) throws Exception {
        if(tracks.size() == 0) {
            throw new Exception("Album must contain at least one track");
        }
        if(tracks.stream().allMatch(track -> track.getArtistName() == tracks.get(0).getArtistName())) {
            artist = tracks.get(0).getArtistName();
        } else {
            throw new Exception("All tracks in album must be made by one artist. Otherwise use Compilation instead of Album");
        }
        this.name = name;
        this.releaseDate = releaseDate;
        this.tracks = tracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
