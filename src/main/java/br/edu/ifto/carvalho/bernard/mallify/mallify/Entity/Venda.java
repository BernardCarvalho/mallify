package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
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

import lombok.Data;

@Data// notação do lombok.Data que permite que não precisemos informar getters e setters
@Table(name = "tb_venda")
@Scope(value=WebApplicationContext.SCOPE_SESSION)
@Component
@Entity
public class Venda {

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
