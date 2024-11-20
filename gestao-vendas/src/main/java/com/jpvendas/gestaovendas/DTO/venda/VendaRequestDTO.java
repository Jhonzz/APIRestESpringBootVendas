package com.jpvendas.gestaovendas.DTO.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Schema(name = "Venda request DTO")
@Data
public class VendaRequestDTO {

    @NotNull(message = "Data")
    private LocalDate data;

    @NotNull(message = "Item venda")
    @Valid //para validar os atributos dentro dessa classe
    private List<ItemVendaRequestDTO> itemVendaRequestDTOList;

}
