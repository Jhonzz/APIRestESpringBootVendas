package com.jpvendas.gestaovendas.DTO.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Schema(name = "Cliente da venda response DTO")
public class ClienteVendaResponseDTO {
    @Schema(name = "Nome cliente")
    private String nome;

    @Schema(name = "Vendas")
    private List<VendasResponseDTO> vendaResponseDTO;

}
