
package edu.fiuba.algo3.modelo.unidades.veks.ia;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.ia.reglas.ReglaAtacarEdificio;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;

public class ReglaAtacarEdificioTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01IntentarEjecutarDevuelveTrueCuandoHayUnEdificioAlAlcance() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 2));
        ReglaAtacarEdificio regla = new ReglaAtacarEdificio();

        // Act
        boolean resultado = regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void test02IntentarEjecutarMueveAlVekALaPosicionDesdeDondeAtacaAlEdificio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 2));
        ReglaAtacarEdificio regla = new ReglaAtacarEdificio();

        // Act
        regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);

        // Assert
        assertEquals(new Posicion(3, 2), saltador.obtenerPosicion());
    }

    @Test
    public void test03IntentarEjecutarPreparaElAtaqueContraElEdificioAlAlcance() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 2));
        ReglaAtacarEdificio regla = new ReglaAtacarEdificio();

        // Act
        regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);
        saltador.resolverAtaque(tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test04IntentarEjecutarDevuelveFalseCuandoNoHayNingunEdificioAlAlcance() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 2));
        ReglaAtacarEdificio regla = new ReglaAtacarEdificio();

        // Act
        boolean resultado = regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);

        // Assert
        assertFalse(resultado);
    }

    @Test
    public void test05IntentarEjecutarNoAtacaAUnMechAunqueEsteAlAlcance() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 3), tablero);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 2));
        ReglaAtacarEdificio regla = new ReglaAtacarEdificio();

        // Act
        boolean resultado = regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);

        // Assert
        assertFalse(resultado);
    }
}