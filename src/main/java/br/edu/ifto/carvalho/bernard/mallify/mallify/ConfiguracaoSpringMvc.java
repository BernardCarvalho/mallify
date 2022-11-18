package br.edu.ifto.carvalho.bernard.mallify.mallify;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.ClientePessoaFisica;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ClientePessoaFisicaRepository;

/**
 * @Configuration para indicar ao Spring que essa é uma classe de configuração.
 * Em seguida, é preciso implementar a interface WebMvcConfigurer.
 * @author fagno
 */

@Configuration
public class ConfiguracaoSpringMvc implements WebMvcConfigurer{
    
    

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/home");
    }
}