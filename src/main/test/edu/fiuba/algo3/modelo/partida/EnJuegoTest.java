
package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.ReceptorDeEstadoPartida;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EnJuegoTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    private Mech crearMechEnTablero(Tablero tablero) {
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(7, 7), tablero);
        return mech;
    }

    @Test
    public void test01TerminarFaseJugadorDevuelveEnJuegoMientrasQuedenTurnosYMechsVivos() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, List.of(crearMechEnTablero(tablero)), List.of());
        EstadoPartida estado = new EnJuego();
        ReceptorDeEstadoPartida receptor = mock(ReceptorDeEstadoPartida.class);

        // Act
        EstadoPartida nuevoEstado = estado.terminarFaseJugador(partida);
        nuevoEstado.informarA(receptor);

        // Assert
        verify(receptor).partidaEnJuego();
    }

    @Test
    public void test02TerminarFaseJugadorEnElQuintoTurnoDevuelveVictoriaSiHayMechsYRedElectrica() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, List.of(crearMechEnTablero(tablero)), List.of());
        for (int turno = 0; turno < 4; turno++) {
            partida.terminarFaseJugador();
        }
        EstadoPartida estado = new EnJuego();
        ReceptorDeEstadoPartida receptor = mock(ReceptorDeEstadoPartida.class);

        // Act
        EstadoPartida nuevoEstado = estado.terminarFaseJugador(partida);
        nuevoEstado.informarA(receptor);

        // Assert
        verify(receptor).partidaGanada();
    }

    @Test
    public void test03TerminarFaseJugadorDevuelveDerrotaSiTodosLosMechsSonDestruidos() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = crearMechEnTablero(tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of());
        mech.recibirDanio(3);
        EstadoPartida estado = new EnJuego();
        ReceptorDeEstadoPartida receptor = mock(ReceptorDeEstadoPartida.class);

        // Act
        EstadoPartida nuevoEstado = estado.terminarFaseJugador(partida);
        nuevoEstado.informarA(receptor);

        // Assert
        verify(receptor).partidaPerdida();
    }
}