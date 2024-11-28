
package com.gamestock.servergamestockapp.logica;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author pedro
 * Clase que representa un cliente en el sistema.
 */
@Entity
public class Cliente implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cliente") // Ignorar el cliente dentro de cada alquiler
    private List<Alquiler> alquileres;
    
    /**
     * Constructor por defecto.
     */
    public Cliente() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id Identificador único del cliente.
     * @param nombre Nombre del cliente.
     * @param apellido1 Primer apellido del cliente.
     * @param apellido2 Segundo apellido del cliente.
     * @param email Correo electrónico del cliente.
     * @param telefono Telefono del cliente.
     * @param alquileres
     */

    public Cliente(Long id, String nombre, String apellido1, String apellido2, String email, String telefono, List<Alquiler> alquileres) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
        this.alquileres = alquileres;
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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }
}
