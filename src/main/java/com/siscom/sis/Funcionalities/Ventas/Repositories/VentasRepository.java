package com.siscom.sis.Funcionalities.Ventas.Repositories;

import com.siscom.sis.Funcionalities.Ventas.Models.VentasDetalleModel;
import com.siscom.sis.Funcionalities.Ventas.Models.VentasModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentasRepository {
    private Connection conexion;

    public VentasRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public List<VentasModel> get() {
        String query = "select id_venta, no_factura, serie, fecha_factura, c.id_cliente, c.nit, e.id_empleado, concat(e.nombre, ' ', e.apellido) empleado, v.fecha_ingreso from venta v inner join cliente c on v.id_cliente = c.id_cliente inner join empleado e on v.id_empleado = e.id_empleado order by id_venta desc;";
        List<VentasModel> modelList = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    var idVenta = resultSet.getInt("id_venta");
                    var noFactura = resultSet.getInt("no_factura");
                    var serie = resultSet.getString("serie").charAt(0);
                    var fechaFactura = resultSet.getDate("fecha_factura");
                    var idCliente = resultSet.getInt("id_cliente");
                    var nit = resultSet.getString("nit");
                    var idEmpleado = resultSet.getInt("id_empleado");
                    var empleado = resultSet.getString("empleado");
                    var fechaIngreso = resultSet.getTimestamp("fecha_ingreso").toLocalDateTime();

                    VentasModel model = new VentasModel(idVenta, noFactura, serie, fechaFactura, idCliente, nit, idEmpleado, empleado, fechaIngreso);
                    modelList.add(model);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return modelList;
    }

    public List<VentasDetalleModel> getDetailById(int id) {
        String query = "select p.producto, p.descripcion, vd.cantidad, vd.precio_unitario, vd.cantidad * vd.precio_unitario as total " +
                "from venta_detalle vd " +
                "inner join producto p on vd.id_producto = p.id_producto " +
                "where vd.id_venta = ?";
        List<VentasDetalleModel> detalleList = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, id); // Establece el parámetro de la consulta
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String producto = resultSet.getString("producto");
                    String descripcion = resultSet.getString("descripcion");
                    int cantidad = resultSet.getInt("cantidad");
                    double precioUnitario = resultSet.getDouble("precio_unitario");
                    double total = resultSet.getDouble("total");

                    VentasDetalleModel detalle = new VentasDetalleModel(producto, descripcion, cantidad, precioUnitario, total);
                    detalleList.add(detalle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return detalleList;
    }

    public int post(VentasModel model) {
        String query = "INSERT INTO venta (no_factura, serie, fecha_factura, id_cliente, id_empleado, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?)";
        int idVentaGenerado = -1;  // Inicializa con un valor de error, por si no se genera el ID
        try (var preparedStatement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, model.getNoFactura());
            preparedStatement.setString(2, model.getSerie().toString());
            preparedStatement.setDate(3, model.getFechaFactura());
            preparedStatement.setInt(4, model.getIdCliente());
            preparedStatement.setInt(5, model.getIdEmpleado());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(model.getFechaIngreso()));

            preparedStatement.executeUpdate();

            // Obtener el ID autogenerado
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idVentaGenerado = generatedKeys.getInt(1);
                    System.out.println("Venta creada con éxito. ID generado: " + idVentaGenerado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return idVentaGenerado;
    }

    public void postDetalle(int idVenta, String idProducto, String cantidad) {
        String query = """
            INSERT INTO venta_detalle (id_venta, id_producto, cantidad, precio_unitario)
            SELECT ?, ?, ?, precio_venta FROM producto WHERE id_producto = ?
            """;

        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, idVenta);
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
