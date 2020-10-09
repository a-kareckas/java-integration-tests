package uk.co.zenitech.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "ALBUM")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_sq_generator")
    @SequenceGenerator(name = "album_sq_generator", sequenceName = "ALBUM_SEQ", allocationSize = 0)
    @Column(name = "AL_ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AL_AR_ID", referencedColumnName = "AR_ID", nullable = false)
    private Artist artist;

    @Column(name = "AL_NAME", nullable = false)
    private String name;

    @Column(name = "AL_SONGS", nullable = false)
    private String songs;

    public Artist getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public String getSongs() {
        return songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Album album = (Album) o;
        return Objects.equals(id, album.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Album{" + "artist=" + artist + ", name='" + name + '\'' + ", songs='" + songs + '\''
            + '}';
    }
}