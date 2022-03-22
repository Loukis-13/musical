package br.com.gft.musical.api.estilo;

import br.com.gft.musical.api.estilo.model.Estilo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EstiloControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void getTodosEstilos() throws Exception {
        mockMvc.perform(get("/estilo"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descricao").value("Estilo 1"));
    }

    @Test
    void getEstilo() throws Exception {
        mockMvc.perform(get("/estilo/1"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Estilo 1"));
    }

    @Test
    void postEstilo() throws Exception {
        mockMvc.perform(post("/estilo")
                        .content(mapper.writeValueAsString(new Estilo(null, "Estilo 3")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.descricao").value("Estilo 3"));
    }

    @Test
    void putEstilo() throws Exception {
        mockMvc.perform(put("/estilo/2")
                        .content(mapper.writeValueAsString(new Estilo(null, "Estilo 3")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.descricao").value("Estilo 3"));
    }

//    @Test
    void deleteEstilo() throws Exception {
        mockMvc.perform(delete("/estilo/1"))
                .andDo(print())
                .andExpect(status().is(200));
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].descricao").value("Estilo 1"));
    }
}