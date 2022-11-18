package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Classes.EntityValidatorHelper;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.ExistenceInSelfRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.VendaRepository;
import lombok.Data;

@Data// notação do lombok.Data que permite que não precisemos informar getters e setters
@Table(name = "tbl_venda")
@Scope(value=WebApplicationContext.SCOPE_SESSION)
@Component
@Entity
public class Venda implements Serializable, Validable, ExistenceInSelfRepository{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Transient
    @JsonIgnore
    private VendaRepository repository;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDate data = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)//notação que informa a multiplicidade do proximo atributo
    @JoinColumn(name="cod_venda") //nome da coluna
    @NotNull
    private List<ItemVenda> itensVenda = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @NotNull
    @JoinColumn(name="cod_cliente")
    private Cliente cliente;

    public Double getValorTotal(){
        
        Double valor = Double.valueOf("0");
        for(int i=0; i<itensVenda.size();i++){
            if(itensVenda.get(i)!=null)
            {
                valor+=itensVenda.get(i).getPreco();
            }
        }
        return valor;
    }

    @Override
    public Boolean isValid() {
        return this.getErros().isEmpty();
    }

    @Override
    public Map<String, List<String>> getErros() {
        EntityValidatorHelper<Venda> entityValidatorHelper = new EntityValidatorHelper<>(this);
        Map<String, List<String>> erros = entityValidatorHelper.getErros();

        if(itensVenda.size()<1)
            erros.putIfAbsent("itensVenda", Arrays.asList("e necessario possuir ter ao menos um item") );
        
        //ITENSVENDA ERRORS
        if(!(itensVenda==null))
        for(int i=0; i<itensVenda.size(); i++){
            if(!itensVenda.get(i).isValid()){
                Map<String, List<String>> errosItemVenda = itensVenda.get(i).getErros();
                for (String fieldErrorName : errosItemVenda.keySet()) {
                    erros.putIfAbsent("itensVenda_"+i+"."+fieldErrorName, new ArrayList<>());
                    for (String erroString : errosItemVenda.get(fieldErrorName)) {
                        erros.get("itensVenda_"+i+"."+fieldErrorName).add(erroString);
                    }
                }
            }
        }

        //TODO CLIENTE ERRORS
        if(!(cliente==null))
        if(!cliente.isValid()){
            Map<String, List<String>> errosCliente = cliente.getErros();
            errosCliente.keySet().stream().parallel().forEach(chave->{
                erros.putIfAbsent("cliente."+chave, new ArrayList<>());
                errosCliente.get(chave).stream().parallel().forEach(valor->{
                    erros.get("cliente."+chave).add(valor);
                });
            });
        }



        return erros;
    }

    @Override
    public Boolean existsInRepository() {
        // TODO Auto-generated method stub
        return null;
    }

   
    

   

    
}
