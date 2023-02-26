package com.example.reproductor2;

public class VideosModelo {

    String id;
    String path;
    String titulo;
    String size;
    String resolucion;
    String duracion;
    String displayName;
    String wh;

    public VideosModelo(String id, String path, String titulo, String size, String resolucion,
                        String duracion, String displayName, String wh) {
        this.id = id;
        this.path = path;
        this.titulo = titulo;
        this.size = size;
        this.resolucion = resolucion;
        this.duracion = duracion;
        this.displayName = displayName;
        this.wh = wh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh;
    }
}
