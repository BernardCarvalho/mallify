package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "itemvenda")
public class ItemVenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Min(1)
    private Double preço;

    @NotNull
    @Min(1)
    private Double quantidade;
    
}