package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT DISTINCT p.nombre, p.birthYear, p.deadYear FROM Person p")
    List<Object[]> findUniqueAuthors();

    @Query("""
       SELECT p FROM Person p 
       WHERE (:year BETWEEN p.birthYear AND COALESCE(p.deadYear, :year))
       """)
    List<Person> findAuthorsAliveInYear(@Param("year") Integer year);

    @Query("SELECT DISTINCT b.lenguaje FROM Book b")
    List<String> findDistinctLanguages();

    @Query("SELECT b FROM Book b JOIN b.lenguaje l WHERE l = :language")
    List<Book> findBooksByLanguage(@Param("language") String language);

    List<Book> findByLenguaje(String lenguaje);
}
