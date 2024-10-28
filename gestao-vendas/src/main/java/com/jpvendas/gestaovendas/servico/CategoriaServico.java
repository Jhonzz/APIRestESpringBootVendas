package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.entidades.Categoria;
import com.jpvendas.gestaovendas.entidades.Produto;
import com.jpvendas.gestaovendas.excecao.RegraNegocioException;
import com.jpvendas.gestaovendas.repositorio.CategoriaRepositorio;
import com.jpvendas.gestaovendas.repositorio.ProdutoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServico {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public List<Categoria> listarCategorias(){
        return categoriaRepositorio.findAll();
    }

    public Optional<Categoria> listarCategoriaPorID(Long id){
        return categoriaRepositorio.findById(id);
    }

    public Categoria salvarCategoria(Categoria categoria){
        validarCategoriaDuplicada(categoria);
        return categoriaRepositorio.save(categoria);
    }

    public void deletar(Long id){
        validarCategoriaEstaSendoUtilizada(id);
        categoriaRepositorio.deleteById(id);
    }
    public Categoria atualizarCategoria(Long id, Categoria categoria){
        Categoria categoriaSalvar = validarCategoriaExiste(id);
        validarCategoriaDuplicada(categoria);
        BeanUtils.copyProperties(categoria, categoriaSalvar, "id");
        return categoriaRepositorio.save(categoriaSalvar);
    }

    private Categoria validarCategoriaExiste(Long id) { ///valida se o id informado existe
        Optional<Categoria> categoria = listarCategoriaPorID(id);
        if (categoria.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return categoria.get();
    }


    private void validarCategoriaDuplicada(Categoria categoria) {
        Categoria categoriaEncontrada = categoriaRepositorio.findByNome(categoria.getNome());
        if (categoriaEncontrada != null && categoriaEncontrada.getCodigo() != categoria.getCodigo()) {
            throw new RegraNegocioException(String.format("A categoria %s já esta cadastrada", categoriaEncontrada.getNome().toUpperCase()));
        }
    }

    private void validarCategoriaEstaSendoUtilizada(Long categoria){
        List<Produto> categoriaUtilizada = produtoRepositorio.findByCategoriaCodigo(categoria);
        if (categoriaUtilizada.isEmpty()) {
            throw new RegraNegocioException("Categoria informada é utilizada em um produto ativo");
        }
    }
}
