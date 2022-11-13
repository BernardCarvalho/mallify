package br.edu.ifto.carvalho.bernard.mallify.mallify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Produto;

@SpringBootApplication
public class MallifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallifyApplication.class, args);
		Produto produto = new Produto();
		produto.isValid();
		System.out.println(produto.getErros());
		
		

	}

}
