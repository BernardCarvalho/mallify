package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity; 

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Classes.EntityValidatorHelper;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.ExistenceInSelfRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ProdutoRepository;

@Entity
@Data

@Table(name = "tbl_produto")
public class Produto implements Serializable, Validable, ExistenceInSelfRepository{
    
    
    @JsonIgnore
    @Transient
    private ProdutoRepository produtoRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Min(0)
    private double preco;

    @NotBlank
    @NotNull
    private String nome;    
    

    @Override
    public Boolean isValid() {
        return this.getErros().isEmpty();
    }


    @Override
    public Map<String, List<String>> getErros() {
        EntityValidatorHelper<Produto> entityValidatorHelper = new EntityValidatorHelper<>(this);
        return entityValidatorHelper.getErros();        
    }


    @Override
    public Boolean existsInRepository() {
        // TODO Auto-generated method stub
        return null;
    }


 
 
}