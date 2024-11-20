package com.jpvendas.gestaovendas.DTO.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Schema(name = "Itens da venda response DTO")
public class ItemVendaResponseDTO {
    @Schema(name = "CodigoVenda")
    private Long codigo;
    @Schema(name = "Quantidade")
    private Integer quantidade;
    @Schema(name = "PrecoVendido")
    private BigDecimal precoVendido;
    @Schema(name = "CodigoProduto")
    private Long codigoProduto;
    @Schema(name = "DescricaoProduto")
    private String produtoDescricao;
}
