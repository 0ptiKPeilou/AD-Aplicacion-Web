<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar imágenes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css">
</head>
<body>
<div class="contenedor">
    <h1>Buscar imágenes</h1>
    <form action="${pageContext.request.contextPath}/imagenes/buscar" method="get" class="formulario">
        <label for="criterio">Criterio</label>
        <input type="text" id="criterio" name="criterio" value="${criterio}">
        <button type="submit">Buscar</button>
        <a class="boton-secundario" href="${pageContext.request.contextPath}/menu">Volver</a>
    </form>

    <h2>Resultados</h2>
    <table>
        <thead>
        <tr>
            <th>Identificador</th>
            <th>Título</th>
            <th>Descripción</th>
            <th>Palabras clave</th>
            <th>Archivo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="imagen" items="${imagenes}">
            <tr>
                <td>${imagen.identificador}</td>
                <td>${imagen.titulo}</td>
                <td>${imagen.descripcion}</td>
                <td>${imagen.palabrasClave}</td>
                <td><a href="${pageContext.request.contextPath}/${imagen.rutaArchivo}" target="_blank">Ver archivo</a></td>
            </tr>
        </c:forEach>
        <c:if test="${empty imagenes}">
            <tr>
                <td colspan="5">No se encontraron imágenes.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>