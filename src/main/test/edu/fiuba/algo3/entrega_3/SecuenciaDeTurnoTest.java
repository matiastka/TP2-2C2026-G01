package edu.fiuba.algo3.entrega_3;

import java.util.List;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.FabricaDeVeks;
import edu.fiuba.algo3.modelo.partida.GeneradorDeEmergencias;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecuenciaDeTurnoTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    private GeneradorDeEmergencias sinEmergencias() {
        return new GeneradorDeEmergencias(new FabricaDeVeks(), List.of());
    }

    private GeneradorDeEmergencias emergenciasEnLaFila(int fila) {
        return new GeneradorDeEmergencias(new FabricaDeVeks(), List.of(fila));
    }

    private Mech colocarCombate(Posicion posicion, Tablero tablero) {
        Mech combate = new Combate();
        combate.colocarseEn(posicion, tablero);
        return combate;
    }

    private Vek colocarSaltador(Posicion posicion, Tablero tablero) {
        Vek saltador = new Saltador();
        saltador.colocarseEn(posicion, tablero);
        return saltador;
    }

    @Test
    public void test01EnLaFaseVekElVekAnunciaSuAtaqueAntesDeQueJuegueElJugador() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posicionMech = new Posicion(4, 4);
        Mech mech = colocarCombate(posicionMech, tablero);
        Vek vek = colocarSaltador(new Posicion(4, 5), tablero);

        // Act
        new Partida(tablero, List.of(mech), List.of(vek), sinEmergencias());

        // Assert
        assertEquals(List.of(posicionMech), vek.obtenerPosicionesAmenazadas(tablero));
    }

    @Test
    public void test02DuranteLaFaseDelJugadorElAtaqueAnunciadoTodaviaNoSeEjecuta() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = colocarCombate(new Posicion(4, 4), tablero);
        Vek vek = colocarSaltador(new Posicion(4, 5), tablero);

        // Act
        new Partida(tablero, List.of(mech), List.of(vek), sinEmergencias());

        // Assert
        assertEquals(3, mech.obtenerVida());
    }

    @Test
    public void test03EnLaFaseDeResolucionSeEjecutaElAtaqueAnunciado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = colocarCombate(new Posicion(4, 4), tablero);
        Vek vek = colocarSaltador(new Posicion(4, 5), tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of(vek), sinEmergencias());

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(1, mech.obtenerVida());
    }

    @Test
    public void test04ElJugadorPuedeEsquivarElAtaqueAnunciadoMoviendoAlMech() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = colocarCombate(new Posicion(4, 4), tablero);
        Vek vek = colocarSaltador(new Posicion(4, 5), tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of(vek), sinEmergencias());
        mech.moverA(new Posicion(4, 1), tablero);

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(3, mech.obtenerVida());
    }

    @Test
    public void test05LaEmergenciaAnunciadaHaceAparecerUnVekAlTerminarElTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = colocarCombate(new Posicion(4, 4), tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of(), emergenciasEnLaFila(7));

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(1, partida.obtenerVeks().size());
    }

    @Test
    public void test06UnMechParadoSobreLaEmergenciaLaBloqueaYRecibeUnoDeDanio() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = colocarCombate(new Posicion(6, 0), tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of(), emergenciasEnLaFila(7));
        mech.moverA(new Posicion(7, 0), tablero);

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test07SiLaEmergenciaEstaBloqueadaNoApareceNingunVek() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = colocarCombate(new Posicion(6, 0), tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of(), emergenciasEnLaFila(7));
        mech.moverA(new Posicion(7, 0), tablero);

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(0, partida.obtenerVeks().size());
    }

    @Test
    public void test08AlTerminarElTurnoComienzaElSiguienteYElVekVuelveAAnunciar() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posicionMech = new Posicion(4, 4);
        Mech mech = colocarCombate(posicionMech, tablero);
        Vek vek = colocarSaltador(new Posicion(4, 5), tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of(vek), sinEmergencias());

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(List.of(posicionMech), vek.obtenerPosicionesAmenazadas(tablero));
    }

    @Test
    public void test09AlTerminarLaFaseDelJugadorSePasaAlSiguienteTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, List.of(colocarCombate(new Posicion(7, 7), tablero)), List.of(), sinEmergencias());

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(2, partida.obtenerNumeroDeTurno());
    }

    @Test
    public void test10EnElTurnoSiguienteLosMechsPuedenVolverAMoverse() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = colocarCombate(new Posicion(7, 7), tablero);
        Partida partida = new Partida(tablero, List.of(mech), List.of(), sinEmergencias());
        mech.moverA(new Posicion(7, 6), tablero);
        partida.terminarFaseJugador();

        // Act
        mech.moverA(new Posicion(7, 5), tablero);

        // Assert
        assertEquals(new Posicion(7, 5), mech.obtenerPosicion());
    }
}
