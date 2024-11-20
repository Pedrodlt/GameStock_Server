
package com.gamestock.servergamestockapp.logica;

import com.gamestock.servergamestockapp.persistencia.ControladoraPersistencia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedro
 * Clase que controla la lógica de gestión de usuarios en el sistema.
 */
@Service
public class Controladora {
    
    @Autowired
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    // MÉTODOS PARA USERS -----------------------------------
    
    /**
     * Crea un nuevo usuario en el sistema.
     * 
     * @param user Objeto User que contiene la información del usuario a crear.
     */
    
     public void crearUser (User user) {
        controlPersis.crearUser(user);
    }
    
      /**
     * Elimina un usuario del sistema.
     * 
     * @param id Identificador único del usuario a eliminar.
     */
     
    public void eliminarUser (Long id) {
        controlPersis.eliminarUser(id);
    }
    
    /**
     * Trae una lista de todos los usuarios en el sistema.
     * 
     * @return Lista de objetos User.
     */
    
    public List<User> traerUser() {
        return controlPersis.traerUsers();
    }
    
    /**
     * Edita la información de un usuario existente.
     * 
     * @param user Objeto User con la información actualizada del usuario.
     */
    
    public void editarUser (User user) {
        controlPersis.editarUser(user);
    }
    
     /**
     * Muestra una lista de todos los usuarios en el sistema.
     * 
     * @return Lista de objetos User.
     */
    
    public List<User> mostrarUsers(){
        List <User> ciudadanosCoincidentes =controlPersis.traerUsers().stream()
                 .toList(); 
        return ciudadanosCoincidentes;
    
    }
    
    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id Identificador único del usuario.
     * @return Objeto User correspondiente al ID dado.
     */
    
    public User obtenerUser (Long id){
        return controlPersis.obtenerUser(id);
    }
    
    /**
     * Comprueba si existe un nombre de usuario en el sistema.
     * 
     * @param username Nombre de usuario a comprobar.
     * @return {@code true} si el nombre de usuario existe, {@code false} en caso contrario.
     */
    
    public boolean traerUsername (String username){
        return controlPersis.traerUsername(username);
    }
    
    /**
     * Obtiene un usuario por su nombre de usuario.
     * 
     * @param username Nombre de usuario.
     * @return Objeto User correspondiente al nombre de usuario dado.
     */
    
    public User obtenerUserName (String username){
        return controlPersis.obtenerUserName(username);
    }
    
    
    // MÉTODOS PARA JUEGOS -----------------------------------

    /**
     * Crea un nuevo juego en el sistema.
     * 
     * @param juego Objeto Juego que contiene la información del juego a crear.
     */
    public void crearJuego(Juego juego) {
        controlPersis.crearJuego(juego);
    }

    /**
     * Elimina un juego del sistema.
     * 
     * @param id Identificador único del juego a eliminar.
     */
    public void eliminarJuego(Long id) {
        controlPersis.eliminarJuego(id);
    }

    /**
     * Trae una lista de todos los juegos en el sistema.
     * 
     * @return Lista de objetos Juego.
     */
    public List<Juego> traerJuegos() {
        return controlPersis.traerJuegos();
    }

    /**
     * Edita la información de un juego existente.
     * 
     * @param juego Objeto Juego con la información actualizada del juego.
     */
    public void editarJuego(Juego juego) {
        controlPersis.editarJuego(juego);
    }

    /**
     * Obtiene un juego por su ID.
     * 
     * @param id Identificador único del juego.
     * @return Objeto Juego correspondiente al ID dado.
     */
    public Juego obtenerJuego(Long id) {
        return controlPersis.obtenerJuego(id);
    }

    /**
     * Comprueba si existe un juego con el nombre dado en el sistema.
     * 
     * @param nombre Nombre del juego a comprobar.
     * @return {@code true} si el juego existe, {@code false} en caso contrario.
     */
    public boolean existeJuego(String nombre) {
        return controlPersis.existeJuego(nombre);
    }

    /**
     * Obtiene un juego por su nombre.
     * 
     * @param nombre Nombre del juego.
     * @return Objeto Juego correspondiente al nombre dado.
     */
    public Juego obtenerJuegoNombre(String nombre) {
        return controlPersis.obtenerJuegoNombre(nombre);
    }

    /**
     * Alquila un juego, reduciendo su stock si hay disponibilidad.
     * 
     * @param juegoId Identificador único del juego.
     * @return {@code true} si el alquiler fue exitoso, {@code false} si no hay stock.
     */
    public boolean alquilarJuego(Long juegoId) {
        return controlPersis.alquilarJuego(juegoId);
    }

    /**
     * Devuelve un juego, incrementando su stock.
     * 
     * @param juegoId Identificador único del juego.
     */
    public void devolverJuego(Long juegoId) {
        controlPersis.devolverJuego(juegoId);
    }

    // MÉTODOS PARA CLIENTES -----------------------------------

    /**
     * Crea un nuevo cliente en el sistema.
     * 
     * @param cliente Objeto Cliente que contiene la información del cliente a crear.
     */
    public void crearCliente(Cliente cliente) {
        controlPersis.crearCliente(cliente);
    }

    /**
     * Elimina un cliente del sistema.
     * 
     * @param id Identificador único del cliente a eliminar.
     */
    public void eliminarCliente(Long id) {
        controlPersis.eliminarCliente(id);
    }

    /**
     * Trae una lista de todos los clientes en el sistema.
     * 
     * @return Lista de objetos Cliente.
     */
    public List<Cliente> traerClientes() {
        return controlPersis.traerClientes();
    }

    /**
     * Edita la información de un cliente existente.
     * 
     * @param cliente Objeto Cliente con la información actualizada del cliente.
     */
    public void editarCliente(Cliente cliente) {
        controlPersis.editarCliente(cliente);
    }

    /**
     * Obtiene un cliente por su ID.
     * 
     * @param id Identificador único del cliente.
     * @return Objeto Cliente correspondiente al ID dado.
     */
    public Cliente obtenerCliente(Long id) {
        return controlPersis.obtenerCliente(id);
    }

    // MÉTODOS PARA ALQUILERES -----------------------------------

    /**
     * Crea un nuevo alquiler en el sistema.
     * 
     * @param alquiler Objeto Alquiler que contiene la información del alquiler a crear.
     */
    public void crearAlquiler(Alquiler alquiler) {
        controlPersis.crearAlquiler(alquiler);
    }

    /**
     * Elimina un alquiler del sistema.
     * 
     * @param id Identificador único del alquiler a eliminar.
     */
    public void eliminarAlquiler(Long id) {
        controlPersis.eliminarAlquiler(id);
    }

    /**
     * Trae una lista de todos los alquileres en el sistema.
     * 
     * @return Lista de objetos Alquiler.
     */
    public List<Alquiler> traerAlquileres() {
        return controlPersis.traerAlquileres();
    }

    /**
     * Edita la información de un alquiler existente.
     * 
     * @param alquiler Objeto Alquiler con la información actualizada del alquiler.
     */
    public void editarAlquiler(Alquiler alquiler) {
        controlPersis.editarAlquiler(alquiler);
    }

    /**
     * Obtiene un alquiler por su ID.
     * 
     * @param id Identificador único del alquiler.
     * @return Objeto Alquiler correspondiente al ID dado.
     */
    public Alquiler obtenerAlquiler(Long id) {
        return controlPersis.obtenerAlquiler(id);
    }

    /**
     * Trae una lista de todos los alquileres activos en el sistema.
     * 
     * @return Lista de objetos Alquiler que están activos.
     */
    public List<Alquiler> traerAlquileresActivos() {
        return controlPersis.traerAlquileresActivos();
    }

    /**
     * Finaliza un alquiler marcándolo como inactivo.
     * 
     * @param id Identificador único del alquiler a finalizar.
     */
    public void finalizarAlquiler(Long id) {
        controlPersis.finalizarAlquiler(id);
    }

    /**
     * Trae una lista de alquileres asociados a un cliente específico.
     * 
     * @param clienteId Identificador único del cliente.
     * @return Lista de objetos Alquiler asociados al cliente dado.
     */
    public List<Alquiler> traerAlquileresPorCliente(Long clienteId) {
        return controlPersis.traerAlquileresPorCliente(clienteId);
    }

    /**
     * Trae una lista de alquileres asociados a un juego específico.
     * 
     * @param juegoId Identificador único del juego.
     * @return Lista de objetos Alquiler asociados al juego dado.
     */
    public List<Alquiler> traerAlquileresPorJuego(Long juegoId) {
        return controlPersis.traerAlquileresPorJuego(juegoId);
    }
}
