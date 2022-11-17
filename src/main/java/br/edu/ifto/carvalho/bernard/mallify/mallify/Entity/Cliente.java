package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Classes.EntityValidatorHelper;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "tbl_cliente")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente implements Serializable , Validable{

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;

    @NotBlank
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;

    @Override
    public Boolean isValid() {
        EntityValidatorHelper<Cliente> entityValidatorHelper = new EntityValidatorHelper<>(this);
        return entityValidatorHelper.isValid();
    }


    @Override
    public Map<String, List<String>> getErros() {
        EntityValidatorHelper<Cliente> entityValidatorHelper = new EntityValidatorHelper<>(this);
        return entityValidatorHelper.getErros();        
    }
    
}