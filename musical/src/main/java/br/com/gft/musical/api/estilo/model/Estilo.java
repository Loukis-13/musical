package br.com.gft.musical.api.estilo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "ESTILO")
@Getter
@Setter
public class Estilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Integer id;

    @Column(name = "DESCRICAO")
    String descricao;

    public Estilo() {
    }

    public Estilo(Integer id) {
        this.id = id;
    }

    public Estilo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
