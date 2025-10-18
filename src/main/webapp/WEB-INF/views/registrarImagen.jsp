<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar imagen</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css">
</head>
<body>
<div class="contenedor">
    <h1>Registrar imagen</h1>
    <form action="${pageContext.request.contextPath}/imagenes/registrar" method="post" enctype="multipart/form-data" class="formulario">
        <label for="titulo">Título</label>
        <input type="text" id="titulo" name="titulo" required>

        <label for="descripcion">Descripción</label>
        <textarea id="descripcion" name="descripcion" rows="4"></textarea>

        <label for="palabrasClave">Palabras clave (separadas por coma)</label>
        <input type="text" id="palabrasClave" name="palabrasClave">

        <label for="autor">Autor</label>
        <input type="text" id="autor" name="autor" required>

        <label for="creador">Usuario que captura</label>
        <input type="text" id="creador" name="creador" required>

        <label for="fechaCreacion">Fecha creación de la imagen</label>
        <input type="date" id="fechaCreacion" name="fechaCreacion" required>

        <label for="archivo">Archivo</label>
        <input type="file" id="archivo" name="archivo" accept="image/*" required>

        <div class="acciones">
            <button type="submit">Guardar</button>
            <a class="boton-secundario" href="${pageContext.request.contextPath}/menu">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>