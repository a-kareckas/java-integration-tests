package uk.co.zenitech.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

@IntegrationTest
public class GetArtistIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenArtistsPersisted_whenGettingAll_thenReturnAll() throws Exception {
        var result = mockMvc.perform(get("/artists").contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(5)))
            .andReturn().getResponse().getContentAsString();

        System.out.println("--- TEST RESULT ---");
        System.out.println(result);
    }

    // TODO: clean data before each test
    @Test
    @Sql(scripts = "/additional_data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenArtistsPersistedWithAdditionalData_whenGettingAll_thenReturnAll() throws Exception {
        var result = mockMvc.perform(get("/artists").contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(6)))
            .andReturn().getResponse().getContentAsString();

        System.out.println("--- TEST RESULT ---");
        System.out.println(result);
    }

    @Test
    public void givenArtistsPersisted_whenGettingByName_thenReturnOnlySpecific() throws Exception {
        var result = mockMvc.perform(get("/artists").queryParam("name", "artist_1").contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", equalTo("artist_1")))
            .andExpect(jsonPath("$[0].genre", equalTo("rock")))
            .andReturn().getResponse().getContentAsString();

        System.out.println("--- TEST RESULT ---");
        System.out.println(result);
    }
}
