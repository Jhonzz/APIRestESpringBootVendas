package com.jpvendas.gestaovendas.repositorio;

import com.jpvendas.gestaovendas.entidades.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendasRepositorio extends JpaRepository<Venda, Long> {

    List<Venda> findByClienteCodigo(Long codigoCliente);
}
