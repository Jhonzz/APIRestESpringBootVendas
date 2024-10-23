package com.jpvendas.gestaovendas.DTO.categoria;

import com.jpvendas.gestaovendas.entidades.Categoria;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Categoria request DTO") // representa o APImodel do springfox
@Data
public class CategoriaRequestDTO {

    @ApiParam(value = "Nome") //@apimodelproperty para spring
    @NotBlank(message = "Nome") //valida que o campo não pode ser null ou vazio
    @Length(min = 3, max = 50, message = "Nome") //valida o minimo e maximo de caracteres         para que as validações funcionem é necessario informar o @valid no controller
    private String nome;

    public Categoria converterParaEntidade(){
        return new Categoria(nome);
    }

    public Categoria converterParaEntidade(Long codigo){
        return new Categoria(codigo, nome);
    }

}
