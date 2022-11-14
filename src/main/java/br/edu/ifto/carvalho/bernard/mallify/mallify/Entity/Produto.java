package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity; 

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.validation.Validation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data

@Table(name = "produto")
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @NotNull
    @Min(0)
    private Double preco;

    @NotBlank
    @NotNull
    private String nome;    

    @Transient
    private Map<String, List<String>> erros;

    

    public boolean isValid(){

        var constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(this);
        
        erros = new HashMap<String, List<String>>();
        
        constraintViolations
        .parallelStream()
        .forEach( constraint -> {
            String nomeAtributo = constraint.getPropertyPath().toString();                        
            erros.putIfAbsent(nomeAtributo, new ArrayList<String>());
            
            String mensagem = constraint.getMessage().toString();
            erros.get(nomeAtributo).add(mensagem);
        });

        if(erros.isEmpty())
            return true;

        return false;
    }
    
}