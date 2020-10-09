package uk.co.zenitech.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.zenitech.domain.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @EntityGraph(attributePaths = {"albums"})
    @Query("from Artist a where a.name = :name")
    Artist findBy(@Param("name") String name);
}