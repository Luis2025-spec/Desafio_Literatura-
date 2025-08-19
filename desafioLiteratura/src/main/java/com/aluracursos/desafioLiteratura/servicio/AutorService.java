package com.aluracursos.desafioLiteratura.servicio;

import com.aluracursos.desafioLiteratura.modelo.Autor;
import com.aluracursos.desafioLiteratura.repositorio.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    /**
     * Muestra los autores que estaban vivos en un año específico.
     * Un autor se considera vivo si:
     *  - No tiene fecha de fallecimiento (aún vive)
     *  - O falleció en o después del año indicado
     */
    public void mostrarAutoresVivosEnAnio(int anio) {
        List<Autor> autores = autorRepository
                .findByFechaNacimientoLessThanEqualAndFechaFallecimientoIsNullOrFechaFallecimientoGreaterThanEqual(
                        anio, anio
                );

        if (autores.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + anio);
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(a -> System.out.println("- " + a.getNombre()));
        }
    }
}
