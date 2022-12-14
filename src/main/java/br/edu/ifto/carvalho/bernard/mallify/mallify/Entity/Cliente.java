package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Classes.EntityValidatorHelper;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.ExistenceInSelfRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ClienteRepository;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "tbl_cliente")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente implements Serializable , Validable, ExistenceInSelfRepository{

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;

    @NotBlank
    @Email
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;

    @Override
    public Boolean isValid() {
        return this.getErros().isEmpty();
    }


    @Override
    public Map<String, List<String>> getErros() {
        EntityValidatorHelper<Cliente> entityValidatorHelper = new EntityValidatorHelper<>(this);
        return entityValidatorHelper.getErros();
    }


    @Override
    public Boolean existsInRepository() {
        if(this instanceof ClientePessoaFisica){

        }
        return false;
    }


  
    
}