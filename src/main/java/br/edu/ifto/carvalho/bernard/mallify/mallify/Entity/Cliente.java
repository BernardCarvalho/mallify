package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_cliente")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;

    @NotBlank
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;
    
}