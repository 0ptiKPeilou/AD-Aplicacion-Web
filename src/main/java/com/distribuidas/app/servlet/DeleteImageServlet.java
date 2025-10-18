package com.distribuidas.app.servlet;

import com.distribuidas.app.dao.ImageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteImageServlet", urlPatterns = "/imagenes/eliminar")
public class DeleteImageServlet extends HttpServlet {

    private final ImageDAO imageDAO = new ImageDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identificador = req.getParameter("identificador");
        imageDAO.eliminar(identificador);
        resp.sendRedirect(req.getContextPath() + "/menu");
    }
}