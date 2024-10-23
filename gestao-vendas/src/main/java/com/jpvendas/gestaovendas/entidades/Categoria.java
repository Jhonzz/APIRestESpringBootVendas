package com.jpvendas.gestaovendas.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "categoria")
@Data
public class Categoria {

    @Id     //Indica que é uma chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //irá gerar automaticamente
    @Column(name = "codigo") //indica a coluna no banco de dados
    private Long codigo;

    private String nome;


    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Categoria(Long codigo) {
        this.codigo = codigo;
    }
}