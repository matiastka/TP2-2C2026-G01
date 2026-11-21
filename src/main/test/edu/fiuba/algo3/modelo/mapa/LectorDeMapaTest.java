
package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LectorDeMapaTest {

    @Test
    public void test01ElMapaLeidoTiene8Filas() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();

        // Act
        String[] mapa = lector.leer("src/main/java/edu/fiuba/algo3/modelo/mapa/mapa1.txt");

        // Assert
        assertEquals(8, mapa.length);
    }

    @Test
    public void test02LaPrimeraFilaDelMapaSeLeeCompleta() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();

        // Act
        String[] mapa = lector.leer("src/main/java/edu/fiuba/algo3/modelo/mapa/mapa1.txt");

        // Assert
        assertEquals("AMESSMSM", mapa[0]);
    }

    @Test
    public void test03LeerUnMapaInexistenteLanzaExcepcion() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();

        // Act & Assert
        assertThrows(TableroInvalidoException.class, () -> lector.leer("src/main/java/edu/fiuba/algo3/mapa/noExiste.txt"));
    }
}