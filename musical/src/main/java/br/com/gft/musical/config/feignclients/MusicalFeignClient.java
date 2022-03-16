package br.com.gft.musical.config.feignclients;

import br.com.gft.musical.api.artista.model.Artista;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "musicalKafkaProducer", url = "http://localhost:8082/v1")
public interface MusicalFeignClient {
    @PostMapping(value = "/artista", consumes = "application/json")
    ResponseEntity<Artista> returnArtista(@RequestBody Artista artista);
}
