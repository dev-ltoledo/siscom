
package com.siscom.sis.Funcionalities.Proveedores.Servlets;

import com.siscom.sis.Funcionalities.Proveedores.Models.ProveedoresModel;
import com.siscom.sis.Funcionalities.Proveedores.Repositories.ProveedoresRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "proveedores", value = "/proveedores")
public class ProveedoresServlet extends HttpServlet {

    private ProveedoresRepository _proveedor;

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

        _proveedor = new ProveedoresRepository(connection);
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
        var listado = _proveedor.get();
        request.setAttribute("Listado", listado);
        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Catalogos/Proveedores/Proveedores.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String proveedor = request.getParameter("proveedor");
        String nit = request.getParameter("nit");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");

        ProveedoresModel model = new ProveedoresModel();
        model.setProveedor(proveedor);
        model.setNit(nit);
        model.setDireccion(direccion);
        model.setTelefono(telefono);
        model.setIdEstado(1);

        _proveedor.post(model);

        request.setAttribute("success", "Proveedor creado correctamente");
        request.getRequestDispatcher("/proveedores?action=list").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String proveedor = request.getParameter("proveedor");
        String nit = request.getParameter("nit");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        int idEstado = Integer.parseInt(request.getParameter("estado"));

        ProveedoresModel model = new ProveedoresModel();
        model.setIdProveedor(id);
        model.setProveedor(proveedor);
        model.setNit(nit);
        model.setDireccion(direccion);
        model.setTelefono(telefono);
        model.setIdEstado(idEstado);

        _proveedor.put(model);

        request.setAttribute("success", "Proveedor actualizado correctamente");
        request.getRequestDispatcher("/proveedores?action=list").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ProveedoresModel model = new ProveedoresModel();
        model.setIdProveedor(id);

        _proveedor.delete(model);

        request.setAttribute("success", "Proveedor eliminado correctamente");
        request.getRequestDispatcher("/proveedores?action=list").forward(request, response);
    }
}

