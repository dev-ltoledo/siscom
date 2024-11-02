package com.siscom.sis.Funcionalities.Compras.Servlets;

import com.siscom.sis.Funcionalities.Compras.Models.ComprasDetalleModel;
import com.siscom.sis.Funcionalities.Compras.Models.ComprasModel;
import com.siscom.sis.Funcionalities.Compras.Repositories.ComprasRepository;
import com.siscom.sis.Funcionalities.Productos.Repositories.ProductosRepository;
import com.siscom.sis.Funcionalities.Proveedores.Repositories.ProveedoresRepository;
import com.siscom.sis.Funcionalities.Ventas.Models.VentasDetalleModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "compras", value = "/compras")
public class ComprasServlet extends HttpServlet {

    private ComprasRepository comprasRepository;
    private ProductosRepository productosRepository;
    private ProveedoresRepository proveedoresRepository;

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

        comprasRepository = new ComprasRepository(connection);
        productosRepository = new ProductosRepository(connection);
        proveedoresRepository = new ProveedoresRepository(connection);

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                create(request, response);
                break;
            case "getData":
                getDetail(request, response);
                break;
            default:
                read(request, response);
                break;
        }
    }

    private void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var listado = comprasRepository.get();
        var listadoProveedores = proveedoresRepository.getActive();
        var listadoProductos = productosRepository.getActive();

        request.setAttribute("Listado", listado);
        request.setAttribute("Proveedores", listadoProveedores);
        request.setAttribute("Productos", listadoProductos);

        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/CompraVenta/Compras.jsp").forward(request, response);
    }

    private void getDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCompra = Integer.parseInt(request.getParameter("idCompra"));
        List<ComprasDetalleModel> detallesCompra = comprasRepository.getDetailById(idCompra);

        StringBuilder htmlResponse = new StringBuilder();

        if (detallesCompra != null && !detallesCompra.isEmpty()) {
            for (ComprasDetalleModel detalle : detallesCompra) {
                htmlResponse.append("<tr>")
                        .append("<td>").append(detalle.getProducto()).append("</td>")
                        .append("<td>").append(detalle.getDescripcion()).append("</td>")
                        .append("<td>").append(detalle.getCantidad()).append("</td>")
                        .append("<td>Q").append(detalle.getPrecioCostoUnitario()).append("</td>")
                        .append("<td>Q").append(detalle.getTotal()).append("</td>")
                        .append("</tr>");
            }
        } else {
            htmlResponse.append("<tr><td colspan='6'>No se encontraron detalles para esta compra.</td></tr>");
        }

        // Configura el tipo de respuesta como HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(htmlResponse.toString());
        out.close();
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer noOrdenCompra = Integer.parseInt(request.getParameter("noOrdenCompra"));
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        LocalDate fechaOrden = LocalDate.parse(request.getParameter("fechaOrden")); // Formato 'YYYY-MM-DD'
        Timestamp fechaIngreso = Timestamp.valueOf(LocalDateTime.now());

        ComprasModel model = new ComprasModel();
        model.setNoOrdenCompra(noOrdenCompra);
        model.setIdProveedor(idProveedor);
        model.setFechaOrden(Date.valueOf(fechaOrden));
        model.setFechaIngreso(fechaIngreso);

        int idCompra = comprasRepository.post(model);

        String[] productos = request.getParameterValues("idProducto");
        String[] cantidades = request.getParameterValues("cantidad");

        List<String> cantidadesFiltradas = Arrays.stream(cantidades)
                .filter(cantidad -> cantidad != null && !cantidad.trim().isEmpty())
                .collect(Collectors.toList());

        String[] cantidadesFinales = cantidadesFiltradas.toArray(new String[0]);

        if (productos != null && cantidadesFinales != null) {
            for (int i = 0; i < productos.length; i++) {
                String idProducto = productos[i];
                String cantidad = cantidadesFinales[i];

                comprasRepository.postDetalle(idCompra, idProducto, cantidad);
                productosRepository.putCantidadPercent(idProducto, cantidad);
            }
        } else {
            System.out.println("No se recibieron productos en el carrito.");
        }

        request.setAttribute("success", "Compra registrada correctamente");
        request.getRequestDispatcher("/compras?action=list").forward(request, response);
    }
}
