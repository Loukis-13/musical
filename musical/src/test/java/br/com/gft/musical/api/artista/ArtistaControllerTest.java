package br.com.gft.musical.api.artista;

import br.com.gft.musical.api.artista.model.Artista;
import br.com.gft.musical.api.musica.model.Musica;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/artista"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Artista 1"));
    }

    @Test
    void saveArtista() throws Exception {
        mockMvc.perform(post("/artista")
                        .content(mapper.writeValueAsString(new Artista(null, "Artista 3", List.of(new Musica()))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nome").value("Artista 3"));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/artista/1"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Artista 1"));
    }

    @Test
    void updateArtista() throws Exception {
        mockMvc.perform(put("/artista/2")
                        .content(mapper.writeValueAsString(new Artista(null, "Artista 3", null)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nome").value("Artista 3"));
    }

//    @Test
//    void deleteArtista() throws Exception {
//    }
}