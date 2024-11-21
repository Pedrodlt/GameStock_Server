
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.logica.Controladora;
import com.gamestock.servergamestockapp.logica.User;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * Controlador REST para gestionar usuarios.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {
    
    @Autowired
    private Controladora controladoraLogica;

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param user Objeto User con la información del usuario a crear.
     * @return Mensaje indicando que el usuario fue creado exitosamente.
     */
    @PostMapping
    public ResponseEntity<String> crearUser(@RequestBody User user) {
        controladoraLogica.crearUser(user);
        return ResponseEntity.ok("Usuario creado exitosamente");
    }
    
     @GetMapping("/")
    public ResponseEntity<String> testConnection() {
        return new ResponseEntity<>("Server is running", HttpStatus.OK);
    }

    /**
     * Elimina un usuario del sistema por su ID.
     *
     * @param id Identificador único del usuario.
     * @return Mensaje indicando que el usuario fue eliminado exitosamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUser(@PathVariable Long id) {
        controladoraLogica.eliminarUser(id);
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    }

    /**
     * Obtiene una lista de todos los usuarios del sistema.
     *
     * @return Lista de objetos User.
     */
    @GetMapping
    public ResponseEntity<List<User>> traerUsers() {
        List<User> users = controladoraLogica.traerUser();
        return ResponseEntity.ok(users);
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param id   Identificador único del usuario.
     * @param user Objeto User con la información actualizada.
     * @return Mensaje indicando que el usuario fue actualizado exitosamente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> editarUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        controladoraLogica.editarUser(user);
        return ResponseEntity.ok("Usuario actualizado exitosamente");
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id Identificador único del usuario.
     * @return Objeto User correspondiente al ID dado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUser(@PathVariable Long id) {
        return ResponseEntity.ok(controladoraLogica.obtenerUser(id));
    }
    
    /**
     * Inicia sesión de un usuario.
     *
     * @param loginRequest Objeto con el username y password para autenticarse.
     * @return Respuesta indicando éxito o error en la autenticación.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean loginExitoso = controladoraLogica.iniciarSesion(loginRequest.getUsername(), loginRequest.getPassword());
        if (loginExitoso) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    /**
     * Cierra sesión de un usuario.
     *
     * @param request Mapa con el username del usuario que quiere cerrar sesión.
     * @return Respuesta indicando éxito en el cierre de sesión.
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        if (controladoraLogica.estaLogueado(username)) {
            controladoraLogica.cerrarSesion(username);
            return ResponseEntity.ok("Logout exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no está logueado");
        }
    }

    /**
     * Verifica si un usuario está logueado.
     *
     * @param username Nombre de usuario.
     * @return Respuesta indicando si el usuario está logueado o no.
     */
    @GetMapping("/isLoggedIn/{username}")
    public ResponseEntity<Boolean> isLoggedIn(@PathVariable String username) {
        return ResponseEntity.ok(controladoraLogica.estaLogueado(username));
    }
}
