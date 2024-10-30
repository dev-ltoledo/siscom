package com.siscom.sis.Funcionalities.Empleados.Repositories;
import com.siscom.sis.Funcionalities.Empleados.Models.EmpleadosModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {
    private Connection conexion;

    public EmpleadoRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public List<EmpleadosModel> get() {
        String query = "SELECT id_empleado, nombre, apellido, direccion, telefono, dpi, genero, fecha_nacimiento, p.id_puesto, p.puesto, fecha_inicio_labor, fecha_ingreso FROM empleado e inner join puesto p on e.id_puesto = p.id_puesto";

        List<EmpleadosModel> modelList = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    var idEmpleado = resultSet.getInt("id_empleado");
                    var nombre = resultSet.getString("nombre");
                    var apellido = resultSet.getString("apellido");
                    var direccion = resultSet.getString("direccion");
                    var telefono = resultSet.getString("telefono");
                    var dpi = resultSet.getString("dpi");
                    var genero = resultSet.getBoolean("genero");
                    var fechaNacimiento = resultSet.getDate("fecha_nacimiento").toLocalDate();
                    var idPuesto = resultSet.getShort("id_puesto");
                    var puesto = resultSet.getString("puesto");
                    var fechaInicioLabor = resultSet.getDate("fecha_inicio_labor").toLocalDate();
                    var fechaIngreso = resultSet.getTimestamp("fecha_ingreso").toLocalDateTime();

                    var model = new EmpleadosModel(idEmpleado, nombre, apellido, direccion, telefono, dpi, genero, fechaNacimiento, idPuesto, puesto, fechaInicioLabor, fechaIngreso);

                    modelList.add(model);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return modelList;
    }

    public void put(EmpleadosModel model) {
        String query = "UPDATE empleado SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, dpi = ?, genero = ?, fecha_nacimiento = ?, id_puesto = ?, fecha_inicio_labor = ? WHERE id_empleado = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, model.getNombre());
            preparedStatement.setString(2, model.getApellido());
            preparedStatement.setString(3, model.getDireccion());
            preparedStatement.setString(4, model.getTelefono());
            preparedStatement.setString(5, model.getDpi());
            preparedStatement.setBoolean(6, model.isGenero());
            preparedStatement.setDate(7, java.sql.Date.valueOf(model.getFechaNacimiento()));
            preparedStatement.setShort(8, model.getIdPuesto());
            preparedStatement.setDate(9, java.sql.Date.valueOf(model.getFechaInicioLabor()));
            preparedStatement.setInt(10, model.getIdEmpleado());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Empleado actualizado con éxito");
            } else {
                System.out.println("No se encontró el empleado para actualizar");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void post(EmpleadosModel model) {
        String query = "INSERT INTO empleado (nombre, apellido, direccion, telefono, dpi, genero, fecha_nacimiento, id_puesto, fecha_inicio_labor, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, model.getNombre());
            preparedStatement.setString(2, model.getApellido());
            preparedStatement.setString(3, model.getDireccion());
            preparedStatement.setString(4, model.getTelefono());
            preparedStatement.setString(5, model.getDpi());
            preparedStatement.setBoolean(6, model.isGenero());
            preparedStatement.setDate(7, java.sql.Date.valueOf(model.getFechaNacimiento()));
            preparedStatement.setShort(8, model.getIdPuesto());
            preparedStatement.setDate(9, java.sql.Date.valueOf(model.getFechaInicioLabor()));
            preparedStatement.setTimestamp(10, Timestamp.valueOf(model.getFechaIngreso()));

            preparedStatement.executeUpdate();
            System.out.println("Empleado creado con éxito");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void delete(EmpleadosModel model) {
        String query = "DELETE FROM empleado WHERE id_empleado = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getIdEmpleado());
            preparedStatement.executeUpdate();
            System.out.println("Empleado eliminado con éxito");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


