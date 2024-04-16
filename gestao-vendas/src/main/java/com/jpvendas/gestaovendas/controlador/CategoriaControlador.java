package com.jpvendas.gestaovendas.controlador;

import com.jpvendas.gestaovendas.entidades.Categoria;
import com.jpvendas.gestaovendas.servico.CategoriaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
