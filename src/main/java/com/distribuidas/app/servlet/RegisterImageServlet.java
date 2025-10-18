package com.distribuidas.app.servlet;

import com.distribuidas.app.dao.ImageDAO;
import com.distribuidas.app.model.Image;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.UUID;

@WebServlet(name = "RegisterImageServlet", urlPatterns = "/imagenes/registrar")
@MultipartConfig
public class RegisterImageServlet extends HttpServlet {

    private final ImageDAO imageDAO = new ImageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registrarImagen.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identificador = UUID.randomUUID().toString();
        String titulo = req.getParameter("titulo");
        String descripcion = req.getParameter("descripcion");
        String palabrasClave = req.getParameter("palabrasClave");
        String autor = req.getParameter("autor");
        String creador = req.getParameter("creador");
        LocalDate fechaCreacion = LocalDate.parse(req.getParameter("fechaCreacion"));
        LocalDate fechaRegistro = LocalDate.now();

        Part archivo = req.getPart("archivo");
        String nombreArchivo = identificador + "-" + archivo.getSubmittedFileName();
        String rutaUploads = getServletContext().getRealPath("/uploads");
        if (rutaUploads == null) {
            rutaUploads = new File(System.getProperty("java.io.tmpdir"), "uploads").getAbsolutePath();
        }
        File carpeta = new File(rutaUploads);
        if (!carpeta.exists()) {
            Files.createDirectories(carpeta.toPath());
        }
        File destino = new File(carpeta, nombreArchivo);
        archivo.write(destino.getAbsolutePath());

        Image image = new Image();
        image.setIdentificador(identificador);
        image.setTitulo(titulo);
        image.setDescripcion(descripcion);
        image.setPalabrasClave(palabrasClave);
        image.setAutor(autor);
        image.setCreador(creador);
        image.setFechaCreacion(fechaCreacion);
        image.setFechaRegistro(fechaRegistro);
        image.setRutaArchivo("uploads/" + nombreArchivo);

        imageDAO.guardar(image);
        resp.sendRedirect(req.getContextPath() + "/menu");
    }
}