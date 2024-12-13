
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.forms.ServerStartForm;
import com.gamestock.servergamestockapp.forms.ServerStartFormAdmin;
import com.gamestock.servergamestockapp.logica.Controladora;
import com.gamestock.servergamestockapp.logica.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.springframework.boot.SpringApplication;

/**
 *
 * @author pedro
 */
public class ServerManager {
    
    static MockSocketsServer server;
    private static Map<String, String> activeSessions = new HashMap<>();
    private static Controladora controladora = new Controladora();
    private static boolean serverRunning = false;
    
    public static void startServer() {
    if (server != null && server.isRunning()) {
        JOptionPane.showMessageDialog(null, "Server is already running.", "Server Status", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        if (server == null) {
            server = new MockSocketsServer();
        }
        server.initialize(8080, "server.log", true);
        Thread serverThread = new Thread(() -> server.run());
        serverThread.start();
        
        SpringApplication.run(ServerGameStockApp.class);
        serverRunning = true; 

        JOptionPane.showMessageDialog(null, "Server started successfully", "Server Status", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error starting server: " + ex.getMessage(), "Server Error", JOptionPane.ERROR_MESSAGE);
        serverRunning = false;
    }
}

   
    public static boolean login(String username, String password) {
     // Obtener el usuario por su nombre de usuario
    User user = controladora.obtenerUserName(username);
    
        if (user != null && user.checkPassword(password)) {
            // Autenticación exitosa
            // Abrir el formulario correspondiente según el rol del usuario
            if ("admin".equals(user.getRole())) {
                ServerStartFormAdmin adminForm = new ServerStartFormAdmin();
                adminForm.setVisible(true);
            } else {
                ServerStartForm userForm = new ServerStartForm();
                userForm.setVisible(true);
            }
            return true;
        } else {
            // Autenticación fallida
            return false;
        }
    }
    
    public static boolean authenticate(String username, String password, String role) {
    // Obtener el usuario completo de la base de datos
    User user = controladora.obtenerUserName(username);
    
        // Verificar si el usuario existe y la contraseña y el rol coinciden
        if (user != null && user.getPassword().equals(password) && user.getRole().equals(role)) {
            return true; // Autenticación exitosa
        }

        return false; // Autenticación fallida
        }

        private static String generateSessionId() {
            // Lógica para generar un identificador de sesión único
            return "session_" + System.currentTimeMillis(); 
        }

        public static void stopServer() {
            if (server != null) {
                server.stop();
                server = null;
                serverRunning = false; 
        }
        }
    
    
    //TODO aun no está terminado, son unas pruebas que se han ido haciendo
    private static void initClientConnection() {
        try {
            ServerSocket serverSocket = new ServerSocket(8090); // Puerto para conexiones de clientes

            Thread clientThread = new Thread(() -> {
                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        // Aquí maneja la conexión con el cliente
                        handleClientConnection(clientSocket);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            clientThread.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void handleClientConnection(Socket clientSocket) {
        //Pruebas para lectura y escritura cliente (no es la final)
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received from client: " + inputLine);

                // Procesar el comando del cliente y enviar la respuesta
                String response = processClientCommand(inputLine);
                out.println(response);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String processClientCommand(String command) {
       //implementar logica cliente proximamente
        return "Comando recibido: " + command;
    }
    
    public static boolean isRunning() {
        return serverRunning;
    }

    }
