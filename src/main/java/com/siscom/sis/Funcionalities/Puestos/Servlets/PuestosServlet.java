package com.siscom.sis.Funcionalities.Puestos.Servlets;

import com.siscom.sis.Funcionalities.Puestos.Models.PuestosModel;
import com.siscom.sis.Funcionalities.Puestos.Repositories.PuestosRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "puestos", value = "/puestos")
public class PuestosServlet extends HttpServlet {

    private PuestosRepository _puesto;

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

        _puesto = new PuestosRepository(connection);
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
        var listado = _puesto.get();
        request.setAttribute("Listado", listado);
        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Catalogos/Empleados/Puestos.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String puesto = request.getParameter("puesto");

        PuestosModel model = new PuestosModel();
        model.setPuesto(puesto);
        model.setIdEstado(1);

        _puesto.post(model);

        request.setAttribute("success", "Puesto creado correctamente");
        request.getRequestDispatcher("/puestos?action=list").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        short id = Short.parseShort(request.getParameter("id"));
        String puesto = request.getParameter("puesto");
        int idEstado = Integer.parseInt(request.getParameter("estado"));

        PuestosModel model = new PuestosModel();
        model.setIdPuesto(id);
        model.setPuesto(puesto);
        model.setIdEstado(idEstado);

        _puesto.put(model);

        request.setAttribute("success", "Puesto actualizado correctamente");
        request.getRequestDispatcher("/puestos?action=list").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        short id = Short.parseShort(request.getParameter("id"));

        PuestosModel model = new PuestosModel();
        model.setIdPuesto(id);

        _puesto.delete(model);

        request.setAttribute("success", "Puesto eliminado correctamente");
        request.getRequestDispatcher("/puestos?action=list").forward(request, response);
    }
}
