
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.logica.Controladora;
import com.gamestock.servergamestockapp.logica.Juego;
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
 * Controlador REST para gestionar juegos.
 */
@RestController
@RequestMapping("/api/juegos")
public class JuegoRestController {
    
    @Autowired
    private Controladora controladoraLogica;

    /**
     * Crea un nuevo juego en el sistema.
     *
     * @param juego Objeto Juego con la información del juego a crear.
     * @return Mensaje indicando que el juego fue creado exitosamente.
     */
    @PostMapping
    public ResponseEntity<String> crearJuego(@RequestBody Juego juego) {
        controladoraLogica.crearJuego(juego);
        return ResponseEntity.ok("Juego creado exitosamente");
    }

    /**
     * Elimina un juego del sistema por su ID.
     *
     * @param id Identificador único del juego.
     * @return Mensaje indicando que el juego fue eliminado exitosamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarJuego(@PathVariable Long id) {
        controladoraLogica.eliminarJuego(id);
        return ResponseEntity.ok("Juego eliminado exitosamente");
    }

    /**
     * Obtiene una lista de todos los juegos del sistema.
     *
     * @return Lista de objetos Juego.
     */
    @GetMapping
    public ResponseEntity<List<Juego>> traerJuegos() {
        List<Juego> juegos = controladoraLogica.traerJuegos();
        return ResponseEntity.ok(juegos);
    }

    /**
     * Actualiza la información de un juego existente.
     *
     * @param id    Identificador único del juego.
     * @param juego Objeto Juego con la información actualizada.
     * @return Mensaje indicando que el juego fue actualizado exitosamente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> editarJuego(@PathVariable Long id, @RequestBody Juego juego) {
        juego.setId(id);
        controladoraLogica.editarJuego(juego);
        return ResponseEntity.ok("Juego actualizado exitosamente");
    }

    /**
     * Obtiene un juego por su ID.
     *
     * @param id Identificador único del juego.
     * @return Objeto Juego correspondiente al ID dado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Juego> obtenerJuego(@PathVariable Long id) {
        return ResponseEntity.ok(controladoraLogica.obtenerJuego(id));
    }
    
    /**
     * Obtiene un juego por su nombre.
     *
     * @param nombre Nombre del juego.
     * @return Objeto Juego correspondiente al nombre dado.
     */
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Juego> obtenerJuegoNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(controladoraLogica.obtenerJuegoNombre(nombre));
    }

    /**
     * Alquila un juego, reduciendo su stock si hay disponibilidad.
     *
     * @param id Identificador único del juego.
     * @return Mensaje indicando si el alquiler fue exitoso o no.
     */
    @PostMapping("/{id}/alquilar")
    public ResponseEntity<String> alquilarJuego(@PathVariable Long id) {
        boolean exito = controladoraLogica.alquilarJuego(id);
        if (exito) {
            return ResponseEntity.ok("Juego alquilado exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("No hay stock disponible para este juego.");
        }
    }

    /**
     * Devuelve un juego, incrementando su stock.
     *
     * @param id Identificador único del juego.
     * @return Mensaje indicando que el juego fue devuelto exitosamente.
     */
    @PostMapping("/{id}/devolver")
    public ResponseEntity<String> devolverJuego(@PathVariable Long id) {
        controladoraLogica.devolverJuego(id);
        return ResponseEntity.ok("Juego devuelto exitosamente.");
    }

    /**
     * Obtiene una lista de todos los juegos ordenados según el ranking.
     *
     * @return Lista de objetos Juego segun el ranking.
     */
    @GetMapping("/ranking")
    public ResponseEntity<List<Juego>> obtenerRankingJuegos() {
        List<Juego> ranking = controladoraLogica.obtenerRankingJuegos();
        return ResponseEntity.ok(ranking);
    }
}
