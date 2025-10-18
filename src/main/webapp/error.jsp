<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css">
</head>
<body>
<div class="contenedor">
    <h1>Ha ocurrido un error</h1>
    <p>${requestScope['jakarta.servlet.error.message']}</p>
    <a class="boton" href="${pageContext.request.contextPath}/menu">Volver al menú</a>
</div>
</body>
</html>