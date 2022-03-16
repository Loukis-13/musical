package br.com.gft.musical.api.artista;

import br.com.gft.musical.api.artista.model.Artista;
import br.com.gft.musical.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("artista")
public class ArtistaController {
	@Autowired
	private ArtistaService artistaService;


	@PostMapping
	public ResponseEntity<Artista> returnArtista(@RequestBody Artista artista) {
		artistaService.sendArtistaToKafka(artista);
		return ResponseEntity.ok().body(artista);
	}
}
