/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestock.servergamestockapp.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pedro
 * Clase que representa un juego en el sistema.
 */
@Entity
public class Juego implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String genero;
    private String estudio;
    private Double precio;
    private int stock;
    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alquiler> listaAlquileres;
    
    /**
     * Constructor por defecto.
     */
    public Juego() {
    }
    
    /**
     * Constructor con parámetros.
     * 
     * @param id Identificador único del juego.
     * @param nombre Nombre del juego.
     * @param genero Genero del juego.
     * @param estudio Estudio creador del juego.
     * @param precio Precio del juego.
     * @param stock Cantidad de juegos del mismo tipo disponibles.
     */
    public Juego(Long id, String nombre, String genero, String estudio, Double precio, int stock, List<Alquiler> listaAlquileres) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.estudio = estudio;
        this.precio = precio;
        this.stock = stock;
        this.listaAlquileres = listaAlquileres;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Alquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    public void setListaAlquileres(List<Alquiler> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
    }
    
    // Métodos para controlar el stock
    public boolean alquilar() {
        if (stock > 0) {
            stock--; // Reduce el stock
            return true;
        }
        return false; // No hay stock disponible
    }

    public void devolver() {
        stock++; // Incrementa el stock
    }
}
