package br.edu.ifto.carvalho.bernard.mallify.mallify.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Validation;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces.Validable;

public class EntityValidatorHelper<EntityType> implements Validable{

    private EntityType entityObj;
    
    public EntityValidatorHelper(EntityType entityObj){
        this.entityObj=entityObj;
    }

    @Override
    public Boolean isValid() {
        var constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(this.entityObj);
        if(constraintViolations.isEmpty())
            return true;
        return false;
    }

    @Override
    public Map<String, List<String>> getErros() {
        var constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(this.entityObj);
        var erros = new HashMap<String, List<String>>();
        constraintViolations
        .parallelStream()
        .forEach( constraint -> {
            String nomeAtributo = constraint.getPropertyPath().toString();                        
            erros.putIfAbsent(nomeAtributo, new ArrayList<String>());
            
            String mensagem = constraint.getMessage().toString();
            erros.get(nomeAtributo).add(mensagem);
        });
        return erros;
    }
    
}
