import java.util.Collections;
import java.util.List;

public class Compilation {
    private final List<Track> tracks;

    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    public Compilation(List<Track> tracks) {
        this.tracks = tracks;
    }
}
