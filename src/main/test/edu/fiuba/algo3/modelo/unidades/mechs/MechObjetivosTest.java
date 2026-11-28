package edu.fiuba.algo3.modelo.unidades.mechs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;

public class MechObjetivosTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01ElMechDeCombatePuedeAtacarUnaCasillaAdyacente() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech combate = new Combate();
        combate.colocarseEn(new Posicion(3, 3), tablero);
        List<Posicion> objetivos = new ArrayList<>();

        // Act
        combate.agregarSiPuedeAtacarA(new Posicion(3, 4), objetivos, tablero);

        // Assert
        assertTrue(objetivos.contains(new Posicion(3, 4)));
    }

    @Test
    public void test02ElMechDeCombateNoPuedeAtacarUnaCasillaADistanciaDos() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech combate = new Combate();
        combate.colocarseEn(new Posicion(3, 3), tablero);
        List<Posicion> objetivos = new ArrayList<>();

        // Act
        combate.agregarSiPuedeAtacarA(new Posicion(3, 5), objetivos, tablero);

        // Assert
        assertFalse(objetivos.contains(new Posicion(3, 5)));
    }

    @Test
    public void test03ElCanonPuedeAtacarEnLineaRectaLejos() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 3), tablero);
        List<Posicion> objetivos = new ArrayList<>();

        // Act
        canon.agregarSiPuedeAtacarA(new Posicion(3, 7), objetivos, tablero);

        // Assert
        assertTrue(objetivos.contains(new Posicion(3, 7)));
    }

    @Test
    public void test04ElCanonNoPuedeAtacarEnDiagonal() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 3), tablero);
        List<Posicion> objetivos = new ArrayList<>();

        // Act
        canon.agregarSiPuedeAtacarA(new Posicion(4, 4), objetivos, tablero);

        // Assert
        assertFalse(objetivos.contains(new Posicion(4, 4)));
    }

    @Test
    public void test05LaArtilleriaNoPuedeAtacarUnaCasillaAdyacente() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech artilleria = new Artilleria();
        artilleria.colocarseEn(new Posicion(3, 3), tablero);
        List<Posicion> objetivos = new ArrayList<>();

        // Act
        artilleria.agregarSiPuedeAtacarA(new Posicion(3, 4), objetivos, tablero);

        // Assert
        assertFalse(objetivos.contains(new Posicion(3, 4)));
    }

    @Test
    public void test06LaArtilleriaPuedeAtacarADistanciaDosEnLineaRecta() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech artilleria = new Artilleria();
        artilleria.colocarseEn(new Posicion(3, 3), tablero);
        List<Posicion> objetivos = new ArrayList<>();

        // Act
        artilleria.agregarSiPuedeAtacarA(new Posicion(3, 5), objetivos, tablero);

        // Assert
        assertTrue(objetivos.contains(new Posicion(3, 5)));
    }
}