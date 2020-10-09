package uk.co.zenitech.domain;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "ARTIST")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_sq_generator")
    @SequenceGenerator(name = "artist_sq_generator", sequenceName = "ARTIST_SEQ", allocationSize = 0)
    @Column(name = "AR_ID")
    private Long id;

    @Column(name = "AR_NAME", nullable = false)
    private String name;

    @Column(name = "AR_GENRE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private Set<Album> albums;

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Artist{" + "name='" + name + '\'' + ", genre=" + genre + ", albums=" + albums + '}';
    }
}
