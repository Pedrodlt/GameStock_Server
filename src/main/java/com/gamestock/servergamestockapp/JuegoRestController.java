
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
}
