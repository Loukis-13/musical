package br.com.gft.musical.domain.estilo.repository;

import br.com.gft.musical.api.estilo.model.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Integer> {
}
