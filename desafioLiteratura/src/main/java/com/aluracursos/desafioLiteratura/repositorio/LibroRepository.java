package com.aluracursos.desafioLiteratura.repositorio;

import com.aluracursos.desafioLiteratura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libro por título (ignorando mayúsculas/minúsculas)
    List<Libro> findByTitleContainingIgnoreCase(String title);

    // Buscar libros por idioma exacto
    List<Libro> findByIdioma(String idioma);

    long countByIdioma(String idioma);
}

