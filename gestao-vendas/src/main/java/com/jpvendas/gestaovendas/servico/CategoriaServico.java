package com.jpvendas.gestaovendas.servico;

import com.jpvendas.gestaovendas.entidades.Categoria;
import com.jpvendas.gestaovendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
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

}
