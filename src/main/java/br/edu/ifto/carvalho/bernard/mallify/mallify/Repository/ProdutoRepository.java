package br.edu.ifto.carvalho.bernard.mallify.mallify.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    public Optional<Produto> findByPrecoAndNome(double preco, String nome);
}
