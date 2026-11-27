package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PartidaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO_FILA1 = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio_fila1.txt";
    private static final String RUTA_MAPA_1 = "src/main/java/edu/fiuba/algo3/modelo/mapa/mapa1.txt";

    private Mech colocarMech(Tablero tablero) {
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(7, 7), tablero);
        return mech;
    }

    @Test
    public void test01UnaPartidaNuevaEmpiezaEnElTurno1() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        // Act
        int turno = partida.obtenerNumeroDeTurno();

        // Assert
        assertEquals(1, turno);
    }

    @Test
    public void test02AlTerminarLaFaseDelJugadorSePasaAlSiguienteTurno() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(2, partida.obtenerNumeroDeTurno());
    }

    @Test
    public void test03AntesDeCompletarLos5TurnosLaPartidaSigueEnJuego() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        // Act
        for (int turno = 0; turno < 4; turno++) {
            partida.terminarFaseJugador();
        }

        // Assert
        assertEquals(EnJuego.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test04TerminarLaFaseDeUnaPartidaTerminadaNoAvanzaElTurno() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());
        for (int turno = 0; turno < 5; turno++) {
            partida.terminarFaseJugador();
        }

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(5, partida.obtenerNumeroDeTurno());
    }

    @Test
    public void test05EnElTurnoSiguienteLosMechsPuedenVolverAMoverse() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Mech mech = colocarMech(tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of());
        mech.moverA(new Posicion(7, 6), tablero);
        partida.terminarFaseJugador();

        // Act & Assert
        assertDoesNotThrow(() -> mech.moverA(new Posicion(7, 5), tablero));
    }

    @Test
    public void test06UnVekMuertoSeRetiraDelTablero() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek vek = new Saltador();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of(vek));
        Posicion posicionDelVek = vek.obtenerPosicion();
        vek.recibirDanio(2);

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertTrue(tablero.enCasilla(posicionDelVek).estaLibre());
    }

    @Test
    public void test07LosVekAnuncianAlEmpezarElTurnoYAtacanAlTerminarLaFaseDelJugador() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO_FILA1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek vek = new Saltador();
        vek.colocarseEn(new Posicion(4, 1), tablero);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of(vek));
        int redInicial = tablero.obtenerRedElectrica();

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test08UnaPartidaNuevaEmpiezaEnFaseJugadorLuegoDeLaFaseVek() {
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        assertEquals(FaseTurno.JUGADOR, partida.obtenerFaseActual());
    }

    @Test
    public void test09AlPasarElTurnoSeReiniciaLaFaseJugador() {
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        partida.terminarFaseJugador();

        assertEquals(FaseTurno.JUGADOR, partida.obtenerFaseActual());
    }

    @Test
    public void test10UnaPartidaNuevaNoTieneVeksEliminados() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        // Act
        int eliminados = partida.obtenerVeksEliminados();

        // Assert
        assertEquals(0, eliminados);
    }

    @Test
    public void test11UnVekMuertoAlTerminarElTurnoCuentaComoEliminado() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek vek = new Saltador();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of(vek));
        vek.recibirDanio(2);

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(1, partida.obtenerVeksEliminados());
    }

    @Test
    public void test12UnVekMuertoEnLaFaseDelJugadorCuentaComoEliminado() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek vek = new Saltador();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of(vek));
        vek.recibirDanio(2);

        // Act
        partida.retirarVeksMuertos();

        // Assert
        assertEquals(1, partida.obtenerVeksEliminados());
    }

    @Test
    public void test13UnVekRetiradoNoSeCuentaDosVeces() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek vek = new Saltador();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of(vek));
        vek.recibirDanio(2);
        partida.retirarVeksMuertos();

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(1, partida.obtenerVeksEliminados());
    }

    @Test
    public void test14UnVekRetiradoNoSeCuentaDosVeces() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(0, partida.obtenerVeksEliminados());
    }

}