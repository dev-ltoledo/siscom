package com.siscom.sis.Funcionalities.Puestos.Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PuestosModel {

    public PuestosModel(short idPuesto, String puesto, int idEstado) {
        this.idPuesto = idPuesto;
        this.puesto = puesto;
        this.idEstado = idEstado;
    }

    private short idPuesto;
    private String puesto;
    private int idEstado;
}
