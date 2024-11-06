package com.MSAlura.literAluraChallenge1.Modelos.Tables;

import com.MSAlura.literAluraChallenge1.Modelos.Record.DatosLibros;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Double numeroDescargas;
    private String idiomas;
    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Autor autores;




    public Libro() {
    }

    public Libro(DatosLibros dl) {
        this.titulo = dl.titulo();
        try {
            this.numeroDescargas = Double.valueOf(Double.valueOf(dl.numeroDeDescargas()).describeConstable().orElse(0.00));
        } catch (NumberFormatException e){
            this.numeroDescargas = 0.0;
        }
        this.idiomas =idiomaOrganizado (dl.idiomas());
    }



    @Override
    public String toString() {
        return "____________________________________________________________________\n" +
                "          📚📘📚📘📚📘  DETALLES DEL LIBRO  📘📚📘📚📘📚           \n" +
                " +------------------------+----------------------------------------+\n" +
                "-.-.-.-.-.-.-.-.-.-.-.-. 🆔 "+id+".   "+ "-.-.-.-.-.-.-.-.-.-.-.-.\n"+
                "📖 Título:             | \uD83C\uDF1F........." + titulo + "\n"+
                "✍️ Autor:              | \uD83C\uDF1F........." + (autores != null ? autores.getNombre() : "Desconocido") +"\n"+
                "🌐 Idioma:             | \uD83C\uDF1F........." + idiomas +"\n"+
                "🔄 No Descargas:       | \uD83C\uDF1F........." + numeroDescargas +"\n"+
                "_____________________________________________________________________\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Autor getAutores() {
        return autores;
    }

    public void setAutores(Autor autores) {
        //  autores.getLibros().add(this);
        this.autores = autores;
    }


    private String idiomaOrganizado(List<String> idiomas) {
        return (idiomas != null && !idiomas.isEmpty()) ? idiomas.get(0) : "Desconocido";
    }

}
