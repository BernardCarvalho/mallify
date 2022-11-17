package br.edu.ifto.carvalho.bernard.mallify.mallify.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Venda;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.VendaRepository;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    VendaRepository repository;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        try {
            //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
            Venda venda = repository.findById(id).get();
            if(venda==null)
                return new ResponseEntity<>(HttpStatus.GONE);
            return new ResponseEntity<>(venda, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Venda venda) {
        try {
            if(!venda.isValid())
                return new ResponseEntity<>(venda,HttpStatus.NOT_ACCEPTABLE);
            repository.save(venda);
            return new ResponseEntity<>(venda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(venda,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Venda venda) {
        try {
            if(!venda.isValid())
                return new ResponseEntity<>(venda,HttpStatus.NOT_ACCEPTABLE);
            repository.save(venda);
            return new ResponseEntity<>(venda, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Integer id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
