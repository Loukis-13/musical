package br.com.gft.respostamusical.api.artista;

import br.com.gft.respostamusical.api.artista.model.Artista;
import br.com.gft.respostamusical.domain.artista.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/artista")
public class ArtistaController {
    @Autowired
    private ArtistaService artistaService;


    @GetMapping(value = "/{artistaId}")
    public ResponseEntity<Artista> getArtista(@PathVariable Integer artistaId) {
        return ResponseEntity.ok(artistaService.getArtista(artistaId));
    }
}
