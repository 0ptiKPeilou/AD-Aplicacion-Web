package com.distribuidas.app.dao;

import com.distribuidas.app.model.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

    private final DatabaseManager databaseManager = DatabaseManager.getInstance();

    public List<Image> listarTodas() {
        String sql = "SELECT * FROM imagenes ORDER BY fecha_registro DESC";
        List<Image> imagenes = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                imagenes.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error listando imágenes", e);
        }
        return imagenes;
    }

    public void guardar(Image image) {
        String sql = "INSERT INTO imagenes (identificador, titulo, descripcion, palabras_clave, autor, creador, fecha_creacion, fecha_registro, ruta_archivo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, image.getIdentificador());
            stmt.setString(2, image.getTitulo());
            stmt.setString(3, image.getDescripcion());
            stmt.setString(4, image.getPalabrasClave());
            stmt.setString(5, image.getAutor());
            stmt.setString(6, image.getCreador());
            stmt.setString(7, image.getFechaCreacion().toString());
            stmt.setString(8, image.getFechaRegistro().toString());
            stmt.setString(9, image.getRutaArchivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Error guardando la imagen", e);
        }
    }

    public Image buscarPorIdentificador(String identificador) {
        String sql = "SELECT * FROM imagenes WHERE identificador = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error buscando imagen", e);
        }
        return null;
    }

    public void actualizar(Image image) {
        String sql = "UPDATE imagenes SET titulo = ?, descripcion = ?, palabras_clave = ?, autor = ?, creador = ?, fecha_creacion = ?, ruta_archivo = ? WHERE identificador = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, image.getTitulo());
            stmt.setString(2, image.getDescripcion());
            stmt.setString(3, image.getPalabrasClave());
            stmt.setString(4, image.getAutor());
            stmt.setString(5, image.getCreador());
            stmt.setString(6, image.getFechaCreacion().toString());
            stmt.setString(7, image.getRutaArchivo());
            stmt.setString(8, image.getIdentificador());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Error actualizando la imagen", e);
        }
    }

    public void eliminar(String identificador) {
        String sql = "DELETE FROM imagenes WHERE identificador = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Error eliminando la imagen", e);
        }
    }

    public List<Image> buscarPorCriterio(String criterio) {
        String sql = "SELECT * FROM imagenes WHERE LOWER(titulo) LIKE ? OR LOWER(descripcion) LIKE ? OR LOWER(palabras_clave) LIKE ?";
        List<Image> imagenes = new ArrayList<>();
        String like = "%" + criterio.toLowerCase() + "%";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, like);
            stmt.setString(2, like);
            stmt.setString(3, like);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    imagenes.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error buscando imágenes", e);
        }
        return imagenes;
    }

    private Image mapear(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt("id"));
        image.setIdentificador(rs.getString("identificador"));
        image.setTitulo(rs.getString("titulo"));
        image.setDescripcion(rs.getString("descripcion"));
        image.setPalabrasClave(rs.getString("palabras_clave"));
        image.setAutor(rs.getString("autor"));
        image.setCreador(rs.getString("creador"));
        image.setFechaCreacion(LocalDate.parse(rs.getString("fecha_creacion")));
        image.setFechaRegistro(LocalDate.parse(rs.getString("fecha_registro")));
        image.setRutaArchivo(rs.getString("ruta_archivo"));
        return image;
    }
}