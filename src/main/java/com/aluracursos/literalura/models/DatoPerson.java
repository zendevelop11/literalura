package com.aluracursos.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoPerson(@JsonAlias("birth_year") Integer birthYear,
                         @JsonAlias("death_year") Integer deathYear,
                         @JsonAlias("name") String nombre) { }
