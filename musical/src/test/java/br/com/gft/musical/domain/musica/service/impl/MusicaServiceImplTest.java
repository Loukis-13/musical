package br.com.gft.musical.domain.musica.service.impl;

import br.com.gft.musical.api.artista.model.Artista;
import br.com.gft.musical.api.estilo.model.Estilo;
import br.com.gft.musical.api.musica.model.Musica;
import br.com.gft.musical.domain.musica.repositoty.MusicaRepository;
import br.com.gft.musical.domain.musica.service.MusicaService;
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

class MusicaServiceImplTest {
    @Mock
    private MusicaRepository musicaRepository;
    @InjectMocks
    private MusicaService musicaService = new MusicaServiceImpl();

    Musica musicaTeste;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        musicaTeste = new Musica(1, "Debil metal", new Artista(), new Estilo());
    }

    @Test
    void pegarTodosMusicas() {
        when(musicaRepository.findAll()).thenReturn(List.of(musicaTeste));
        List<Musica> musicas = musicaService.findAll();

        assertNotNull(musicas);
        assertNotNull(musicas.get(0));
    }

    @Test
    void pegarMusica() {
        when(musicaRepository.findById(1)).thenReturn(java.util.Optional.of(musicaTeste));
        Musica musica = musicaService.searchMusica(1);

        assertNotNull(musica);
        assertEquals(musica.getId(), 1);
        assertEquals(musica.getNome(), "Debil metal");
        assertEquals(musica.getArtista().getClass(), Artista.class);
        assertEquals(musica.getEstilo().getClass(), Estilo.class);
    }

    @Test
    void salvarMusica() {
        when(musicaRepository.save(musicaTeste)).thenReturn(musicaTeste);
        Musica musica = musicaService.saveMusica(musicaTeste);

        assertNotNull(musica);
    }

    @Test
    void atualizarMusica() {
        Musica novoMusica = new Musica(null, "1406", null, null);
        Musica musicaAtualizado = new Musica(1, "1406", null, null);

        when(musicaRepository.findById(1)).thenReturn(java.util.Optional.of(musicaTeste));
        when(musicaRepository.save(novoMusica)).thenReturn(musicaAtualizado);

        Musica musica = musicaService.updateMusica(1, novoMusica);

        assertNotNull(musica);
        assertEquals(musica.getId(), 1);
        assertEquals(musica.getNome(), "1406");
    }

    @Test
    void excluirMusica() {
        doNothing().when(musicaRepository).deleteById(1);
        musicaService.deleteMusica(1);
        verify(musicaRepository, times(1)).deleteById(1);
    }

    @Test
    void musicaNaoExistente() {
        doThrow(new NotFoundException("Musica não encontrado")).when(musicaRepository).findById(1);
        try {
            musicaService.searchMusica(1);
        } catch (NotFoundException ex) {
            assertEquals(ex.getMessage(), "Musica não encontrado");
        }
        verify(musicaRepository, times(1)).findById(1);
    }
}