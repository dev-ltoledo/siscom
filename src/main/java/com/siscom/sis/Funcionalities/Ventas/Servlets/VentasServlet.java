package com.siscom.sis.Funcionalities.Ventas.Servlets;

import com.siscom.sis.Funcionalities.Clientes.Repositories.ClientesRepository;
import com.siscom.sis.Funcionalities.Empleados.Repositories.EmpleadoRepository;
import com.siscom.sis.Funcionalities.Productos.Repositories.ProductosRepository;
import com.siscom.sis.Funcionalities.Ventas.Models.VentasDetalleModel;
import com.siscom.sis.Funcionalities.Ventas.Models.VentasModel;
import com.siscom.sis.Funcionalities.Ventas.Repositories.VentasRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ventas", value = "/ventas")
public class VentasServlet extends HttpServlet {

    private VentasRepository ventasRepository;
    private EmpleadoRepository empleadoRepository;
    private ClientesRepository clientesRepository;
    private ProductosRepository productosRepository;

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

        ventasRepository = new VentasRepository(connection);
        empleadoRepository = new EmpleadoRepository(connection);
        clientesRepository = new ClientesRepository(connection);
        productosRepository = new ProductosRepository(connection);

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
        var listado = ventasRepository.get();
        var listadoEmpleados = empleadoRepository.get();
        var listadoClientes = clientesRepository.get();
        var listadoProductos = productosRepository.get();

        request.setAttribute("Listado", listado);
        request.setAttribute("Empleados", listadoEmpleados);
        request.setAttribute("Clientes", listadoClientes);
        request.setAttribute("Productos", listadoProductos);

        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/CompraVenta/Ventas.jsp").forward(request, response);
    }

    private void getDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idVenta = Integer.parseInt(request.getParameter("idVenta")); // Obtener el ID de la solicitud
        List<VentasDetalleModel> detallesVenta = ventasRepository.getDetailById(idVenta); // Obtener los detalles de la venta

        // Genera el HTML solo para los detalles de la venta solicitada
        StringBuilder htmlResponse = new StringBuilder();

        if (detallesVenta != null && !detallesVenta.isEmpty()) {
            for (VentasDetalleModel detalle : detallesVenta) {
                htmlResponse.append("<tr>")
                        .append("<td>").append(detalle.getProducto()).append("</td>")
                        .append("<td>").append(detalle.getDescripcion()).append("</td>")
                        .append("<td>").append(detalle.getCantidad()).append("</td>")
                        .append("<td>").append(detalle.getPrecioUnitario()).append("</td>")
                        .append("<td>").append(detalle.getTotal()).append("</td>")
                        .append("</tr>");
            }
        } else {
            htmlResponse.append("<tr><td colspan='6'>No se encontraron detalles para esta venta.</td></tr>");
        }

        // Configura el tipo de respuesta como HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(htmlResponse.toString());
        out.close();
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String noFactura = request.getParameter("factura");
        char serie = request.getParameter("serie").charAt(0);
        LocalDate fechaFactura = LocalDate.parse(request.getParameter("fechaFactura")); // Formato 'YYYY-MM-DD'
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        LocalDateTime fechaIngreso = LocalDateTime.now();

        VentasModel model = new VentasModel();

        model.setNoFactura(Integer.valueOf(noFactura));
        model.setSerie(serie);
        model.setFechaFactura(Date.valueOf(fechaFactura));
        model.setIdCliente(idCliente);
        model.setIdEmpleado(idEmpleado);
        model.setFechaIngreso(fechaIngreso);

        int idVenta = ventasRepository.post(model);

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

                ventasRepository.postDetalle(idVenta, idProducto, cantidad);
                productosRepository.putCantidad(idProducto, cantidad);
            }
        } else {
            System.out.println("No se recibieron productos en el carrito.");
        }

        request.setAttribute("success", "Venta registrada correctamente");
        request.getRequestDispatcher("/ventas?action=list").forward(request, response);
    }
}
