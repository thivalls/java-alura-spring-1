package br.com.alura.forum.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveriaRetornar400ComCredenciaisIncorretas() throws Exception {
        URI uri = new URI("/auth");
        String json = "{ \"email\" : \"invalido@email.com\", \"password\" : \"wrongpassword\" }";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void deveriaRetornar200ComCredenciaisCorretas() throws Exception {
        URI uri = new URI("/auth");
        String json = "{ \"email\" : \"aluno@email.com\", \"password\" : \"123456\" }";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }
}