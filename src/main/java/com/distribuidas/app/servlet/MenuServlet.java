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

@WebServlet(name = "MenuServlet", urlPatterns = "/menu")
public class MenuServlet extends HttpServlet {

    private final ImageDAO imageDAO = new ImageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Image> imagenes = imageDAO.listarTodas();
        req.setAttribute("imagenes", imagenes);
        req.getRequestDispatcher("/WEB-INF/views/menu.jsp").forward(req, resp);
    }
}