<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Redireccionando...</title>
    <script type="text/javascript">
        // Redirigir automáticamente al cargar la página
        window.onload = function() {
            window.location.href = "${pageContext.request.contextPath}/Inicio";
        };
    </script>
</head>
<body>
<!-- Puedes agregar contenido adicional aquí si es necesario -->
Redireccionando a la página principal...
</body>
</html>
