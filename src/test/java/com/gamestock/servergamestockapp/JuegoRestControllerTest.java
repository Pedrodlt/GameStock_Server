
package com.gamestock.servergamestockapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamestock.servergamestockapp.logica.Controladora;
import com.gamestock.servergamestockapp.logica.Juego;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author pedro
 */


@WebMvcTest(JuegoRestController.class)
public class JuegoRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Controladora controladoraLogica;

    @Autowired
    private ObjectMapper objectMapper;

    // 1. Test para crear un juego
    @Test
    public void testCrearJuego() throws Exception {
        Juego juego = new Juego(1L, "The Witcher 3", "RPG", "CD Projekt", 59.99, 5, null);
        
        Mockito.doNothing().when(controladoraLogica).crearJuego(any(Juego.class));

        mockMvc.perform(post("/api/juegos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(juego)))
                .andExpect(status().isOk())
                .andExpect(content().string("Juego creado exitosamente"));
    }

    // 2. Test para eliminar un juego
    @Test
    public void testEliminarJuego() throws Exception {
        Long juegoId = 1L;

        Mockito.doNothing().when(controladoraLogica).eliminarJuego(juegoId);

        mockMvc.perform(delete("/api/juegos/{id}", juegoId))
                .andExpect(status().isOk())
                .andExpect(content().string("Juego eliminado exitosamente"));
    }

    // 3. Test para obtener todos los juegos
    @Test
    public void testTraerJuegos() throws Exception {
        List<Juego> juegos = Arrays.asList(
                new Juego(2L, "Super Mario Bros", "Juego clásico de plataformas.", "Nintendo", 69.99, 9, null),
                new Juego(3L, "The Legend of Zelda", "Juego de aventuras épico.","Nintendo", 59.99, 10, null)
        );

        Mockito.when(controladoraLogica.traerJuegos()).thenReturn(juegos);

        mockMvc.perform(get("/api/juegos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(juegos.size()))
                .andExpect(jsonPath("$[0].nombre").value("Super Mario Bros"))
                .andExpect(jsonPath("$[1].nombre").value("The Legend of Zelda"));
    }

    // 4. Test para editar un juego
    @Test
    public void testEditarJuego() throws Exception {
        Long juegoId = 2L;
        Juego juegoActualizado = new Juego(juegoId, "Super Mario Bros", "Juego clásico ACTUALIZADO de plataformas.", "Nintendo", 69.99, 9, null);

        Mockito.doNothing().when(controladoraLogica).editarJuego(any(Juego.class));

        mockMvc.perform(put("/api/juegos/{id}", juegoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(juegoActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().string("Juego actualizado exitosamente"));
    }

    // 5. Test para obtener un juego por ID
    @Test
    public void testObtenerJuego() throws Exception {
        Long juegoId = 2L;
        Juego juego = new Juego(juegoId, "Super Mario Bros", "Juego clásico de plataformas.", "Nintendo", 69.99, 9, null);

        Mockito.when(controladoraLogica.obtenerJuego(juegoId)).thenReturn(juego);

        mockMvc.perform(get("/api/juegos/{id}", juegoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Super Mario Bros"))
                .andExpect(jsonPath("$.genero").value("Juego clásico de plataformas."));
    }

    // 6. Test para obtener un juego por nombre
    @Test
    public void testObtenerJuegoNombre() throws Exception {
        String nombreJuego = "Super Mario Bros";
        Juego juego = new Juego(2L, nombreJuego, "Juego clásico de plataformas.", "Nintendo", 69.99, 9, null);

        Mockito.when(controladoraLogica.obtenerJuegoNombre(nombreJuego)).thenReturn(juego);

        mockMvc.perform(get("/api/juegos/nombre/{nombre}", nombreJuego))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(nombreJuego))
                .andExpect(jsonPath("$.genero").value("Juego clásico de plataformas."));
    }

    // 7. Test para alquilar un juego
    @Test
    public void testAlquilarJuego() throws Exception {
        Long juegoId = 2L;

        Mockito.when(controladoraLogica.alquilarJuego(juegoId)).thenReturn(true);

        mockMvc.perform(post("/api/juegos/{id}/alquilar", juegoId))
                .andExpect(status().isOk())
                .andExpect(content().string("Juego alquilado exitosamente."));
    }

    // 8. Test para devolver un juego
    @Test
    public void testDevolverJuego() throws Exception {
        Long juegoId = 2L;

        Mockito.doNothing().when(controladoraLogica).devolverJuego(juegoId);

        mockMvc.perform(post("/api/juegos/{id}/devolver", juegoId))
                .andExpect(status().isOk())
                .andExpect(content().string("Juego devuelto exitosamente."));
    }
}
