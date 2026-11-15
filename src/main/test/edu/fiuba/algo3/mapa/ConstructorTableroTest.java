package edu.fiuba.algo3.mapa;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import org.junit.jupiter.api.Test;

public class ConstructorTableroTest {
    private static final String[] MAPA = {
            "MSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01UnaLetraDesconocidaLanzaExcepcion() {
        // Arrange
        String[] mapaInvalido = {
                "XSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        };
        ConstructorDeTablero constructor = new ConstructorDeTablero();

        // Act & Assert
        assertThrows(TableroInvalidoException.class, () -> constructor.construir(mapaInvalido));
    }

    @Test
    public void test02UnaLetraMGeneraUnaCasillaNoTransitable() {
        // Arrange
        ConstructorDeTablero constructor = new ConstructorDeTablero();

        // Act
        Tablero tablero = constructor.construir(MAPA);

        // Assert
        assertFalse(tablero.enCasilla(new Posicion(0, 0)).esTransitable());
    }

    @Test
    public void test03UnaLetraSGeneraUnaCasillaTransitable() {
        // Arrange
        ConstructorDeTablero constructor = new ConstructorDeTablero();

        // Act
        Tablero tablero = constructor.construir(MAPA);

        // Assert
        assertTrue(tablero.enCasilla(new Posicion(0, 1)).esTransitable());
    }

    @Test
    public void test04ElTableroConstruidoTieneTantasFilasComoElMapa() {
        // Arrange
        ConstructorDeTablero constructor = new ConstructorDeTablero();
        int filasEsperadas = 8;

        // Act
        Tablero tablero = constructor.construir(MAPA);

        // Assert
        assertEquals(filasEsperadas, tablero.cantidadFilas());
    }
}
