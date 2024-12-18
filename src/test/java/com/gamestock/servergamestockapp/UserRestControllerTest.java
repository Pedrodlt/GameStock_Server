package com.gamestock.servergamestockapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamestock.servergamestockapp.logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Controladora controladoraLogica;

    @Autowired
    private ObjectMapper objectMapper;

    // 1. Test para crear un usuario
    @Test
    public void testCrearUsuario() throws Exception {
        User user = new User(1L, "juan123", "admin", "juan.perez@example.com", "password");

        Mockito.doNothing().when(controladoraLogica).crearUser(any(User.class));

        mockMvc.perform(post("https://localhost:9090/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario creado exitosamente"));
    }

    // 2. Test para eliminar un usuario
    @Test
    public void testEliminarUsuario() throws Exception {
        Long userId = 1L;

        Mockito.doNothing().when(controladoraLogica).eliminarUser(userId);

        mockMvc.perform(delete("https://localhost:9090/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario eliminado exitosamente"));
    }

    // 3. Test para obtener todos los usuarios
    @Test
    public void testTraerUsuarios() throws Exception {
        List<User> users = Arrays.asList(
                new User(1L, "juan123", "admin", "juan.perez@example.com", "password"),
                new User(2L, "ana456", "user", "ana.lopez@example.com", "password123")
        );

        Mockito.when(controladoraLogica.traerUser()).thenReturn(users);

        mockMvc.perform(get("https://localhost:9090/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andExpect(jsonPath("$[0].username").value("juan123"))
                .andExpect(jsonPath("$[1].username").value("ana456"));
    }

    // 4. Test para editar un usuario
    @Test
    public void testEditarUsuario() throws Exception {
        Long userId = 1L;
        User userActualizado = new User(userId, "juan123", "admin", "juan.perez@newemail.com", "newpassword");

        Mockito.doNothing().when(controladoraLogica).editarUser(any(User.class));

        mockMvc.perform(put("https://localhost:9090/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario actualizado exitosamente"));
    }

    // 5. Test para obtener un usuario por ID
    @Test
    public void testObtenerUsuario() throws Exception {
        Long userId = 1L;
        User user = new User(userId, "juan123", "admin", "juan.perez@example.com", "password");

        Mockito.when(controladoraLogica.obtenerUser(userId)).thenReturn(user);

        mockMvc.perform(get("https://localhost:9090/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("juan123"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"));
    }

    // 6. Test para iniciar sesión
    @Test
    public void testLoginExitoso() throws Exception {
        LoginRequest loginRequest = new LoginRequest("juan123", "password");

        Mockito.when(controladoraLogica.iniciarSesion("juan123", "password")).thenReturn(true);

        mockMvc.perform(post("https://localhost:9090/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login exitoso"));
    }

    // 7. Test para fallo en inicio de sesión
    @Test
    public void testLoginFallido() throws Exception {
        LoginRequest loginRequest = new LoginRequest("juan123", "wrongpassword");

        Mockito.when(controladoraLogica.iniciarSesion("juan123", "wrongpassword")).thenReturn(false);

        mockMvc.perform(post("https://localhost:9090/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Credenciales incorrectas"));
    }

    // 8. Test para cerrar sesión
    @Test
    public void testLogoutExitoso() throws Exception {
        Map<String, String> request = Map.of("username", "juan123");

        Mockito.when(controladoraLogica.estaLogueado("juan123")).thenReturn(true);

        mockMvc.perform(post("https://localhost:9090/api/users/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Logout exitoso"));
    }

    // 9. Test para fallo en cierre de sesión
    @Test
    public void testLogoutFallido() throws Exception {
        Map<String, String> request = Map.of("username", "juan123");

        Mockito.when(controladoraLogica.estaLogueado("juan123")).thenReturn(false);

        mockMvc.perform(post("https://localhost:9090/api/users/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El usuario no está logueado"));
    }
}

