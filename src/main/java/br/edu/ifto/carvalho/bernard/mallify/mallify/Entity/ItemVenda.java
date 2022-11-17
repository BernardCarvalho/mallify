package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Classes.EntityValidatorHelper;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_itemvenda")
public class ItemVenda implements Serializable , Validable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    Produto produto;

    @NotNull
    @Min(1)
    private Double preco;

    @NotNull
    @Min(1)
    private Double quantidade;
    
    @Override
    public Boolean isValid() {
        EntityValidatorHelper<ItemVenda> entityValidatorHelper = new EntityValidatorHelper<>(this);
        return entityValidatorHelper.isValid();
    }


    @Override
    public Map<String, List<String>> getErros() {
        EntityValidatorHelper<ItemVenda> entityValidatorHelper = new EntityValidatorHelper<>(this);
        return entityValidatorHelper.getErros();        
    }

}