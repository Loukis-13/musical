package br.com.gft.musical.domain.estilo.service.impl;

import br.com.gft.musical.api.estilo.model.Estilo;
import br.com.gft.musical.domain.estilo.repository.EstiloRepository;
import br.com.gft.musical.domain.estilo.service.EstiloService;
import br.com.gft.musical.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstiloServiceImpl implements EstiloService {
    @Autowired
    private EstiloRepository estiloRepository;


    @Override
    public List<Estilo> pegarTodosEstilos() {
        return estiloRepository.findAll();
    }

    @Override
    public Estilo pegarEstilo(Integer id) {
        return estiloRepository.findById(id).orElseThrow(() -> new NotFoundException("Estilo não encontrado"));
    }

    @Override
    public Estilo salvarEstilo(Estilo estilo) {
        return estiloRepository.save(estilo);
    }

    @Override
    public Estilo atualizarEstilo(Integer id, Estilo estilo) {
        Estilo estiloOriginal = pegarEstilo(id);
        estilo.setId(estiloOriginal.getId());
        return estiloRepository.save(estilo);
    }

    @Override
    public void excluirEstilo(Integer id) {
        estiloRepository.deleteById(id);
    }
}
