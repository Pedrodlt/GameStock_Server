
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.logica.Alquiler;
import com.gamestock.servergamestockapp.logica.Controladora;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
/**
 * Controlador REST para gestionar alquileres.
 */
@RestController
@RequestMapping("/api/alquileres")
public class AlquilerRestController {
    
    @Autowired
    private Controladora controladoraLogica;

    /**
     * Crea un nuevo alquiler en el sistema.
     *
     * @param alquiler Objeto Alquiler con la información del alquiler a crear.
     * @return Mensaje indicando que el alquiler fue creado exitosamente.
     */
    @PostMapping
    public ResponseEntity<String> crearAlquiler(@RequestBody Alquiler alquiler) {
        controladoraLogica.crearAlquiler(alquiler);
        return ResponseEntity.ok("Alquiler creado exitosamente");
    }

    /**
     * Elimina un alquiler del sistema por su ID.
     *
     * @param id Identificador único del alquiler.
     * @return Mensaje indicando que el alquiler fue eliminado exitosamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAlquiler(@PathVariable Long id) {
        controladoraLogica.eliminarAlquiler(id);
        return ResponseEntity.ok("Alquiler eliminado exitosamente");
    }

    /**
     * Obtiene una lista de todos los alquileres del sistema.
     *
     * @return Lista de objetos Alquiler.
     */
    @GetMapping
    public ResponseEntity<List<Alquiler>> traerAlquileres() {
        List<Alquiler> alquileres = controladoraLogica.traerAlquileres();
        return ResponseEntity.ok(alquileres);
    }

    /**
     * Actualiza la información de un alquiler existente.
     *
     * @param id       Identificador único del alquiler.
     * @param alquiler Objeto Alquiler con la información actualizada.
     * @return Mensaje indicando que el alquiler fue actualizado exitosamente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> editarAlquiler(@PathVariable Long id, @RequestBody Alquiler alquiler) {
        alquiler.setId(id);
        controladoraLogica.editarAlquiler(alquiler);
        return ResponseEntity.ok("Alquiler actualizado exitosamente");
    }

    /**
     * Obtiene un alquiler por su ID.
     *
     * @param id Identificador único del alquiler.
     * @return Objeto Alquiler correspondiente al ID dado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> obtenerAlquiler(@PathVariable Long id) {
        return ResponseEntity.ok(controladoraLogica.obtenerAlquiler(id));
    }
    
    /**
     * Obtiene una lista de todos los alquileres activos.
     *
     * @return Lista de objetos Alquiler que están activos.
     */
    @GetMapping("/activos")
    public ResponseEntity<List<Alquiler>> traerAlquileresActivos() {
        List<Alquiler> alquileresActivos = controladoraLogica.traerAlquileresActivos();
        return ResponseEntity.ok(alquileresActivos);
    }

    /**
     * Finaliza un alquiler marcándolo como inactivo.
     *
     * @param id Identificador único del alquiler a finalizar.
     * @return Mensaje indicando que el alquiler fue finalizado exitosamente.
     */
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<String> finalizarAlquiler(@PathVariable Long id) {
        controladoraLogica.finalizarAlquiler(id);
        return ResponseEntity.ok("Alquiler finalizado exitosamente");
    }

    /**
     * Obtiene una lista de alquileres asociados a un cliente específico.
     *
     * @param clienteId Identificador único del cliente.
     * @return Lista de objetos Alquiler asociados al cliente dado.
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Alquiler>> traerAlquileresPorCliente(@PathVariable Long clienteId) {
        List<Alquiler> alquileresCliente = controladoraLogica.traerAlquileresPorCliente(clienteId);
        return ResponseEntity.ok(alquileresCliente);
    }

    /**
     * Obtiene una lista de alquileres asociados a un juego específico.
     *
     * @param juegoId Identificador único del juego.
     * @return Lista de objetos Alquiler asociados al juego dado.
     */
    @GetMapping("/juego/{juegoId}")
    public ResponseEntity<List<Alquiler>> traerAlquileresPorJuego(@PathVariable Long juegoId) {
        List<Alquiler> alquileresJuego = controladoraLogica.traerAlquileresPorJuego(juegoId);
        return ResponseEntity.ok(alquileresJuego);
    }
}
