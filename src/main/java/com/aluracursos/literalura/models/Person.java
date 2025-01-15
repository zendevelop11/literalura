package com.aluracursos.literalura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(nullable = true)
    private Integer birthYear;
    @Column(nullable = true)
    private Integer deadYear;

    @ManyToOne
    private Book libro;

    public Person(){}

    public Person(DatoPerson datoPerson){
        this.nombre = datoPerson.nombre();
        this.birthYear = datoPerson.birthYear();
        this.deadYear = datoPerson.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeadYear() {
        return deadYear;
    }

    public void setDeadYear(Integer deadYear) {
        this.deadYear = deadYear;
    }

    public Book getLibro() {
        return libro;
    }

    public void setLibro(Book libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return  "---------------------------------------\n"+
                "Nombre: "+ nombre + "\n"+
                "Fecha nacimiento: " + birthYear + "\n"+
                "Fecha de falleciento: " + deadYear + "\n"+
                "---------------------------------------\n";
    }
}
