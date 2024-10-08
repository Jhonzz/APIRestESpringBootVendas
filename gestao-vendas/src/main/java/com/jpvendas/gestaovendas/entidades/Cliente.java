package com.jpvendas.gestaovendas.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id     //Indica que é uma chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //irá gerar automaticamente
    @Column(name = "codigo") //indica a coluna no banco de dados
    private Long codigo;

    @Column(name = "nome")
    @NotBlank(message = "Nome") //valida que o campo não pode ser null ou vazio
    @Length(min = 3, max = 50) //valida o minimo e maximo de caracteres         para que as validações funcionem é necessario informar o @valid no controller
    private String nome;
}
    