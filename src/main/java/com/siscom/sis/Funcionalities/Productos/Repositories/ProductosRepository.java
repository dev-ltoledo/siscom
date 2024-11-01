package com.siscom.sis.Funcionalities.Productos.Repositories;

import com.siscom.sis.Funcionalities.Productos.Models.ProductosModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosRepository {
    private Connection conexion;

    public ProductosRepository(Connection conexion) {
        this.conexion = conexion;
    }

    public List<ProductosModel> get() {
        String query = "SELECT id_producto, producto, m.id_marca, m.marca, descripcion, imagen, precio_costo, precio_venta, existencia, fecha_ingreso, p.id_estado FROM producto p inner join marca m on p.id_marca = m.id_marca";
        List<ProductosModel> productos = new ArrayList<>();

        try (var preparedStatement = conexion.prepareStatement(query)) {

            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    var idProducto = resultSet.getInt("id_producto");
                    var producto = resultSet.getString("producto");
                    var idMarca = resultSet.getShort("id_marca");
                    var marca = resultSet.getString("marca");
                    var descripcion = resultSet.getString("descripcion");
                    var imagen = resultSet.getString("imagen");
                    var precioCosto = resultSet.getBigDecimal("precio_costo");
                    var precioVenta = resultSet.getBigDecimal("precio_venta");
                    var existencia = resultSet.getInt("existencia");
                    var fechaIngreso = resultSet.getTimestamp("fecha_ingreso").toLocalDateTime();
                    var idEstado = resultSet.getInt("id_estado");

                    var productoModel = new ProductosModel(idProducto, producto, idMarca, marca, descripcion, imagen, precioCosto, precioVenta, existencia, fechaIngreso, idEstado);
                    productos.add(productoModel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return productos;
    }

    public void put(ProductosModel producto) {
        String query = "UPDATE producto SET producto = ?, id_marca = ?, descripcion = ?, precio_costo = ?, precio_venta = ?, existencia = ? WHERE id_producto = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, producto.getProducto());
            preparedStatement.setShort(2, producto.getIdMarca());
            preparedStatement.setString(3, producto.getDescripcion());
            preparedStatement.setBigDecimal(4, producto.getPrecioCosto());
            preparedStatement.setBigDecimal(5, producto.getPrecioVenta());
            preparedStatement.setInt(6, producto.getExistencia());
            preparedStatement.setInt(7, producto.getIdProducto());
            preparedStatement.executeUpdate();
            System.out.println("Producto actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void post(ProductosModel producto) {
        String query = "INSERT INTO producto (producto, id_marca, descripcion, imagen, precio_costo, precio_venta, existencia, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, producto.getProducto());
            preparedStatement.setShort(2, producto.getIdMarca());
            preparedStatement.setString(3, producto.getDescripcion());
            preparedStatement.setString(4, producto.getImagen());
            preparedStatement.setBigDecimal(5, producto.getPrecioCosto());
            preparedStatement.setBigDecimal(6, producto.getPrecioVenta());
            preparedStatement.setInt(7, producto.getExistencia());
            preparedStatement.setObject(8, producto.getFechaIngreso());
            preparedStatement.executeUpdate();
            System.out.println("Producto creado con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void delete(ProductosModel producto) {
        String query = "DELETE FROM producto WHERE id_producto = ?";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, producto.getIdProducto());
            preparedStatement.executeUpdate();
            System.out.println("Producto eliminado con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}

