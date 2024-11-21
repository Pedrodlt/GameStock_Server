
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.logica.User;
import com.gamestock.servergamestockapp.persistencia.ControladoraPersistencia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author pedro
 */
@SpringBootApplication
public class ServerGameStockApp {
    
    public static void main(String[] args) {
        // Inicia la GUI de login cuando la aplicación se ejecute
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Abre el formulario de Login
            LoginUser loginForm = new LoginUser(); 
            loginForm.setVisible(true);  
        });
    }
}
