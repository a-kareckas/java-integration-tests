package uk.co.zenitech.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import uk.co.zenitech.domain.Artist;
import uk.co.zenitech.domain.Genre;

@IntegrationTest
public class GetArtistIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void givenArtistsPersisted_whenGettingAll_thenReturnAll() {
        ResponseEntity<List<Artist>> response =
            testRestTemplate.exchange("/artists", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        var artists = response.getBody();
        assertThat(artists, IsCollectionWithSize.hasSize(5));

        System.out.println("--- TEST RESULT ---");
        System.out.println(artists);
    }

    @Test
    @Sql(scripts = "/additional_data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenArtistsPersistedWithAdditionalData_whenGettingAll_thenReturnAll() {
        ResponseEntity<List<Artist>> response =
            testRestTemplate.exchange("/artists", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        var artists = response.getBody();
        assertThat(artists, IsCollectionWithSize.hasSize(6));

        System.out.println("--- TEST RESULT ---");
        System.out.println(artists);
    }

    @Test
    public void givenArtistsPersisted_whenGettingByName_thenReturnOnlySpecific() {
        ResponseEntity<List<Artist>> response =
            testRestTemplate.exchange("/artists?name=artist_1", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        var artists = response.getBody();
        assertThat(artists, IsCollectionWithSize.hasSize(1));

        var artist = artists.get(0);
        assertThat(artist.getName(), is("artist_1"));
        assertThat(artist.getGenre(), is(Genre.rock));

        System.out.println("--- TEST RESULT ---");
        System.out.println(artists);
    }
}
