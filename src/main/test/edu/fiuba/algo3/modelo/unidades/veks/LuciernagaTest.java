
package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LuciernagaTest {

    @Test
    public void test01LuciernagaEmpiezaCon3DeVida() {
        // Arrange
        Luciernaga luciernaga = new Luciernaga(new Posicion(0, 0));
        int vidaEsperada = 3;

        // Act
        int vidaActual = luciernaga.obtenerVida();

        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    @Test
    public void test02LuciernagaTieneRango2() {
        // Arrange
        Luciernaga luciernaga = new Luciernaga(new Posicion(0, 0));
        int rangoEsperado = 2;

        // Act
        int rangoActual = luciernaga.obtenerRango();

        // Assert
        assertEquals(rangoEsperado, rangoActual);
    }
}