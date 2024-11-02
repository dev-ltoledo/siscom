package com.siscom.sis.Utils;

import com.siscom.sis.Funcionalities.Autenticacion.Models.MenuModel;
import com.siscom.sis.Funcionalities.Autenticacion.Repositories.AutenticacionRepository;
import com.siscom.sis.Funcionalities.Compras.Repositories.ComprasRepository;
import com.siscom.sis.Funcionalities.Empleados.Repositories.EmpleadoRepository;
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
import java.util.List;

@WebServlet(name = "Login", value = "/Login")
public class LoginServlet extends HttpServlet {
    private AutenticacionRepository _autenticacionRepository;

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
        Connection connection = (Connection) session.getAttribute("conexion");

        _autenticacionRepository = new AutenticacionRepository(connection);
        var auth = _autenticacionRepository.AuthUsuario(usuario, password);

        if (auth) {
            List<MenuModel> menuList = _autenticacionRepository.getMenuItems();

            session.setMaxInactiveInterval(3600);
            session.setAttribute("usuario", usuario);
            session.setAttribute("menuList", menuList);

            request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Principal/Inicio.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Credenciales incorrectas");
            request.getRequestDispatcher("/Component/Login/Login.jsp").forward(request, response);
        }
    }
}