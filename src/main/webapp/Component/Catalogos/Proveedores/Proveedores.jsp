<%-- 
    Document   : Productos
    Created on : 29/09/2024, 12:54:39 a. m.
    Author     : ltoledo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content-wrapper">

    <div class="container-xxl flex-grow-1 container-p-y">

        <div class="card">
            <div class="mt-5 mx-5">
                <div class="app-producto">
                    <div class="d-flex flex-column flex-md-row justify-content-between align-items-start align-items-md-center mb-6 row-gap-4">
                        <div class="d-flex flex-column justify-content-center">
                            <h4 class="mb-1">Catalogo de empleados</h4>
                            <p class="mb-0">Listado de empleados siscom</p>
                        </div>
                        <div class="d-flex align-content-center flex-wrap gap-4">
                            <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exLargeModal">
                                Nuevo&nbsp;<i class='bx bx-add-to-queue bx-rotate-90' ></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-responsive text-nowrap">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>                            
                            <th>Proveedor</th>
                            <th>NIT</th>
                            <th>Dirección</th>
                            <th>Teléfono</th>
                            <th>Fecha de ingreso</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                        <tr>
                            <td>1</td>
                            <td>Luis Fernando Toledo Rodríguez</td>
                            <td>6ta avenida 20-28 san francisco I</td>
                            <td>+502 30585028</td>
                            <td>Gerente de operaciones</td>
                            <td>22/08/2024</td>
                            <td><span class="badge bg-label-primary me-1">Activo</span></td>

                            <td>
                                <div class="dropdown">
                                    <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                        <i class="bx bx-dots-vertical-rounded"></i>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#exLargeModal"
                                           ><i class="bx bx-edit-alt me-1"></i>Detalles y Edición</a
                                        >
                                        <a class="dropdown-item" href="javascript:void(0);"
                                           ><i class="bx bx-trash me-1"></i> Eliminar</a
                                        >
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>




    <div class="modal fade" id="exLargeModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
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
                            <div class="card mb-6">
                                <div class="card-header">
                                    <h5 class="card-tile mb-0">Datos del empleado</h5>
                                </div>
                                <div class="card-body">
                                    <div class="row g-6">
                                        <div class="col-6 mb-3">
                                            <label class="form-label" for="ecommerce-product-name">Nombres</label>
                                            <input type="text" class="form-control" id="ecommerce-product-name" placeholder="Ingrese el código de barras" name="productTitle" aria-label="Product title">
                                        </div>
                                        <div class="col-6 mb-3">
                                            <label class="form-label" for="ecommerce-product-name">Apellidos</label>
                                            <input type="text" class="form-control" id="ecommerce-product-name" placeholder="Ingrese el código de barras" name="productTitle" aria-label="Product title">
                                        </div>
                                    </div>
                                    <div class="row g-6">
                                        <div class="col-6 mb-3">
                                            <label class="form-label" for="ecommerce-product-name">DPI</label>
                                            <input type="text" class="form-control" id="ecommerce-product-name" placeholder="Ingrese el código de barras" name="productTitle" aria-label="Product title">
                                        </div>
                                        <div class="col-6 mb-3">
                                            <label class="form-label" for="ecommerce-product-name">Télefono</label>
                                            <input type="text" class="form-control" id="ecommerce-product-name" placeholder="Ingrese el código de barras" name="productTitle" aria-label="Product title">
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label" for="ecommerce-product-name">Dirección</label>
                                        <input type="text" class="form-control" id="ecommerce-product-name" placeholder="Ingrese la descripción del producto" name="productTitle" aria-label="Product title">
                                    </div>

                                    <div class="row g-6">
                                        <div class="col-6">
                                            <label for="language" class="form-label">Género</label>
                                            <div class="input-group">
                                                <select
                                                    class="form-select"
                                                    id="inputGroupSelect04">
                                                    <option selected>Selecciona un género</option>
                                                    <option value="1">Masculino</option>
                                                    <option value="2">Femenino</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-6">
                                            <label for="language" class="form-label">Puesto</label>
                                            <div class="input-group">
                                                <select
                                                    class="form-select"
                                                    id="inputGroupSelect04">
                                                    <option selected>Seleccione un puesto</option>
                                                    <option value="1">Gerente de operaciones</option>
                                                    <option value="2">Vendedor</option>
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
                                    <h5 class="card-title mb-0">Fechas</h5>
                                </div>
                                <div class="card-body">

                                    <div class="mb-3">
                                        <label for="dobBasic" class="form-label">Fecha de nacimiento</label>
                                        <input type="date" id="dobBasic" class="form-control" />
                                    </div>

                                    <div class="mb-3">
                                        <label for="dobBasic" class="form-label">Fecha de ingreso</label>
                                        <input type="date" id="dobBasic" class="form-control" />
                                    </div>

                                    <div class="mb-3">
                                        <label for="dobBasic" class="form-label">Fecha de inicio de labores</label>
                                        <input type="date" id="dobBasic" class="form-control" />
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
                    <button type="button" class="btn btn-primary">
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    </div>
