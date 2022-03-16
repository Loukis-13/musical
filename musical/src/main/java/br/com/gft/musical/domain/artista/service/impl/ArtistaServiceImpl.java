package br.com.gft.musical.domain.artista.service.impl;

import br.com.gft.musical.api.artista.model.Artista;
import br.com.gft.musical.config.feignclients.MusicalFeignClient;
import br.com.gft.musical.domain.artista.repository.ArtistaRepository;
import br.com.gft.musical.domain.artista.service.ArtistaService;
import br.com.gft.musical.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaServiceImpl implements ArtistaService{
	@Autowired
	private ArtistaRepository artistaRepository;
	@Autowired
	private MusicalFeignClient musicalFeignClient;


	@Override
	public List<Artista> findAll() {
		return artistaRepository.findAll();
	}

	@Override
	public Artista saveArtista(Artista artista) {
		return artistaRepository.save(artista);
	}

	@Override
	public Artista searchArtista(Integer id) {
		Artista artista = artistaRepository.findById(id).orElseThrow(() -> new NotFoundException("Artista não encontrado"));
		artista = musicalFeignClient.returnArtista(artista).getBody();
		return artista;
	}

	@Override
	public Artista updateArtista(Integer id, Artista artista) {
		Artista artistaOriginal = searchArtista(id);
		artista.setId(artistaOriginal.getId());
		return artistaRepository.save(artista);
	}

	@Override
	public void deleteArtista(Integer id) {
		artistaRepository.deleteById(id);
	}
}
