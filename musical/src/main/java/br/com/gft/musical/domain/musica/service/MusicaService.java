package br.com.gft.musical.domain.musica.service;

import java.util.List;
import java.util.Optional;

import br.com.gft.musical.api.musica.model.Musica;

public interface MusicaService {
	List<Musica> findAll();
	
	Musica saveMusica(Musica musica);
	
	Musica searchMusica(Integer id);
	
	Musica updateMusica(Integer id, Musica musica);
	
	void deleteMusica(Integer id);
}
