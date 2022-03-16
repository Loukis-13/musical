package br.com.gft.musical.api.artista;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.musical.api.artista.model.Artista;
import br.com.gft.musical.domain.artista.repository.ArtistaRepository;
import br.com.gft.musical.domain.artista.service.ArtistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("artista")
public class ArtistaController {
    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private ArtistaRepository artistaRepository;


    @GetMapping
    @Operation(
            tags = {"Artista"},
            responses = {@ApiResponse(responseCode = "200", description = "success")}
    )
    public ResponseEntity<List<Artista>> findAll() {
        List<Artista> artistas = artistaService.findAll();
        return ResponseEntity.ok().body(artistas);
    }

    @PostMapping
    @Operation(
            tags = {"Artista"},
            responses = {@ApiResponse(responseCode = "201", description = "Created success")}
    )
    public ResponseEntity<Artista> saveArtista(@RequestBody Artista artista) {
        artistaService.saveArtista(artista);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(artista.getId()).toUri();
        return ResponseEntity.created(uri).body(artista);
    }

    @GetMapping(value = "/{id}")
    @Operation(
            tags = {"Artista"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "success"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public ResponseEntity<Artista> findById(@PathVariable Integer id) {
        Artista artista = artistaService.searchArtista(id);
        return ResponseEntity.ok(artista);
    }

    @PutMapping(value = "/{id}")
    @Operation(
            tags = {"Artista"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update success"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public ResponseEntity<Artista> updateArtista(@PathVariable Integer id, @RequestBody Artista artista) {
        artista = artistaService.updateArtista(id, artista);
        return ResponseEntity.ok().body(artista);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            tags = {"Artista"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public ResponseEntity<Void> deleteArtista(@PathVariable Integer id) {
        artistaService.deleteArtista(id);
        return ResponseEntity.noContent().build();
    }
}
