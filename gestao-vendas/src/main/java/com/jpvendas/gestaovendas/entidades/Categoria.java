package com.jpvendas.gestaovendas.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categoria")
@Data
public class Categoria {

    @Id     //Indica que é uma chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //irá gerar automaticamente
    @Column(name = "codigo") //indica a coluna no banco de dados
    private Long codigo;

    @Column(name = "nome")
    private String nome;
}