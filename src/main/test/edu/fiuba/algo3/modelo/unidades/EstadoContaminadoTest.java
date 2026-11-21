
package edu.fiuba.algo3.modelo.unidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstadoContaminadoTest {

    @Test
    public void test01CalcularDanioDuplicaElValorRecibido() {
        // Arrange
        EstadoContaminado estado = new EstadoContaminado();
        int danioBase = 2;

        // Act
        int danioCalculado = estado.calcularDanio(danioBase);

        // Assert
        assertEquals(4, danioCalculado);
    }

    @Test
    public void test02CalcularDanioConUnoDevuelveDos() {
        // Arrange
        EstadoContaminado estado = new EstadoContaminado();

        // Act
        int danioCalculado = estado.calcularDanio(1);

        // Assert
        assertEquals(2, danioCalculado);
    }
}