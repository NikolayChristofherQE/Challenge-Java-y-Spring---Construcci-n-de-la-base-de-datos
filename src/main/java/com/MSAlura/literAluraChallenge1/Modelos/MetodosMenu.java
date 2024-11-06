package com.MSAlura.literAluraChallenge1.Modelos;

import com.MSAlura.literAluraChallenge1.Modelos.Record.DatosAutor;
import com.MSAlura.literAluraChallenge1.Modelos.Record.DatosLibros;
import com.MSAlura.literAluraChallenge1.Modelos.Record.DatosResultados;
import com.MSAlura.literAluraChallenge1.Modelos.Tables.Autor;
import com.MSAlura.literAluraChallenge1.Modelos.Tables.Libro;
import com.MSAlura.literAluraChallenge1.Repository.IAutorRepository;
import com.MSAlura.literAluraChallenge1.Repository.ILibroRepository;
import com.MSAlura.literAluraChallenge1.Service.ConsumoAPI;
import com.MSAlura.literAluraChallenge1.Service.ConvierteDatos;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MetodosMenu {
    // Variables
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private static String tituloLibro;
    private ILibroRepository repositorioLibro;
    private IAutorRepository repositorioAutor;



    public MetodosMenu(ILibroRepository repositorioLibro, IAutorRepository repositorioAutor) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioAutor = repositorioAutor;
    }

   // METODOS DEL MENU
    //1.🔍  Buscar Libro por Título
    public void buscarLibrosPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        try {
          tituloLibro = lectura.nextLine().toLowerCase();
            var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
           // System.out.println("JSON obtenido de la API: " + json);
            var datosBusqueda = conversor.obtenerDatos(json, DatosResultados.class);
           // System.out.println("Resultados de la búsqueda: " + datosBusqueda.resultados());
            System.out.println("URL: "+URL_BASE+"?search=" + tituloLibro.replace(" ", "+"));

            Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                    .findFirst();
            if (libroBuscado.isPresent()) {
                DatosLibros libroEncontrado = libroBuscado.get();

                // verifica si esta el libro en la base
                Optional<Libro> libroLocal = repositorioLibro.findByTituloContainsIgnoreCase(libroEncontrado.titulo());

                if (libroLocal.isPresent()) {
                    System.out.println("📚 Libro ya esta registrado en  la base de datos, a continuacion los detalles:");
                    System.out.println(libroLocal.get());
                } else {
                   //guarda
                    Libro nuevoLibro = new Libro();
                    nuevoLibro.setTitulo(libroEncontrado.titulo());
                    nuevoLibro.setNumeroDescargas(libroEncontrado.numeroDeDescargas());
                    nuevoLibro.setIdiomas(libroEncontrado.idiomas().get(0));

                    if (!libroEncontrado.autor().isEmpty()) {
                        DatosAutor autorDatos = libroEncontrado.autor().get(0);
                        Autor autor = new Autor("Desconocido");
                        autor.setNombre(autorDatos.nombre());
                        autor.setFechaDeNacimiento(autorDatos.fechaDeNacimiento());
                        autor.setFechaDefallecimiento(autorDatos.fechaDefallecimiento());
                        nuevoLibro.setAutores(autor);
                    } else {
                        nuevoLibro.setAutores(new Autor("Desconocido"));
                    }

                    repositorioLibro.save(nuevoLibro);
                    System.out.println("✅ El libro ha sido guardado exitosamente en la base de datos:\n" + nuevoLibro);
                }
            } else {
                System.out.println("⚠️ No se encontraron libros con el título especificado. Intenta con otro título.\n");
            }

        } catch (InputMismatchException e) {
            System.out.println("⚠️ ¡Ups! Solo se aceptan textos válidos para el título. Por favor, inténtalo nuevamente. 😊\n");
            lectura.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("⚠️ No se encontró ningún libro. Verifica el título e intenta nuevamente.\n");
        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error inesperado. Por favor, inténtalo más tarde. 😓\n");
        }
        System.out.println("🔄 Volviendo al menú principal...");
    }

    //2. 📖  Listar Libros Registrados
    public void   mostrarLibrosRegistrados() {
        List<Libro> libros=repositorioLibro.findAll();
        System.out.println("          📚 Libros registrados en la base de datos:                                 ");
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════");
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo).reversed())
                .forEach(System.out::println);
    }

    //3.🌐  Listar Libros por Idiomas
    public void buscarLibrosPorIdiomas() {
        var mensaje = """
                📚 Escribe el idioma de los libros que deseas buscar:
                -------------------------------------------------------
                1. es           ║   Español
                2. en           ║   Inglés
                3. fr           ║   Francés
                4. pt           ║   Portugués
                -------------------------------------------------------
                Escribe una opcion : por ejemplo: es
                """;
        System.out.println(mensaje);
        var idioma = lectura.nextLine().trim();
        List<Libro> librosPorIdioma = repositorioLibro.findLibroByIdiomas(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("❌ No se encontraron libros en la base de datos para el idioma ingresado.");
        } else {
            System.out.println("📚 Libros encontrados en el idioma: " + idioma.toUpperCase());
            System.out.println("═══════════════════════════════════════════════════════════════════════════════");
            int contador = 1;
            for (Libro libro : librosPorIdioma) {
                System.out.printf("Libro %d:\n", contador++);
                System.out.println(libro.toString());
            }
        }
    }

    //4.🏆  Top 10 Libros Más Descargados
    public void buscarTop10LibrosDescargados() {
// 1. Mostrar los libros más descargados en la base de datos
        List<Libro> librosEnBaseDeDatos = repositorioLibro.findAll();
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("🎉 ¡Aquí están los libros más descargados en nuestra base de datos! 📚");
        System.out.println("--------------------------------------------------------------------------------------");

        AtomicInteger contador = new AtomicInteger(1);
        librosEnBaseDeDatos.stream()
                .sorted(Comparator.comparing(Libro::getNumeroDescargas).reversed())
                .limit(10)
                .forEachOrdered(libro -> System.out.printf("  🔥 Libro %d: '%s' - con %, .0f descargas 💥\n",
                        contador.getAndIncrement(),
                        libro.getTitulo(),
                        libro.getNumeroDescargas()));

        // 2. Obtener el top 10 de libros más descargados en Gutendex
        System.out.println("══════════════════════════════════════════════════════════════════════════════════════");
        System.out.println("\n🌐 ¡Directo desde Gutendex! Los 10 libros más populares del momento: 🌟");
        System.out.println("---------------------------------------------------------------------------------------");
        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, DatosResultados.class);

        AtomicInteger contadorGutendex = new AtomicInteger(1);
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .forEachOrdered(libro -> System.out.printf("  📘 Libro %d: '%s' - " +
                                "con %, .0f descargas 🌍\n",
                        contadorGutendex.getAndIncrement(),
                        libro.titulo().toUpperCase(),
                        libro.numeroDeDescargas()));
    }




    //5. 👤  Buscar Autor por Nombre
    public void buscarAutoresPorNombre() {
        System.out.println("Escribe el Nombre del Autor que deseas Buscar: ");
        var nombreAutor = lectura.nextLine();

        List<Autor> autoresEncontrados = repositorioAutor.autoresPorNombre(nombreAutor);
        if (autoresEncontrados.isEmpty()) {
            System.out.println("❌ No se encontró ningún autor con el nombre especificado.");
        } else {
            System.out.println("📚 Autores encontrados:");
            autoresEncontrados.forEach(a -> {
                int totalDescargas = a.getLibros().stream()
                        .mapToInt(libro -> libro.getNumeroDescargas().intValue())
                        .sum();
                // System.out.printf("👤 Autor: %s - 📈 Número de Descargas: %d\n", a.getNombre(), totalDescargas);
                autoresEncontrados.forEach(System.out::println);
            });
        }
        System.out.println("---------------------------------------------------");
    }


    //6.🧑‍💼Listar Autores Registrados
    public void  mostrarAutoresRegistrados(){
        List<Autor> autores = repositorioAutor.findAll();
        if (autores.isEmpty()) {
            System.out.println("❌ No hay autores registrados en la base de datos.");
        } else {
            System.out.println("🧑‍💼 Autores registrados en la base de datos:");
            System.out.println("══════════════════════════════════════════════════════════════════════════════════════");

            autores.stream()
                    .sorted(Comparator.comparing(Autor::getNombre))
                    .forEach(autor -> System.out.printf("👤 Autor: %s\n", autor));
        }
        System.out.println("---------------------------------------------------");
    }


    //7. 📅  Listar Autores por Año
    public void buscarAutoresPorAnio() {
        System.out.println("Indica un año para consultar qué autores estaban vivos en el siglo correspondiente: ");
        int anioBuscado = lectura.nextInt();
        lectura.nextLine();

        int inicioSiglo = (anioBuscado / 100) * 100;
        int finSiglo = inicioSiglo + 99;

        List<Autor> autoresVivos = repositorioAutor.findByFechaDeNacimientoLessThanEqualAndFechaDefallecimientoGreaterThanEqualOrFechaDefallecimientoIsNull(finSiglo, inicioSiglo);

        if (autoresVivos.isEmpty()) {
            System.out.println("⚠️ No se encontraron autores que estuvieran vivos entre " + anioBuscado +".");
        } else {
            System.out.println("📜 Los autores que estaban vivos entre son:");
            autoresVivos.stream()
                    .sorted(Comparator.comparing(Autor::getNombre))
                    .forEach(autor -> {
                        String nombre = autor.getNombre() != null ? autor.getNombre() : "Sin nombre";
                        String fechaNacimiento = autor.getFechaDeNacimiento() != null ? autor.getFechaDeNacimiento().toString() : "Sin datos";
                        String fechaFallecimiento = autor.getFechaDefallecimiento() != null ? autor.getFechaDefallecimiento().toString() : "Sin datos";
                        String libro = (autor.getLibros() != null && !autor.getLibros().isEmpty()) ? autor.getLibros().get(0).getTitulo() : "Desconocido";

                        System.out.printf("👤 Autor: %s\n", autor);
                    });
        }
    }

    //8. 📊  Mostrar Estadísticas de Descargas
    public void mostrarEstadisticasDeDescarga() {
        List<Libro> libros = repositorioLibro.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la base de datos para generar estadísticas.");
            return;
        }
        DoubleSummaryStatistics estadisticasDescargas = libros.stream()
                .mapToDouble(libro -> libro.getNumeroDescargas() != null ? libro.getNumeroDescargas() : 0)
                .summaryStatistics();

        double totalDescargas = estadisticasDescargas.getSum();
        double promedioDescargas = estadisticasDescargas.getAverage();
        double minimoDescargas = estadisticasDescargas.getMin();
        double maximoDescargas = estadisticasDescargas.getMax();
        long numeroLibros = estadisticasDescargas.getCount();


        String estadisticas = """
                          📊 Estadísticas de Descargas:
                -------------------------------------------------------
                🔹 Total de descargas:      ║ %,.0f
                🔹 Promedio de descargas:   ║ %,.2f
                🔹 Mínimo de descargas:     ║ %,.0f
                🔹 Máximo de descargas:     ║ %,.0f
                🔹 Número de libros:        ║ %d
                """.formatted(totalDescargas, promedioDescargas, minimoDescargas, maximoDescargas, numeroLibros);
        System.out.println(estadisticas);
    }



}
