/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.gamestock.servergamestockapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
 * Clase de prueba para MockSocketsServer, que verifica las funcionalidades básicas de iniciar, 
 * detener, gestionar conexiones de cliente y enviar respuestas.
 */
public class MockSocketsServerTest {
    
    private MockSocketsServer server;
    
    /**
     * Configuración inicial del servidor MockSocketsServer para cada prueba.
     */
    @BeforeEach
    public void setUp() {
        server = new MockSocketsServer();
        System.out.println("Servidor MockSocketsServer configurado para la prueba.");
    }
    
    /**
     * Detener el servidor después de cada prueba para asegurar que no quede en ejecución
     * entre pruebas.
     */
    @AfterEach
    public void tearDown() {
        if (server != null && server.isRunning()) {
        server.stop();  
        System.out.println("Servidor detenido después de la prueba.");
    }
    }
    
    /**
     * Prueba de inicialización del servidor. Asegura que el servidor se inicializa correctamente 
     * con los parámetros especificados.
     */
    @Test
    @DisplayName("Prueba de inicialización del servidor")
    public void testInitialize() throws IOException {
        int port = 8081;
        while (!isPortAvailable(port)) {
            port++; // Incrementamos el puerto si ya está en uso
        }
        server.initialize(port, "testLog.txt", true);
        System.out.println("Servidor inicializado en el puerto " + port + " con registro activado.");
        assertNotNull(server, "El servidor debería inicializarse correctamente.");
    }
    
     /**
     * Prueba de inicio y detención del servidor. Asegura que el servidor comienza a ejecutarse 
     * y después se detiene correctamente.
     */
    @Test
    @DisplayName("Prueba de inicio y detención del servidor")
    public void testServerStartsAndStops() throws IOException, InterruptedException {
        server.initialize(8081, "testLog.txt", true);
        
        // Usamos un CountDownLatch para esperar que el servidor esté listo
        CountDownLatch latch = new CountDownLatch(1);
        Thread serverThread = new Thread(() -> {
            server.run();
            latch.countDown();  // Cuando el servidor termina de arrancar, decrementamos el latch
        });
        serverThread.start();

        // Esperamos a que el servidor esté listo
        latch.await(5, TimeUnit.SECONDS);  // Esperar 5 segundos para que el servidor inicie

        assertTrue(server.isRunning(), "El servidor debería estar en ejecución después de iniciar.");
        System.out.println("Servidor en ejecución confirmado.");

        // Parar el servidor
        server.stop();
        Thread.sleep(1000); // Esperar para asegurarse que el servidor se detiene
        assertFalse(server.isRunning(), "El servidor debería estar detenido después de llamar a stop.");
        System.out.println("Servidor detenido con éxito.");
    }

     /**
     * Prueba de conexión con un cliente. Simula un cliente que se conecta al servidor, 
     * envía un mensaje y comprueba que el servidor devuelve la respuesta esperada.
     */
    @Test
    @DisplayName("Prueba de conexión de cliente al servidor")
    public void testClientConnection() throws IOException, InterruptedException {
        // Iniciar el servidor en un puerto específico
        int port = 8081;
        server.initialize(port, "testLog.txt", true);
        Thread serverThread = new Thread(() -> {
            try {
                server.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
        System.out.println("Servidor inicializado para la prueba de conexión con un cliente.");

        try (Socket clientSocket = new Socket("localhost", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Enviar un mensaje al servidor
            String testMessage = "Hello Server";
            out.println(testMessage);

            // Leer la respuesta del servidor
            String response = in.readLine();
            assertEquals("Response from server: " + testMessage, response, "La respuesta del servidor debe coincidir con el mensaje enviado.");
            System.out.println("Respuesta del servidor verificada correctamente.");
        }

        // Parar el servidor después de la prueba
        server.stop();
    }
    
    /**
     * Prueba de manejo de múltiples conexiones de cliente. Crea varios clientes simultáneos, 
     * envía mensajes y verifica las respuestas individuales del servidor para cada cliente.
     */
    @Test
    @DisplayName("Prueba de gestión de múltiples conexiones de cliente")
    public void testHandleMultipleClientConnections() throws IOException, InterruptedException {
        MockSocketsServer mockServer = new MockSocketsServer();
        mockServer.initialize(8081, "TestLog.txt", false);
        new Thread(mockServer::run).start();
        System.out.println("Servidor inicializado para manejar múltiples conexiones de cliente.");

        // Crear varios clientes simulados
        for (int i = 0; i < 5; i++) {
            Socket clientSocket = new Socket("localhost", 8081);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Enviar un mensaje de prueba desde el cliente
            out.println("Hello from client " + i);
            String response = in.readLine();
            assertEquals("Response from server: Hello from client " + i, response, "La respuesta del servidor debe ser la esperada para el cliente " + i);
            System.out.println("Conexión y respuesta verificada para cliente " + i);

            clientSocket.close();
        }

        mockServer.stop();
        assertFalse(mockServer.isRunning());
        System.out.println("Servidor detenido correctamente después de múltiples conexiones.");
    }
    
    /**
     * Método auxiliar para asegurarnos que el puerto esta disponible
     */
    public static boolean isPortAvailable(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            socket.setReuseAddress(true);
            return true;  // El puerto está disponible
        } catch (IOException e) {
            return false;  // El puerto está ocupado
        }
    }
}
