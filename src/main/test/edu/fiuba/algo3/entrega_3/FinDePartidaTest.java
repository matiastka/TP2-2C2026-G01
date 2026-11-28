package edu.fiuba.algo3.entrega_3;

import java.util.List;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.FabricaDeVeks;
import edu.fiuba.algo3.modelo.partida.GeneradorDeEmergencias;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.partida.estado.Derrota;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinDePartidaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_CINCO_EDIFICIOS = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_cinco_edificios.txt";

    private GeneradorDeEmergencias sinEmergencias() {
        return new GeneradorDeEmergencias(new FabricaDeVeks(), List.of());
    }

    private List<Mech> colocarLosTresMechs(Tablero tablero) {
        Mech combate = new Combate();
        Mech canon = new Canon();
        Mech artilleria = new Artilleria();
        combate.colocarseEn(new Posicion(7, 0), tablero);
        canon.colocarseEn(new Posicion(7, 3), tablero);
        artilleria.colocarseEn(new Posicion(7, 6), tablero);
        return List.of(combate, canon, artilleria);
    }

    private Vek colocarSaltador(Posicion posicion, Tablero tablero) {
        Vek saltador = new Saltador();
        saltador.colocarseEn(posicion, tablero);
        return saltador;
    }

    private void jugarTurnos(Partida partida, int cantidad) {
        for (int turno = 0; turno < cantidad; turno++) {
            partida.terminarFaseJugador();
        }
    }

    @Test
    public void test01SobrevivirLosCincoTurnosEsVictoria() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, colocarLosTresMechs(tablero), List.of(), sinEmergencias());

        // Act
        jugarTurnos(partida, 5);

        // Assert
        assertEquals(Victoria.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test02AntesDelQuintoTurnoLaPartidaSigueEnJuego() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Partida partida = new Partida(tablero, colocarLosTresMechs(tablero), List.of(), sinEmergencias());

        // Act
        jugarTurnos(partida, 4);

        // Assert
        assertEquals(EnJuego.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test03SiLosVekDestruyenLosCincoEdificiosLaRedElectricaLlegaACeroYEsDerrota() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_CINCO_EDIFICIOS);
        List<Vek> veks = List.of(
                colocarSaltador(new Posicion(1, 0), tablero),
                colocarSaltador(new Posicion(1, 1), tablero),
                colocarSaltador(new Posicion(1, 2), tablero),
                colocarSaltador(new Posicion(1, 3), tablero),
                colocarSaltador(new Posicion(1, 4), tablero)
        );
        Partida partida = new Partida(tablero, colocarLosTresMechs(tablero), veks, sinEmergencias());

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(Derrota.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test04SiLosVekDestruyenLosTresMechsEsDerrota() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        List<Mech> mechs = colocarLosTresMechs(tablero);
        List<Vek> veks = List.of(
                colocarSaltador(new Posicion(6, 0), tablero),
                colocarSaltador(new Posicion(7, 1), tablero),
                colocarSaltador(new Posicion(6, 3), tablero),
                colocarSaltador(new Posicion(7, 4), tablero),
                colocarSaltador(new Posicion(6, 6), tablero)
        );
        Partida partida = new Partida(tablero, mechs, veks, sinEmergencias());

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(Derrota.class, partida.obtenerEstado().getClass());
    }
}
