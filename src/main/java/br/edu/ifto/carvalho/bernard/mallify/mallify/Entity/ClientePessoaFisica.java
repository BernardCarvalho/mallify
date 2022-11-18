package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Classes.EntityValidatorHelper;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.ExistenceInSelfRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ClientePessoaFisicaRepository;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "tbL_cliente_pf")
public class ClientePessoaFisica extends Cliente implements Serializable, Validable, ExistenceInSelfRepository{
    
    @NotNull
    @NotBlank
    private String cpf;

    @Override
    public Boolean isValid() {
        return this.getErros().isEmpty();
    }


    @Override
    public Map<String, List<String>> getErros() {
        EntityValidatorHelper<ClientePessoaFisica> entityValidatorHelper = new EntityValidatorHelper<>(this);
        return entityValidatorHelper.getErros(); 
    }

    


    
 
    


    
}