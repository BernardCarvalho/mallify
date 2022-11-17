package br.edu.ifto.carvalho.bernard.mallify.mallify.Controller;

import lombok.RequiredArgsConstructor;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Produto;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ProdutoRepository;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
            return new ResponseEntity<>(produtoRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        try {
            //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
            return new ResponseEntity<>(produtoRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Produto produto) {
        try {
            if(!produto.isValid())
                throw new Exception();
            produtoRepository.save(produto);
            return new ResponseEntity<>(produto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(produto,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Produto produto) {
        try {
            if(!produto.isValid())
                throw new Exception();
            produtoRepository.save(produto);
            return new ResponseEntity<>(produto, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(produto,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Integer id) {
        try {
            produtoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
