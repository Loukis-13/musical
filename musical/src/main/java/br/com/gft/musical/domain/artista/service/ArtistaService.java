package br.com.gft.musical.domain.artista.service;

import br.com.gft.musical.api.artista.model.Artista;

import java.util.List;

public interface ArtistaService {
	
	List<Artista> findAll();
	
	Artista saveArtista(Artista artista);
	
	Artista searchArtista(Integer id);
	
	Artista updateArtista(Integer id, Artista artista);
	
	void deleteArtista(Integer id);

}
