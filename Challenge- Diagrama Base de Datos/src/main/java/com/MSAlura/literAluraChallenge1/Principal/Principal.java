package com.MSAlura.literAluraChallenge1.Principal;

import com.MSAlura.literAluraChallenge1.Modelos.*;
import com.MSAlura.literAluraChallenge1.Repository.IAutorRepository;
import com.MSAlura.literAluraChallenge1.Repository.ILibroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

public class Principal {
    //Variables
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final Scanner lectura = new Scanner(System.in);
    private ILibroRepository repositorioLibro;
    private IAutorRepository repositorioAutor;
   private MetodosMenu metodosMenu;


    public Principal(ILibroRepository repositorioLibro, IAutorRepository repositorioAutor) {
        this.repositorioLibro = repositorioLibro;
        this.repositorioAutor = repositorioAutor;
        this.metodosMenu = new MetodosMenu(repositorioLibro, repositorioAutor);
    }


    //Metodo mostrar menu
    public void VisualizarMenu() throws JsonProcessingException {
        var opcion = -1;
        while (opcion != 0) {
            var Menu = """
                    ═══════════════════════════════════════════════════════════════════════════════
                                                          📚 ¡Bienvenido(a) a literAlura! 📚
                         ═══════════════════════════════════════════════════════════════════════════════
                                                          🌟 MENÚ DE OPCIONES 🌟
                         ═══════════════════════════════════════════════════════════════════════════════
                           1️⃣  🔍  Buscar Libro por Título
                           2️⃣  📖  Listar Libros Registrados
                           3️⃣  🌐  Listar Libros por Idiomas
                           4️⃣  🏆  Top 10 Libros Más Descargados 
                           5️⃣  👤  Buscar Autor por Nombre
                           6️⃣  🧑‍💼Listar Autores Registrados
                           7️⃣  📅  Listar Autores por Año
                           8️⃣  📊  Mostrar Estadísticas de Descargas
                           0️⃣  🚪  Salir
                         ═══════════════════════════════════════════════════════════════════════════════
                                          💡 **Elija una opción válida** 💡
                         ═══════════════════════════════════════════════════════════════════════════════
                    
                    """;
            System.out.println(Menu);
            try {

                opcion = lectura.nextInt();
                lectura.nextLine();

                switch (opcion) {
                    case 1:
                        metodosMenu.buscarLibrosPorTitulo();
                        break;
                    case 2:
                        metodosMenu.mostrarLibrosRegistrados();
                        break;
                    case 3:
                        metodosMenu.buscarLibrosPorIdiomas();
                        break;
                    case 4:
                        metodosMenu.buscarTop10LibrosDescargados();
                        break;
                    case 5:
                        metodosMenu.buscarAutoresPorNombre();
                        break;
                    case 6:
                        metodosMenu.mostrarAutoresRegistrados();
                        break;
                    case 7:
                        metodosMenu.buscarAutoresPorAnio();
                        break;
                    case 8:
                        metodosMenu.mostrarEstadisticasDeDescarga();
                        break;
                    case 0:
                        System.out.println("\uD83D\uDED1 La aplicación se está cerrando. ¡Gracias por usar nuestros servicios! \uD83D\uDC4B\n");
                        break;
                    default:
                        System.out.println(" ❌ ¡Esa opción no es válida! Por favor, selecciona una opción del menú. \uD83D\uDD04\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠\uFE0F ¡Ups! Solo se aceptan números. Por favor, inténtalo nuevamente. \uD83D\uDE0A\n");
                lectura.next();
            }
        }
    }

}