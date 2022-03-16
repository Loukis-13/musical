package br.com.gft.musical.api.estilo;

import br.com.gft.musical.api.estilo.model.Estilo;
import br.com.gft.musical.domain.estilo.service.EstiloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estilo")
@Tag(name = "Estilo", description = "Endpoints para musicas")
public class EstiloController {
    @Autowired
    private EstiloService estiloService;


    @GetMapping()
    @Operation(
            responses = {@ApiResponse(responseCode = "200", description = "success")}
    )
    public ResponseEntity<List<Estilo>> getTodosEstilos() {
        return ResponseEntity.ok(estiloService.pegarTodosEstilos());
    }

    @GetMapping("{id}")
    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200", description = "success"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public ResponseEntity<Estilo> getEstilo(@PathVariable Integer id) {
        return ResponseEntity.ok(estiloService.pegarEstilo(id));
    }

    @PostMapping()
    @Operation(
            responses = {@ApiResponse(responseCode = "201", description = "Created success")}
    )
    public ResponseEntity<Estilo> PostEstilo(@RequestBody Estilo estilo) {
        return ResponseEntity.status(201).body(estiloService.salvarEstilo(estilo));
    }

    @PutMapping("{id}")
    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update success"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public ResponseEntity<Estilo> putEstilo(@PathVariable Integer id, @RequestBody Estilo estilo) {
        return ResponseEntity.ok(estiloService.atualizarEstilo(id, estilo));
    }

    @DeleteMapping("{id}")
    @Operation(
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public void deleteEstilo(@PathVariable Integer id) {
        estiloService.excluirEstilo(id);
    }
}
