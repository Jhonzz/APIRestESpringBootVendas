package com.jpvendas.gestaovendas.excecao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
//caso aconteça algum erro na chamada da aplicação essa classe sera como um listener, vai procurar um tratamento do erro ocorrido
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    public static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";
    private static final String CONSTANT_VALIDATION_PATTERN = "Pattern";
    private static final String CONSTANT_VALIDATION_MIN = "Min";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<Errors> errors = gerarListaDeErros(ex.getBindingResult());//resultado das exceções que recebimos

        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioExeception(RegraNegocioException ex, WebRequest request) {
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();

        List<Errors> erros = Arrays.asList(new Errors(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    //indica que há um metodo para tratar o erro buscado pela classe de controller advice
    private ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String msgUsuario = "Recurso não encontrado para o ID informado.";
        String msgDesenvolvedor = ex.toString();
        logger.error(ex.getStackTrace(), ex);

        List<Errors> erros = Arrays.asList(new Errors(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String msgUsuario = "Recurso não encontrado para o ID informado";
        String msgDesenvolvedor = ex.toString();


        List<Errors> erros = Arrays.asList(new Errors(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    private List<Errors> gerarListaDeErros(BindingResult bindingResult) {
        List<Errors> erros = new ArrayList<Errors>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgUsuario = tratarMensagemDeErroParaUsuario(fieldError);
            String msgDesenvolvedor = fieldError.toString();
            erros.add(new Errors(msgUsuario, msgDesenvolvedor));
        });

        return erros;
    }

    private String tratarMensagemDeErroParaUsuario(FieldError fieldError) {
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)) {
            return fieldError.getDefaultMessage().concat(" é obrigatorio");
        }

        if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)){
            return fieldError.getDefaultMessage().concat(" é obrigatorio");
        }

        if (fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)) {
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.",
                    fieldError.getArguments()[2], fieldError.getArguments()[1]));
        }

        if (fieldError.getCode().equals((CONSTANT_VALIDATION_PATTERN))) {
            return "Formato invalido para ".concat(fieldError.getDefaultMessage());

        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_MIN)) {
            return fieldError.getDefaultMessage().concat((" deve ser maior que 0."));

        }
        return fieldError.toString();
    }


}
