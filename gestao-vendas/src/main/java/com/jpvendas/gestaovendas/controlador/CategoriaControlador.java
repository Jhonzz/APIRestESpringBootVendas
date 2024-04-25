package com.jpvendas.gestaovendas.controlador;

import com.jpvendas.gestaovendas.entidades.Categoria;
import com.jpvendas.gestaovendas.servico.CategoriaServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @GetMapping
    public List<Categoria> listarCategorias(){
        return categoriaServico.listarCategorias();
    }

    @GetMapping("/{id}") //a partir do momento que existem mais de um getmapping dentro do controller é necessario informar o parametro
    public ResponseEntity<Optional<Categoria>> listarCategoriaPorID(@PathVariable Long id){
      Optional<Categoria> categoria = categoriaServico.listarCategoriaPorID(id);
      return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){ //@Valid faz com que as validações do bean funcionem, como obrigatoriedade
        Categoria categoriaSalva = categoriaServico.salvarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaServico.atualizarCategoria(id, categoria));
    }

}
