
package edu.fiuba.algo3.modelo.unidades.veks.ia;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReglaAcercarseAEdificioTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01IntentarEjecutarDevuelveTrueCuandoHayUnEdificioYPosicionesAlcanzables() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 0), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 1), new Posicion(3, 2));
        ReglaAcercarseAEdificio regla = new ReglaAcercarseAEdificio();

        // Act
        boolean resultado = regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void test02IntentarEjecutarMueveAlVekALaPosicionMasCercanaAlEdificio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 0), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 1), new Posicion(3, 2));
        ReglaAcercarseAEdificio regla = new ReglaAcercarseAEdificio();

        // Act
        regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);

        // Assert
        assertEquals(new Posicion(3, 2), saltador.obtenerPosicion());
    }

    @Test
    public void test03IntentarEjecutarNoAnunciaNingunAtaqueAlAcercarse() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 0), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 1), new Posicion(3, 2));
        ReglaAcercarseAEdificio regla = new ReglaAcercarseAEdificio();

        // Act
        regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);
        saltador.resolverAtaque(tablero);

        // Assert
        assertEquals(redInicial, tablero.obtenerRedElectrica());
    }

    @Test
    public void test04IntentarEjecutarDevuelveFalseCuandoNoHayEdificiosEnElTablero() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 0), tablero);
        List<Posicion> posicionesAlcanzables = List.of(new Posicion(3, 1), new Posicion(3, 2));
        ReglaAcercarseAEdificio regla = new ReglaAcercarseAEdificio();

        // Act
        boolean resultado = regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);

        // Assert
        assertFalse(resultado);
    }

    @Test
    public void test05IntentarEjecutarDevuelveFalseCuandoNoHayPosicionesAlcanzables() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 0), tablero);
        List<Posicion> posicionesAlcanzables = List.of();
        ReglaAcercarseAEdificio regla = new ReglaAcercarseAEdificio();

        // Act
        boolean resultado = regla.intentarEjecutar(saltador, posicionesAlcanzables, tablero);

        // Assert
        assertFalse(resultado);
    }
}