package com.siscom.sis.Funcionalities.Proveedores.Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProveedoresModel {

    public ProveedoresModel(int idProveedor, String proveedor, String nit, String direccion, String telefono, int idEstado) {
        this.idProveedor = idProveedor;
        this.proveedor = proveedor;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idEstado = idEstado;
    }

    private int idProveedor;
    private String proveedor;
    private String nit;
    private String direccion;
    private String telefono;
    private int idEstado;

}
