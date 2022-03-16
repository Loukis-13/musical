package br.com.gft.musical.api.musica;

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

import br.com.gft.musical.api.musica.model.Musica;
import br.com.gft.musical.domain.musica.service.MusicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("musica")
public class MusicaController {

		@Autowired
		private MusicaService musicaService;

		@GetMapping
		@Operation(
				tags = {"Musica"},
				responses = {@ApiResponse(responseCode = "200", description = "success")}
			)

		public ResponseEntity<List<Musica>> findAll() {
			List<Musica> musicas = musicaService.findAll();
			return ResponseEntity.ok().body(musicas);
		}


		@PostMapping
		@Operation(
				tags = {"Musica"},
				responses = {@ApiResponse(responseCode = "201", description = "Created success"),
						@ApiResponse(responseCode = "404", description = "Not found")}
			)
		public ResponseEntity<Musica> saveMusica(@RequestBody Musica musica) {

			return ResponseEntity.ok().body(musicaService.saveMusica(musica));
		}
		
		@GetMapping(value = "/{id}")
		@Operation(
				tags = {"Musica"},
				responses = {@ApiResponse(responseCode = "200", description = "success")}
			)
		public ResponseEntity<Musica> findById(@PathVariable Integer id) {
			Musica musica = musicaService.searchMusica(id);
			return ResponseEntity.ok(musica);
		}
		
		@PutMapping(value = "/{id}")
		@Operation(
				tags = {"Musica"},
				responses = {@ApiResponse(responseCode = "200", description = "Update success"),
						@ApiResponse(responseCode = "404", description = "Not found")}
			)
		public ResponseEntity<Musica> updateMusica(@PathVariable Integer id, @RequestBody Musica musica) {
			musica = musicaService.updateMusica(id, musica);
			return ResponseEntity.ok().body(musica);
		}
		
		@DeleteMapping(value = "/{id}")
		@Operation(
				tags = {"Musica"},
				responses = {@ApiResponse(responseCode = "204", description = "Success"),
						@ApiResponse(responseCode = "404", description = "Not found")}
			)
		public ResponseEntity<Void> deleteMusica(@PathVariable Integer id) {
			musicaService.deleteMusica(id);
			return ResponseEntity.noContent().build();
		}
		
	}

