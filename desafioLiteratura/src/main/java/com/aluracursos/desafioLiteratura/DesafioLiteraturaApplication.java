package com.aluracursos.desafioLiteratura;

import com.aluracursos.desafioLiteratura.modelo.Autor;
import com.aluracursos.desafioLiteratura.modelo.Libro;
import com.aluracursos.desafioLiteratura.repositorio.AutorRepository;
import com.aluracursos.desafioLiteratura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@SpringBootApplication
public class DesafioLiteraturaApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mostrarMenu();
	}

	private void mostrarMenu() {
		Scanner sc = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("\n--- MENÚ PRINCIPAL ---");
			System.out.println("1. Listar autores");
			System.out.println("2. Buscar autor por nombre");
			System.out.println("3. Listar libros");
			System.out.println("4. Buscar libros por idioma");
			System.out.println("5. Salir");
			System.out.print("Seleccione una opción: ");
			opcion = sc.nextInt();
			sc.nextLine(); // limpiar buffer

			switch (opcion) {
				case 1:
					listarAutores();
					break;
				case 2:
					buscarAutorPorNombre(sc);
					break;
				case 3:
					listarLibros();
					break;
				case 4:
					buscarLibrosPorIdioma(sc);
					break;
				case 5:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción inválida, intente de nuevo.");
			}

		} while (opcion != 5);
	}

	private void listarAutores() {
		List<Autor> autores = autorRepository.findAll();
		autores.forEach(System.out::println);
	}

	private void buscarAutorPorNombre(Scanner sc) {
		System.out.print("Ingrese el nombre del autor: ");
		String nombre = sc.nextLine();
		Optional<Autor> autorOpt = autorRepository.findByNombre(nombre);
		if (autorOpt.isPresent()) {
			System.out.println("Autor encontrado: " + autorOpt.get());
		} else {
			System.out.println("No se encontró el autor.");
		}
	}

	private void listarLibros() {
		List<Libro> libros = libroRepository.findAll();
		libros.forEach(System.out::println);
	}

	private void buscarLibrosPorIdioma(Scanner sc) {
		System.out.print("Ingrese el idioma (ej. 'es', 'en'): ");
		String idioma = sc.nextLine();
		List<Libro> libros = libroRepository.findByIdioma(idioma);
		if (libros.isEmpty()) {
			System.out.println("No se encontraron libros en ese idioma.");
		} else {
			libros.forEach(System.out::println);
		}
	}
}
