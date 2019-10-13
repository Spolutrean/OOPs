import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Artist {
    private final String name;
    private final Integer age;
    private final List<Album> albums = new ArrayList<>();

    public Artist(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void releaseAlbum(Album album) {
        albums.add(album);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }
}
