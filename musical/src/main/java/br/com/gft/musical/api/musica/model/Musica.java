package br.com.gft.musical.api.musica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.gft.musical.api.artista.model.Artista;
import br.com.gft.musical.api.estilo.model.Estilo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table(name = "MUSICA")
@Entity
@Getter
@Setter
public class Musica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOME")
	private String nome;

	@ManyToOne
	@JsonIgnoreProperties("musicas")
	private Artista artista;

	@ManyToOne
	private Estilo estilo;


	public Musica() {
	}

	public Musica(Integer id, String nome, Artista artista, Estilo estilo) {
		this.id = id;
		this.nome = nome;
		this.artista = artista;
		this.estilo = estilo;
	}
}
