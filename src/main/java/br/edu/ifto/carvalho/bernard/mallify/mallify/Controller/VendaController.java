package br.edu.ifto.carvalho.bernard.mallify.mallify.Controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.ItemVenda;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Produto;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Entity.Venda;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ClienteRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ItemVendaRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.ProdutoRepository;
import br.edu.ifto.carvalho.bernard.mallify.mallify.Repository.VendaRepository;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/vendas")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
@Transactional
public class VendaController {

    @Autowired
    VendaRepository repository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ItemVendaRepository itemVendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    Venda carrinho;

    //
    // <CARRINHO>
    //
    @GetMapping("carrinho")
    public ResponseEntity<?> get(){
        return new ResponseEntity<>(carrinho, HttpStatus.OK);
    }

    @PutMapping("carrinho")
    public ResponseEntity<?> put(@RequestBody Venda venda){
        try{
        carrinho.setId(venda.getId());
        carrinho.setData(venda.getData());
        carrinho.setCliente(venda.getCliente());
        carrinho.setItensVenda(venda.getItensVenda());
        
        if(!carrinho.isValid())
            return new ResponseEntity<>(carrinho, HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(carrinho, HttpStatus.NO_CONTENT);
        }catch(Exception e){ return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
    }

    @DeleteMapping("carrinho")
    public ResponseEntity<?> delete(){
        carrinho = new Venda();
        return new ResponseEntity<>(HttpStatus.OK);
    }

        ///
        /// <itens>
        ///

        @GetMapping("carrinho/itens") 
        public ResponseEntity<?> findAllItens(){
            if(carrinho==null)
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(carrinho.getItensVenda(),HttpStatus.OK);
        }

        @GetMapping("carrinho/itens/{id}") 
        public ResponseEntity<?> findItemById(@PathVariable Integer id){
            if(carrinho==null)
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            ItemVenda itemVenda = carrinho.getItensVenda().get(id);
            if(itemVenda==null)
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(itemVenda,HttpStatus.OK);
        }

        //TODO
        @PutMapping("carrinho/itens")
        public ResponseEntity<?> updateItemVendaList(@RequestBody List<ItemVenda> itensList){
            
            for (ItemVenda itemVenda : itensList) {
                if(!itemVenda.isValid())
                return new ResponseEntity<>(itemVenda,HttpStatus.NOT_ACCEPTABLE);
                if(!itemVenda.getProduto().isValid())
                return new ResponseEntity<>(itensList,HttpStatus.NOT_ACCEPTABLE);
            }
            
            carrinho.setItensVenda(itensList);

            //////////////////////////////////////////////////////////////////////////////////

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        @PutMapping("carrinho/itens/{index}")
        public ResponseEntity<?> putItemVenda(@PathVariable Integer index,@RequestBody ItemVenda itemVenda){
            if(!itemVenda.isValid())
                return new ResponseEntity<>(itemVenda,HttpStatus.NOT_ACCEPTABLE);
            if(!itemVenda.getProduto().isValid())
                return new ResponseEntity<>(itemVenda,HttpStatus.NOT_ACCEPTABLE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }



        ///
        /// </itens>
        ///

    //
    // </CARRINHO>
    //

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
            
            //TODO CREATE PRODUTOS
            
            //TODO CREATE ITEMVENDAS
            
            //repository.save(venda);
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
