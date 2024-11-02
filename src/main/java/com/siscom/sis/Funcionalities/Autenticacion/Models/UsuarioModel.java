package com.siscom.sis.Funcionalities.Autenticacion.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioModel {

    public UsuarioModel(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    private String usuario;
    private String password;
}
