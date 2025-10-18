<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Menú principal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css">
</head>
<body>
<div class="barra-superior">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit" class="boton-secundario">Cerrar sesión</button>
    </form>
</div>
<div class="contenedor">
    <h1>Menú de gestión de imágenes</h1>
    <div class="acciones">
        <a class="boton" href="${pageContext.request.contextPath}/imagenes/registrar">Registrar nueva imagen</a>
        <a class="boton" href="${pageContext.request.contextPath}/imagenes/buscar">Buscar imágenes</a>
    </div>

    <h2>Listado reciente</h2>
    <table>
        <thead>
        <tr>
            <th>Identificador</th>
            <th>Título</th>
            <th>Autor</th>
            <th>Fecha registro</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="imagen" items="${imagenes}">
            <tr>
                <td>${imagen.identificador}</td>
                <td>${imagen.titulo}</td>
                <td>${imagen.autor}</td>
                <td>${imagen.fechaRegistro}</td>
                <td>
                    <a class="boton" href="${pageContext.request.contextPath}/imagenes/actualizar?id=${imagen.identificador}">Editar</a>
                    <form action="${pageContext.request.contextPath}/imagenes/eliminar" method="post" class="form-inline">
                        <input type="hidden" name="identificador" value="${imagen.identificador}">
                        <button type="submit" class="boton-secundario" onclick="return confirm('¿Eliminar la imagen?');">Eliminar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty imagenes}">
            <tr>
                <td colspan="5">Todavía no hay imágenes registradas.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>