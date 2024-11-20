package com.jpvendas.gestaovendas.DTO.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Schema(name = "Cliente da venda response DTO")
public class ClienteVendaResponseDTO {
    private String nome;

    private List<VendasResponseDTO> vendaResponseDTO;

}
