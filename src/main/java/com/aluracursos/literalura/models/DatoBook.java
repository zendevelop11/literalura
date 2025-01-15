package com.aluracursos.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoBook(@JsonAlias("id") Long id,
                       @JsonAlias("title") String titulo,
                       @JsonAlias("authors") List<DatoPerson> autores,
                       @JsonAlias("languages") List<String> languages,
                       @JsonAlias("download_count") Integer totalDescargas
                   ) { }
