
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.Acido;
import edu.fiuba.algo3.modelo.terrenos.Edificio;
import edu.fiuba.algo3.modelo.terrenos.Montana;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableroTest {

    private static final String RUTA_MAPA_1 = "src/main/java/edu/fiuba/algo3/modelo/mapa/mapa1.txt";

    @Test
    public void test01UnTableroCargadoTiene8Columnas() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int cantidadEsperada = 8;

        // Act
        int cantidadColumnas = tablero.cantidadColumnas();

        // Assert
        assertEquals(cantidadEsperada, cantidadColumnas);
    }

    @Test
    public void test02UnTableroCargadoTiene8Filas() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int cantidadEsperada = 8;

        // Act
        int cantidadFilas = tablero.cantidadFilas();

        // Assert
        assertEquals(cantidadEsperada, cantidadFilas);
    }

    @Test
    public void test03UnTableroCargadoEsDe8x8() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int cantidadEsperada = 64;

        // Act
        int cantidadCeldas = tablero.cantidadColumnas() * tablero.cantidadFilas();

        // Assert
        assertEquals(cantidadEsperada, cantidadCeldas);
    }

    @Test
    public void test04UnTableroCargadoPreDefinidoTieneMontanaEnUnaPosicionParticular() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMontana = new Posicion(0, 7);

        // Act & Assert
        assertEquals(Montana.class, tablero.enCasilla(posicionMontana).obtenerTerreno().getClass());
    }

    @Test
    public void test05UnTableroCargadoPreDefinidoTieneAguaEnUnaPosicionParticular() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionAgua = new Posicion(0, 0);
        Unidad luciernaga = new Luciernaga();
        luciernaga.colocarseEn(posicionAgua, tablero);

        // Act
        tablero.enCasilla(posicionAgua).aplicarEfectoA(luciernaga);

        // Assert
        assertEquals(0, luciernaga.obtenerVida());
    }

    @Test
    public void test06UnTableroCargadoPreDefinidoTieneAcidoEnUnaPosicionParticular() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionAcido = new Posicion(1, 5);

        // Act & Assert
        assertEquals(Acido.class, tablero.enCasilla(posicionAcido).obtenerTerreno().getClass());
    }

    @Test
    public void test07UnTableroCargadoPreDefinidoTieneEdificioEnUnaPosicionParticular() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionEdificio = new Posicion(0, 2);

        // Act & Assert
        assertEquals(Edificio.class, tablero.enCasilla(posicionEdificio).obtenerTerreno().getClass());
    }

    @Test
    public void test08UnTableroCargadoPreDefinidoTieneSueloEnUnaPosicionParticular() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionSuelo = new Posicion(6, 1);
        Unidad luciernaga = new Luciernaga();
        luciernaga.colocarseEn(posicionSuelo, tablero);

        // Act
        tablero.enCasilla(posicionSuelo).aplicarEfectoA(luciernaga);

        // Assert
        assertEquals(3, luciernaga.obtenerVida());
    }

    @Test
    public void test09Posicion77EstaEnElTablero() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(7, 7);

        // Act & Assert
        assertTrue(tablero.estaEnRango(posicion));
    }

    @Test
    public void test10Posicion80NoEstaEnElTablero() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_1);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(8, 0);

        // Act & Assert
        assertFalse(tablero.estaEnRango(posicion));
    }
}