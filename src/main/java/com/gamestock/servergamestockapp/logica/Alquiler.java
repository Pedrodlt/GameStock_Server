
package com.gamestock.servergamestockapp.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author pedro
 * Clase que representa un alquiler en el sistema.
 */
@Entity
public class Alquiler implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "juego_id")
    private Juego juego;
    private String fechaAlquiler;
    private String fechaDevolucion;
    private Double importe;
    private boolean activo; // Nuevo campo para identificar si el alquiler está en curso

    /**
     * Constructor por defecto.
     */
    public Alquiler() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param id Identificador único del alquiler.
     * @param cliente Nombre del cliente que alquila.
     * @param juego Nombre del juego que es alquilado.
     * @param fechaAlquiler Fecha en la que se alquila el juego.
     * @param fechaDevolucion Fecha en la que se devuelve el juego.
     * @param importe Precio de alquilar el juego.
     */
    public Alquiler(Long id, Cliente cliente, Juego juego, String fechaAlquiler, String fechaDevolucion, Double importe, boolean activo) {
        this.id = id;
        this.cliente = cliente;
        this.juego = juego;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
        this.importe = importe;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public String getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(String fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    } 

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }  
}
