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
                            <h4 class="mb-1">Catálogo de productos</h4>
                            <p class="mb-0">Listado de productos</p>
                        </div>
                        <div class="d-flex align-content-center flex-wrap gap-4">
                            <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#create">
                                Nuevo&nbsp;<i class='bx bx-add-to-queue bx-rotate-90' ></i>
                            </button>
                            
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/marcas">
                                Ir a marcas&nbsp;<i class='bx bx-navigation' ></i>
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
                            <th>UPC</th>
                            <th>Descripción</th>
                            <th>Marca</th>
                            <th>Precio Costo</th>
                            <th>Precio venta</th>
                            <th>Existencia</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <c:choose>
                        <c:when test="${!empty(Listado)}">
                            <c:forEach var="producto" items="${Listado}">
                                <tr>
                                    <td>${producto.idProducto}</td>
                                    <td>${producto.producto}</td>
                                    <td>${producto.descripcion}</td>
                                    <td style="display: none;">${producto.idMarca}</td>
                                    <td>${producto.marca}</td>
                                    <td>${producto.precioCosto}</td>
                                    <td>${producto.precioVenta}</td>
                                    <td>${producto.existencia}</td>
                                    <td>
                                    <span class="badge ${producto.idEstado == 1 ? 'bg-label-primary' : 'bg-label-danger'} me-1">
                                            ${producto.idEstado == 1 ? 'Activo' : 'Inactivo'}
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
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/productos?action=delete&id=${producto.idProducto}">
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
                <form id="form-proveedores-create" action="${pageContext.request.contextPath}/productos?action=create" method="POST">
                    <div class="modal-header">
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 col-lg-8">
                                <div class="mb-6">
                                    <div class="card-header">
                                        <h5 class="card-tile mb-0">Información del producto</h5>
                                    </div>
                                    <div class="card-body mt-5">
                                        <div class="mb-3">
                                            <label class="form-label" >UPC</label>
                                            <input type="text" class="form-control" placeholder="Ingrese el código de barras" name="producto" required>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label" >Descripción del producto</label>
                                            <input type="text" class="form-control" placeholder="Ingrese la descripción del producto" name="descripcion" required>
                                        </div>

                                        <div class="row g-6">
                                            <div class="col-6">
                                                <label class="form-label">Marca</label>
                                                <div class="input-group">
                                                    <select class="form-select" name="idMarca" required>
                                                        <option>Seleccione una marca</option>
                                                        <c:choose>
                                                            <c:when test="${!empty(Marca)}">
                                                                <c:forEach var="marca" items="${Marca}">
                                                                    <option value="${marca.idMarca}">${marca.marca}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="9999">Sin marcas</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </select>
                                                </div>
                                            </div>

                                        </div>

                                        <div class="mt-3">
                                            <label class="form-label">Imagen del producto</label>
                                            <input class="form-control" type="file" name="imagen" required/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-lg-4">
                                <div class="card mb-6">
                                    <div class="card-header">
                                        <h5 class="card-title mb-0">Precio</h5>
                                    </div>
                                    <div class="card-body">

                                        <div class="mb-3">
                                            <label class="form-label">Precio costo</label>
                                            <input type="number" class="form-control" placeholder="Precio costo" name="precioCosto" step="0.01" required>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Precio venta</label>
                                            <input type="number" class="form-control" placeholder="Precio venta" name="precioVenta" step="0.01" required>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Existencias</label>
                                            <input type="number" class="form-control" placeholder="Ingrese su existencia" name="existencia" required>
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
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <form id="form-marcas-update" action="${pageContext.request.contextPath}/productos?action=update" method="POST">
                    <div class="modal-header">
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 col-lg-8">
                                <div class="mb-6">
                                    <div class="card-header">
                                        <h5 class="card-tile mb-0">Información del producto</h5>
                                    </div>
                                    <div class="card-body mt-5">
                                        <div style="display: none">
                                            <label class="form-label">Id</label>
                                            <input type="text" class="form-control" id="id" name="id" readonly />
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label" >UPC</label>
                                            <input type="text" class="form-control" placeholder="Ingrese el código de barras" name="producto" id="producto" required>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label" >Descripción del producto</label>
                                            <input type="text" class="form-control" placeholder="Ingrese la descripción del producto" name="descripcion" id="descripcion" required>
                                        </div>

                                        <div class="row g-6">
                                            <div class="col-6">
                                                <label class="form-label">Marca</label>
                                                <div class="input-group">
                                                    <select class="form-select" name="idMarca" id="idMarca" required>
                                                        <option>Seleccione una marca</option>
                                                        <c:choose>
                                                            <c:when test="${!empty(Marca)}">
                                                                <c:forEach var="marca" items="${Marca}">
                                                                    <option value="${marca.idMarca}">${marca.marca}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="9999">Sin marcas</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-lg-4">
                                <div class="card mb-6">
                                    <div class="card-header">
                                        <h5 class="card-title mb-0">Precio</h5>
                                    </div>
                                    <div class="card-body">

                                        <div class="mb-3">
                                            <label class="form-label">Precio costo</label>
                                            <input type="number" class="form-control" placeholder="Precio costo" name="precioCosto" id="precioCosto" step="0.01"  required>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Precio venta</label>
                                            <input type="number" class="form-control" placeholder="Precio venta" name="precioVenta" id="precioVenta" step="0.01" required>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label">Existencias</label>
                                            <input type="number" class="form-control" placeholder="Ingrese su existencia" name="existencia" id="existencia" required>
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
        const cells = row.querySelectorAll("td");
        document.getElementById("id").value = cells[0].innerText;
        document.getElementById("producto").value = cells[1].innerText;
        document.getElementById("descripcion").value = cells[2].innerText;
        document.getElementById("idMarca").value = cells[3].innerText;
        document.getElementById("precioCosto").value = cells[5].innerText;
        document.getElementById("precioVenta").value = cells[6].innerText;
        document.getElementById("existencia").value = cells[7].innerText;
        document.getElementById("idEstado").value = cells[9].innerText === 'Activo' ? '1' : '0';
    }
</script>
