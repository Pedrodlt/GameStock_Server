/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.logica.Cliente;
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
 * Controlador REST para gestionar clientes.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
    
    @Autowired
    private Controladora controladoraLogica;

    /**
     * Crea un nuevo cliente en el sistema.
     *
     * @param cliente Objeto Cliente con la información del cliente a crear.
     * @return Mensaje indicando que el cliente fue creado exitosamente.
     */
    @PostMapping
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        controladoraLogica.crearCliente(cliente);
        return ResponseEntity.ok("Cliente creado exitosamente");
    }

    /**
     * Elimina un cliente del sistema por su ID.
     *
     * @param id Identificador único del cliente.
     * @return Mensaje indicando que el cliente fue eliminado exitosamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        controladoraLogica.eliminarCliente(id);
        return ResponseEntity.ok("Cliente eliminado exitosamente");
    }

    /**
     * Obtiene una lista de todos los clientes del sistema.
     *
     * @return Lista de objetos Cliente.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> traerClientes() {
        List<Cliente> clientes = controladoraLogica.traerClientes();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Actualiza la información de un cliente existente.
     *
     * @param id      Identificador único del cliente.
     * @param cliente Objeto Cliente con la información actualizada.
     * @return Mensaje indicando que el cliente fue actualizado exitosamente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        controladoraLogica.editarCliente(cliente);
        return ResponseEntity.ok("Cliente actualizado exitosamente");
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id Identificador único del cliente.
     * @return Objeto Cliente correspondiente al ID dado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(controladoraLogica.obtenerCliente(id));
    }
}