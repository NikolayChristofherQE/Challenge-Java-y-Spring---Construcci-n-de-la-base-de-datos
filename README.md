

#**📚 Proyecto LiterAlura**
LiterAlura es una aplicación interactiva de gestión de libros desarrollada en Java y Spring Boot, que permite a los usuarios consultar, registrar y gestionar información de libros y autores. Este proyecto es parte de un challenge de Alura y tiene como objetivo facilitar la organización y búsqueda de libros con diversas opciones de filtrado y visualización.

**🌟 Menú de Opciones**
El sistema de LiterAlura permite las siguientes acciones a través de su menú interactivo:


───────────────────────────────────────────────────────────────────────────────────────
                        📚 ¡Bienvenido(a) a literAlura! 📚 
───────────────────────────────────────────────────────────────────────────────────────
                                  🌟 MENÚ DE OPCIONES 🌟                                 
───────────────────────────────────────────────────────────────────────────────────────
 1️⃣  🔍 Buscar Libro por Título           
 2️⃣  📖 Listar Libros Registrados         
 3️⃣  🌐 Listar Libros por Idiomas         
 4️⃣  🏆 Top 10 Libros Más Descargados     
 5️⃣  👤 Buscar Autor por Nombre           
 6️⃣  🧑‍💼 Listar Autores Registrados      
 7️⃣  📅 Listar Autores por Año            
 8️⃣  📊 Mostrar Estadísticas de Descargas 
 0️⃣  🚪 Salir                             
───────────────────────────────────────────────────────────────────────────────────────
                    💡 **Elija una opción válida** 💡
───────────────────────────────────────────────────────────────────────────────────────



**📋**** Funcionalidades**
1.Buscar Libro por Título: Permite buscar libros específicos ingresando un título.
2. Listar Libros Registrados: Muestra todos los libros almacenados en la base de datos.
3. Listar Libros por Idiomas: Filtra y muestra los libros disponibles según el idioma.
4. Top 10 Libros Más Descargados: Lista los libros más populares según el número de descargas.
5. Buscar Autor por Nombre: Realiza una búsqueda de autores según el nombre ingresado.
6. Listar Autores Registrados: Muestra todos los autores registrados en la base de datos.
7. Listar Autores por Año: Filtra y lista autores de acuerdo con su año de nacimiento.
8. Mostrar Estadísticas de Descargas: Proporciona estadísticas detalladas de descargas para los libros registrados.
0. Salir: Cierra la aplicación.




🚀 **Tecnologías Utilizadas**
Java: Lenguaje de programación principal.
Spring Boot: Framework utilizado para la configuración y desarrollo de la aplicación.
PostgreSQL: Base de datos relacional para almacenar la información de libros y autores.
Hibernate: ORM (Object-Relational Mapping) para facilitar la interacción con la base de datos.



**📋 Ejemplo de Uso**
Aquí tienes un ejemplo de cómo usar algunas opciones del menú:


**Buscar Libro por Título (Opción 1):**
Ingresa el título del libro que deseas buscar. La aplicación mostrará los detalles del libro si está registrado.

1
**Ingrese el nombre del libro que desea buscar**
Romeo and Juliet

 🔍Buscando ...
URL: https://gutendex.com/books/?search=romeo+and+juliet
📚 Libro ya esta registrado en  la base de datos, a continuacion los detalles:
____________________________________________________________________
          📚📘📚📘📚📘  DETALLES DEL LIBRO  📘📚📘📚📘📚           
 +------------------------+----------------------------------------+
-.-.-.-.-.-.-.-.-.-.-.-. 🆔 10.   -.-.-.-.-.-.-.-.-.-.-.-.
📖 Título:             | 🌟.........Romeo and Juliet
✍️ Autor:              | 🌟.........Shakespeare, William
🌐 Idioma:             | 🌟.........en
🔄 No Descargas:       | 🌟.........62300.0
_____________________________________________________________________

🔄 Volviendo al menú principal...
═══════════════════════════════════════════════════════════════════════════════





**Top de los libros mas descargados (Opción 4):****Muestra una lista de los 10 libros de la base de datos y los 10 de la aplicacion
4
══════════════════════════════════════════════════════════════════════════════════════
🎉 ¡Aquí están los libros más descargados en nuestra base de datos! 📚
--------------------------------------------------------------------------------------
  🔥 Libro 1: 'Romeo and Juliet' - con  62,300 descargas 💥
  🔥 Libro 2: 'Pride and Prejudice' - con  58,812 descargas 💥
  🔥 Libro 3: 'Crime and Punishment' - con  17,649 descargas 💥
  🔥 Libro 4: 'Don Quijote' - con  12,877 descargas 💥
  🔥 Libro 5: 'A Christmas Carol in Prose; Being a Ghost Story of Christmas' - con  11,017 descargas 💥
  🔥 Libro 6: 'Wuthering Heights' - con  10,956 descargas 💥
  🔥 Libro 7: 'The Adventures of Tom Sawyer, Complete' - con  10,656 descargas 💥
  🔥 Libro 8: 'White Nights and Other Stories: The Novels of Fyodor Dostoevsky, Volume X' - con  10,600 descargas 💥
  🔥 Libro 9: 'Narrative of the Captivity and Restoration of Mrs. Mary Rowlandson' - con  10,309 descargas 💥
  🔥 Libro 10: 'The Tragical History of Doctor Faustus: From the Quarto of 1604' - con  6,859 descargas 💥
══════════════════════════════════════════════════════════════════════════════════════





🌐 ¡Directo desde Gutendex! Los 10 libros más populares del momento: 🌟
---------------------------------------------------------------------------------------

 🔍Buscando ...
  📘 Libro 1: 'FRANKENSTEIN; OR, THE MODERN PROMETHEUS' - con  78,467 descargas 🌍
  📘 Libro 2: 'ROMEO AND JULIET' - con  62,300 descargas 🌍
  📘 Libro 3: 'PRIDE AND PREJUDICE' - con  58,812 descargas 🌍
  📘 Libro 4: 'MOBY DICK; OR, THE WHALE' - con  58,362 descargas 🌍
  📘 Libro 5: 'ALICE'S ADVENTURES IN WONDERLAND' - con  51,356 descargas 🌍
  📘 Libro 6: 'LITTLE WOMEN; OR, MEG, JO, BETH, AND AMY' - con  50,288 descargas 🌍
  📘 Libro 7: 'THE SCARLET LETTER' - con  45,002 descargas 🌍
  📘 Libro 8: 'THE ENCHANTED APRIL' - con  42,584 descargas 🌍
  📘 Libro 9: 'A ROOM WITH A VIEW' - con  40,304 descargas 🌍
  📘 Libro 10: 'MIDDLEMARCH' - con  39,632 descargas 🌍
═══════════════════════════════════════════════════════════════════════════════





**Listar Autores por Años (Opción 7):**muestra los autores vivos en ese lapso de tiempo
7
Indica un año para consultar qué autores estaban vivos en el siglo correspondiente: 
1800
**📜 Los autores que estaban vivos entre son:**
👤 Autor: _______________________________________________________
          ❄❄❄❄❄❄❄❄ ✍️ AUTOR  ❄❄❄❄❄❄❄❄           
 +------------------------+---------------------------+
➤ Nombre:              | •Austen, Jane
➤ Fecha Nacimiento:    | •1775
➤ Fecha Fallecimiento: | •1817
➤ libro:               | •Pride and Prejudice
_______________________________________________________




👤 Autor: _______________________________________________________
          ❄❄❄❄❄❄❄❄ ✍️ AUTOR  ❄❄❄❄❄❄❄❄           
 +------------------------+---------------------------+
➤ Nombre:              | •Brontë, Emily
➤ Fecha Nacimiento:    | •1818
➤ Fecha Fallecimiento: | •1848
➤ libro:               | •Wuthering Heights
_______________________________________________________



Gracias por usar LiterAlura! 📚😊
