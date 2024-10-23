package com.jpvendas.gestaovendas.repositorio;

import com.jpvendas.gestaovendas.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);
}
