package com.distribuidas.app.model;

import java.time.LocalDate;

public class Image {
    private int id;
    private String identificador;
    private String titulo;
    private String descripcion;
    private String palabrasClave;
    private String autor;
    private String creador;
    private LocalDate fechaCreacion;
    private LocalDate fechaRegistro;
    private String rutaArchivo;

    public Image() {
    }

    public Image(int id, String identificador, String titulo, String descripcion, String palabrasClave,
                 String autor, String creador, LocalDate fechaCreacion, LocalDate fechaRegistro, String rutaArchivo) {
        this.id = id;
        this.identificador = identificador;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.palabrasClave = palabrasClave;
        this.autor = autor;
        this.creador = creador;
        this.fechaCreacion = fechaCreacion;
        this.fechaRegistro = fechaRegistro;
        this.rutaArchivo = rutaArchivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
}