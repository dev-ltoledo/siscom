<%-- 
    Document   : Compras
    Created on : 11/01/2024, 12:54:39 a. m.
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
                            <h4 class="mb-1">Registro de compras</h4>
                            <p class="mb-0">Listado de compras</p>
                        </div>
                        <div class="d-flex align-content-center flex-wrap gap-4">
                            <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#create">
                                Nuevo&nbsp;<i class='bx bx-add-to-queue bx-rotate-90' ></i>
                            </button>

                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/proveedores">
                                Ir a proveedores&nbsp;<i class='bx bx-navigation' ></i>
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
                        <th>Orden de compra</th>
                        <th>Proveedor</th>
                        <th>Fecha de compra</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <c:choose>
                        <c:when test="${!empty(Listado)}">
                            <c:forEach var="venta" items="${Listado}">
                                <tr>
                                    <td>${venta.idCompra}</td>
                                    <td>${venta.noOrdenCompra}</td>
                                    <td style="display:none;">${venta.idProveedor}</td>
                                    <td>${venta.proveedor}</td>
                                    <td>${venta.fechaOrden}</td>
                                    <td>
                                        <div class="dropdown">
                                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                                <i class="bx bx-dots-vertical-rounded"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#detail" onclick="setSelectedIndex(this.closest('tr'))">
                                                    <i class="bx bx-detail me-1"></i> Detalle de venta
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

                <div class="modal-header">
                    <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <form id="form-ventas-create" action="${pageContext.request.contextPath}/compras?action=create" method="POST">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 col-lg-12">
                                <div class="mb-6">
                                    <div class="card-header">
                                        <h5 class="card-tile mb-0">Información de la compra</h5>
                                    </div>
                                    <div class="card-body mt-5">
                                        <div class="row">
                                            <div class="mb-3 col-4">
                                                <label class="form-label" >Orden de compra</label>
                                                <input type="text" class="form-control" placeholder="Ingrese la orden de compra" name="noOrdenCompra" required>
                                            </div>

                                            <div class="mb-3 col-4">
                                                <label class="form-label">Proovedores</label>
                                                <select class="form-select" name="idProveedor" required>
                                                    <c:choose>
                                                        <c:when test="${!empty(Proveedores)}">
                                                            <c:forEach var="proveedor" items="${Proveedores}">
                                                                <option value="${proveedor.idProveedor}">${proveedor.proveedor}</option>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="9999">Sin proveedores</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </select>
                                            </div>

                                            <div class="mb-3 col-4">

                                                <label class="form-label">Fecha de compra:</label>
                                                <input type="date" class="form-control" name="fechaOrden" required>

                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-6">
                                            <label class="form-label">Producto</label>
                                            <select class="form-select" name="idProducto" id="idProducto">
                                                <c:choose>
                                                    <c:when test="${!empty(Productos)}">
                                                        <c:forEach var="producto" items="${Productos}">
                                                            <option value="${producto.idProducto}">${producto.descripcion}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="9999">Sin productos</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </div>
                                        <div class="col-5">
                                            <label class="form-label">Cantidad</label>
                                            <input type="text" class="form-control" placeholder="ingrese una cantidad" name="cantidad" id="cantidad" value="0">
                                        </div>
                                        <div class="col-1">
                                            <button type="button" onclick="agregarAlCarrito()" class="btn btn-primary mt-6"> + </button>
                                        </div>
                                    </div>

                                    <div class="card-header mt-5">
                                        <h5 class="card-tile mb-0">Listado de productos</h5>
                                    </div>
                                    <div style="max-height: 300px; overflow-y: auto;">
                                        <table id="carrito" class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Producto</th>
                                                <th>Cantidad</th>
                                                <th>Acción</th>
                                            </tr>
                                            </thead>
                                            <tbody class="table-border-bottom-0" id="tbodyCarrito">
                                            </tbody>
                                        </table>
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

    <div class="modal fade" id="detail" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <form id="form-detail" action="${pageContext.request.contextPath}/compras" method="GET">
                    <div class="modal-header">
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="modalContent">
                        <div class="row">
                            <div style="display: none">
                                <label class="form-label">Id</label>
                                <input type="text" class="form-control" id="id"/>
                            </div>
                            <div class="row">
                                <div class="col-11 text-end">
                                    <h3>
                                        <strong>Compra siscom</strong>
                                    </h3>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-2">
                                    <h4>
                                        <strong>Datos:</strong>
                                    </h4>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-5">
                                    <h5>
                                        <strong>Proveedor:</strong>
                                        <label id="proveedor"></label>
                                    </h5>
                                </div>

                                <div class="col-6 text-end">
                                    <h5>
                                        <strong>Orden de Compra:</strong>
                                        <label id="noOrdenCompra"></label>
                                    </h5>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-11 text-end">
                                    <h5>
                                        <strong>Fecha:</strong>
                                        <label id="fecha"></label>
                                    </h5>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <h6>Productos de la venta</h6>
                        </div>

                        <div class="row">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Producto</th>
                                    <th>Descripción</th>
                                    <th>Cantidad</th>
                                    <th>Precio</th>
                                    <th>Total</th>
                                </tr>
                                </thead>
                                <tbody class="table-border-bottom-0" id="resultados">
                                </tbody>
                            </table>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" style="margin-right: 1rem;">
                            Cerrar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>

    function setSelectedIndex(row) {
        const cells = row.querySelectorAll("td");
        document.getElementById("id").innerText = cells[0].innerText;
        document.getElementById("noOrdenCompra").innerText = cells[1].innerText;
        document.getElementById("proveedor").innerText = cells[3].innerText;
        document.getElementById("fecha").innerText = cells[4].innerText;

        loadData(cells[0].innerText);
    }

    function loadData(idCompra) {
        $.ajax({
            type: "GET",
            url: "compras?action=getData&idCompra=" + idCompra,
            success: function(response) {

                $("#resultados").html(response);
            },
            error: function() {
                alert("Error al cargar los datos.");
            }
        });
    }

    function agregarAlCarrito() {
        const cantidad = document.getElementsByName("cantidad")[0].value;
        const selectProducto = document.getElementById(`idProducto`);
        const idProducto = selectProducto.value; // Obtener el valor del ID del producto
        const productoNombre = selectProducto.options[selectProducto.selectedIndex].text;

        if (cantidad > 0 && selectProducto != null) {
            const carrito = document.getElementById("tbodyCarrito");
            const fila = document.createElement("tr");

            fila.innerHTML = "<td>" + idProducto + "</td>" +
                "<td>" + productoNombre + "</td>" +
                "<td>" + cantidad + "</td>" +
                "<td><input type='hidden' name='idProducto' value='" + idProducto + "'>" +
                "<input type='hidden' name='cantidad' value='" + cantidad + "'>" +
                "<button type='button' class='btn btn-sm btn-danger' onClick='eliminarDelCarrito(this)'> X </button></td>";

            carrito.appendChild(fila);

        } else {
            alert("Debe de seleccionar un producto y cantidad diferente a 0 para continuar");
        }
    }

    function eliminarDelCarrito(button) {
        const row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
    }
</script>

