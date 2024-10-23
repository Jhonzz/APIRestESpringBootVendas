package com.jpvendas.gestaovendas.DTO.cliente;

import com.jpvendas.gestaovendas.entidades.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Endereço request DTO")
@Data
@AllArgsConstructor
public class EnderecoRequestDTO {

    @Length(min = 3, max = 30, message = "Logradouro")
    private String logradouro;

    @NotNull(message = "Numero")
    private Integer numero;

    @Length(max = 30, message = "Complemento")
    private String complemento;

    @NotBlank(message = "Bairro")
    @Length(min = 3, max = 30, message = "Bairro")
    private String bairro;

    @NotBlank(message = "CEP")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP")
    private String cep;

    @NotBlank(message = "Cidade")
    @Length(min = 3, max = 30, message = "Cidade")
    private String cidade;

    @NotBlank(message = "Estado")
    @Pattern(regexp = "[A-Z]{2}",message = "Estado")
    private String estado;

    public Endereco converterDTOParaEntidade(Endereco endereco){
       return new Endereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(),
                endereco.getEstado());
    }
}
