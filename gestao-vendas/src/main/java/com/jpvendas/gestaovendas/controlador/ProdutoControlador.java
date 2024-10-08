package com.jpvendas.gestaovendas.controlador;

import com.jpvendas.gestaovendas.entidades.Produto;
import com.jpvendas.gestaovendas.servico.ProdutoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Produto")
@RestController
@RequestMapping("/categoria{idCategoria}/produtos")
public class ProdutoControlador {

    @Autowired
    private ProdutoServico produtoServico;

    @Operation(summary = "Listar por tipo de categoria")
    @GetMapping
    public List<Produto> listarProdutos(@PathVariable Long idCategoria){
        return produtoServico.listarTodos(idCategoria);
    }

    @Operation(summary = "Listar por ID")
    @GetMapping("/{idProduto}")
    public ResponseEntity<Optional<Produto>> listarProdutoPorID(@PathVariable Long idCategoria, @PathVariable Long idProduto){ //@PathVariable indica que é um parametro que é passado na URL, mas não como o que vem apos o ?
        Optional<Produto> produto = produtoServico.buscarPorCodigo(idCategoria, idProduto);
        return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Salvar produto")
    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@PathVariable Long idCategoria, @Valid @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServico.salvarProduto(idCategoria, produto));
    }
    @Operation(summary = "Atualizar produto")
    @PutMapping("/{idProduto}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long idCategoria, @PathVariable Long idProduto, @Valid @RequestBody Produto produto){
        return ResponseEntity.ok(produtoServico.atualizarProduto(idCategoria, idProduto, produto));
    }

    @Operation(summary = "Deletar Produto")
    @DeleteMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long idCategoria, @PathVariable Long idProduto){
        produtoServico.deletarProduto(idCategoria, idProduto);
    }
}
