package com.siscom.sis.Funcionalities.Clientes.Repositories;

import com.siscom.sis.Funcionalities.Clientes.Models.ClientesModel;
import com.siscom.sis.Funcionalities.Marcas.Models.MarcasModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesRepository {
    private Connection conexion;

    public ClientesRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public List<ClientesModel> get() {
        String query = "SELECT * FROM cliente";

        List<ClientesModel> modelList = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    var IdCliente = resultSet.getInt("id_cliente");
                    var Nombre = resultSet.getString("nombre");
                    var Apellido = resultSet.getString("apellido");
                    var Nit = resultSet.getString("nit");
                    var Genero = resultSet.getBoolean("genero");
                    var Telefono = resultSet.getString("telefono");
                    var CorreoElectronico = resultSet.getString("correo_electronico");
                    var FechaIngreso = resultSet.getTimestamp("fecha_ingreso").toLocalDateTime();

                    var model = new ClientesModel(IdCliente, Nombre, Apellido, Nit, Genero, Telefono, CorreoElectronico, FechaIngreso);

                    modelList.add(model);
                }
            }

        }catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return modelList;
    }

    public void put(ClientesModel model) {
        String query = "UPDATE cliente SET nombre = ?, apellido = ?, nit = ?, genero = ?, telefono = ?, correo_electronico = ? WHERE id_cliente = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, model.getNombre());
            preparedStatement.setString(2, model.getApellido());
            preparedStatement.setString(3, model.getNit());
            preparedStatement.setBoolean(4, model.isGenero());
            preparedStatement.setString(5, model.getTelefono());
            preparedStatement.setString(6, model.getCorreoElectronico());
            preparedStatement.setInt(7, model.getIdCliente());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cliente actualizado con éxito");
            } else {
                System.out.println("No se encontró el cliente para actualizar");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void post(ClientesModel model) {
        String query = "INSERT INTO cliente (nombre, apellido, nit, genero, telefono, correo_electronico, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, model.getNombre());
            preparedStatement.setString(2, model.getApellido());
            preparedStatement.setString(3, model.getNit());
            preparedStatement.setBoolean(4, model.isGenero());
            preparedStatement.setString(5, model.getTelefono());
            preparedStatement.setString(6, model.getCorreoElectronico());
            preparedStatement.setTimestamp(7, java.sql.Timestamp.valueOf(model.getFechaIngreso()));

            preparedStatement.executeUpdate();
            System.out.println("Cliente creado con éxito");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void delete(ClientesModel model) {
        String query = "DELETE FROM cliente WHERE id_cliente = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getIdCliente());
            preparedStatement.executeUpdate();
            System.out.println("Cliente eliminado con éxito");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
