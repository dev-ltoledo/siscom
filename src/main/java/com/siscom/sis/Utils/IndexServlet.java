package com.siscom.sis.Utils;

import com.siscom.sis.Funcionalities.Autenticacion.Models.MenuModel;
import com.siscom.sis.Funcionalities.Autenticacion.Repositories.AutenticacionRepository;
import com.siscom.sis.dbcontext.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "Index", value = "/Inicio")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Conexion conexion = new Conexion();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        session.setAttribute("conexion", conexion.obtenerConexion());

        var usuario = session.getAttribute("usuario");

        if (usuario == null) {
            request.getRequestDispatcher("/Component/Login/Login.jsp").forward(request, response);
        } else {

            AutenticacionRepository menuRepository = new AutenticacionRepository(conexion.obtenerConexion());
            List<MenuModel> menuList = menuRepository.getMenuItems();

            request.setAttribute("menuList", session.getAttribute("menuList"));
            request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Principal/Inicio.jsp").forward(request, response);
        }
    }
}
