package com.siscom.sis.Funcionalities.Clientes.Models;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ClientesModel {

    public ClientesModel(int IdCliente, String Nombre, String Apellido, String Nit, boolean Genero, String Telefono, String CorreoElectronico, LocalDateTime FechaIngreso) {
        this.IdCliente = IdCliente;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Nit = Nit;
        this.Genero = Genero;
        this.Telefono = Telefono;
        this.CorreoElectronico = CorreoElectronico;
        this.FechaIngreso = FechaIngreso;
    }

    private int IdCliente;
    private String Nombre;
    private String Apellido;
    private String Nit;
    private boolean Genero;
    private String Telefono;
    private String CorreoElectronico;
    private LocalDateTime FechaIngreso;
}

