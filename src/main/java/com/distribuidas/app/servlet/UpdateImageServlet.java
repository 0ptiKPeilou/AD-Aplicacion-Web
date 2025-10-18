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

@WebServlet(name = "UpdateImageServlet", urlPatterns = "/imagenes/actualizar")
@MultipartConfig
public class UpdateImageServlet extends HttpServlet {

    private final ImageDAO imageDAO = new ImageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identificador = req.getParameter("id");
        Image image = imageDAO.buscarPorIdentificador(identificador);
        if (image == null) {
            resp.sendRedirect(req.getContextPath() + "/menu");
            return;
        }
        req.setAttribute("imagen", image);
        req.getRequestDispatcher("/WEB-INF/views/editarImagen.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identificador = req.getParameter("identificador");
        Image image = imageDAO.buscarPorIdentificador(identificador);
        if (image == null) {
            resp.sendRedirect(req.getContextPath() + "/menu");
            return;
        }

        image.setTitulo(req.getParameter("titulo"));
        image.setDescripcion(req.getParameter("descripcion"));
        image.setPalabrasClave(req.getParameter("palabrasClave"));
        image.setAutor(req.getParameter("autor"));
        image.setCreador(req.getParameter("creador"));
        image.setFechaCreacion(LocalDate.parse(req.getParameter("fechaCreacion")));

        Part archivo = req.getPart("archivo");
        if (archivo != null && archivo.getSize() > 0) {
            String rutaUploads = getServletContext().getRealPath("/uploads");
            if (rutaUploads == null) {
                rutaUploads = new File(System.getProperty("java.io.tmpdir"), "uploads").getAbsolutePath();
            }
            File carpeta = new File(rutaUploads);
            if (!carpeta.exists()) {
                Files.createDirectories(carpeta.toPath());
            }
            String nombreArchivo = identificador + "-" + archivo.getSubmittedFileName();
            File destino = new File(carpeta, nombreArchivo);
            archivo.write(destino.getAbsolutePath());
            image.setRutaArchivo("uploads/" + nombreArchivo);
        }

        imageDAO.actualizar(image);
        resp.sendRedirect(req.getContextPath() + "/menu");
    }
}