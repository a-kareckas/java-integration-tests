package uk.co.zenitech.controller;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.domain.Country;
import uk.co.zenitech.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping()
    public List<Artist> getArtists(@RequestParam(required = false) String name) {
        if (StringUtils.isNotBlank(name)) {
            LOGGER.info("getting artist by name: {}", name);
            return List.of(artistService.findBy(name));
        } else {
            LOGGER.info("getting all artists");
            return artistService.findAll();
        }
    }

    @GetMapping("{id}/album-editions-by-country")
    public Map<String, Integer> getArtistAlbumEditionsByCountry(@PathVariable(name = "id") Long artistId,
                                                                @RequestParam Country country) {
        LOGGER.info("getting artist with id [{}] album editions for country [{}]", artistId, country);
        return artistService.getAlbumEditionsByCountry(artistId, country);
    }
}
