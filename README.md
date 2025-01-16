# Proyecto: Catálogo de Libros Gutendex

## Descripción

Esta aplicación Spring Boot se conecta a una base de datos PostgreSQL para almacenar y gestionar información de libros obtenida de la API Gutendex. Permite realizar diversas consultas y análisis sobre los libros y sus autores.

### Funcionalidades

- **Búsqueda de libros por título**: Encuentra libros específicos en la base de datos.
- **Listado de libros registrados**: Muestra todos los libros almacenados.
- **Listado de autores registrados**: Presenta una lista completa de autores.
- **Listar autores vivos en un determinado año**: Muestra los autores que estaban vivos en un año específico.
- **Listar libros por idiomas**: Filtra los libros según su idioma.

### Tecnologías Utilizadas

- **Spring Boot**: Framework Java para desarrollo de aplicaciones empresariales.
- **PostgreSQL**: Sistema de gestión de bases de datos relacionales.
- **Gutendex API**: API pública para obtener metadatos de libros de Project Gutenberg.
- **Java 17**: Lenguaje de programación principal.
- **Maven**: Gestor de dependencias.
- **Jackson**: Biblioteca para mapear JSON a objetos Java.

## Instrucciones de Uso

### Clonar el repositorio

```bash
git clone https://github.com/tuusuario/tu-repositorio.git
```

### Configurar la base de datos
- Crear una base de datos PostgreSQL.
- Configurar las credenciales de la base de datos en el archivo ```application.properties.```
- Construir la aplicación
- Ejecutar el comando Maven o Gradle para construir el proyecto:

```bash
mvn clean install
```

Ejecutar la clase principal de la aplicación Spring Boot:

```bash
java -jar target/nombre-del-archivo.jar

```
## Interactuar con la aplicación
Utilizar la consola para interactura con las opciones: 

```java
1 - Buscar libro por titulo
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idiomas
                                 
0 - Salir
```




