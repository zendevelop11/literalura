package com.aluracursos.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoBookResponse(@JsonAlias("count") Integer totalLibros,
                               @JsonAlias("results") List<DatoBook> libros) { }
