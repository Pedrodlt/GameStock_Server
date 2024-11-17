/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.logica.User;
import com.gamestock.servergamestockapp.persistencia.ControladoraPersistencia;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author pedro
 */
public class ControladoraPersistenciaTest extends DBUnitTest {
    
    private ControladoraPersistencia controladoraPersistencia;

    public ControladoraPersistenciaTest() throws ClassNotFoundException, SQLException, DatabaseUnitException {
        super();
    }

    @BeforeEach
    public void setUp() throws Exception {
    
    // Configura la base de datos con el dataset cargado
    setUpDatabase("userData.xml");
    controladoraPersistencia = new ControladoraPersistencia();
    }

    @AfterEach
    public void tearDown() throws Exception {
        tearDownDatabase();
    }

    @Test
    @DisplayName("Prueba de búsqueda de usuario por nombre de usuario")
    public void testFindUserByUsername() {
        User user = controladoraPersistencia.obtenerUserName("pedro");
        assertNotNull(user, "El usuario 'pedro' debería existir en la base de datos.");
        assertEquals("admin", user.getRole(), "El rol del usuario 'pedro' debería ser 'admin'.");
    }

    @Test
    @DisplayName("Prueba de inserción de usuario")
    public void testInsertUser() throws Exception {
        User newUser = new User();
        newUser.setUsername("maria");
        newUser.setPassword("456");
        newUser.setRole("user");

        controladoraPersistencia.crearUser(newUser);

        User retrievedUser = controladoraPersistencia.obtenerUserName("maria");
        assertNotNull(retrievedUser, "El usuario 'maria' debería haberse insertado correctamente.");
        assertEquals("456", retrievedUser.getPassword(), "La contraseña del usuario 'maria' debería coincidir.");
    }

    @Test
    @DisplayName("Prueba de actualización de usuario")
    public void testUpdateUser() throws Exception {
        User user = controladoraPersistencia.obtenerUserName("juan");
        user.setPassword("789");
        controladoraPersistencia.editarUser(user);

        User updatedUser = controladoraPersistencia.obtenerUserName("juan");
        assertEquals("789", updatedUser.getPassword(), "La contraseña del usuario 'juan' debería haberse actualizado.");
    }

    @Test
    @DisplayName("Prueba de eliminación de usuario")
    public void testDeleteUser() throws Exception {
    // Obtenemos el usuario por su ID
    User user = controladoraPersistencia.obtenerUser(13L); 
    assertNotNull(user, "El usuario con ID 13L debería existir antes de la eliminación.");
    
    // Eliminamos el usuario usando el ID
    controladoraPersistencia.eliminarUser(user.getId());
    // Intentamos buscar por el nombre de usuario para confirmar que se ha eliminado
    User deletedUser = controladoraPersistencia.obtenerUserName("belen");
    assertNull(deletedUser, "El usuario 'belen' debería haberse eliminado de la base de datos.");
    
    }
}
    
