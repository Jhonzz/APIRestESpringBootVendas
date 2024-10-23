package com.jpvendas.gestaovendas.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id     //Indica que é uma chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //irá gerar automaticamente
    @Column(name = "codigo") //indica a coluna no banco de dados
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "ativo")
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Cliente(String nome, String telefone, Boolean ativo, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(codigo, cliente.codigo) && Objects.equals(nome, cliente.nome) && Objects.equals(telefone, cliente.telefone) && Objects.equals(ativo, cliente.ativo) && Objects.equals(endereco, cliente.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, telefone, ativo, endereco);
    }
}
    