package br.com.gft.musical.domain.artista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.musical.api.artista.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer>{

}
