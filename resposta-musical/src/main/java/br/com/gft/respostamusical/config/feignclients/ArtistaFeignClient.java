package br.com.gft.respostamusical.config.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.gft.respostamusical.api.artista.model.Artista;

@Component
@FeignClient(name = "resposta-musical", url = "localhost:8084/v1", path = "/artista")
public interface ArtistaFeignClient {
    @GetMapping(value = "/{id}")
    ResponseEntity<Artista> findById(@PathVariable Integer id);
}
