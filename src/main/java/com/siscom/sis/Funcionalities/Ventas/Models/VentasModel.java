package com.siscom.sis.Funcionalities.Ventas.Models;

import lombok.*;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class VentasModel {

    public VentasModel(int idVenta, Integer noFactura, Character serie, Date fechaFactura, int idCliente, String nit, int idEmpleado, String empleado, LocalDateTime fechaIngreso) {
        this.idVenta = idVenta;
        this.noFactura = noFactura;
        this.serie = serie;
        this.fechaFactura = fechaFactura;
        this.idCliente = idCliente;
        this.nit = nit;
        this.idEmpleado = idEmpleado;
        this.empleado = empleado;
        this.fechaIngreso = fechaIngreso;
    }

    private int idVenta;
    private Integer noFactura;
    private Character serie;
    private Date fechaFactura;
    private int idCliente;
    private String nit;
    private int idEmpleado;
    private String empleado;
    private LocalDateTime fechaIngreso;
}
