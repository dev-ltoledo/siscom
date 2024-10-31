package com.siscom.sis.Funcionalities.Productos.Models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductosModel {

    public ProductosModel(int idProducto, String producto, short idMarca, String descripcion, String imagen, BigDecimal precioCosto, BigDecimal precioVenta, int existencia, LocalDateTime fechaIngreso) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.idMarca = idMarca;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.existencia = existencia;
        this.fechaIngreso = fechaIngreso;
    }

    private int idProducto;
    private String producto;
    private short idMarca;
    private String descripcion;
    private String imagen;
    private BigDecimal precioCosto;
    private BigDecimal precioVenta;
    private int existencia;
    private LocalDateTime fechaIngreso;

}
