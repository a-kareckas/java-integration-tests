package uk.co.zenitech.integration;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.zenitech.service.EditionService;

@IntegrationTest
public class GetAlbumEditionMockedIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EditionService editionService;

    @Test
    public void givenArtistsPersisted_whenCallingMockedNotExistingApiService_thenReturnOk() throws Exception {
        Mockito.when(editionService.getAlbumEditionsByCountry(Mockito.anyString(), Mockito.any()))
            .thenReturn(Map.of(
                "artist_1_album_1", 5200,
                "artist_1_album_2", 1400,
                "artist_1_album_3", 148521));

        var response = mockMvc.perform(get("/artists/1/album-editions-by-country")
            .queryParam("country", "LT")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        System.out.println("--- TEST RESULT ---");
        System.out.println(response);
    }
}
