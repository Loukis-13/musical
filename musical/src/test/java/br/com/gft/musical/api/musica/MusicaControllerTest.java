package br.com.gft.musical.api.musica;

import br.com.gft.musical.api.artista.model.Artista;
import br.com.gft.musical.api.estilo.model.Estilo;
import br.com.gft.musical.api.musica.model.Musica;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MusicaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/musica"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Musica 1"));
    }

    @Test
    void saveMusica() throws Exception {
        mockMvc.perform(post("/musica")
                        .content(mapper.writeValueAsString(new Musica(null, "Musica 3", new Artista(1), new Estilo(1))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nome").value("Musica 3"));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/musica/1"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Musica 1"));
    }

    @Test
    void updateMusica() throws Exception {
        mockMvc.perform(put("/musica/2")
                        .content(mapper.writeValueAsString(new Musica(null, "Musica 3", new Artista(2), new Estilo(2))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nome").value("Musica 3"));
    }

//    @Test
//    void deleteMusica() {
//    }
}