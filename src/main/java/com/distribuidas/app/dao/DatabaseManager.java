package com.distribuidas.app.dao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gestiona la conexión con la base de datos SQLite que utiliza la aplicación.
 */
public class DatabaseManager {

    private static final Path DB_PATH = Paths.get("data", "imagenes.db");
    private static final String JDBC_URL = "jdbc:sqlite:" + DB_PATH.toAbsolutePath();
    private static DatabaseManager instance;

    private DatabaseManager() {
        inicializar();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    private void inicializar() {
        try {
            Path carpeta = DB_PATH.getParent();
            if (carpeta != null && !Files.exists(carpeta)) {
                Files.createDirectories(carpeta);
            }
            File db = DB_PATH.toFile();
            boolean nuevo = !db.exists();
            if (nuevo) {
                try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate("CREATE TABLE usuarios (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nombre TEXT NOT NULL UNIQUE," +
                            "password TEXT NOT NULL," +
                            "rol TEXT NOT NULL DEFAULT 'USUARIO'");
                    stmt.executeUpdate("INSERT INTO usuarios (nombre, password, rol) VALUES ('admin', 'admin', 'ADMIN')");

                    stmt.executeUpdate("CREATE TABLE imagenes (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "identificador TEXT NOT NULL UNIQUE," +
                            "titulo TEXT NOT NULL," +
                            "descripcion TEXT," +
                            "palabras_clave TEXT," +
                            "autor TEXT NOT NULL," +
                            "creador TEXT NOT NULL," +
                            "fecha_creacion TEXT NOT NULL," +
                            "fecha_registro TEXT NOT NULL," +
                            "ruta_archivo TEXT NOT NULL");
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error inicializando la base de datos", e);
        }
    }
}