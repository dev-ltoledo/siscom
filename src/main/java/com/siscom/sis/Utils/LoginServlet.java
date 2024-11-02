package com.siscom.sis.Utils;

import com.siscom.sis.Funcionalities.Compras.Repositories.ComprasRepository;
import com.siscom.sis.Funcionalities.Productos.Repositories.ProductosRepository;
import com.siscom.sis.Funcionalities.Proveedores.Repositories.ProveedoresRepository;
import com.siscom.sis.dbcontext.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "Login", value = "/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Conexion conexion = new Conexion();

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        session.setAttribute("conexion", conexion.obtenerConexion());

        request.getRequestDispatcher("/Component/Login/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        session.setAttribute("usuario", usuario);

        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Principal/Inicio.jsp").forward(request, response);
    }
}