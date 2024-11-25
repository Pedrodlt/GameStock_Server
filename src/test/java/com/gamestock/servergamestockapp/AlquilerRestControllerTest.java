
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
/**
 *
 * @author pedro
 */
@WebMvcTest(AlquilerRestController.class)
public class AlquilerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Controladora controladoraLogica;

    @Autowired
    private ObjectMapper objectMapper;

    // 1. Test para crear un alquiler
    @Test
    public void testCrearAlquiler() throws Exception {
        Alquiler alquiler = new Alquiler(1L, new Cliente(1L, "Juan", "Perez", "Gallardo", "juan@juan.com", "6666666"), new Juego(1L, "The Witcher 3", "RPG", "CD Projekt", 59.99, 5, null), "2024-11-24", "2024-12-01", 20.0, true);
        
        Mockito.doNothing().when(controladoraLogica).crearAlquiler(any(Alquiler.class));

        mockMvc.perform(post("/api/alquileres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alquiler)))
                .andExpect(status().isOk())
                .andExpect(content().string("Alquiler creado exitosamente"));
    }

    // 2. Test para eliminar un alquiler
    @Test
    public void testEliminarAlquiler() throws Exception {
        Long alquilerId = 1L;

        Mockito.doNothing().when(controladoraLogica).eliminarAlquiler(alquilerId);

        mockMvc.perform(delete("/api/alquileres/{id}", alquilerId))
                .andExpect(status().isOk())
                .andExpect(content().string("Alquiler eliminado exitosamente"));
    }

    // 3. Test para obtener todos los alquileres
    @Test
    public void testTraerAlquileres() throws Exception {
        List<Alquiler> alquileres = Arrays.asList(
                new Alquiler(2L, new Cliente(1L, "Juan", "Perez", "Gallardo", "juan@juan.com", "6666666"), new Juego(1L, "The Witcher 3", "RPG", "CD Projekt", 59.99, 5, null), "2024-11-24", "2024-12-01", 20.0, true),
                new Alquiler(3L, new Cliente(13L, "Ana", "Lopez", "Gallardo", "juan@juan.com", "6666666"), new Juego(3L, "The Legend of Zelda", "Aventura", "Nintendo", 59.99, 10, null), "2024-11-22", "2024-11-29", 18.0, true)
        );

        Mockito.when(controladoraLogica.traerAlquileres()).thenReturn(alquileres);

        mockMvc.perform(get("/api/alquileres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(alquileres.size()))
                .andExpect(jsonPath("$[0].cliente.nombre").value("Juan"))
                .andExpect(jsonPath("$[1].juego.nombre").value("The Legend of Zelda"));
    }

    // 4. Test para editar un alquiler
    @Test
    public void testEditarAlquiler() throws Exception {
        Long alquilerId = 2L;
        Alquiler alquilerActualizado = new Alquiler(2L, new Cliente(1L, "Juan", "Perez", "Gallardo", "juan@juan.com", "6666666"), new Juego(1L, "The Witcher 3", "RPG", "CD Projekt", 59.99, 5, null), "2024-11-24", "2024-12-01", 20.0, true);

        Mockito.doNothing().when(controladoraLogica).editarAlquiler(any(Alquiler.class));

        mockMvc.perform(put("/api/alquileres/{id}", alquilerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alquilerActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().string("Alquiler actualizado exitosamente"));
    }

    // 5. Test para obtener un alquiler por ID
    @Test
    public void testObtenerAlquiler() throws Exception {
        Long alquilerId = 2L;
        Alquiler alquiler = new Alquiler(alquilerId, new Cliente(1L, "Juan", "Perez", "Gallardo", "juan@juan.com", "6666666"), new Juego(1L, "The Witcher 3", "RPG", "CD Projekt", 59.99, 5, null), "2024-11-24", "2024-12-01", 20.0, true);

        Mockito.when(controladoraLogica.obtenerAlquiler(alquilerId)).thenReturn(alquiler);

        mockMvc.perform(get("/api/alquileres/{id}", alquilerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente.nombre").value("Juan"))
                .andExpect(jsonPath("$.juego.nombre").value("The Witcher 3"));
    }

    // 6. Test para obtener todos los alquileres activos
    @Test
    public void testTraerAlquileresActivos() throws Exception {
        List<Alquiler> alquileresActivos = Arrays.asList(
                new Alquiler(2L, new Cliente(1L, "Juan", "Perez", "Gallardo", "juan@juan.com", "6666666"), new Juego(1L, "The Witcher 3", "RPG", "CD Projekt", 59.99, 5, null), "2024-11-24", "2024-12-01", 20.0, true),
                new Alquiler(3L, new Cliente(13L, "Ana", "Lopez", "Gallardo", "juan@juan.com", "6666666"), new Juego(3L, "The Legend of Zelda", "Aventura", "Nintendo", 59.99, 10, null), "2024-11-22", "2024-11-29", 18.0, true));

        Mockito.when(controladoraLogica.traerAlquileresActivos()).thenReturn(alquileresActivos);

        mockMvc.perform(get("/api/alquileres/activos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(alquileresActivos.size()))
                .andExpect(jsonPath("$[0].juego.nombre").value("The Witcher 3"))
                .andExpect(jsonPath("$[1].juego.nombre").value("The Legend of Zelda"));
    }

    // 7. Test para finalizar un alquiler
    @Test
    public void testFinalizarAlquiler() throws Exception {
        Long alquilerId = 1L;

        Mockito.doNothing().when(controladoraLogica).finalizarAlquiler(alquilerId);

        mockMvc.perform(put("/api/alquileres/{id}/finalizar", alquilerId))
                .andExpect(status().isOk())
                .andExpect(content().string("Alquiler finalizado exitosamente"));
    }

    // 8. Test para obtener alquileres por cliente
    @Test
    public void testTraerAlquileresPorCliente() throws Exception {
        Long clienteId = 1L;
        List<Alquiler> alquileres = Arrays.asList(
                new Alquiler(2L, new Cliente(1L, "Juan", "Perez", "Gallardo", "juan@juan.com", "6666666"), new Juego(1L, "The Witcher 3", "RPG", "CD Projekt", 59.99, 5, null), "2024-11-24", "2024-12-01", 20.0, true)
        );

        Mockito.when(controladoraLogica.traerAlquileresPorCliente(clienteId)).thenReturn(alquileres);

        mockMvc.perform(get("/api/alquileres/cliente/{clienteId}", clienteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(alquileres.size()))
                .andExpect(jsonPath("$[0].cliente.nombre").value("Juan"));
    }

    // 9. Test para obtener alquileres por juego
    @Test
    public void testTraerAlquileresPorJuego() throws Exception {
        Long juegoId = 1L;
        List<Alquiler> alquileres = Arrays.asList(
                new Alquiler(2L, new Cliente(1L, "Juan", "Perez", "Gallardo", "juan@juan.com", "6666666"), new Juego(1L, "The Witcher 3", "RPG", "CD Projekt", 59.99, 5, null), "2024-11-24", "2024-12-01", 20.0, true)
        );

        Mockito.when(controladoraLogica.traerAlquileresPorJuego(juegoId)).thenReturn(alquileres);

        mockMvc.perform(get("/api/alquileres/juego/{juegoId}", juegoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(alquileres.size()))
                .andExpect(jsonPath("$[0].juego.nombre").value("The Witcher 3"));
    }
}
