package com.siscom.sis.Funcionalities.Compras.Repositories;

import com.siscom.sis.Funcionalities.Compras.Models.ComprasDetalleModel;
import com.siscom.sis.Funcionalities.Compras.Models.ComprasModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComprasRepository {
    private Connection conexion;

    public ComprasRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public List<ComprasModel> get() {
        String query = "select id_compra, no_orden_compra, p.id_proveedor, p.proveedor, fecha_orden, fecha_ingreso from compra c inner join proveedor p on c.id_proveedor = p.id_proveedor;";
        List<ComprasModel> modelList = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idCompra = resultSet.getInt("id_compra");
                    Integer noOrdenCompra = resultSet.getInt("no_orden_compra");
                    int idProveedor = resultSet.getInt("id_proveedor");
                    String proveedor = resultSet.getString("proveedor");
                    Date fechaOrden = resultSet.getDate("fecha_orden");
                    Timestamp fechaIngreso = resultSet.getTimestamp("fecha_ingreso");

                    ComprasModel model = new ComprasModel(idCompra, noOrdenCompra, idProveedor, proveedor, fechaOrden, fechaIngreso);
                    modelList.add(model);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return modelList;
    }

    public List<ComprasDetalleModel> getDetailById(int id) {
        String query = "select p.producto, p.descripcion, cd.cantidad, cd.precio_costo_unitario, cd.cantidad * cd.precio_costo_unitario as total " +
                "from compra_detalle cd " +
                "inner join producto p on cd.id_producto = p.id_producto " +
                "where cd.id_compra = ?";
        List<ComprasDetalleModel> detalleList = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, id); // Establece el parámetro de la consulta
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String producto = resultSet.getString("producto");
                    String descripcion = resultSet.getString("descripcion");
                    int cantidad = resultSet.getInt("cantidad");
                    double precioCostoUnitario = resultSet.getDouble("precio_costo_unitario");
                    double total = resultSet.getDouble("total");

                    ComprasDetalleModel detalle = new ComprasDetalleModel(producto, descripcion, cantidad, precioCostoUnitario, total);
                    detalleList.add(detalle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return detalleList;
    }

    public int post(ComprasModel model) {
        String query = "INSERT INTO compra (no_orden_compra, id_proveedor, fecha_orden, fecha_ingreso) VALUES (?, ?, ?, ?)";
        int idCompraGenerado = -1;
        try (var preparedStatement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, model.getNoOrdenCompra());
            preparedStatement.setInt(2, model.getIdProveedor());
            preparedStatement.setDate(3, model.getFechaOrden());
            preparedStatement.setTimestamp(4, model.getFechaIngreso());

            preparedStatement.executeUpdate();

            // Obtener el ID autogenerado
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idCompraGenerado = generatedKeys.getInt(1);
                    System.out.println("Compra creada con éxito. ID generado: " + idCompraGenerado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return idCompraGenerado;
    }

    public void postDetalle(int idCompra, String idProducto, String cantidad) {
        String query = """
            INSERT INTO compra_detalle (id_compra, id_producto, cantidad, precio_costo_unitario)
            SELECT ?, ?, ?, precio_costo FROM producto WHERE id_producto = ?
            """;

        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, idCompra);
            preparedStatement.setString(2, idProducto);
            preparedStatement.setString(3, cantidad);
            preparedStatement.setString(4, idProducto);

            preparedStatement.executeUpdate();
            System.out.println("Detalle de venta insertado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al insertar detalle de venta: " + e.getMessage());
        }
    }

}
