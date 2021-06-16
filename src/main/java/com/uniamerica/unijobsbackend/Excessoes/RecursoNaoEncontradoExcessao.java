package com.uniamerica.unijobsbackend.Excessoes;

public class RecursoNaoEncontradoExcessao extends RuntimeException {

    public RecursoNaoEncontradoExcessao(String msg) {
        super(msg);
    }

    public RecursoNaoEncontradoExcessao() {
        super("Recurso não Encontrado!");
    }

}
