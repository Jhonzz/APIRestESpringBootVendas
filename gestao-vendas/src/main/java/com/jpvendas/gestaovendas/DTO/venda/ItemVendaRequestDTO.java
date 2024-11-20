package com.jpvendas.gestaovendas.DTO.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(name = "Itens venda request DTO")
@Data
public class ItemVendaRequestDTO {

    @NotNull(message = "Codigo produto")
    private Long codigoProduto;

    @NotNull(message = "Quantidade")
    @Min(value = 1, message = "Quantidade")
    private Integer quantidade;

    @NotNull(message = "Preço vendido")
    private BigDecimal precoVendido;
}
