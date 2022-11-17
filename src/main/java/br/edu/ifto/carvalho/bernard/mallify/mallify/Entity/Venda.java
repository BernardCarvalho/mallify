package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Validation;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Classes.EntityValidatorHelper;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;
import lombok.Data;

@Data// notação do lombok.Data que permite que não precisemos informar getters e setters
@Table(name = "tbl_venda")
@Scope(value=WebApplicationContext.SCOPE_SESSION)
@Component
@Entity
public class Venda implements Serializable, Validable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDate data = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)//notação que informa a multiplicidade do proximo atributo
    @JoinColumn(name="cod_venda") //nome da coluna
    private List<ItemVenda> itensVenda = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="cod_cliente")
    private Cliente cliente;

    public Double getValorTotal(){
        return 
        itensVenda
            .stream()
            .mapToDouble(item->
                item.getPreco())
            .sum();
    }

    @Override
    public Boolean isValid() {
        EntityValidatorHelper<Venda> entityValidatorHelper = new EntityValidatorHelper<>(this);
        Boolean valid = entityValidatorHelper.isValid();
        if(!valid)
            return Boolean.FALSE;
        
        if(itensVenda.size()<1)
            return Boolean.FALSE;

        return Boolean.TRUE;
    }

    @Override
    public Map<String, List<String>> getErros() {
        EntityValidatorHelper<Venda> entityValidatorHelper = new EntityValidatorHelper<>(this);
        Map<String, List<String>> erros = entityValidatorHelper.getErros();

        if(itensVenda.size()<1)
            erros.putIfAbsent("itensVenda", Arrays.asList("e necessario possuir ter ao menos um item") );
        
        return erros;
    }

   

    
}
