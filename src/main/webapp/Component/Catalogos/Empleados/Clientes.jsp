<%-- 
    Document   : Productos
    Created on : 29/09/2024, 12:54:39 a. m.
    Author     : ltoledo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-wrapper">

    <div class="container-xxl flex-grow-1 container-p-y">

        <div class="card">
            <div class="mt-5 mx-5">
                <div class="app-producto">
                    <div class="d-flex flex-column flex-md-row justify-content-between align-items-start align-items-md-center mb-6 row-gap-4">
                        <div class="d-flex flex-column justify-content-center">
                            <h4 class="mb-1">Catálogo de clientes</h4>
                            <p class="mb-0">Listado de clientes siscom</p>
                        </div>
                        <div class="d-flex align-content-center flex-wrap gap-4">
                            <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#create">
                                Nuevo&nbsp;<i class='bx bx-add-to-queue bx-rotate-90' ></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-responsive text-nowrap">
                <c:if test="${!empty(error)}">
                    <div class="row">
                        <div class="col-12">
                            <div class="alert alert-success alert-dismissible">
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                    ${error}
                            </div>
                        </div>
                    </div>
                </c:if>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>                            
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>NIT</th>
                            <th>Télefono</th>
                            <th>Correo electronico</th>
                            <th>Fecha de ingreso</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">

                    <c:choose>
                        <c:when test="${!empty(Listado)}">
                            <c:forEach var="List" items="${Listado}">
                                <tr>
                                    <td>${List.idCliente}</td>
                                    <td>${List.nombre}</td>
                                    <td>${List.apellido}</td>
                                    <td>${List.nit}</td>
                                    <td style="display: none">${List.genero ? 'Masculino' : 'Femenino'}</td>
                                    <td>${List.telefono}</td>
                                    <td>${List.correoElectronico}</td>
                                    <td>${List.fechaIngreso}</td>

                                    <td>
                                        <div class="dropdown">
                                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                                <i class="bx bx-dots-vertical-rounded"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#update" onclick="setSelectedIndex(this.closest('tr'))">
                                                    <i class="bx bx-edit-alt me-1"></i> Editar
                                                </a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/clientes?action=delete&id=${List.idCliente}">
                                                    <i class="bx bx-trash me-1"></i> Eliminar
                                                </a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="9">No hay información para mostrar</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="modal fade" id="create" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <form id="form-clientes-create" action="${pageContext.request.contextPath}/clientes?action=create" method="POST">
                    <div class="modal-header">
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 col-lg-12">
                                <div>
                                    <div class="card-header">
                                        <h5 class="card-tile mb-0">Información del cliente:</h5>
                                    </div>
                                    <div class="card-body mt-3">
                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Nombre:</label>
                                                <input type="text" class="form-control" name="nombre" placeholder="Ingrese el nombre" required>
                                            </div>

                                            <div class="mb-3 col-6">
                                                <label class="form-label">Apellido:</label>
                                                <input type="text" class="form-control" name="apellido" placeholder="Ingrese el apellido" required>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">NIT:</label>
                                                <input type="text" class="form-control" name="nit" placeholder="Ingrese el NIT" required>
                                            </div>
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Género</label>
                                                <div class="input-group" >
                                                    <select class="form-select" name="genero" required>
                                                        <option value="True">Masculino</option>
                                                        <option value="False">Femenino</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Télefono:</label>
                                                <input type="text" class="form-control" name="telefono" placeholder="Ingrese el telefono" required>
                                            </div>

                                            <div class="mb-3 col-6">
                                                <label class="form-label">Correo electronico:</label>
                                                <input type="text" class="form-control" name="correoElectronico" placeholder="Ingrese el correo electronico" required>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" style="margin-right: 1rem;">
                            Cancelar
                        </button>
                        <button type="submit" class="btn btn-primary">
                            Crear
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="update" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog  modal-xl" role="document">
            <div class="modal-content">
                <form id="form-clientes-update" action="${pageContext.request.contextPath}/clientes?action=update" method="POST">
                    <div class="modal-header">
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 col-lg-12">
                                <div>
                                    <div class="card-header">
                                        <h5 class="card-tile mb-0">Información del cliente:</h5>
                                    </div>
                                    <div class="card-body mt-3">
                                        <div style="display: none">
                                            <label class="form-label">Id</label>
                                            <input type="text" class="form-control" id="id" name="id" readonly />
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Nombre:</label>
                                                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" required>
                                            </div>

                                            <div class="mb-3 col-6">
                                                <label class="form-label">Apellido:</label>
                                                <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingrese el apellido" required>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">NIT:</label>
                                                <input type="text" class="form-control" id="nit" name="nit" placeholder="Ingrese el NIT" required>
                                            </div>
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Género</label>
                                                <div class="input-group">
                                                    <select class="form-select" id="genero" name="genero" required>
                                                        <option value="True">Masculino</option>
                                                        <option value="False">Femenino</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Télefono:</label>
                                                <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Ingrese el telefono" required>
                                            </div>

                                            <div class="mb-3 col-6">
                                                <label class="form-label">Correo electronico:</label>
                                                <input type="text" class="form-control" id="correoElectronico" name="correoElectronico" placeholder="Ingrese el correo electronico" required>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-12">
                                                <label class="form-label">Fecha Ingreso:</label>
                                                <input type="text" class="form-control" id="fechaIngreso" name="fechaIngreso" readonly>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" style="margin-right: 1rem;">
                            Cancelar
                        </button>
                        <button type="submit" class="btn btn-primary">
                            Guardar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

    <script>
        function setSelectedIndex(row) {

            const idCliente = row.cells[0].innerText;
            const nombre = row.cells[1].innerText;
            const apellido = row.cells[2].innerText;
            const nit = row.cells[3].innerText;
            const genero = row.cells[4].innerText;
            const telefono = row.cells[5].innerText;
            const correoElectronico = row.cells[6].innerText;
            const fechaIngreso = row.cells[7].innerText;

            document.getElementById('id').value = idCliente;
            document.getElementById('nombre').value = nombre;
            document.getElementById('apellido').value = apellido;
            document.getElementById('nit').value = nit;
            document.getElementById('genero').value = (genero === 'Masculino') ? 'True' : 'False';
            document.getElementById('telefono').value = telefono;
            document.getElementById('correoElectronico').value = correoElectronico;
            document.getElementById('fechaIngreso').value = fechaIngreso;
        }
    </script>