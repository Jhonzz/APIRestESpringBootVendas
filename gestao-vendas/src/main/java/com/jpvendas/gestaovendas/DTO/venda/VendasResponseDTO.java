package com.jpvendas.gestaovendas.DTO.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Schema(name = "Venda response DTO")
@Data
@AllArgsConstructor
public class VendasResponseDTO {
    @Schema(name = "Codigo")
    private Long codigo;

    @Schema(name = "Data")
    private LocalDate data;

    @Schema(name = "ItensVenda")
    private List<ItemVendaResponseDTO> itemVenda;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendasResponseDTO that = (VendasResponseDTO) o;
        return Objects.equals(codigo, that.codigo) && Objects.equals(data, that.data) && Objects.equals(itemVenda, that.itemVenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, data, itemVenda);
    }
}
