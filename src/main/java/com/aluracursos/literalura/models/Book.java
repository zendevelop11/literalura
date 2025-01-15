package com.aluracursos.literalura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true)
    private String titulo;
    private String lenguaje;
    public Integer descargas;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> autores;

    public Book() {
    }

    public Book(DatoBook datoBook) {
        this.titulo = datoBook.titulo();
        this.lenguaje = datoBook.languages().get(0);
        this.descargas = datoBook.totalDescargas();
        this.autores = datoBook.autores().stream().map(Person::new).collect(Collectors.toList());
        autores.forEach(a -> a.setLibro(this));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public List<Person> getAutores() {
        return autores;
    }

    public void setAutores(List<Person> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "----------------------------------------\n" +
                "Titulo: " + titulo + "\n" +
                "Lenguaje: " + lenguaje + "\n" +
                "Descargas: " + descargas + "\n" +
                "Autor: " + autores.get(0).getNombre() + "\n" +
                "----------------------------------------\n";
    }
}
