package com.jpvendas.gestaovendas.DTO.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Schema(name = "Itens da venda response DTO")
public class ItemVendaResponseDTO {
    @Schema(name = "Codigo da venda")
    private Long codigo;
    @Schema(name = "Quantidade")
    private Integer quantidade;
    @Schema(name = "Preco vendido")
    private BigDecimal precoVendido;
    @Schema(name = "Codigo do produto")
    private Long codigoProduto;
    @Schema(name = "Descricao do produto")
    private String produtoDescricao;
}
