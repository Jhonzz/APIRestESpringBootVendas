package com.jpvendas.gestaovendas.excecao;

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
