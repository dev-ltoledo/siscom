package com.siscom.sis.Funcionalities.Compras.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComprasDetalleModel {
    public ComprasDetalleModel(String producto, String descripcion, int cantidad, double precioCostoUnitario, double total) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioCostoUnitario = precioCostoUnitario;
        this.total = total;
    }

    private String producto;
    private String descripcion;
    private int cantidad;
    private double precioCostoUnitario;
    private double total;
}
