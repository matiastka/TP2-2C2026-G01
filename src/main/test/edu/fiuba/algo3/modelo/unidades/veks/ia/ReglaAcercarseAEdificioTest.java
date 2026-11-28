
package edu.fiuba.algo3.modelo.unidades.veks.ia;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.ia.reglas.ReglaAcercarseAEdificio;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReglaAcercarseAEdificioTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01IntentarEjecutarDevuelveTrueCuandoHayUnEdificioYPosicionesAlcanzables() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
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
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
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
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
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
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
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