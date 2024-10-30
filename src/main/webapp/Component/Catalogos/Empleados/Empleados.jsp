<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <div class="mt-5 mx-5">
                <div class="app-empleado">
                    <div class="d-flex flex-column flex-md-row justify-content-between align-items-start align-items-md-center mb-6 row-gap-4">
                        <div class="d-flex flex-column justify-content-center">
                            <h4 class="mb-1">Catálogo de empleados</h4>
                            <p class="mb-0">Listado de empleados Siscom</p>
                        </div>
                        <div class="d-flex align-content-center flex-wrap gap-4">
                            <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#create">
                                Nuevo&nbsp;<i class='bx bx-add-to-queue bx-rotate-90'></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-responsive text-nowrap">
                <c:if test="${!empty(success)}">
                    <div class="row">
                        <div class="col-12">
                            <div class="alert alert-success alert-dismissible">
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                    ${success}
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
                        <th style="display: none">DPI</th>
                        <th style="display: none">Género</th>
                        <th>Teléfono</th>
                        <th style="display: none">Id Puesto</th>
                        <th>Puesto</th>
                        <th style="display: none">Dirección</th>
                        <th style="display: none">Fecha de nacimiento</th>
                        <th style="display: none">Fecha de inicio laboral</th>
                        <th style="display: none">Fecha de ingreso</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <c:choose>
                        <c:when test="${!empty(Listado)}">
                            <c:forEach var="empleado" items="${Listado}">
                                <tr>
                                    <td>${empleado.idEmpleado}</td>
                                    <td>${empleado.nombre}</td>
                                    <td>${empleado.apellido}</td>
                                    <td style="display: none">${empleado.direccion}</td>
                                    <td>${empleado.telefono}</td>
                                    <td style="display: none">${empleado.dpi}</td>
                                    <td style="display: none">${empleado.genero ? 'Masculino' : 'Femenino'}</td>
                                    <td style="display: none">${empleado.fechaNacimiento}</td>
                                    <td style="display: none">${empleado.idPuesto}</td>
                                    <td>${empleado.puesto}</td>
                                    <td style="display: none">${empleado.fechaInicioLabor}</td>
                                    <td style="display: none">${empleado.fechaIngreso}</td>
                                    <td>
                                        <div class="dropdown">
                                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                                <i class="bx bx-dots-vertical-rounded"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#update" onclick="setSelectedIndex(this.closest('tr'))">
                                                    <i class="bx bx-edit-alt me-1"></i> Editar
                                                </a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados?action=delete&id=${empleado.idEmpleado}">
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
                                <td colspan="11">No hay información para mostrar</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Modal para crear empleado -->
    <div class="modal fade" id="create" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <form id="form-empleados-create" action="${pageContext.request.contextPath}/empleados?action=create" method="POST">
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 col-lg-12">
                                <div>
                                    <div class="card-header">
                                        <h5 class="card-tile mb-0">Información del empleado:</h5>
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
                                                <label class="form-label">DPI:</label>
                                                <input type="text" class="form-control" name="dpi" placeholder="Ingrese el DPI" required>
                                            </div>
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Género:</label>
                                                <select class="form-select" name="genero" required>
                                                    <option value="True">Masculino</option>
                                                    <option value="False">Femenino</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Teléfono:</label>
                                                <input type="text" class="form-control" name="telefono" placeholder="Ingrese el teléfono" required>
                                            </div>
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Dirección:</label>
                                                <input type="text" class="form-control" name="direccion" placeholder="Ingrese la dirección" required>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Fecha de nacimiento:</label>
                                                <input type="date" class="form-control" name="fechaNacimiento" required>
                                            </div>
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Fecha de inicio laboral:</label>
                                                <input type="date" class="form-control" name="fechaInicioLabor" required>
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

    <!-- Modal para actualizar empleado -->
    <div class="modal fade" id="update" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <form id="form-empleados-update" action="${pageContext.request.contextPath}/empleados?action=update" method="POST">
                    <div class="modal-header">
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 col-lg-12">
                                <div>
                                    <div class="card-header">
                                        <h5 class="card-tile mb-0">Información del empleado:</h5>
                                    </div>
                                    <div class="card-body mt-3">
                                        <div style="display: none">
                                            <label class="form-label">Id</label>
                                            <input type="text" class="form-control" id="id" name="idEmpleado" readonly />
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
                                                <label class="form-label">DPI:</label>
                                                <input type="text" class="form-control" id="dpi" name="dpi" placeholder="Ingrese el DPI" required>
                                            </div>
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Género:</label>
                                                <select class="form-select" id="genero" name="genero" required>
                                                    <option value="True">Masculino</option>
                                                    <option value="False">Femenino</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Teléfono:</label>
                                                <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Ingrese el teléfono" required>
                                            </div>
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Dirección:</label>
                                                <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Ingrese la dirección" required>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Estado</label>
                                                <div class="input-group">
                                                    <select class="form-select" id="idPuesto" name="idPuesto">
                                                        <c:choose>
                                                            <c:when test="${!empty(Puesto)}">
                                                                <c:forEach var="puesto" items="${Puesto}">
                                                                    <option value="${puesto.idPuesto}">${puesto.puesto}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="9999">Sin puestos</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="mb-3 col-6">
                                                <label class="form-label">Fecha de nacimiento:</label>
                                                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="mb-3 col-6">
                                                <label class="form-label">Fecha de ingreso:</label>
                                                <input type="datetime-local" class="form-control" id="fechaIngreso" name="fechaIngreso" readonly>
                                            </div>

                                            <div class="mb-3 col-6">
                                                <label class="form-label">Fecha de inicio laboral:</label>
                                                <input type="date" class="form-control" id="fechaInicioLabor" name="fechaInicioLabor" required>
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
                            Actualizar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function setSelectedIndex(row) {
        const cells = row.querySelectorAll("td");
        document.getElementById("id").value = cells[0].innerText;
        document.getElementById("nombre").value = cells[1].innerText;
        document.getElementById("apellido").value = cells[2].innerText;
        document.getElementById("direccion").value = cells[3].innerText;
        document.getElementById("telefono").value = cells[4].innerText;
        document.getElementById("dpi").value = cells[5].innerText;
        document.getElementById("genero").value = cells[6].innerText === 'Masculino' ? 'True' : 'False';
        document.getElementById("fechaNacimiento").value = cells[7].innerText;
        document.getElementById("idPuesto").value = cells[8].innerText;
        document.getElementById("fechaInicioLabor").value = cells[10].innerText;
        document.getElementById("fechaIngreso").value = cells[11].innerText;

    }
</script>
