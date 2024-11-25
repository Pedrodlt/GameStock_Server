package com.gamestock.servergamestockapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamestock.servergamestockapp.logica.Alquiler;
import com.gamestock.servergamestockapp.logica.Cliente;
import com.gamestock.servergamestockapp.logica.Controladora;
import com.gamestock.servergamestockapp.logica.Juego;
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
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteRestController.class)
public class ClienteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Controladora controladoraLogica;

    @Autowired
    private ObjectMapper objectMapper;

    // 1. Test para crear un cliente
    @Test
    public void testCrearCliente() throws Exception {
        Cliente cliente = new Cliente(1L, "Juan", "Pérez", "González", "juan.perez@example.com", "123456789");

        Mockito.doNothing().when(controladoraLogica).crearCliente(any(Cliente.class));

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente creado exitosamente"));
    }

    // 2. Test para eliminar un cliente
    @Test
    public void testEliminarCliente() throws Exception {
        Long clienteId = 1L;

        Mockito.doNothing().when(controladoraLogica).eliminarCliente(clienteId);

        mockMvc.perform(delete("/api/clientes/{id}", clienteId))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente eliminado exitosamente"));
    }

    // 3. Test para obtener todos los clientes
    @Test
    public void testTraerClientes() throws Exception {
        List<Cliente> clientes = Arrays.asList(
                new Cliente(1L, "Juan", "Pérez", "González", "juan.perez@example.com", "123456789"),
                new Cliente(2L, "Ana", "López", "Martínez", "ana.lopez@example.com", "987654321")
        );

        Mockito.when(controladoraLogica.traerClientes()).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(clientes.size()))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].nombre").value("Ana"));
    }

    // 4. Test para editar un cliente
    @Test
    public void testEditarCliente() throws Exception {
        Long clienteId = 1L;
        Cliente clienteActualizado = new Cliente(clienteId, "Juan", "Pérez", "González", "juan.perez@example.com", "123456789");

        Mockito.doNothing().when(controladoraLogica).editarCliente(any(Cliente.class));

        mockMvc.perform(put("/api/clientes/{id}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente actualizado exitosamente"));
    }

    // 5. Test para obtener un cliente por ID
    @Test
    public void testObtenerCliente() throws Exception {
        Long clienteId = 1L;
        Cliente cliente = new Cliente(clienteId, "Juan", "Pérez", "González", "juan.perez@example.com", "123456789");

        Mockito.when(controladoraLogica.obtenerCliente(clienteId)).thenReturn(cliente);

        mockMvc.perform(get("/api/clientes/{id}", clienteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido1").value("Pérez"));
    }
}

