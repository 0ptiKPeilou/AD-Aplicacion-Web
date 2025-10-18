<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio de sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css">
</head>
<body>
<div class="contenedor">
    <h1>Gestor de Imágenes</h1>
    <form action="${pageContext.request.contextPath}/login" method="post" class="formulario">
        <label for="usuario">Usuario</label>
        <input type="text" id="usuario" name="usuario" required>

        <label for="password">Contraseña</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Entrar</button>
        <c:if test="${not empty error}">
            <p class="mensaje error">${error}</p>
        </c:if>
    </form>
</div>
</body>
</html>