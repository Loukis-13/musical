package br.com.gft.musical.domain.artista.service.impl;

import br.com.gft.musical.api.artista.model.Artista;
import br.com.gft.musical.api.musica.model.Musica;
import br.com.gft.musical.config.feignclients.MusicalFeignClient;
import br.com.gft.musical.domain.artista.repository.ArtistaRepository;
import br.com.gft.musical.domain.artista.service.ArtistaService;
import br.com.gft.musical.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ArtistaServiceImplTest {
    @Mock
    private ArtistaRepository artistaRepository;
    @Mock
    private MusicalFeignClient musicalFeignClient;
    @InjectMocks
    private ArtistaService artistaService = new ArtistaServiceImpl();

    Artista artistaTeste;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        artistaTeste = new Artista(1, "Raul", List.of(new Musica()));
        when(musicalFeignClient.returnArtista(artistaTeste)).thenReturn(ResponseEntity.of(Optional.of(artistaTeste)));
    }

    @Test
    void pegarTodosArtistas() {
        when(artistaRepository.findAll()).thenReturn(List.of(artistaTeste));
        List<Artista> artistas = artistaService.findAll();

        assertNotNull(artistas);
        assertNotNull(artistas.get(0));
    }

    @Test
    void pegarArtista() {
        when(artistaRepository.findById(1)).thenReturn(java.util.Optional.of(artistaTeste));
        Artista artista = artistaService.searchArtista(1);

        assertNotNull(artista);
        assertEquals(artista.getId(), 1);
        assertEquals(artista.getNome(), "Raul");
        assertEquals(artista.getMusicas().get(0).getClass(), Musica.class);
    }

    @Test
    void salvarArtista() {
        when(artistaRepository.save(artistaTeste)).thenReturn(artistaTeste);
        Artista artista = artistaService.saveArtista(artistaTeste);

        assertNotNull(artista);
    }

    @Test
    void atualizarArtista() {
        Artista novoArtista = new Artista(null, "Zeca pagodinho", null);
        Artista artistaAtualizado = new Artista(1, "Zeca pagodinho", null);

        when(artistaRepository.findById(1)).thenReturn(java.util.Optional.of(artistaTeste));
        when(artistaRepository.save(novoArtista)).thenReturn(artistaAtualizado);

        Artista artista = artistaService.updateArtista(1, novoArtista);

        assertNotNull(artista);
        assertEquals(artista.getId(), 1);
        assertEquals(artista.getNome(), "Zeca pagodinho");
    }

    @Test
    void excluirArtista() {
        doNothing().when(artistaRepository).deleteById(1);
        artistaService.deleteArtista(1);
        verify(artistaRepository, times(1)).deleteById(1);
    }

    @Test
    void artistaNaoExistente() {
        doThrow(new NotFoundException("Artista não encontrado")).when(artistaRepository).findById(1);
        try {
            artistaService.searchArtista(1);
        } catch (NotFoundException ex) {
            assertEquals(ex.getMessage(), "Artista não encontrado");
        }
        verify(artistaRepository, times(1)).findById(1);
    }
}