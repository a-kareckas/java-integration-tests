package uk.co.zenitech.integration;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
public class GetAlbumEditionsExIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenArtistsPersisted_whenCallingNotExistingApi_thenReturnError() throws Exception {
        mockMvc.perform(get("/artists/1/album-editions-by-country")
            .queryParam("country", "LT")
            .contentType(APPLICATION_JSON))
            .andExpect(status().is5xxServerError());
    }
}
