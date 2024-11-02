package com.siscom.sis.Funcionalities.Ventas.Models;

import lombok.*;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class VentasDetalleModel {

    public VentasDetalleModel(String producto, String descripcion, int cantidad, double precioUnitario, double total) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    private String producto;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private double total;
}
