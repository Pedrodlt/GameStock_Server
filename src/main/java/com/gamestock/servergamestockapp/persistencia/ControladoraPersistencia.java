
package com.gamestock.servergamestockapp.persistencia;

import com.gamestock.servergamestockapp.logica.Alquiler;
import com.gamestock.servergamestockapp.logica.Cliente;
import com.gamestock.servergamestockapp.logica.Juego;
import com.gamestock.servergamestockapp.logica.User;
import com.gamestock.servergamestockapp.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pedro
 * Clase que gestiona la persistencia de los usuarios en la base de datos.
 */
@Repository
public class ControladoraPersistencia {
    
    UserJpaController userJPA = new UserJpaController();
    JuegoJpaController juegoJPA = new JuegoJpaController();
    ClienteJpaController clienteJPA = new ClienteJpaController();
    AlquilerJpaController alquilerJPA = new AlquilerJpaController();
    
    // MÉTODOS PARA USERS -----------------------------------
    /**
     * Crea un nuevo usuario en la base de datos.
     * 
     * @param user Objeto User que contiene la información del usuario a crear.
     */
    public void crearUser (User user) {
        userJPA.create(user);
    }
    
    /**
     * Elimina un usuario de la base de datos.
     * 
     * @param id Identificador único del usuario a eliminar.
     */
    
    public void eliminarUser (Long id) {
        try {
            userJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Trae una lista de todos los usuarios en la base de datos.
     * 
     * @return Lista de objetos User.
     */
    
    public List<User> traerUsers () {
    return userJPA.findUserEntities();
    }
    
    /**
     * Edita la información de un usuario en la base de datos.
     * 
     * @param user Objeto User con la información actualizada del usuario.
     */
    
    public void editarUser (User user) {
        try {
            userJPA.edit(user);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id Identificador único del usuario.
     * @return Objeto User correspondiente al ID dado.
     */
    
     public User obtenerUser(Long id) {
        return userJPA.findUser(id);
    }
     
      /**
     * Comprueba si un nombre de usuario existe en la base de datos.
     * 
     * @param username Nombre de usuario a comprobar.
     * @return {@code true} si el nombre de usuario existe, {@code false} en caso contrario.
     */
     
    public boolean traerUsername (String username){
        return userJPA.existeUsername(username);
    }
    
     /**
     * Obtiene un usuario por su nombre de usuario.
     * 
     * @param username Nombre de usuario.
     * @return Objeto User correspondiente al nombre de usuario dado.
     */
      
    public User obtenerUserName (String username){
        return userJPA.findUserByUsername(username);
    }
    
    // MÉTODOS PARA JUEGOS -----------------------------------

    /**
     * Crea un nuevo juego en la base de datos.
     *
     * @param juego Objeto Juego que contiene la información del juego a crear.
     */
    public void crearJuego(Juego juego) {
        juegoJPA.create(juego);
    }

    /**
     * Elimina un juego de la base de datos.
     *
     * @param id Identificador único del juego a eliminar.
     */
    public void eliminarJuego(Long id) {
        try {
            juegoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Trae una lista de todos los juegos en la base de datos.
     *
     * @return Lista de objetos Juego.
     */
    public List<Juego> traerJuegos() {
        return juegoJPA.findJuegoEntities();
    }

    /**
     * Edita la información de un juego en la base de datos.
     *
     * @param juego Objeto Juego con la información actualizada del juego.
     */
    public void editarJuego(Juego juego) {
        try {
            juegoJPA.edit(juego);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene un juego por su ID.
     *
     * @param id Identificador único del juego.
     * @return Objeto Juego correspondiente al ID dado.
     */
    public Juego obtenerJuego(Long id) {
        return juegoJPA.findJuego(id);
    }

    /**
     * Comprueba si existe un juego con el nombre dado en la base de datos.
     *
     * @param nombre Nombre del juego a comprobar.
     * @return {@code true} si el juego existe, {@code false} en caso contrario.
     */
    public boolean existeJuego(String nombre) {
        return juegoJPA.existeJuego(nombre);
    }

    /**
     * Obtiene un juego por su nombre.
     *
     * @param nombre Nombre del juego.
     * @return Objeto Juego correspondiente al nombre dado.
     */
    public Juego obtenerJuegoNombre(String nombre) {
        return juegoJPA.findJuegoByName(nombre);
    }

    /**
     * Alquila un juego, reduciendo su stock si hay disponibilidad.
     *
     * @param juegoId Identificador único del juego.
     * @return {@code true} si el alquiler fue exitoso, {@code false} si no hay stock.
     */
    public boolean alquilarJuego(Long juegoId) {
        return juegoJPA.alquilarJuego(juegoId);
    }

    /**
     * Devuelve un juego, incrementando su stock.
     *
     * @param juegoId Identificador único del juego.
     */
    public void devolverJuego(Long juegoId) {
        juegoJPA.devolverJuego(juegoId);
    }

    // MÉTODOS PARA CLIENTES -----------------------------------

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param cliente Objeto Cliente que contiene la información del cliente a crear.
     */
    public void crearCliente(Cliente cliente) {
        clienteJPA.create(cliente);
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param id Identificador único del cliente a eliminar.
     */
    public void eliminarCliente(Long id) {
        try {
            clienteJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Trae una lista de todos los clientes en la base de datos.
     *
     * @return Lista de objetos Cliente.
     */
    public List<Cliente> traerClientes() {
        return clienteJPA.findClienteEntities();
    }

    /**
     * Edita la información de un cliente en la base de datos.
     *
     * @param cliente Objeto Cliente con la información actualizada del cliente.
     */
    public void editarCliente(Cliente cliente) {
        try {
            clienteJPA.edit(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id Identificador único del cliente.
     * @return Objeto Cliente correspondiente al ID dado.
     */
    public Cliente obtenerCliente(Long id) {
        return clienteJPA.findCliente(id);
    }

    // MÉTODOS PARA ALQUILERES -----------------------------------

    /**
     * Crea un nuevo alquiler en la base de datos.
     *
     * @param alquiler Objeto Alquiler que contiene la información del alquiler a crear.
     */
    public void crearAlquiler(Alquiler alquiler) {
        alquilerJPA.create(alquiler);
    }

    /**
     * Elimina un alquiler de la base de datos.
     *
     * @param id Identificador único del alquiler a eliminar.
     */
    public void eliminarAlquiler(Long id) {
        try {
            alquilerJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Trae una lista de todos los alquileres en la base de datos.
     *
     * @return Lista de objetos Alquiler.
     */
    public List<Alquiler> traerAlquileres() {
        return alquilerJPA.findAlquilerEntities();
    }

    /**
     * Edita la información de un alquiler en la base de datos.
     *
     * @param alquiler Objeto Alquiler con la información actualizada del alquiler.
     */
    public void editarAlquiler(Alquiler alquiler) {
        try {
            alquilerJPA.edit(alquiler);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene un alquiler por su ID.
     *
     * @param id Identificador único del alquiler.
     * @return Objeto Alquiler correspondiente al ID dado.
     */
    public Alquiler obtenerAlquiler(Long id) {
        return alquilerJPA.findAlquiler(id);
    }

    /**
     * Trae una lista de todos los alquileres activos.
     *
     * @return Lista de objetos Alquiler que están activos.
     */
    public List<Alquiler> traerAlquileresActivos() {
        return alquilerJPA.findActiveRentals();
    }

    /**
     * Finaliza un alquiler marcándolo como inactivo.
     *
     * @param id Identificador único del alquiler a finalizar.
     */
    public void finalizarAlquiler(Long id) {
        try {
            Alquiler alquiler = alquilerJPA.findAlquiler(id);
            alquiler.setActivo(false);
            alquilerJPA.edit(alquiler);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Trae una lista de alquileres asociados a un cliente específico.
     *
     * @param clienteId Identificador único del cliente.
     * @return Lista de objetos Alquiler asociados al cliente dado.
     */
    public List<Alquiler> traerAlquileresPorCliente(Long clienteId) {
        return alquilerJPA.findRentalsByCliente(clienteId);
    }

    /**
     * Trae una lista de alquileres asociados a un juego específico.
     *
     * @param juegoId Identificador único del juego.
     * @return Lista de objetos Alquiler asociados al juego dado.
     */
    public List<Alquiler> traerAlquileresPorJuego(Long juegoId) {
        return alquilerJPA.findRentalsByJuego(juegoId);
    }
    
}
