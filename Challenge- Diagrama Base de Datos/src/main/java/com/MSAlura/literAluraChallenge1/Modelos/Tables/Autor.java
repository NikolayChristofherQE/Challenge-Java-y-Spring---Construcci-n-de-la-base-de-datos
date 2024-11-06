package com.MSAlura.literAluraChallenge1.Modelos.Tables;

import com.MSAlura.literAluraChallenge1.Modelos.Record.DatosAutor;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDefallecimiento;
    private Double numeroDescargas;
    @OneToMany(mappedBy ="autores", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(String desconocido) {
    }
    public  Autor(){}

    public Autor( DatosAutor da) {
        this.nombre = da.nombre();
        this.fechaDeNacimiento = da.fechaDeNacimiento();
        this.fechaDefallecimiento = da.fechaDefallecimiento();
        try{
            this.numeroDescargas = Double.valueOf(da.numeroDescargas ());
        }catch (NumberFormatException e){
            this.numeroDescargas = 0.0;
        }
    }



    @Override
    public String toString() {
        return "_______________________________________________________\n" +
                "          ❄❄❄❄❄❄❄❄ ✍️ AUTOR  ❄❄❄❄❄❄❄❄           \n" +
                " +------------------------+---------------------------+\n" +
                "➤ Nombre:              | •" + nombre + "\n"+
                "➤ Fecha Nacimiento:    | •" + (fechaDeNacimiento != null ? fechaDeNacimiento : "Sin datos") +"\n"+
                "➤ Fecha Fallecimiento: | •" + (fechaDefallecimiento != null ? fechaDefallecimiento : "Sin datos")  +"\n"+
                "➤ libro:               | •" + (libros != null && !libros.isEmpty() ? libros.get(0).getTitulo() : "Desconocido") +"\n"+
                "_______________________________________________________\n";

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDefallecimiento() {
        return fechaDefallecimiento;
    }

    public void setFechaDefallecimiento(Integer fechaDefallecimiento) {
        this.fechaDefallecimiento = fechaDefallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}