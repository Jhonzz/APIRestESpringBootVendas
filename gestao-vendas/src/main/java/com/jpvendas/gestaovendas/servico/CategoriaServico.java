package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.entidades.Categoria;
import com.jpvendas.gestaovendas.repositorio.CategoriaRepositorio;
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

    public List<Categoria> listarCategorias(){
        return categoriaRepositorio.findAll();
    }

    public Optional<Categoria> listarCategoriaPorID(Long id){
        return categoriaRepositorio.findById(id);
    }

    public Categoria salvarCategoria(Categoria categoria){
        return categoriaRepositorio.save(categoria);
    }

    public void deletar(Long id){
        categoriaRepositorio.deleteById(id);
    }
    public Categoria atualizarCategoria(Long id, Categoria categoria){
        Categoria categoriaSalvar = validarCategoriaExiste(id);
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


    private void validarCategoriaDuplicada(Categoria categoria){
        List<Categoria> categoriaList = categoriaRepositorio.findByNome(categoria.getNome());
    }
}
