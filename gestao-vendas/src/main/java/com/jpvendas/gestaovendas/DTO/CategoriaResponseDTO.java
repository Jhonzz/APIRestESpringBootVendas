package com.jpvendas.gestaovendas.DTO;

import com.jpvendas.gestaovendas.entidades.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Categoria retorno DTO")
@Data
public class CategoriaResponseDTO {
    private Long codigo;

    private String nome;

    public CategoriaResponseDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    public static CategoriaResponseDTO converterParaCategoriaDTO(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
    }
}
