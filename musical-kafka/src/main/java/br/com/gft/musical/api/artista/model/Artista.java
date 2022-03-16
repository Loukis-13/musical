package br.com.gft.musical.api.artista.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artista {
	private Integer id;

    private String nome;
	
//	@OneToMany
//	private List<Musica> musicas;
}
