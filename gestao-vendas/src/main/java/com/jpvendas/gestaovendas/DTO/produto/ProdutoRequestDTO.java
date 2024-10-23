package com.jpvendas.gestaovendas.DTO.produto;

import com.jpvendas.gestaovendas.entidades.Categoria;
import com.jpvendas.gestaovendas.entidades.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Produto request DTO")
@Data
public class ProdutoRequestDTO {
    @NotBlank(message = "Descrição") //valida que o campo não pode ser null ou vazio
    @Length(min = 3, max = 100, message = "Descrição") //valida o minimo e maximo de caracteres. Para que as validações funcionem é necessario informar o @valid no controller
    private String descricao;

    @NotNull(message = "Quantidade") //para integer é melhor utilizar o notnull pois valida que tem que ser maior que 0 e nao pode ser vazio
    private Integer quantidade;

    @NotNull(message = "Preço custo") //para integer é melhor utilizar o notnull pois valida que tem que ser maior que 0 e nao pode ser vazio
    private Double precoCusto;

    @NotNull(message = "Quantidade") //para integer é melhor utilizar o notnull pois valida que tem que ser maior que 0 e nao pode ser vazio
    private Double precoVenda;

    @Length(max = 500, message = "Observação")
    private String observacao;

    public Produto converterDTOParaEntidade(Long codigoCategoria){
        return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
    }

    public Produto converterDTOParaEntidade(Long codigoCategoria, Long codigoProduto){
        return new Produto(codigoProduto, descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
    }

}
