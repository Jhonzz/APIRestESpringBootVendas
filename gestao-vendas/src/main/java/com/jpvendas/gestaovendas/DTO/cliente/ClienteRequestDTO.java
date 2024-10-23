package com.jpvendas.gestaovendas.DTO.cliente;

import com.jpvendas.gestaovendas.entidades.Cliente;
import com.jpvendas.gestaovendas.entidades.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Cliente Request DTO")
@Data
public class ClienteRequestDTO {

    @Schema(name = "nome")
    @Length(min = 3, max = 50, message = "Nome")
    @NotBlank(message = "Nome")
    private String nome;

    @Schema(name = "telefone")
    @NotBlank(message = "Telefone")
    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}", message = "Telefone")
    private String telefone;

    @Schema(name = "ativo")
    @NotNull(message = "Ativo") //por ser boolean
    private Boolean ativo;

    @Schema(name = "endereco")
    @NotNull
    @Valid //para validar os bean validation dos campos da classe endereçoDTO
    private EnderecoRequestDTO endereco;

    public Cliente converterDTOParaEntidade(){
        Endereco enderecoDTO = new Endereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCep(),
                endereco.getCidade(), endereco.getEstado());

        return new Cliente(nome, telefone, ativo, enderecoDTO);
    }
    public Cliente converterDTOParaEntidade(Long id){
        Endereco enderecoDTO = new Endereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCep(),
                endereco.getCidade(), endereco.getEstado());

        return new Cliente(id, nome, telefone, ativo, enderecoDTO);
    }

}
