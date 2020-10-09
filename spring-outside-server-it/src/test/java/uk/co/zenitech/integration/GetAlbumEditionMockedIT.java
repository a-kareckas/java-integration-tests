package uk.co.zenitech.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.co.zenitech.domain.Artist;

@IntegrationTest
public class GetAlbumEditionMockedIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void givenArtistsPersisted_whenCallingMockedNotExistingApiService_thenReturnOk() {
        var wireMockServer = new WireMockServer();
        wireMockServer.start();

        // in order to work add to [/etc/hosts] this line or use [localhost] as external service url:
        // 127.0.0.1       non-existing-api
        configureFor("non-existing-api", 8080);
        stubFor(
            get(urlEqualTo("/album-editions-by-country?artist-name=artist_1&country=LT"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(
                       "{\"artist_1_album_1\" : 5200,\n"
                    + " \"artist_1_album_2\" : 1400,\n"
                    + " \"artist_1_album_3\" : 148521}")));

        // TODO: pasiaiskinti del sortinimo

        ResponseEntity<Map<String, Integer>> response =
            testRestTemplate.exchange("/artists/1/album-editions-by-country?country=LT", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        wireMockServer.stop();

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        
        System.out.println("--- TEST RESULT ---");
        System.out.println(response.getBody());
    }
}
