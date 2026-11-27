
package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import edu.fiuba.algo3.modelo.terrenos.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTableroTest {

    @Test
    public void test01LaLetraSCreaUnaCasillaDeSuelo() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(List.of("S"));

        // Assert
        assertEquals(Suelo.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test02LaLetraECreaUnaCasillaDeEdificio() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(List.of("E"));

        // Assert
        assertEquals(Edificio.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test03LaLetraMCreaUnaCasillaDeMontana() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(List.of("M"));

        // Assert
        assertEquals(Montana.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test04LaLetraACreaUnaCasillaDeAgua() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(List.of("A"));

        // Assert
        assertEquals(Agua.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test05LaLetraCCreaUnaCasillaDeAcido() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(List.of("C"));

        // Assert
        assertEquals(Acido.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test06UnaLetraDesconocidaLanzaExcepcion() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act & Assert
        assertThrows(TableroInvalidoException.class, () -> constructor.construir(List.of("X")));
    }

    @Test
    public void test07DosCasillasConLaMismaLetraTienenTerrenosDistintos() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(List.of("EE"));

        // Assert
        assertNotSame(tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno(),
                tablero.enCasilla(new Posicion(0, 1)).obtenerTerreno());
    }
}