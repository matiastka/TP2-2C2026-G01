
package edu.fiuba.algo3.modelo.terrenos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EdificioTest {

    @Test
    public void test01EdificioNoEsTransitable() {
        // Arrange
        Edificio edificio = new Edificio();

        // Act
        boolean esTransitable = edificio.esTransitable();

        // Assert
        assertFalse(esTransitable);
    }
}