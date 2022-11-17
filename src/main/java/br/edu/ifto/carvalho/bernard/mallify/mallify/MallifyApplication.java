package br.edu.ifto.carvalho.bernard.mallify.mallify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Controller.ClienteController;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.ClientePessoaFisica;
//import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Produto;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Venda;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ClientePessoaFisicaRepository;

@SpringBootApplication
public class MallifyApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(MallifyApplication.class, args);		
		


		

	}


}
