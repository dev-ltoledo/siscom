package com.siscom.sis.dbcontext;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conexion = null;
    private final String url = "jdbc:mysql://localhost:3306/siscom";
    private final String user = "apsiscom";
    private final String password = "Temp0ral1,";

    public Connection obtenerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");
            return conexion;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de MySQL: " + e);
        }
        return null;
    }

    public void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión");
                e.printStackTrace();
            }
        }
    }
}
