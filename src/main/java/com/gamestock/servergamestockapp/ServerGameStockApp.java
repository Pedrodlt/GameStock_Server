
package com.gamestock.servergamestockapp;

import com.gamestock.servergamestockapp.forms.LoginUser;
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
