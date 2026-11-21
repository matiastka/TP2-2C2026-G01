
package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import edu.fiuba.algo3.modelo.terrenos.Acido;
import edu.fiuba.algo3.modelo.terrenos.Agua;
import edu.fiuba.algo3.modelo.terrenos.Edificio;
import edu.fiuba.algo3.modelo.terrenos.Montana;
import edu.fiuba.algo3.modelo.terrenos.Suelo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstructorTableroTest {

    @Test
    public void test01LaLetraSCreaUnaCasillaDeSuelo() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(new String[]{"S"});

        // Assert
        assertEquals(Suelo.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test02LaLetraECreaUnaCasillaDeEdificio() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(new String[]{"E"});

        // Assert
        assertEquals(Edificio.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test03LaLetraMCreaUnaCasillaDeMontana() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(new String[]{"M"});

        // Assert
        assertEquals(Montana.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test04LaLetraACreaUnaCasillaDeAgua() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(new String[]{"A"});

        // Assert
        assertEquals(Agua.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test05LaLetraCCreaUnaCasillaDeAcido() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(new String[]{"C"});

        // Assert
        assertEquals(Acido.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test06UnaLetraDesconocidaLanzaExcepcion() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act & Assert
        assertThrows(TableroInvalidoException.class, () -> constructor.construir(new String[]{"X"}));
    }

    @Test
    public void test07DosCasillasConLaMismaLetraTienenTerrenosDistintos() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(new String[]{"EE"});

        // Assert
        assertNotSame(tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno(),
                tablero.enCasilla(new Posicion(0, 1)).obtenerTerreno());
    }
}