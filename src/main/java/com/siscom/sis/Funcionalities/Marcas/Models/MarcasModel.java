package com.siscom.sis.Funcionalities.Marcas.Models;

import lombok.*;

@Setter
@Getter

public class MarcasModel {

    public MarcasModel(int idMarca, String marca, int idEstado) {
        this.idMarca = idMarca;
        this.marca = marca;
        this.idEstado = idEstado;
    }

    private int idMarca;
    private String marca;
    private int idEstado;
}
