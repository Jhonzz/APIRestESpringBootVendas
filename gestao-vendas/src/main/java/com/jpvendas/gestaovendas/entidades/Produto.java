package com.jpvendas.gestaovendas.entidades;

import io.swagger.annotations.ApiParam;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "produto")
@Data
public class Produto {

    @Id     //Indica que é uma chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //irá gerar automaticamente
    @Column(name = "codigo") //indica a coluna no banco de dados
    private Long codigo;

    @Column(name = "descricao")
    @NotBlank(message = "Descrição") //valida que o campo não pode ser null ou vazio
    @Length(min = 3, max = 100, message = "Descrição") //valida o minimo e maximo de caracteres. Para que as validações funcionem é necessario informar o @valid no controller
    private String descricao;

    @Column(name = "quantidade")
    @NotNull(message = "Quantidade") //para integer é melhor utilizar o notnull pois valida que tem que ser maior que 0 e nao pode ser vazio
    private Integer quantidade;

    @Column(name = "preco_custo")
    @NotNull(message = "Preço custo") //para integer é melhor utilizar o notnull pois valida que tem que ser maior que 0 e nao pode ser vazio
    private Double precoCusto;

    @Column(name = "preco_venda")
    @NotNull(message = "Quantidade") //para integer é melhor utilizar o notnull pois valida que tem que ser maior que 0 e nao pode ser vazio
    private Double precoVenda;

    @Column(name = "observacao")
    @Length(max = 500, message = "Observação")
    private String observacao;


    @ManyToOne
    @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
    @NotNull(message = "Codigo categoria")
    private Categoria categoria;
}
