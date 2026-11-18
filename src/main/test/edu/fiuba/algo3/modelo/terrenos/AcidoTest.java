
package edu.fiuba.algo3.modelo.terrenos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AcidoTest {

    @Test
    public void test01AcidoEsTransitable() {
        // Arrange
        Acido acido = new Acido();

        // Act
        boolean esTransitable = acido.esTransitable();

        // Assert
        assertTrue(esTransitable);
    }
}