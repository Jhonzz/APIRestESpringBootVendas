package com.jpvendas.gestaovendas.controlador;

import com.jpvendas.gestaovendas.entidades.Categoria;
import com.jpvendas.gestaovendas.servico.CategoriaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


//swagger http://localhost:8080/swagger-ui/index.html
@Tag(name = "Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @Operation(summary = "Listar")
    @GetMapping
    public List<Categoria> listarCategorias(){
        return categoriaServico.listarCategorias();
    }

    @Operation(summary = "Listar por ID")
    @GetMapping("/{id}") //a partir do momento que existem mais de um getmapping dentro do controller é necessario informar o parametro
    public ResponseEntity<Optional<Categoria>> listarCategoriaPorID(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaServico.listarCategoriaPorID(id);
        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();

    }

    @Operation(summary = "Salvar")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){ //@Valid faz com que as validações do bean funcionem, como obrigatoriedade
        Categoria categoriaSalva = categoriaServico.salvarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);

    }

    @Operation(summary = "Atualizar")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) { //@PathVariable indica que é um parametro que é passado na URL, mas não como o que vem apos o ?
        return ResponseEntity.ok(categoriaServico.atualizarCategoria(id, categoria));

    }

    @Operation(summary = "Deletar")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //INDICA QUE NÃO TEM NADA PRA RETORNAR
    public void delete(@PathVariable Long id){
         categoriaServico.deletar(id);

    }
}