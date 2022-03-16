package br.com.gft.musical.api.artista.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import br.com.gft.musical.api.musica.model.Musica;
import com.fasterxml.jackson.annotation.JsonIgnore;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ARTISTA")
@Getter
@Setter
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOME")
    private String nome;

	@OneToMany(mappedBy = "artista")
	@JsonIgnoreProperties("artista")
	private List<Musica> musicas;


	public Artista() {
	}

	public Artista(Integer id) {
		this.id = id;
	}

	public Artista(Integer id, String nome, List<Musica> musicas) {
		this.id = id;
		this.nome = nome;
		this.musicas = musicas;
	}
}
