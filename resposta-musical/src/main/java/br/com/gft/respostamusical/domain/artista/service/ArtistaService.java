package br.com.gft.respostamusical.domain.artista.service;

import br.com.gft.respostamusical.api.artista.model.Artista;
import br.com.gft.respostamusical.config.feignclients.ArtistaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaFeignClient artistaFeignClient;


    public Artista getArtista(Integer artistaId) {
        return artistaFeignClient.findById(artistaId).getBody();
    }
}
