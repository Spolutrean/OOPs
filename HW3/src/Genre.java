import java.util.*;

public class Genre {
    private static final List<Genre> genres = new ArrayList<>();
    public static final Genre basicGenre = new Genre(null, "BasicGenre");

    private final Genre parentGenre;
    private final String name;

    public Genre(Genre parentGenre, String name) {
        this.parentGenre = parentGenre;
        this.name = name;
        genres.add(this);
    }

    public static List<Genre> getGenres() {
        return Collections.unmodifiableList(genres);
    }

    public boolean isSubGenre(Genre genre) {
        if(this == genre) {
            return true;
        }
        Genre thisCopy = new Genre(parentGenre, name);
        while(thisCopy != basicGenre) {
            if(thisCopy == genre) {
                return true;
            }
            thisCopy = thisCopy.parentGenre;
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }
}
