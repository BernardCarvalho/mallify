package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Classes.EntityValidatorHelper;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.ExistencyCheckable;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ItemVendaRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ProdutoRepository;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_itemvenda")
public class ItemVenda implements Serializable , Validable, ExistencyCheckable<ItemVendaRepository>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    Produto produto;

    @Min(1)
    private double preco;

    @NotNull
    @Min(1)
    private Double quantidade;
    
    @Override
    public Boolean isValid() {
        return this.getErros().isEmpty();
    }

    //TODO
    @Override
    public Map<String, List<String>> getErros() {
        EntityValidatorHelper<ItemVenda> entityValidatorHelper = new EntityValidatorHelper<>(this);
        
        Map<String, List<String>> erros = entityValidatorHelper.getErros();        

        //TODO PRODUTO ERRORS

        if(!(produto==null))
        if(!produto.isValid()){
            Map<String, List<String>> errosProduto = produto.getErros();
            for (String fieldErrorName : errosProduto.keySet()) {
                for(String fieldErrorValue : errosProduto.get(fieldErrorName)){
                    erros.putIfAbsent("produto."+fieldErrorName, new ArrayList<String>());
                    erros.get("produto."+fieldErrorName).add(fieldErrorValue);
                }                
            }
        }

        return erros;
    }

    @Override
    public Boolean existsIn(ItemVendaRepository repository) {
        if(this.id==null)
            return false;
        return !repository.findById(id).isEmpty();
    }

}