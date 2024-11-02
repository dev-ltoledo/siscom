package com.siscom.sis.Funcionalities.Compras.Models;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ComprasModel {

    public ComprasModel(int idCompra, Integer noOrdenCompra, int idProveedor, String proveedor, Date fechaOrden, Timestamp fechaIngreso) {
        this.idCompra = idCompra;
        this.noOrdenCompra = noOrdenCompra;
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
        this.fechaOrden = fechaOrden;
        this.fechaIngreso = fechaIngreso;
    }

    private int idCompra;
    private Integer noOrdenCompra;
    private int idProveedor;
    private String proveedor;
    private Date fechaOrden;
    private Timestamp fechaIngreso;
}
