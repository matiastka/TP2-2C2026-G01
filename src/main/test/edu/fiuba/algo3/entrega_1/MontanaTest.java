
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.terrenos.Montana;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MontanaTest {

    @Test
    public void test01MontanaNoEsTransitable() {
        // Arrange
        Montana montana = new Montana();

        // Act
        boolean esTransitable = montana.esTransitable();

        // Assert
        assertFalse(esTransitable);
    }
}