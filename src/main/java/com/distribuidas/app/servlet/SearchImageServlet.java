package com.distribuidas.app.servlet;

import com.distribuidas.app.dao.ImageDAO;
import com.distribuidas.app.model.Image;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchImageServlet", urlPatterns = "/imagenes/buscar")
public class SearchImageServlet extends HttpServlet {

    private final ImageDAO imageDAO = new ImageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String criterio = req.getParameter("criterio");
        List<Image> imagenes = imageDAO.buscarPorCriterio(criterio == null ? "" : criterio);
        req.setAttribute("imagenes", imagenes);
        req.setAttribute("criterio", criterio);
        req.getRequestDispatcher("/WEB-INF/views/buscarImagen.jsp").forward(req, resp);
    }
}