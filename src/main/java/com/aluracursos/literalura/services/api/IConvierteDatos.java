package com.aluracursos.literalura.services.api;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
