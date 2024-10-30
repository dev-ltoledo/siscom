package com.siscom.sis.Funcionalities.Empleados.Servlets;

import com.siscom.sis.Funcionalities.Empleados.Models.EmpleadosModel;
import com.siscom.sis.Funcionalities.Empleados.Repositories.EmpleadoRepository;
import com.siscom.sis.Funcionalities.Puestos.Repositories.PuestosRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "empleados", value = "/empleados")
public class EmpleadosServlet extends HttpServlet {

    private EmpleadoRepository _empleado;
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

        _empleado = new EmpleadoRepository(connection);
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
        var listado = _empleado.get();
        var puesto = _puesto.getActive();

        request.setAttribute("Puesto", puesto);
        request.setAttribute("Listado", listado);
        request.getRequestDispatcher("Component/Layout/MainLayout.jsp?page=/Component/Catalogos/Empleados/Empleados.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String dpi = request.getParameter("dpi");
        boolean genero = Boolean.parseBoolean(request.getParameter("genero"));
        LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento")); // Asumiendo formato 'YYYY-MM-DD'
        short idPuesto = Short.parseShort(request.getParameter("idPuesto"));
        LocalDate fechaInicioLabor = LocalDate.parse(request.getParameter("fechaInicioLabor")); // Asumiendo formato 'YYYY-MM-DD'
        LocalDateTime fechaIngreso = LocalDateTime.now();

        EmpleadosModel model = new EmpleadosModel();
        model.setNombre(nombre);
        model.setApellido(apellido);
        model.setDireccion(direccion);
        model.setTelefono(telefono);
        model.setDpi(dpi);
        model.setGenero(genero);
        model.setFechaNacimiento(fechaNacimiento);
        model.setIdPuesto(idPuesto);
        model.setFechaInicioLabor(fechaInicioLabor);
        model.setFechaIngreso(fechaIngreso);

        _empleado.post(model);

        request.setAttribute("success", "Registro creado correctamente");
        request.getRequestDispatcher("/empleados?action=list").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String dpi = request.getParameter("dpi");
        boolean genero = Boolean.parseBoolean(request.getParameter("genero"));
        LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento")); // Asumiendo formato 'YYYY-MM-DD'
        short idPuesto = Short.parseShort(request.getParameter("idPuesto"));
        LocalDate fechaInicioLabor = LocalDate.parse(request.getParameter("fechaInicioLabor")); // Asumiendo formato 'YYYY-MM-DD'

        EmpleadosModel model = new EmpleadosModel();
        model.setIdEmpleado(id);
        model.setNombre(nombre);
        model.setApellido(apellido);
        model.setDireccion(direccion);
        model.setTelefono(telefono);
        model.setDpi(dpi);
        model.setGenero(genero);
        model.setFechaNacimiento(fechaNacimiento);
        model.setIdPuesto(idPuesto);
        model.setFechaInicioLabor(fechaInicioLabor);

        _empleado.put(model);

        request.setAttribute("success", "Registro actualizado correctamente");
        request.getRequestDispatcher("/empleados?action=list").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        EmpleadosModel model = new EmpleadosModel();
        model.setIdEmpleado(id);

        _empleado.delete(model);

        request.setAttribute("success", "Registro eliminado correctamente");
        request.getRequestDispatcher("/empleados?action=list").forward(request, response);
    }
}
