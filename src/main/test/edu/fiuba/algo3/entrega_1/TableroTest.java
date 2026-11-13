package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {
    private static final String[] MAPA = { // Pasar a ser un archivo.txt con su test
            "AMESSMSM",
            "AMSSECSA",
            "ASESSCSA",
            "ASSESCSA",
            "ASSSSCSA",
            "ASSSECSA",
            "ASSSSCSA",
            "AMMMSCSA"
    };

    @Test
    public void test01UnTableroCargadoTiene8Columnas(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        int cantidadEsperada = 8;

        // Act
        int cantidadColumnas = tablero.cantidadColumnas();

        // Assert
        assertEquals(cantidadColumnas, cantidadEsperada);
    }

    @Test
    public void test02UnTableroCargadoTiene8Filas(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        int cantidadEsperada = 8;

        // Act
        int cantidadFilas = tablero.cantidadFilas();

        // Assert
        assertEquals(cantidadFilas, cantidadEsperada);
    }

    @Test
    public void test03UnTableroCargadoEsDe8x8(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        int cantidadEsperada = 64;

        // Act
        int cantidadCeldas = tablero.cantidadColumnas() * tablero.cantidadFilas();

        // Assert
        assertEquals(cantidadCeldas, cantidadEsperada);
    }

    @Test
    public void test04UnTableroCargadoPreDefinidoTieneMontanaEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMontana = new Posicion(0,7);

        // Act & Assert
        assertFalse(tablero.enCasilla(posicionMontana).esTransitable());
    }

    @Test
    public void test05UnTableroCargadoPreDefinidoTieneAguaEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionAgua = new Posicion(0,0);

        Unidad luciernaga = new Luciernaga(posicionAgua);

        tablero.ocuparCasilla(posicionAgua, luciernaga);

        // Act
        tablero.enCasilla(posicionAgua).aplicarEfectoA(luciernaga);

        // Assert
        assertEquals(0, luciernaga.obtenerVida());
    }


    @Test
    public void test06UnTableroCargadoPreDefinidoTieneAcidoEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionAcido = new Posicion(1,5);

        // Act & Assert
        assertTrue(tablero.enCasilla(posicionAcido).esTransitable());
    }

    @Test
    public void test07UnTableroCargadoPreDefinidoTieneEdificioEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionEdificio = new Posicion(0,2);

        // Act & Assert
        assertFalse(tablero.enCasilla(posicionEdificio).esTransitable());
    }
    @Test
    public void test08UnTableroCargadoPreDefinidoTieneSueloEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionSuelo = new Posicion(6,1);

        Unidad luciernaga = new Luciernaga(posicionSuelo);

        tablero.ocuparCasilla(posicionSuelo, luciernaga);

        // Act
        tablero.enCasilla(posicionSuelo).aplicarEfectoA(luciernaga);

        // Assert
        assertEquals(3, luciernaga.obtenerVida());
    }

}

