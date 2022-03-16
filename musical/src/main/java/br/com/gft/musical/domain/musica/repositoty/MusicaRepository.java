package br.com.gft.musical.domain.musica.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.musical.api.musica.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer>{

}
