package com.siscom.sis.Funcionalities.Proveedores.Repositories;

import com.siscom.sis.Funcionalities.Proveedores.Models.ProveedoresModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresRepository {
    private Connection conexion;

    public ProveedoresRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public List<ProveedoresModel> get() {
        String query = "SELECT * FROM proveedor";
        List<ProveedoresModel> proveedores = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    var idProveedor = resultSet.getInt("id_proveedor");
                    var proveedor = resultSet.getString("proveedor");
                    var nit = resultSet.getString("nit");
                    var direccion = resultSet.getString("direccion");
                    var telefono = resultSet.getString("telefono");
                    var idEstado = resultSet.getInt("id_estado");

                    var proveedorModel = new ProveedoresModel(idProveedor, proveedor, nit, direccion, telefono, idEstado);
                    proveedores.add(proveedorModel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return proveedores;
    }

    public List<ProveedoresModel> getActive() {
        String query = "SELECT * FROM proveedor WHERE id_estado = 1";
        List<ProveedoresModel> proveedores = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    var idProveedor = resultSet.getInt("id_proveedor");
                    var proveedor = resultSet.getString("proveedor");
                    var nit = resultSet.getString("nit");
                    var direccion = resultSet.getString("direccion");
                    var telefono = resultSet.getString("telefono");
                    var idEstado = resultSet.getInt("id_estado");

                    var proveedorModel = new ProveedoresModel(idProveedor, proveedor, nit, direccion, telefono, idEstado);
                    proveedores.add(proveedorModel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return proveedores;
    }

    public void put(ProveedoresModel proveedor) {
        String query = "UPDATE proveedor SET proveedor = ?, nit = ?, direccion = ?, telefono = ?, id_estado = ? WHERE id_proveedor = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, proveedor.getProveedor());
            preparedStatement.setString(2, proveedor.getNit());
            preparedStatement.setString(3, proveedor.getDireccion());
            preparedStatement.setString(4, proveedor.getTelefono());
            preparedStatement.setInt(5, proveedor.getIdEstado());
            preparedStatement.setInt(6, proveedor.getIdProveedor());
            preparedStatement.executeUpdate();
            System.out.println("Proveedor actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void post(ProveedoresModel proveedor) {
        String query = "INSERT INTO proveedor (proveedor, nit, direccion, telefono, id_estado) VALUES (?, ?, ?, ?, ?)";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, proveedor.getProveedor());
            preparedStatement.setString(2, proveedor.getNit());
            preparedStatement.setString(3, proveedor.getDireccion());
            preparedStatement.setString(4, proveedor.getTelefono());
            preparedStatement.setInt(5, proveedor.getIdEstado());
            preparedStatement.executeUpdate();
            System.out.println("Proveedor creado con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void delete(ProveedoresModel proveedor) {
        String query = "DELETE FROM proveedor WHERE id_proveedor = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, proveedor.getIdProveedor());
            preparedStatement.executeUpdate();
            System.out.println("Proveedor eliminado con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
