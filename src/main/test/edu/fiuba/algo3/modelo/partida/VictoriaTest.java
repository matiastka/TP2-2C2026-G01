package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class VictoriaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    private Mech crearMechEnTablero(Tablero tablero) {
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(7, 7), tablero);
        return mech;
    }

    @Test
    public void test01TerminarFaseJugadorNoCambiaElEstadoDeLaPartida() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, List.of(crearMechEnTablero(tablero)), List.of());
        EstadoPartida victoria = new Victoria();

        // Act
        EstadoPartida siguiente = victoria.terminarFaseJugador(partida);

        // Assert
        assertSame(victoria, siguiente);
    }

    @Test
    public void test02TerminarFaseJugadorNoAvanzaElNumeroDeTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, List.of(crearMechEnTablero(tablero)), List.of());
        EstadoPartida victoria = new Victoria();
        int turnoInicial = partida.obtenerNumeroDeTurno();

        // Act
        victoria.terminarFaseJugador(partida);

        // Assert
        assertEquals(turnoInicial, partida.obtenerNumeroDeTurno());
    }
}