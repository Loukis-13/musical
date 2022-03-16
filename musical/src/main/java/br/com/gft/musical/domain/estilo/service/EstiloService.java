package br.com.gft.musical.domain.estilo.service;

import br.com.gft.musical.api.estilo.model.Estilo;

import java.util.List;

public interface EstiloService {
    List<Estilo> pegarTodosEstilos();
    Estilo pegarEstilo(Integer id);
    Estilo salvarEstilo(Estilo estilo);
    Estilo atualizarEstilo(Integer id, Estilo estilo);
    void excluirEstilo(Integer id);
}
