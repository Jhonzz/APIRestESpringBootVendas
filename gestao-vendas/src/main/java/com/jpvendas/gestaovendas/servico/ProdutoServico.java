package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.entidades.Produto;
import com.jpvendas.gestaovendas.excecao.RegraNegocioException;
import com.jpvendas.gestaovendas.repositorio.ProdutoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaServico categoriaServico;

    public ProdutoServico(ProdutoRepositorio produtoRepositorio) {
    }

    public List<Produto> listarTodos(Long codigoCategoria){
        return produtoRepositorio.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigoCategoria, Long codigo){
       return produtoRepositorio.buscarPorCodigo(codigoCategoria, codigo);
    }

    public Produto salvarProduto(Long codigoCategoria, Produto produto){
        validarCategoriaDoProdutoNaoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        return produtoRepositorio.save(produto);
    }

    public Produto atualizarProduto(Long codigoCategoria, Long codigoProduto, Produto produto){
        Produto produtoSalvar = validarProdutoExiste(codigoCategoria, codigoProduto);
        validarCategoriaDoProdutoNaoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvar, "codigo"); //produto ira ser enviado com as modificações, produtosalvar ira alterar e ignorar o campo codigo
        return produtoRepositorio.save(produtoSalvar);
    }

    public void deletarProduto(Long codigoCategoria, Long codigoProduto){
        Produto produto = validarProdutoExiste(codigoCategoria, codigoProduto);
        produtoRepositorio.delete(produto);
    }
    private Produto validarProdutoExiste(Long codigoCategoria, Long codigoProduto) {
        Optional<Produto> produto = buscarPorCodigo(codigoCategoria, codigoProduto);
        if (produto.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return produto.get();
    }

    private void validarProdutoDuplicado(Produto produto){
        Optional<Produto> produtoPorDescricao = produtoRepositorio.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());

        if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()){ //caso o produto ja existir para a categoria informada então o sistema apresentara o erro
            throw new RegraNegocioException(String.format("Produto %s ja existente para a categoria informada", produto.getDescricao()));
        }
    }

    private void validarCategoriaDoProdutoNaoExiste(Long codigoCategoria){
        if (codigoCategoria == null){
            throw new RegraNegocioException("Categoria não pode ser vazia");
        }

        if (categoriaServico.listarCategoriaPorID(codigoCategoria).isEmpty()){
            throw new RegraNegocioException(String.format("Categoria não encontrada para o ID %s",codigoCategoria));
        }
    }
}
