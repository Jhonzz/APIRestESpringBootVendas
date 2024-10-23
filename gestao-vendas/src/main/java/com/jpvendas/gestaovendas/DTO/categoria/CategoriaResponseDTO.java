package com.jpvendas.gestaovendas.DTO.categoria;

import com.jpvendas.gestaovendas.entidades.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Categoria response DTO")
@Data
@AllArgsConstructor
public class CategoriaResponseDTO {

    @Schema(name = "codigo")
    private Long codigo;

    @Schema(name = "nome")
    private String nome;

    public static CategoriaResponseDTO converterParaCategoriaDTO(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
    }
}
