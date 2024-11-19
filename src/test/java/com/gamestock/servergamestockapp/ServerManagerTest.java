/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.gamestock.servergamestockapp;

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

/**
 * Clase de prueba para ServerManager que verifica el inicio, parada, autenticación y 
 * funcionalidad de login en el servidor.
 */
public class ServerManagerTest {
    
    /**
     * Asegura que el servidor esté detenido antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        ServerManager.stopServer();
    }

    /**
     * Detiene el servidor después de cada prueba para liberar recursos.
     */
    @AfterEach
    public void tearDown() {
        ServerManager.stopServer();
    }
    
    /**
     * Prueba de inicio del servidor. Verifica que el servidor se inicia correctamente y 
     * está en ejecución.
     */
    @Test
    @DisplayName("Prueba de inicio del servidor")
    public void testStartServer() throws InterruptedException {
        ServerManager.startServer();
        // Esperar más tiempo para asegurarse de que el servidor haya arrancado
        Thread.sleep(3000);  // Aumenta el tiempo de espera
        assertTrue(ServerManager.isRunning(), "El servidor debería estar en ejecución después de iniciar");
        System.out.println("Servidor en ejecución confirmado.");
    }
    
    /**
     * Prueba de parada del servidor. Verifica que el servidor se detiene correctamente.
     */
    @Test
    @DisplayName("Prueba de parar el servidor")
    public void testStopServer() throws InterruptedException {
        Thread.sleep(2000); // Espera para asegurarte de que el servidor esté iniciado
        // Detiene el servidor
        ServerManager.stopServer();
        assertFalse(ServerManager.isRunning(), "El servidor debería estar detenido después de llamar a stopServer");
        System.out.println("Servidor detenido confirmado.");
    }
    
     /**
     * Prueba de autenticación exitosa con credenciales correctas.
     */
    @Test
    @DisplayName("Prueba de autenticación exitosa")
    public void testAuthenticateSuccessful() {
        // Usuario de prueba existente en BBDD
        String username = "pedro";
        String password = "123";
        String role = "admin";
        
        // Realizar la autenticación
        boolean result = ServerManager.authenticate(username, password, role);
        // Verificar autenticación exitosa
        assertTrue(result, "La autenticación debería ser exitosa con credenciales correctas");
        System.out.println("Autenticación exitosa con credenciales correctas.");
    }
    
    /**
     * Prueba de autenticación fallida con credenciales incorrectas.
     */
    @Test
    @DisplayName("Prueba de autenticación fallida")
    public void testAuthenticateFailed() {
        // Usuario que no existe con la BBDD
        String username = "bili";
        String password = "234";
        String role = "user";
        
        // Realizar la autenticación
        boolean result = ServerManager.authenticate(username, password, role);
        // Verificar autenticación fallida
        assertFalse(result, "La autenticación debería fallar con credenciales incorrectas");
        System.out.println("Autenticación fallida confirmada con credenciales incorrectas.");
    }
    
     /**
     * Prueba de login exitoso para un usuario con rol de administrador.
     */
    @Test
    @DisplayName("Prueba de login con rol de administrador")
    public void testLoginAdmin() {
        // Usuario administrador de prueba
        String username = "pedro";
        String password = "123";
        
        // Realizar login
        boolean result = ServerManager.login(username, password);
        // Verificar login exitoso
        assertTrue(result, "El login debería ser exitoso para un usuario administrador");
        System.out.println("Login exitoso para usuario administrador.");
    }
    
    /**
     * Prueba de login exitoso para un usuario normal.
     */
    @Test
    @DisplayName("Prueba de login con usuario normal")
    public void testLoginUser() {
        // Usuario de prueba (asegúrate de que exista en la base de datos o mock de pruebas)
        String username = "belen";
        String password = "123";
        
        // Realizar login
        boolean result = ServerManager.login(username, password);
        // Verificar login exitoso
        assertTrue(result, "El login debería ser exitoso para un usuario normal");
        System.out.println("Login exitoso para usuario normal.");
    }  
}
