package br.edu.ifto.carvalho.bernard.mallify.mallify.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Cliente;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.ClientePessoaFisica;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ClientePessoaFisicaRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ClienteRepository;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClientePessoaFisicaRepository clientePessoaFisicaRepository;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
            return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("pessoa-fisica")
    public ResponseEntity<?> findAllPessoaFisica() {
        try {
            //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
            return new ResponseEntity<>(clientePessoaFisicaRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        try {
            //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
            Cliente cliente = clienteRepository.findById(id).get();
            if(cliente==null)
                return new ResponseEntity<>(HttpStatus.GONE);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("pessoa-fisica/{id}")
    public ResponseEntity<?> findPessoaFisica(@PathVariable Integer id) {
        try {
            //TODO Implement Your Logic To Get Data From Service Layer Or Directly From Repository Layer
            ClientePessoaFisica clientePessoaFisica = clientePessoaFisicaRepository.findById(id).get();
            if(clientePessoaFisica==null)
                return new ResponseEntity<>(HttpStatus.GONE);
            return new ResponseEntity<>(clientePessoaFisica, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        try {
            if(!cliente.isValid())
                return new ResponseEntity<>(cliente,HttpStatus.NOT_ACCEPTABLE);
            clienteRepository.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(cliente,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("pessoa-fisica/")
    public ResponseEntity<?> createPessoaFisica(@RequestBody ClientePessoaFisica cliente) {
        try {
            if(!cliente.isValid())
                return new ResponseEntity<>(cliente,HttpStatus.NOT_ACCEPTABLE);
            clienteRepository.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(cliente,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Cliente cliente) {
        try {
            if(!cliente.isValid())
                return new ResponseEntity<>(cliente,HttpStatus.NOT_ACCEPTABLE);
            clienteRepository.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("pessoa-fisica")
    public ResponseEntity<?> update(@RequestBody ClientePessoaFisica cliente) {
        try {
            if(!cliente.isValid())
                return new ResponseEntity<>(cliente,HttpStatus.NOT_ACCEPTABLE);
            clienteRepository.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Integer id) {
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("pessoa-fisica/{id}")
    public ResponseEntity<?> destroyPessoaFisica(@PathVariable Integer id) {
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
