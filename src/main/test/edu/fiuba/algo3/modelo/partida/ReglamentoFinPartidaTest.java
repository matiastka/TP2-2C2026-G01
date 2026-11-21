package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReglamentoFinPartidaTest {

    private static final String[] MAPA = {
            "EEEEESSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

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
        Tablero tablero = new ConstructorTablero().construir(MAPA);

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
        Tablero tablero = new ConstructorTablero().construir(MAPA);

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
        Tablero tablero = new ConstructorTablero().construir(MAPA);

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
