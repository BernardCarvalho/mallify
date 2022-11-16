package br.edu.ifto.carvalho.bernard.mallify.mallify.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "tb_cliente_pf")
public class ClientePessoaFisica extends Cliente implements Serializable {
    
    @NotNull
    @NotBlank
    private String cpf;
    

    
}