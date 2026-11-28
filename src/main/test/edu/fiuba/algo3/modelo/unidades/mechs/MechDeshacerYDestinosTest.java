package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.excepciones.CaminoBloqueadoException;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MechDeshacerYDestinosTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_AGUA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_agua.txt";
    private static final String RUTA_MAPA_ACIDO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_acido.txt";

    @Test
    public void test01NoSePuedeDeshacerSiOtroMechOcupaLaPosicionAnterior() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech primero = new Combate();
        Mech segundo = new Canon();
        primero.colocarseEn(new Posicion(0, 0), tablero);
        segundo.colocarseEn(new Posicion(0, 3), tablero);
        primero.moverA(new Posicion(1, 0), tablero);
        segundo.moverA(new Posicion(0, 0), tablero);

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> primero.deshacerMovimiento(tablero));
    }

    @Test
    public void test02SiNoSePuedeDeshacerElOtroMechSigueEnSuCasilla() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech primero = new Combate();
        Mech segundo = new Canon();
        primero.colocarseEn(new Posicion(0, 0), tablero);
        segundo.colocarseEn(new Posicion(0, 3), tablero);
        primero.moverA(new Posicion(1, 0), tablero);
        segundo.moverA(new Posicion(0, 0), tablero);

        // Act
        assertThrows(CaminoBloqueadoException.class, () -> primero.deshacerMovimiento(tablero));

        // Assert
        assertEquals(segundo, tablero.enCasilla(new Posicion(0, 0)).obtenerOcupante());
    }

    @Test
    public void test03ElAguaEstaEntreLosDestinosAlcanzablesDeUnMech() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(2, 2), tablero);
        List<Posicion> destinos = new ArrayList<>();

        // Act
        mech.agregarSiPuedeLlegarA(new Posicion(2, 4), destinos, tablero);

        // Assert
        assertTrue(destinos.contains(new Posicion(2, 4)));
    }

    @Test
    public void test04ElAcidoEstaEntreLosDestinosAlcanzablesDeUnMech() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_ACIDO);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(1, 0), tablero);
        List<Posicion> destinos = new ArrayList<>();

        // Act
        mech.agregarSiPuedeLlegarA(new Posicion(1, 2), destinos, tablero);

        // Assert
        assertTrue(destinos.contains(new Posicion(1, 2)));
    }
}
