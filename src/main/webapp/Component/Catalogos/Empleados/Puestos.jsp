<%-- 
    Document   : Productos
    Created on : 29/09/2024, 12:54:39 a. m.
    Author     : ltoledo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-xxl flex-grow-1 container-p-y">
    <div class="card">
        <div class="mt-5 mx-5">
            <div class="app-producto">
                <div class="d-flex flex-column flex-md-row justify-content-between align-items-start align-items-md-center mb-6 row-gap-4">
                    <div class="d-flex flex-column justify-content-center">
                        <h4 class="mb-1">Catalogo de puestos</h4>
                        <p class="mb-0">Listado de puestos</p>
                    </div>
                    <div class="d-flex align-content-center flex-wrap gap-4">
                        <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#create">
                            Nuevo&nbsp;<i class='bx bx-add-to-queue bx-rotate-90' ></i>
                        </button>
                        <a class="btn btn-warning" href="${pageContext.request.contextPath}/empleados">
                            Ir a empleados&nbsp;<i class='bx bx-navigation' ></i>
                        </a>
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
                    <th>Puesto</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody class="table-border-bottom-0">
                <c:choose>
                    <c:when test="${!empty(Listado)}">
                        <c:forEach var="puesto" items="${Listado}">
                            <tr>
                                <td>${puesto.idPuesto}</td>
                                <td>${puesto.puesto}</td>
                                <td>
                                    <span class="badge ${puesto.idEstado == 1 ? 'bg-label-primary' : 'bg-label-danger'} me-1">
                                            ${puesto.idEstado == 1 ? 'Activo' : 'Inactivo'}
                                    </span>
                                </td>
                                <td>
                                    <div class="dropdown">
                                        <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                            <i class="bx bx-dots-vertical-rounded"></i>
                                        </button>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#update" onclick="setSelectedIndex(this.closest('tr'))">
                                                <i class="bx bx-edit-alt me-1"></i> Editar
                                            </a>
                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/puestos?action=delete&id=${puesto.idPuesto}">
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
</div>

<div class="modal fade" id="create" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="form-puestos-create" action="${pageContext.request.contextPath}/puestos?action=create" method="POST">
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
                                    <h5 class="card-tile mb-0">Información del puesto:</h5>
                                </div>
                                <div class="card-body mt-3">
                                    <div class="mb-3">
                                        <label class="form-label">Puesto</label>
                                        <input type="text" class="form-control" name="puesto" placeholder="Ingrese el puesto" required>
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
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="form-puestos-update" action="${pageContext.request.contextPath}/puestos?action=update" method="POST">
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
                                    <h5 class="card-tile mb-0">Información del puesto:</h5>
                                </div>
                                <div class="card-body mt-3">
                                    <div style="display: none">
                                        <label class="form-label">Id</label>
                                        <input type="text" class="form-control" id="id" name="id" readonly />
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Puesto</label>
                                        <input type="text" class="form-control" id="puesto" name="puesto" placeholder="Ingrese el nombre del puesto" required>
                                    </div>

                                    <div class="row g-6">
                                        <div class="col-12">
                                            <label class="form-label">Estado</label>
                                            <div class="input-group">
                                                <select class="form-select" id="estado" name="estado" required>
                                                    <option value="1">Activo</option>
                                                    <option value="0">Inactivo</option>
                                                </select>
                                            </div>
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

<script>
    function setSelectedIndex(row) {
        const cells = row.querySelectorAll("td");
        document.getElementById("id").value = cells[0].innerText;
        document.getElementById("puesto").value = cells[1].innerText;
        document.getElementById("estado").value = cells[2].innerText === 'Activo' ? '1' : '0';
    }
</script>