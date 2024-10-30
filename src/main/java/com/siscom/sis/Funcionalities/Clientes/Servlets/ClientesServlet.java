package com.siscom.sis.Funcionalities.Clientes.Servlets;

import com.siscom.sis.Funcionalities.Clientes.Models.ClientesModel;
import com.siscom.sis.Funcionalities.Clientes.Repositories.ClientesRepository;
import com.siscom.sis.Funcionalities.Marcas.Models.MarcasModel;
import com.siscom.sis.Funcionalities.Marcas.Repositories.MarcasRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;

@WebServlet(name = "clientes", value = "/clientes")
public class ClientesServlet extends HttpServlet {

    private ClientesRepository _cliente;
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

        _cliente = new ClientesRepository(connection);
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
        var listado = _cliente.get();
        request.setAttribute("Listado", listado);
        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Catalogos/Empleados/Clientes.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nit = request.getParameter("nit");
        boolean genero = Boolean.parseBoolean(request.getParameter("genero"));
        String telefono = request.getParameter("telefono");
        String correoElectronico = request.getParameter("correoElectronico");
        LocalDateTime fechaIngreso = LocalDateTime.now();

        ClientesModel model = new ClientesModel();

        model.setNombre(nombre);
        model.setApellido(apellido);
        model.setNit(nit);
        model.setGenero(genero);
        model.setTelefono(telefono);
        model.setCorreoElectronico(correoElectronico);
        model.setFechaIngreso(fechaIngreso);

        _cliente.post(model);

        request.setAttribute("success", "Registro creado correctamente");
        request.getRequestDispatcher("/clientes?action=list").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nit = request.getParameter("nit");
        boolean genero = Boolean.parseBoolean(request.getParameter("genero"));
        String telefono = request.getParameter("telefono");
        String correoElectronico = request.getParameter("correoElectronico");

        ClientesModel model = new ClientesModel();

        model.setIdCliente(id);
        model.setIdCliente(id);
        model.setNombre(nombre);
        model.setApellido(apellido);
        model.setNit(nit);
        model.setGenero(genero);
        model.setTelefono(telefono);
        model.setCorreoElectronico(correoElectronico);

        _cliente.put(model);

        request.setAttribute("success", "Registro actualizado correctamente");
        request.getRequestDispatcher("/clientes?action=list").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ClientesModel model = new ClientesModel();
        model.setIdCliente(id);

        _cliente.delete(model);

        request.setAttribute("success", "Registro eliminado correctamente");
        request.getRequestDispatcher("/clientes?action=list").forward(request, response);
    }
}
