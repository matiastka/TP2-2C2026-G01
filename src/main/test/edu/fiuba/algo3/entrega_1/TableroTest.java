package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import edu.fiuba.algo3.mapa.LectorDeMapa;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {
    private static final String[] MAPA = new LectorDeMapa().leer("src/main/java/edu/fiuba/algo3/mapa/mapa1.txt");

    @Test
    public void test01UnTableroCargadoTiene8Columnas(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        int cantidadEsperada = 8;

        // Act
        int cantidadColumnas = tablero.cantidadColumnas();

        // Assert
        assertEquals(cantidadColumnas, cantidadEsperada);
    }

    @Test
    public void test02UnTableroCargadoTiene8Filas(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        int cantidadEsperada = 8;

        // Act
        int cantidadFilas = tablero.cantidadFilas();

        // Assert
        assertEquals(cantidadFilas, cantidadEsperada);
    }

    @Test
    public void test03UnTableroCargadoEsDe8x8(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        int cantidadEsperada = 64;

        // Act
        int cantidadCeldas = tablero.cantidadColumnas() * tablero.cantidadFilas();

        // Assert
        assertEquals(cantidadCeldas, cantidadEsperada);
    }

    @Test
    public void test04UnTableroCargadoPreDefinidoTieneMontanaEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMontana = new Posicion(0,7);

        // Act & Assert
        assertFalse(tablero.enCasilla(posicionMontana).esTransitable());
    }

    @Test
    public void test05UnTableroCargadoPreDefinidoTieneAguaEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionAgua = new Posicion(0,0);

        Unidad luciernaga = new Luciernaga();

        luciernaga.colocarseEn(posicionAgua, tablero);

        // Act
        tablero.enCasilla(posicionAgua).aplicarEfectoA(luciernaga);

        // Assert
        assertEquals(0, luciernaga.obtenerVida());
    }


    @Test
    public void test06UnTableroCargadoPreDefinidoTieneAcidoEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionAcido = new Posicion(1,5);

        // Act & Assert
        assertTrue(tablero.enCasilla(posicionAcido).esTransitable());
    }

    @Test
    public void test07UnTableroCargadoPreDefinidoTieneEdificioEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionEdificio = new Posicion(0,2);

        // Act & Assert
        assertFalse(tablero.enCasilla(posicionEdificio).esTransitable());
    }
    @Test
    public void test08UnTableroCargadoPreDefinidoTieneSueloEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionSuelo = new Posicion(6,1);

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
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion posicion = new Posicion(7, 7);

        // Act & Assert
        assertTrue(tablero.estaEnRango(posicion));
    }

    @Test
    public void test10Posicion80NoEstaEnElTablero() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion posicion = new Posicion(8, 0);

        // Act & Assert
        assertFalse(tablero.estaEnRango(posicion));
    }
}

