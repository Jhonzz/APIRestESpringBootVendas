package com.jpvendas.gestaovendas.DTO.cliente;

import com.jpvendas.gestaovendas.entidades.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Cliente response DTO")
@Data
@AllArgsConstructor
public class ClienteResponseDTO {

    @Schema(name = "codigo")
    private Long codigo;

    @Schema(name = "nome")
    private String nome;

    @Schema(name = "telefone")
    private String telefone;

    @Schema(name = "ativo")
    private Boolean ativo;

    private EnderecoResponseDTO endereco;

    public static ClienteResponseDTO converterEntidadeParaDTO(Cliente cliente){
        EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO(cliente.getEndereco().getLogradouro(), cliente.getEndereco().getNumero(),
                cliente.getEndereco().getComplemento(), cliente.getEndereco().getBairro(), cliente.getEndereco().getCep(), cliente.getEndereco().getCidade(),
                cliente.getEndereco().getEstado());
        return new ClienteResponseDTO(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(), cliente.getAtivo(), enderecoDTO);
    }
}
