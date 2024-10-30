package com.siscom.sis.Funcionalities.Marcas.Servlets;

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

@WebServlet(name = "marcas", value = "/marcas")
public class MarcasServlet extends HttpServlet {

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
        var listado = _marca.get();
        request.setAttribute("ListadoMarcas", listado);
        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Catalogos/Productos/Marcas.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String marca = request.getParameter("marca");
        MarcasModel model = new MarcasModel(0, marca, 1);

        _marca.post(model);

        request.setAttribute("success", "marcas creada correctamente");
        request.getRequestDispatcher("/marcas?action=list").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String marca = request.getParameter("marca");
        int idEstado = Integer.parseInt(request.getParameter("estado"));

        MarcasModel model = new MarcasModel(id, marca, idEstado);

        _marca.put(model);

        request.setAttribute("success", "marca actualizada correctamente");
        request.getRequestDispatcher("/marcas?action=list").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        MarcasModel model = new MarcasModel(id, "", 0);

        _marca.delete(model);

        request.setAttribute("success", "marca eliminada correctamente");
        request.getRequestDispatcher("/marcas?action=list").forward(request, response);
    }
}
