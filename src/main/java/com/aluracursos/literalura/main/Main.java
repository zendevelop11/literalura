package com.aluracursos.literalura.main;

import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.models.DatoBookResponse;
import com.aluracursos.literalura.models.Person;
import com.aluracursos.literalura.repository.IBookRepository;
import com.aluracursos.literalura.services.api.ConsumoAPI;
import com.aluracursos.literalura.services.api.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private IBookRepository repository;
    private Optional<Book> bookFind;
    private List<Book> books;

    public Main(IBookRepository repository) {
        this.repository = repository;
    }

    public void menu() {
        var opc = -1;
        while (opc != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                                 
                    0 - Salir
                    """;
            System.out.println(menu);
            opc = Integer.valueOf(sc.nextLine());

            switch (opc) {
                case 1:
                    getBookByTitle();
                    break;
                case 2:
                    getAllBooks();
                    break;
                case 3:
                    getAllAutors();
                    break;
                case 4:
                    getAuthorsAliveInYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                default:
                    System.out.println("Opc invalida!");
            }
        }
    }

    private void listBooksByLanguage() {
        List<String> languages = repository.findDistinctLanguages();

        if (languages.isEmpty()) {
            System.out.println("No hay idiomas disponibles.");
            return;
        }

        System.out.println("Idiomas disponibles:");
        languages.forEach(System.out::println);

        String selectedLanguage;
        List<Book> books;
        do {
            System.out.println("Seleccione un idioma de la lista:");
            selectedLanguage = sc.nextLine();

            if (!languages.contains(selectedLanguage)) {
                System.out.println("Idioma no válido. Por favor, seleccione un idioma de la lista.");
                books = null;
            } else {
                books = repository.findByLenguaje(selectedLanguage);
            }
        } while (books == null);

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma " + selectedLanguage + ".");
        } else {
            System.out.println("Libros en el idioma " + selectedLanguage + ":");
            books.forEach(book -> System.out.println(book.toString()));
        }
    }

    private void getAuthorsAliveInYear() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        int year = Integer.parseInt(sc.nextLine());

        List<Person> authors = repository.findAuthorsAliveInYear(year);

        if (authors.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + year + ".");
        } else {
            System.out.println("Autores vivos en el año " + year + ":");
            authors.forEach(author -> System.out.println(author.toString()));
        }
    }

    private void getAllBooks(){
        books = repository.findAll();
        books.stream()
                .forEach(System.out::println);
    }

    private void getAllAutors() {
        List<Object[]> authors = repository.findUniqueAuthors();

        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            authors.forEach(author -> System.out.printf(
                    "---------------------------------------\n" +
                            "Nombre: %s\n" +
                            "Fecha nacimiento: %s\n" +
                            "Fecha de fallecimiento: %s\n" +
                            "---------------------------------------\n",
                    author[0], // Nombre
                    author[1] != null ? author[1] : "Desconocida", // Año de nacimiento
                    author[2] != null ? author[2] : "Desconocida"  // Año de fallecimiento
            ));
        }
    }

    private void getBookByTitle() {
        System.out.println("Escribe el libro que desea buscar: ");
        var nombreLibro = sc.nextLine();
        bookFind = repository.findByTituloContainingIgnoreCase(nombreLibro);
        if (bookFind.isPresent()) {
            System.out.println(bookFind.get().toString());
        } else {
            saveFromUrl(nombreLibro);
        }
    }

    private void saveFromUrl(String nombreLibro) {
        var json = consumoAPI.obtenerDatos(URL_BASE+nombreLibro.replace(" ","%20"));
        DatoBookResponse bookResponse = convierteDatos.obtenerDatos(json, DatoBookResponse.class);

        if (bookResponse.totalLibros() > 0 ) {
            saveBook(bookResponse);
            showBook(bookResponse);
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void saveBook(DatoBookResponse bookResponse) {
        Book book = new Book(bookResponse.libros().get(0));
        repository.save(book);
    }

    private void showBook(DatoBookResponse bookResponse) {
        String resultado = bookResponse.libros().stream()
                .limit(1)
                .map(libro -> String.format(
                        "------------------------- \n titulo: %s \n autores: %s \n languages: %s \n totalDescargas: %d \n-------------------------\n",
                        libro.titulo(),
                        libro.autores().get(0).nombre(),
                        libro.languages().stream().collect(Collectors.joining(", ")),
                        libro.totalDescargas()
                ))
                .collect(Collectors.joining());
        System.out.println(resultado);
    }
}