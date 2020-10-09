package uk.co.zenitech.service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.domain.Country;
import uk.co.zenitech.repository.ArtistRepository;

@Component
public class ArtistService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository artistRepository;
    private final EditionService editionService;

    public ArtistService(ArtistRepository artistRepository, EditionService editionService) {
        this.artistRepository = artistRepository;
        this.editionService = editionService;
    }

    public void save(Artist artist) {
        LOGGER.info("saving artist in service {}", artist);
        this.artistRepository.save(artist);
    }

    public List<Artist> findAll() {
        LOGGER.info("Find all artists");
        return this.artistRepository.findAll();
    }

    public Artist findBy(String name) {
        LOGGER.info("Find artist in service by name: {}", name);
        return this.artistRepository.findBy(name);
    }

    public Map<String, Integer> getAlbumEditionsByCountry(Long artistId, Country country) {
        LOGGER.info("Get artist with id [{}] album edition for country [{}]", artistId, country);
        return artistRepository.findById(artistId)
            .map(artist -> {
                var artistName = artist.getName();
                LOGGER.info("Calling editor service with artist name [{}] for album edition for country [{}]", artistName, country);
                return this.editionService.getAlbumEditionsByCountry(artistName, country);
            })
            .orElseThrow(() -> new RuntimeException(String.format("Could not find artist with id: %d", artistId)));
    }
}
