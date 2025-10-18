# Práctica 2 - Desarrollo de una aplicación web

## Descripción general
La aplicación desarrollada es un gestor sencillo de imágenes construido con Jakarta EE 9 (servlets y JSP) y una base de datos SQLite para almacenar los metadatos. El objetivo es cubrir los requisitos de la práctica implementando autenticación, gestión de la información de imágenes y un flujo de navegación completo entre las páginas descritas en el enunciado.

## Flujo de funcionamiento
1. **login.jsp**: Formulario de acceso. Valida las credenciales contra la tabla `usuarios` (usuario por defecto `admin` / `admin`).
2. **menu.jsp**: Página principal con enlaces a registrar y buscar imágenes. Muestra un resumen con las imágenes más recientes y ofrece acciones de edición o eliminación.
3. **registrarImagen.jsp**: Formulario para dar de alta una imagen con título, descripción, palabras clave, autor, creador, fecha de creación y archivo adjunto.
4. **buscarImagen.jsp**: Búsqueda por texto en título, descripción o palabras clave. Permite abrir el archivo almacenado.
5. **editarImagen.jsp**: Muestra los datos de una imagen y permite modificarlos o actualizar el archivo.
6. **error.jsp**: Página genérica para el manejo de errores inesperados.

El filtro `AuthenticationFilter` garantiza que solo los usuarios autenticados acceden al menú y a las operaciones con imágenes. El servlet `LogoutServlet` cierra la sesión de forma explícita.

## Estructura de paquetes
- `com.distribuidas.app.dao`: Gestión de la base de datos y acceso a datos (`DatabaseManager`, `UserDAO`, `ImageDAO`).
- `com.distribuidas.app.model`: Clases de dominio (`User`, `Image`).
- `com.distribuidas.app.servlet`: Servlets que implementan la lógica de control de la aplicación.
- `com.distribuidas.app.filter`: Filtro de autenticación para proteger las páginas internas.

## Base de datos
La base de datos SQLite se inicializa automáticamente la primera vez que se ejecuta la aplicación. Se crea la tabla `usuarios` con un usuario administrador y la tabla `imagenes` que almacena los metadatos requeridos. Los archivos subidos se guardan en la carpeta `uploads` del desplegable y su ruta relativa queda registrada para poder descargarlos.

### Modelo entidad-relación simplificado
- **usuarios** (`id`, `nombre`, `password`, `rol`)
- **imagenes** (`id`, `identificador`, `titulo`, `descripcion`, `palabras_clave`, `autor`, `creador`, `fecha_creacion`, `fecha_registro`, `ruta_archivo`)

## Instrucciones de despliegue
1. Asegúrate de tener instalado Maven y un contenedor compatible con Jakarta EE 9 (por ejemplo Payara, GlassFish o Tomcat 10).
2. Ejecuta `mvn clean package` para generar el archivo WAR.
3. Despliega el WAR en tu servidor de aplicaciones. Al iniciarse, la aplicación creará la base de datos y la carpeta de subida si no existen.
4. Accede mediante `http://localhost:8080/<contexto>/` y utiliza las credenciales `admin` / `admin`.

## Consideraciones de accesibilidad y usabilidad
- Formularios con etiquetas asociadas y mensajes de error visibles.
- Botones y enlaces con contraste suficiente y texto descriptivo.
- Confirmación antes de eliminar registros.

## Posibles mejoras
- Validación adicional de formato para las fechas o el tamaño del archivo.
- Internacionalización de los textos de la interfaz.
- Gestión de roles para restringir operaciones específicas.
- Tests automatizados para la capa DAO.