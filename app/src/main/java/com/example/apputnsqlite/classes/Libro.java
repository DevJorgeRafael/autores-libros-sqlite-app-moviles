package com.example.apputnsqlite.classes;

public class Libro {
    private int Id;
    private String Titulo;
    private int IdAutor;
    private String Isbn;
    private int AnioPublicacion;
    private int Revision;
    private int NroHojas;

    public Libro(int id, String titulo, int idAutor, String isbn, int anioPublicacion, int revision, int nroHojas) {
        Id = id;
        Titulo = titulo;
        IdAutor = idAutor;
        Isbn = isbn;
        AnioPublicacion = anioPublicacion;
        Revision = revision;
        NroHojas = nroHojas;
    }

    public int getId() {
        return Id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public int getIdAutor() {
        return IdAutor;
    }

    public String getIsbn() {
        return Isbn;
    }

    public int getAnioPublicacion() {
        return AnioPublicacion;
    }

    public int getRevision() {
        return Revision;
    }

    public int getNroHojas() {
        return NroHojas;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setIdAutor(int idAutor) {
        IdAutor = idAutor;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        AnioPublicacion = anioPublicacion;
    }

    public void setRevision(int revision) {
        Revision = revision;
    }

    public void setNroHojas(int nroHojas) {
        NroHojas = nroHojas;
    }
}
