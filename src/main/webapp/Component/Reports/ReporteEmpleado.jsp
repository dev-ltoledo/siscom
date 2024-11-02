
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.File" %>
<%@ page import="net.sf.jasperreports.engine.JasperRunManager" %>
<%@ page import="com.siscom.sis.dbcontext.Conexion" %>
<%--
  Created by IntelliJ IDEA.
  User: ltoledo
  Date: 2/11/2024
  Time: 02:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Conexion conexion = new Conexion();

    String reportPath = application.getRealPath("/") + "WEB-INF" + File.separator + "Reportes" + File.separator + "empleados.jasper";
    File reportFile = new File(reportPath);

    Map<String, Object> parameters = new HashMap<String, Object>();

    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), parameters);

                // Ejecutar el reporte y generar el PDF
                bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), parameters, conexion.obtenerConexion());

                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();

%>


</body>
</html>
