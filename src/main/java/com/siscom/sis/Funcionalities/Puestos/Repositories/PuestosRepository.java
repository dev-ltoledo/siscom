package com.siscom.sis.Funcionalities.Puestos.Repositories;

import com.siscom.sis.Funcionalities.Puestos.Models.PuestosModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PuestosRepository {
    private Connection conexion;

    public PuestosRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public List<PuestosModel> get() {
        String query = "SELECT * FROM puesto";
        List<PuestosModel> modelList = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var idPuesto = resultSet.getShort("id_puesto");
                    var puesto = resultSet.getString("puesto");
                    var idEstado = resultSet.getInt("id_estado");

                    var model = new PuestosModel(idPuesto, puesto, idEstado);
                    modelList.add(model);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return modelList;
    }

    public List<PuestosModel> getActive() {
        String query = "SELECT * FROM puesto WHERE id_estado = 1";
        List<PuestosModel> modelList = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var idPuesto = resultSet.getShort("id_puesto");
                    var puesto = resultSet.getString("puesto");
                    var idEstado = resultSet.getInt("id_estado");

                    var model = new PuestosModel(idPuesto, puesto, idEstado);
                    modelList.add(model);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return modelList;
    }

    public void put(PuestosModel model) {
        String query = "UPDATE puesto SET puesto = ?, id_estado = ? WHERE id_puesto = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, model.getPuesto());
            preparedStatement.setInt(2, model.getIdEstado());
            preparedStatement.setShort(3, model.getIdPuesto());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Puesto actualizado con éxito");
            } else {
                System.out.println("No se encontró el puesto para actualizar");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void post(PuestosModel model) {
        String query = "INSERT INTO puesto (puesto, id_estado) VALUES (?,?)";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, model.getPuesto());
            preparedStatement.setInt(2, model.getIdEstado());

            preparedStatement.executeUpdate();
            System.out.println("Puesto creado con éxito");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void delete(PuestosModel model) {
        String query = "DELETE FROM puesto WHERE id_puesto = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setShort(1, model.getIdPuesto());
            preparedStatement.executeUpdate();
            System.out.println("Puesto eliminado con éxito");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
