package com.siscom.sis.Funcionalities.Productos.Servlets;

import com.siscom.sis.Funcionalities.Marcas.Repositories.MarcasRepository;
import com.siscom.sis.Funcionalities.Productos.Models.ProductosModel;
import com.siscom.sis.Funcionalities.Productos.Repositories.ProductosRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;

@WebServlet(name = "productos", value = "/productos")
public class ProductosServlet extends HttpServlet {

    private ProductosRepository _producto;
    private MarcasRepository _marca;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection connection = (Connection) session.getAttribute("conexion");

        _producto = new ProductosRepository(connection);
        _marca = new MarcasRepository(connection);

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                create(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                read(request, response);
                break;
        }
    }

    private void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var listado = _producto.get();
        var listadoMarca = _marca.getAcctive();

        request.setAttribute("Marca", listadoMarca);
        request.setAttribute("Listado", listado);
        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Catalogos/Productos/Productos.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String producto = request.getParameter("producto");
        short idMarca = Short.parseShort(request.getParameter("idMarca"));
        String descripcion = request.getParameter("descripcion");
        String imagen = request.getParameter("imagen");
        BigDecimal precioCosto = new BigDecimal(request.getParameter("precioCosto"));
        BigDecimal precioVenta = new BigDecimal(request.getParameter("precioVenta"));
        int existencia = Integer.parseInt(request.getParameter("existencia"));
        LocalDateTime fechaIngreso = LocalDateTime.now();

        ProductosModel model = new ProductosModel();
        model.setProducto(producto);
        model.setIdMarca(idMarca);
        model.setDescripcion(descripcion);
        model.setImagen(imagen);
        model.setPrecioCosto(precioCosto);
        model.setPrecioVenta(precioVenta);
        model.setExistencia(existencia);
        model.setFechaIngreso(fechaIngreso);

        _producto.post(model);

        request.setAttribute("success", "Producto creado correctamente");
        request.getRequestDispatcher("/productos?action=list").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String producto = request.getParameter("producto");
        short idMarca = Short.parseShort(request.getParameter("idMarca"));
        String descripcion = request.getParameter("descripcion");
        BigDecimal precioCosto = new BigDecimal(request.getParameter("precioCosto"));
        BigDecimal precioVenta = new BigDecimal(request.getParameter("precioVenta"));
        int existencia = Integer.parseInt(request.getParameter("existencia"));

        ProductosModel model = new ProductosModel();
        model.setIdProducto(id);
        model.setProducto(producto);
        model.setIdMarca(idMarca);
        model.setDescripcion(descripcion);
        model.setPrecioCosto(precioCosto);
        model.setPrecioVenta(precioVenta);
        model.setExistencia(existencia);

        _producto.put(model);

        request.setAttribute("success", "Producto actualizado correctamente");
        request.getRequestDispatcher("/productos?action=list").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ProductosModel model = new ProductosModel();
        model.setIdProducto(id);

        _producto.delete(model);

        request.setAttribute("success", "Producto eliminado correctamente");
        request.getRequestDispatcher("/productos?action=list").forward(request, response);
    }
}
