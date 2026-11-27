
package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.estado.Derrota;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        // Act
        EstadoPartida nuevoEstado = estado.terminarFaseJugador(partida);

        // Assert
        assertEquals(EnJuego.class, nuevoEstado.getClass());
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

        // Act
        EstadoPartida nuevoEstado = estado.terminarFaseJugador(partida);

        // Assert
        assertEquals(Victoria.class, nuevoEstado.getClass());
    }

    @Test
    public void test03TerminarFaseJugadorDevuelveDerrotaSiTodosLosMechsSonDestruidos() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = crearMechEnTablero(tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of());
        mech.recibirDanio(3);
        EstadoPartida estado = new EnJuego();

        // Act
        EstadoPartida nuevoEstado = estado.terminarFaseJugador(partida);

        // Assert
        assertEquals(Derrota.class, nuevoEstado.getClass());
    }
}