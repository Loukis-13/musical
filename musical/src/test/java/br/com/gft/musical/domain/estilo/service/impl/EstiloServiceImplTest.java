package br.com.gft.musical.domain.estilo.service.impl;

import br.com.gft.musical.api.estilo.model.Estilo;
import br.com.gft.musical.domain.estilo.repository.EstiloRepository;
import br.com.gft.musical.domain.estilo.service.EstiloService;
import br.com.gft.musical.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EstiloServiceImplTest {
    @Mock
    private EstiloRepository estiloRepository;
    @InjectMocks
    private EstiloService estiloService = new EstiloServiceImpl();

    Estilo estiloTeste;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        estiloTeste = new Estilo(1, "Metal");
    }

    @Test
    void pegarTodosEstilos() {
        when(estiloRepository.findAll()).thenReturn(List.of(estiloTeste));
        List<Estilo> estilos = estiloService.pegarTodosEstilos();

        assertNotNull(estilos);
        assertNotNull(estilos.get(0));
    }

    @Test
    void pegarEstilo() {
        when(estiloRepository.findById(1)).thenReturn(java.util.Optional.of(estiloTeste));
        Estilo estilo = estiloService.pegarEstilo(1);

        assertNotNull(estilo);
        assertEquals(estilo.getId(), 1);
        assertEquals(estilo.getDescricao(), "Metal");
    }

    @Test
    void salvarEstilo() {
        when(estiloRepository.save(estiloTeste)).thenReturn(estiloTeste);
        Estilo estilo = estiloService.salvarEstilo(estiloTeste);

        assertNotNull(estilo);
    }

    @Test
    void atualizarEstilo() {
        Estilo novoEstilo = new Estilo(null, "Pagode");
        Estilo estiloAtualizado = new Estilo(1, "Pagode");

        when(estiloRepository.findById(1)).thenReturn(java.util.Optional.of(estiloTeste));
        when(estiloRepository.save(novoEstilo)).thenReturn(estiloAtualizado);

        Estilo estilo = estiloService.atualizarEstilo(1, novoEstilo);

        assertNotNull(estilo);
        assertEquals(estilo.getId(), 1);
        assertEquals(estilo.getDescricao(), "Pagode");
    }

    @Test
    void excluirEstilo() {
        doNothing().when(estiloRepository).deleteById(1);
        estiloService.excluirEstilo(1);
        verify(estiloRepository, times(1)).deleteById(1);
    }

    @Test
    void estiloNaoExistente() {
        when(estiloRepository.findById(1)).thenThrow(new NotFoundException("Estilo não encontrado"));
        try {
            estiloService.pegarEstilo(1);
        } catch (NotFoundException ex) {
            assertEquals(ex.getMessage(), "Estilo não encontrado");
        }
        verify(estiloRepository, times(1)).findById(1);
    }
}