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
                            <h4 class="mb-1">Catalogo de productos</h4>
                            <p class="mb-0">Listado de productos</p>
                        </div>
                        <div class="d-flex align-content-center flex-wrap gap-4">
                            <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exLargeModal">
                                Nuevo&nbsp;<i class='bx bx-add-to-queue bx-rotate-90' ></i>
                            </button>
                            
                            <a class="btn btn-warning" href="MainLayout.jsp?page=/Component/Catalogos/Productos/Marcas.jsp">
                                Ir a marcas&nbsp;<i class='bx bx-navigation' ></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-responsive text-nowrap">
                <table class="table table-hover">
                    <thead>
                        <tr>
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
                        <tr>
                            <td>7441003500402</td>
                            <td>Coca Cola de vidrio 500ML</td>
                            <td>Coca Cola</td>
                            <td>Q4.30</td>
                            <td>Q5.00</td>
                            <td>15</td>                        
                            <td><span class="badge bg-label-primary me-1">Activo    </span></td>

                            <td>
                                <div class="dropdown">
                                    <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                        <i class="bx bx-dots-vertical-rounded"></i>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#exLargeModal"
                                           ><i class="bx bx-edit-alt me-1"></i> Editar</a
                                        >
                                        <a class="dropdown-item" href="javascript:void(0);"
                                           ><i class="bx bx-trash me-1"></i> Eliminar</a
                                        >
                                    </div>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>7441003500402</td>
                            <td>Coca Cola de vidrio 500ML</td>
                            <td>Coca Cola</td>
                            <td>Q4.30</td>
                            <td>Q5.00</td>
                            <td>15</td>                        
                            <td><span class="badge bg-label-danger me-1">Inactivo</span></td>

                            <td>
                                <div class="dropdown">
                                    <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                        <i class="bx bx-dots-vertical-rounded"></i>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#exLargeModal"
                                           ><i class="bx bx-edit-alt me-1"></i> Editar</a
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
                                    <h5 class="card-tile mb-0">Información del producto</h5>
                                </div>
                                <div class="card-body">
                                    <div class="mb-3">
                                        <label class="form-label" for="ecommerce-product-name">UPC</label>
                                        <input type="text" class="form-control" id="ecommerce-product-name" placeholder="Ingrese el código de barras" name="productTitle" aria-label="Product title">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="ecommerce-product-name">Descripción del producto</label>
                                        <input type="text" class="form-control" id="ecommerce-product-name" placeholder="Ingrese la descripción del producto" name="productTitle" aria-label="Product title">
                                    </div>

                                    <div class="row g-6">
                                        <div class="col-6">
                                            <label for="language" class="form-label">Marca</label>
                                            <div class="input-group">
                                                <select
                                                    class="form-select"
                                                    id="inputGroupSelect04">
                                                    <option selected>Selecciona una marca</option>
                                                    <option value="1">Coca cola</option>
                                                    <option value="2">Pepsi</option>
                                                    <option value="3">Big cola</option>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-6">
                                            <label class="form-label" for="ecommerce-product-price">Existencias</label>
                                            <input type="number" class="form-control" id="ecommerce-product-price" placeholder="Ingrese su existencia" name="productPrice" aria-label="Product price">
                                        </div>
                                    </div>

                                    <div class="mt-3">
                                        <label for="formFile" class="form-label">Imagen del producto</label>
                                        <input class="form-control" type="file" id="formFile" />
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
                                        <label class="form-label" for="ecommerce-product-price">Precio costo</label>
                                        <input type="number" class="form-control" id="ecommerce-product-price" placeholder="Precio costo" name="productPrice" aria-label="Product price">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="ecommerce-product-discount-price">Precio venta</label>
                                        <input type="number" class="form-control" id="ecommerce-product-discount-price" placeholder="Precio venta" name="productDiscountedPrice" aria-label="Product discounted price">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="ecommerce-product-discount-price">Existencias</label>
                                        <input type="number" class="form-control" id="ecommerce-product-discount-price" placeholder="Ingrese su existencia" name="productDiscountedPrice" aria-label="Product discounted price">
                                    </div>

                                    <div class="mb-0">
                                        <label for="dobBasic" class="form-label">Fecha de ingreso</label>
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
