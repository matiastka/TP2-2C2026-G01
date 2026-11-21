
package edu.fiuba.algo3.modelo.unidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstadoSanoTest {

    @Test
    public void test01CalcularDanioDevuelveElMismoValorRecibido() {
        // Arrange
        EstadoSano estado = new EstadoSano();
        int danioBase = 2;

        // Act
        int danioCalculado = estado.calcularDanio(danioBase);

        // Assert
        assertEquals(2, danioCalculado);
    }

    @Test
    public void test02CalcularDanioConCeroDevuelveCero() {
        // Arrange
        EstadoSano estado = new EstadoSano();

        // Act
        int danioCalculado = estado.calcularDanio(0);

        // Assert
        assertEquals(0, danioCalculado);
    }
}
