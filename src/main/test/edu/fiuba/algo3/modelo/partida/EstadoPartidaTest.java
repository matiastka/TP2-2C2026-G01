package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.partida.estado.Derrota;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.ReceptorDeEstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EstadoPartidaTest {

    @Test
    public void test01UnaPartidaEnJuegoInformaQueSigueEnJuego() {
        // Arrange
        EstadoPartida estado = new EnJuego();
        ReceptorDeEstadoPartida receptor = mock(ReceptorDeEstadoPartida.class);

        // Act
        estado.informarA(receptor);

        // Assert
        verify(receptor).partidaEnJuego();
    }

    @Test
    public void test02UnaVictoriaInformaQueLaPartidaFueGanada() {
        // Arrange
        EstadoPartida estado = new Victoria();
        ReceptorDeEstadoPartida receptor = mock(ReceptorDeEstadoPartida.class);

        // Act
        estado.informarA(receptor);

        // Assert
        verify(receptor).partidaGanada();
    }

    @Test
    public void test03UnaDerrotaInformaQueLaPartidaFuePerdida() {
        // Arrange
        EstadoPartida estado = new Derrota();
        ReceptorDeEstadoPartida receptor = mock(ReceptorDeEstadoPartida.class);

        // Act
        estado.informarA(receptor);

        // Assert
        verify(receptor).partidaPerdida();
    }
}