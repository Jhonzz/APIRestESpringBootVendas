package com.jpvendas.gestaovendas.repositorio;

import com.jpvendas.gestaovendas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> { //o JPA ja disponibiliza todas as funcionalidades CRUD dentro da propria classe
}
