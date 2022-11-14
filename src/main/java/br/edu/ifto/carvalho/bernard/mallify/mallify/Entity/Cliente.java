package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    
}