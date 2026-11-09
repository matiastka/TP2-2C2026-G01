package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {
    private static final String[] MAPA = {
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
        assertTrue(tablero.enCasilla(posicionMontana).obtenerTerreno().esDelMismoTipoQue(new Montana()));
    }

    @Test
    public void test05UnTableroCargadoPreDefinidoTieneAguaEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionAgua = new Posicion(0,0);

        // Act & Assert
        assertTrue(tablero.enCasilla(posicionAgua).obtenerTerreno().esDelMismoTipoQue(new Agua()));
    }


    @Test
    public void test06UnTableroCargadoPreDefinidoTieneAcidoEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionAcido = new Posicion(1,5);

        // Act & Assert
        assertTrue(tablero.enCasilla(posicionAcido).obtenerTerreno().esDelMismoTipoQue(new Acido()));
    }

    @Test
    public void test07UnTableroCargadoPreDefinidoTieneEdificioEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionEdificio = new Posicion(0,2);

        // Act & Assert
        assertTrue(tablero.enCasilla(posicionEdificio).obtenerTerreno().esDelMismoTipoQue(new Edificio()));
    }
    @Test
    public void test08UnTableroCargadoPreDefinidoTieneSueloEnUnaPosicionParticular(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionSuelo = new Posicion(6,1);

        // Act & Assert
        assertTrue(tablero.enCasilla(posicionSuelo).obtenerTerreno().esDelMismoTipoQue(new Suelo()));
    }

}

