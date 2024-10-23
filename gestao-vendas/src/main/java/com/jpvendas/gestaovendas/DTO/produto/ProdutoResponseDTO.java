package com.jpvendas.gestaovendas.DTO.produto;

import com.jpvendas.gestaovendas.DTO.categoria.CategoriaResponseDTO;
import com.jpvendas.gestaovendas.entidades.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Produto Response DTO")
@Data
@AllArgsConstructor
public class ProdutoResponseDTO {

    @Schema(name = "Codigo")
    private Long codigo;
    @Schema(name = "Descrição")
    private String descricao;
    @Schema(name = "Quantidade")
    private Integer quantidade;
    @Schema(name = "Preço custo")
    private Double precoCusto;
    @Schema(name = "Preço Venda")
    private Double precoVenda;
    @Schema(name = "Observação")
    private String observacao;
    @Schema(name = "Categoria")
    private CategoriaResponseDTO categoria;

    public static ProdutoResponseDTO converterProdutoParaDTO(Produto produto){
        return new ProdutoResponseDTO(produto.getCodigo(), produto.getDescricao(), produto.getQuantidade(), produto.getPrecoCusto(), produto.getPrecoVenda(),
                produto.getObservacao(),
                CategoriaResponseDTO.converterParaCategoriaDTO(produto.getCategoria()));
    }
}
