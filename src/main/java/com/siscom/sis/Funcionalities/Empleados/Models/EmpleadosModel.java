package com.siscom.sis.Funcionalities.Empleados.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EmpleadosModel {

    public EmpleadosModel(int idEmpleado, String nombre, String apellido, String direccion, String telefono,
                          String dpi, boolean genero, LocalDate fechaNacimiento, short idPuesto, String puesto,
                          LocalDate fechaInicioLabor, LocalDateTime fechaIngreso) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.dpi = dpi;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.idPuesto = idPuesto;
        this.puesto = puesto;
        this.fechaInicioLabor = fechaInicioLabor;
        this.fechaIngreso = fechaIngreso;
    }

    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String dpi;
    private boolean genero;
    private LocalDate fechaNacimiento;
    private short idPuesto;
    private String puesto;
    private LocalDate fechaInicioLabor;
    private LocalDateTime fechaIngreso;
}

