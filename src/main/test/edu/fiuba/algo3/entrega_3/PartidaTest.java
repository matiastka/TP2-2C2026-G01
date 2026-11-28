package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PartidaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_1 = "src/main/java/edu/fiuba/algo3/modelo/mapa/mapa1.txt";

    private Mech colocarMech(Tablero tablero) {
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(7, 7), tablero);
        return mech;
    }

    @Test
    public void test01UnaPartidaNuevaEmpiezaEnElTurno1() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        // Act
        int turno = partida.obtenerNumeroDeTurno();

        // Assert
        assertEquals(1, turno);
    }

    @Test
    public void test02TerminarLaFaseDeUnaPartidaTerminadaNoAvanzaElTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
    public void test03UnVekMuertoSeRetiraDelTablero() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
    public void test04UnaPartidaNuevaNoTieneVeksEliminados() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        // Act
        int eliminados = partida.obtenerVeksEliminados();

        // Assert
        assertEquals(0, eliminados);
    }

    @Test
    public void test05UnVekMuertoAlTerminarElTurnoCuentaComoEliminado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
    public void test06UnVekMuertoEnLaFaseDelJugadorCuentaComoEliminado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
    public void test07UnVekRetiradoNoSeCuentaDosVeces() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
    public void test08UnVekNoEmergeSobreAguaYNoCuentaComoEliminado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_1);
        Partida partida = new Partida(tablero, List.of(colocarMech(tablero)), List.of());

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(0, partida.obtenerVeksEliminados());
    }
}
