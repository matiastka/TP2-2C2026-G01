package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartidaIntegracionTest {

    private static final String RUTA_MAPA_LIBRE =
            "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_CINCO_EDIFICIOS =
            "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_cinco_edificios.txt";

    private Tablero construirTablero(String ruta) {
        String[] mapa = new LectorDeMapa().leer(ruta);
        return new ConstructorTablero().construir(mapa);
    }

    private List<Mech> colocarLosTresMechs(Tablero tablero) {
        Mech combate = new Combate();
        Mech canon = new Canon();
        Mech artilleria = new Artilleria();

        combate.colocarseEn(new Posicion(7, 7), tablero);
        canon.colocarseEn(new Posicion(6, 6), tablero);
        artilleria.colocarseEn(new Posicion(5, 5), tablero);

        return List.of(combate, canon, artilleria);
    }

    @Test
    public void test01UnaPartidaSimuladaCompletaLosCincoTurnosYPreservaVictoria() {
        Tablero tablero = construirTablero(RUTA_MAPA_LIBRE);
        List<Mech> mechs = colocarLosTresMechs(tablero);
        Partida partida = new Partida(tablero, mechs, List.of());
        int[][] destinos = {
                {6, 5, 4},
                {5, 4, 3},
                {4, 3, 2},
                {3, 2, 1},
                {2, 1, 0}
        };

        for (int turno = 1; turno <= 5; turno++) {
            if (turno == 1) {
                mechs.get(0).moverA(new Posicion(7, destinos[turno - 1][0]), tablero);
                mechs.get(1).moverA(new Posicion(6, destinos[turno - 1][1]), tablero);
                mechs.get(2).moverA(new Posicion(5, destinos[turno - 1][2]), tablero);
            }

            partida.terminarFaseJugador();
        }

        assertEquals(Victoria.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test02UnaPartidaSimuladaPierdeCuandoLaRedElectricaLlegaACero() {
        Tablero tablero = construirTablero(RUTA_MAPA_CINCO_EDIFICIOS);
        List<Mech> mechs = colocarLosTresMechs(tablero);
        Partida partida = new Partida(tablero, mechs, List.of());

        for (int columna = 0; columna < 5; columna++) {
            tablero.enCasilla(new Posicion(0, columna)).recibirDanio(1);
        }

        partida.terminarFaseJugador();

        assertEquals(Derrota.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test03UnaPartidaSimuladaPierdeCuandoLosTresMechsSonDestruidos() {
        Tablero tablero = construirTablero(RUTA_MAPA_CINCO_EDIFICIOS);
        List<Mech> mechs = colocarLosTresMechs(tablero);
        Partida partida = new Partida(tablero, mechs, List.of());

        for (Mech mech : mechs) {
            mech.recibirDanio(3);
        }

        partida.terminarFaseJugador();

        assertEquals(Derrota.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test04UnaPartidaIntegraElEmpujeDeUnVekSobreSueloLibre() {
        Tablero tablero = construirTablero(RUTA_MAPA_LIBRE);
        Mech combate = new Combate();
        combate.colocarseEn(new Posicion(3, 2), tablero);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(3, 3), tablero);
        Partida partida = new Partida(tablero, List.of(combate), List.of(vek));

        combate.atacarA(new Posicion(3, 3), tablero);
        partida.retirarVeksMuertos();

        assertEquals(new Posicion(3, 4), vek.obtenerPosicion());
    }
}
