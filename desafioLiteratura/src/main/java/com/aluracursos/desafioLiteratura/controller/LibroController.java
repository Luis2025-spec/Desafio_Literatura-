package com.aluracursos.desafioLiteratura.controller;

import com.aluracursos.desafioLiteratura.modelo.Libro;
import com.aluracursos.desafioLiteratura.repositorio.LibroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Obtener todos los libros
    @GetMapping
    public List<Libro> obtenerLibros() {
        return libroRepository.findAll();
    }

    // Buscar libros por título
    @GetMapping("/buscar")
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return libroRepository.findByTitleContainingIgnoreCase(titulo);
    }

    // Buscar libros por idioma
    @GetMapping("/idioma")
    public List<Libro> buscarPorIdioma(@RequestParam String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    // Guardar un nuevo libro
    @PostMapping
    public Libro guardarLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }
}
