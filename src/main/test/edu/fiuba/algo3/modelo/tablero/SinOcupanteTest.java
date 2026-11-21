
package edu.fiuba.algo3.modelo.tablero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SinOcupanteTest {

    @Test
    public void test01SinOcupanteNoOcupaLaCasilla() {
        // Arrange
        Ocupante sinOcupante = new SinOcupante();

        // Act
        boolean ocupaCasilla = sinOcupante.ocupaCasilla();

        // Assert
        assertFalse(ocupaCasilla);
    }
}