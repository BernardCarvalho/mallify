package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity; 

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "produto")
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Double preco;
    private String nome;
    
}