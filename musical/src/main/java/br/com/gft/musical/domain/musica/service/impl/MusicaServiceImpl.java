package br.com.gft.musical.domain.musica.service.impl;

import br.com.gft.musical.api.musica.model.Musica;
import br.com.gft.musical.domain.musica.repositoty.MusicaRepository;
import br.com.gft.musical.domain.musica.service.MusicaService;
import br.com.gft.musical.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaServiceImpl implements MusicaService {
	@Autowired
	private MusicaRepository musicaRepository;


	@Override
	public List<Musica> findAll() {
		return musicaRepository.findAll();
	}

	@Override
	public Musica saveMusica(Musica musica) {
		return musicaRepository.save(musica);
	}

	@Override
	public Musica searchMusica(Integer id) {
		return musicaRepository.findById(id).orElseThrow(() -> new NotFoundException("Musica não encontrada"));
	}

	@Override
	public Musica updateMusica(Integer id, Musica musica) {
		Musica musicaOriginal = searchMusica(id);
		musica.setId(musicaOriginal.getId());
		return musicaRepository.save(musica);
	}

	@Override
	public void deleteMusica(Integer id) {
		musicaRepository.deleteById(id);
	}
}
