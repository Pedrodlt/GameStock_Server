/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.logica.Controladora;
import com.gamestock.servergamestockapp.logica.User;
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
}
