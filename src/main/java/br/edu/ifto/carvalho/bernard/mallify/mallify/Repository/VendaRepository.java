package br.edu.ifto.carvalho.bernard.mallify.mallify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

}
