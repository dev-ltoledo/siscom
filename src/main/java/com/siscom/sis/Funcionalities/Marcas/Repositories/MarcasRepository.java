package com.siscom.sis.Funcionalities.Marcas.Repositories;

import com.siscom.sis.Funcionalities.Marcas.Models.MarcasModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcasRepository {
    private Connection conexion;

    public MarcasRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public List<MarcasModel> get() {
        String query = "SELECT * FROM marca";
        List<MarcasModel> marcas = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    var IdMarca = resultSet.getInt("id_marca");
                    var Marca = resultSet.getString("marca");
                    var IdEstado = resultSet.getInt("id_estado");

                    var marca = new MarcasModel(IdMarca, Marca, IdEstado);
                    marcas.add(marca);
                }
            }
        }catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return marcas;
    }

    public void put(MarcasModel marcas) {
        String query = "UPDATE marca SET marca = ?, id_estado = ? WHERE id_marca = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, marcas.getMarca());
            preparedStatement.setInt(2, marcas.getIdEstado());
            preparedStatement.setInt(3, marcas.getIdMarca());
            preparedStatement.executeUpdate();
            System.out.println("Marca actualizada con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void post(MarcasModel marcas) {
        String query = "INSERT INTO marca (marca, id_estado) VALUES (?, ?)";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, marcas.getMarca());
            preparedStatement.setInt(2, marcas.getIdEstado());
            preparedStatement.executeUpdate();
            System.out.println("Marca creada con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void delete(MarcasModel marcas) {
        String query = "DELETE FROM marca WHERE id_marca = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, marcas.getIdMarca());
            preparedStatement.executeUpdate();
            System.out.println("Marca eliminada con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
