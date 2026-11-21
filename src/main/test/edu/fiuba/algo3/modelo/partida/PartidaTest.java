package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import edu.fiuba.algo3.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PartidaTest {

    private static final String[] MAPA_LIBRE = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    private static final String[] MAPA_CON_EDIFICIO = {
            "SSSSSSSS",
            "SESSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    private Mech colocarMech(Tablero tablero) {
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(7, 7), tablero);
        return mech;
    }

    @Test
    public void test01UnaPartidaNuevaEmpiezaEnElTurno1() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);

        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        assertEquals(1, partida.obtenerNumeroDeTurno());
    }

    @Test
    public void test02AlTerminarLaFaseDelJugadorSePasaAlSiguienteTurno() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);

        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        partida.terminarFaseJugador();

        assertEquals(2, partida.obtenerNumeroDeTurno());
    }

    @Test
    public void test03AntesDeCompletarLos5TurnosLaPartidaSigueEnJuego() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);

        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        for (int turno = 0; turno < 4; turno++) {
            partida.terminarFaseJugador();
        }

        assertEquals(EnJuego.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test04NoSePuedeSeguirJugandoUnaPartidaTerminada() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);

        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        for (int turno = 0; turno < 5; turno++) {
            partida.terminarFaseJugador();
        }

        assertThrows(PartidaTerminadaException.class, partida::terminarFaseJugador);
    }

    @Test
    public void test05EnElTurnoSiguienteLosMechsPuedenVolverAMoverse() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);

        Mech mech = colocarMech(tablero);

        Partida partida = new Partida(tablero, List.of(mech), List.of());

        mech.moverA(new Posicion(7, 6), tablero);

        partida.terminarFaseJugador();

        assertDoesNotThrow(() -> mech.moverA(new Posicion(7, 5), tablero));
    }

    @Test
    public void test06UnVekMuertoSeRetiraDelTablero() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);

        Vek vek = new Saltador();

        vek.colocarseEn(new Posicion(0, 0), tablero);

        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of(vek));

        Posicion posicionDelVek = vek.obtenerPosicion();

        vek.recibirDanio(2);

        partida.terminarFaseJugador();

        assertTrue(tablero.enCasilla(posicionDelVek).estaLibre());
    }

    @Test
    public void test07LosVekAnuncianAlEmpezarElTurnoYAtacanAlTerminarLaFaseDelJugador() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);

        Vek vek = new Saltador();

        vek.colocarseEn(new Posicion(4, 1), tablero);

        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of(vek));

        int redInicial = tablero.obtenerRedElectrica();

        partida.terminarFaseJugador();

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }
}
