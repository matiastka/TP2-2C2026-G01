
package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.excepciones.PartidaTerminadaException;
import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;

public class VictoriaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    private Mech crearMechEnTablero(Tablero tablero) {
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(7, 7), tablero);
        return mech;
    }

    @Test
    public void test01TerminarFaseJugadorLanzaPartidaTerminadaException() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(crearMechEnTablero(tablero)), List.of());
        EstadoPartida victoria = new Victoria();

        // Act & Assert
        assertThrows(PartidaTerminadaException.class, () -> victoria.terminarFaseJugador(partida));
    }

    @Test
    public void test02TerminarFaseJugadorNoAvanzaElNumeroDeTurno() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(crearMechEnTablero(tablero)), List.of());
        EstadoPartida victoria = new Victoria();
        int turnoInicial = partida.obtenerNumeroDeTurno();

        // Act
        try {
            victoria.terminarFaseJugador(partida);
        } catch (PartidaTerminadaException ignored) {
        }

        // Assert
        assertEquals(turnoInicial, partida.obtenerNumeroDeTurno());
    }
}