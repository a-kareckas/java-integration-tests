package uk.co.zenitech.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.zenitech.domain.Country;

@Component
public class EditionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditionService.class);

    private final RestTemplate restTemplate;

    public EditionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Integer> getAlbumEditionsByCountry(String artistName, Country country) {
        var url = "http://non-existing-api:8080/album-editions-by-country";

        var headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        var builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("artist-name", artistName)
            .queryParam("country", country.name());

        var entity = new HttpEntity<>(headers);

        System.out.println("CALLING THIS ONE");
        System.out.println(builder.toUriString());

        var response = restTemplate.exchange(
            builder.toUriString(),
            HttpMethod.GET,
            entity,
            String.class);

        try {
            var mapper = new ObjectMapper();
            var typeRef = new TypeReference<HashMap<String, Integer>>() {};
            return mapper.readValue(response.getBody(), typeRef);

        } catch (Exception ex) {
            LOGGER.error("Could not extract data for artist album editions for artist: {}", artistName);
            throw new RuntimeException("Could not extract data for artist album editions", ex);
        }
    }
}
