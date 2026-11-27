
package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.estado.Derrota;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;

public class ReglamentoFinPartidaTest {

    private static final String RUTA_MAPA_CINCO_EDIFICIOS = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_cinco_edificios.txt";

    private List<Mech> colocarMechs(Tablero tablero) {
        Mech combate = new Combate();
        Mech canon = new Canon();
        Mech artilleria = new Artilleria();

        combate.colocarseEn(new Posicion(6, 1), tablero);
        canon.colocarseEn(new Posicion(6, 3), tablero);
        artilleria.colocarseEn(new Posicion(6, 5), tablero);

        return List.of(combate, canon, artilleria);
    }

    @Test
    public void test01CompletarLos5TurnosConLaRedElectricaMayorACeroEsVictoria() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_CINCO_EDIFICIOS);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, colocarMechs(tablero), List.of());

        // Act
        for (int turno = 0; turno < 5; turno++) {
            partida.terminarFaseJugador();
        }

        // Assert
        assertEquals(Victoria.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test02SiLaRedElectricaLlegaACeroEsDerrota() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_CINCO_EDIFICIOS);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Partida partida = new Partida(tablero, colocarMechs(tablero), List.of());
        for (int columna = 0; columna < 5; columna++) {
            tablero.enCasilla(new Posicion(0, columna)).recibirDanio(1);
        }

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(Derrota.class, partida.obtenerEstado().getClass());
    }

    @Test
    public void test03SiLosTresMechsSonDestruidosEsDerrota() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_CINCO_EDIFICIOS);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        List<Mech> mechs = colocarMechs(tablero);
        Partida partida = new Partida(tablero, mechs, List.of());
        for (Mech mech : mechs) {
            mech.recibirDanio(3);
        }

        // Act
        partida.terminarFaseJugador();

        // Assert
        assertEquals(Derrota.class, partida.obtenerEstado().getClass());
    }
}
