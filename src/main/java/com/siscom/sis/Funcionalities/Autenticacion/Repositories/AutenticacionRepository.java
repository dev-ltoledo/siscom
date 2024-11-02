package com.siscom.sis.Funcionalities.Autenticacion.Repositories;

import com.siscom.sis.Funcionalities.Autenticacion.Models.MenuModel;
import com.siscom.sis.Funcionalities.Empleados.Models.EmpleadosModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutenticacionRepository {
    private Connection conexion;

    public AutenticacionRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean AuthUsuario(String usuario, String password) {
        String query = "SELECT COUNT(*) AS user_count FROM usuario WHERE usuario = ? AND password = MD5(?)";

        try (var preparedStatement = conexion.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userCount = resultSet.getInt("user_count");
                    return userCount > 0; // Retorna true si hay al menos un usuario encontrado
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false; // Retorna false si ocurre un error o no se encuentra el usuario
    }

    public List<MenuModel> getMenuItems() {
        String query = "SELECT id_menu, menu_text, menu_link, parent_id, menu_order, is_header, icono, id_estado FROM menu ORDER BY menu_order";

        List<MenuModel> menuItems = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var idMenu = resultSet.getInt("id_menu");
                    var menuText = resultSet.getString("menu_text");
                    var menuLink = resultSet.getString("menu_link");
                    var parentId = resultSet.getObject("parent_id", Integer.class); // Puede ser null
                    var menuOrder = resultSet.getInt("menu_order");
                    var isHeader = resultSet.getBoolean("is_header");
                    var icono = resultSet.getString("icono");
                    var idEstado = resultSet.getInt("id_estado");

                    var menuItem = new MenuModel(idMenu, menuText, menuLink, icono, isHeader, parentId, menuOrder, idEstado);
                    menuItems.add(menuItem);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return menuItems;
    }


}
