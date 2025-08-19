package com.aluracursos.desafioLiteratura.servicio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aluracursos.desafioLiteratura.modelo.Libro;
import com.aluracursos.desafioLiteratura.modelo.Autor;
import com.aluracursos.desafioLiteratura.repositorio.LibroRepository;
import com.aluracursos.desafioLiteratura.repositorio.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class LibroService {


    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void buscarYGuardarLibros(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;

        try {
            JsonNode raiz = objectMapper.readTree(new URL(url));
            JsonNode resultados = raiz.get("results");

            for (JsonNode nodoLibro : resultados) {
                Libro libro = new Libro();
                libro.setTitle(nodoLibro.get("title").asText());

                // Idioma
                JsonNode idiomas = nodoLibro.get("languages");
                if (idiomas != null && idiomas.isArray() && idiomas.size() > 0) {
                    libro.setIdioma(idiomas.get(0).asText());
                }

                // Conteo de descargas
                libro.setDownloadCount(nodoLibro.get("download_count").asInt());

                // Autor
                JsonNode autores = nodoLibro.get("authors");
                if (autores != null && autores.isArray() && autores.size() > 0) {
                    String nombreAutor = autores.get(0).get("name").asText();
                    Optional<Autor> autorExistente = autorRepository.findByNombreIgnoreCase(nombreAutor);

                    Autor autor = autorExistente.orElseGet(() -> {
                        Autor nuevoAutor = new Autor();
                        nuevoAutor.setNombre(nombreAutor);
                        return autorRepository.save(nuevoAutor);
                    });

                    libro.setAutor(autor);
                }

                // Guardar libro
                libroRepository.save(libro);
            }

            System.out.println("✅ Libros guardados correctamente.");

        } catch (IOException e) {
            System.out.println("❌ Error al obtener datos: " + e.getMessage());
        }
    }
    public long contarLibrosPorIdioma(String idioma) {
        return libroRepository.countByIdioma(idioma);
    }
    public void estadisticasPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByIdioma(idioma);

        long total = libros.size();
        OptionalInt maxDescargas = libros.stream()
                .mapToInt(Libro::getDownloadCount)
                .max();

        System.out.println("Idioma: " + idioma);
        System.out.println("Cantidad total: " + total);
        maxDescargas.ifPresent(max ->
                System.out.println("Mayor número de descargas: " + max)
        );
    }
}

