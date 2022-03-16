package br.com.gft.musical.exceptions;

import br.com.gft.musical.api.estilo.EstiloController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ControllerExceptionHandlerTest {
    private MockMvc mockMvc;
    @Mock
    private EstiloController estiloController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(estiloController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }


    @Test
    public void handleNotFoundException() throws Exception {
        when(estiloController.getEstilo(1)).thenThrow(new NotFoundException("Estilo não encontrado"));

        mockMvc.perform(get("/estilo/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Entidade não encontrada"))
                .andExpect(jsonPath("$.errors").value("Estilo não encontrado"));
    }
}