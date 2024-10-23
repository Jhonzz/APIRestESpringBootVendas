package com.jpvendas.gestaovendas.DTO.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Endereço response DTO")
@Data
@AllArgsConstructor
public class EnderecoResponseDTO {

    @Schema(name = "Logradouro")
    private String logradouro;

    @Schema(name = "Numero")
    private Integer numero;

    @Schema(name = "Complemento")
    private String complemento;

    @Schema(name = "Bairro")
    private String bairro;

    @Schema(name = "Cep")
    private String cep;

    @Schema(name = "Cidade")
    private String cidade;

    @Schema(name = "Estado")
    private String estado;
}
