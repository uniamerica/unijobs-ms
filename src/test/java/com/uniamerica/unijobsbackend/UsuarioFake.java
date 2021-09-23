package com.uniamerica.unijobsbackend;

import com.uniamerica.unijobsbackend.models.Usuario;

public class UsuarioFake {

    private static Usuario USUARIO_INSTANCIA = null;

    public static Usuario getInstance() {

        if(USUARIO_INSTANCIA == null) {
            USUARIO_INSTANCIA = new Usuario();
            USUARIO_INSTANCIA.setId(1);
            USUARIO_INSTANCIA.setEmail("liocroons@unijobs");
            USUARIO_INSTANCIA.setSenha("1234");
            USUARIO_INSTANCIA.setRa("0007");
            USUARIO_INSTANCIA.setCelular("45984033369");
            USUARIO_INSTANCIA.setNome("lio Croons");
            USUARIO_INSTANCIA.setRa("0987");


        }
        return USUARIO_INSTANCIA;
    }
}
