package com.distribuidas.app.dao;

import com.distribuidas.app.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final DatabaseManager databaseManager = DatabaseManager.getInstance();

    public User buscarPorNombre(String nombre) {
        String sql = "SELECT id, nombre, password, rol FROM usuarios WHERE nombre = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("password"),
                            rs.getString("rol")
                    );
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error buscando usuario", e);
        }
        return null;
    }
}