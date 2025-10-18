<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar imagen</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css">
</head>
<body>
<div class="contenedor">
    <h1>Editar imagen</h1>
    <form action="${pageContext.request.contextPath}/imagenes/actualizar" method="post" enctype="multipart/form-data" class="formulario">
        <input type="hidden" name="identificador" value="${imagen.identificador}">

        <label for="titulo">Título</label>
        <input type="text" id="titulo" name="titulo" value="${imagen.titulo}" required>

        <label for="descripcion">Descripción</label>
        <textarea id="descripcion" name="descripcion" rows="4">${imagen.descripcion}</textarea>

        <label for="palabrasClave">Palabras clave</label>
        <input type="text" id="palabrasClave" name="palabrasClave" value="${imagen.palabrasClave}">

        <label for="autor">Autor</label>
        <input type="text" id="autor" name="autor" value="${imagen.autor}" required>

        <label for="creador">Usuario que captura</label>
        <input type="text" id="creador" name="creador" value="${imagen.creador}" required>

        <label for="fechaCreacion">Fecha creación</label>
        <input type="date" id="fechaCreacion" name="fechaCreacion" value="${imagen.fechaCreacion}" required>

        <p>Archivo actual: <a href="${pageContext.request.contextPath}/${imagen.rutaArchivo}" target="_blank">${imagen.rutaArchivo}</a></p>
        <label for="archivo">Actualizar archivo</label>
        <input type="file" id="archivo" name="archivo" accept="image/*">

        <div class="acciones">
            <button type="submit">Guardar cambios</button>
            <a class="boton-secundario" href="${pageContext.request.contextPath}/menu">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>