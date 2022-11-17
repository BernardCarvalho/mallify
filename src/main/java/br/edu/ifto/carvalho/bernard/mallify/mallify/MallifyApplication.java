package br.edu.ifto.carvalho.bernard.mallify.mallify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Produto;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Venda;

@SpringBootApplication
public class MallifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallifyApplication.class, args);
		Produto produto = new Produto();
		
		produto.isValid();
		System.out.println("Produto é valido: "+produto.isValid());
		System.out.println(produto.getErros());

		Venda venda = new Venda();
		System.out.println("Venda é valida: "+venda.isValid());
		System.out.println(venda.getErros());
		
		

	}

}
