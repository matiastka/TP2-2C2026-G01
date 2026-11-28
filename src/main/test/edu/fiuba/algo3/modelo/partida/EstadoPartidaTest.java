package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.partida.estado.Derrota;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EstadoPartidaTest {

    @Test
    public void testEnJuegoNoEstaTerminadaNiEsVictoria() {
        EstadoPartida estado = new EnJuego();

        assertFalse(estado.estaTerminada());
        assertFalse(estado.ganoElJugador());
    }

    @Test
    public void testVictoriaEstaTerminadaYElJugadorGano() {
        EstadoPartida estado = new Victoria();

        assertTrue(estado.estaTerminada());
        assertTrue(estado.ganoElJugador());
    }

    @Test
    public void testDerrotaEstaTerminadaYElJugadorNoGano() {
        EstadoPartida estado = new Derrota();

        assertTrue(estado.estaTerminada());
        assertFalse(estado.ganoElJugador());
    }
}
