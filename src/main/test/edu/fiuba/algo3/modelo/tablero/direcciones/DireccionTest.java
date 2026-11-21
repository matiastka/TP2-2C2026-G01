
package edu.fiuba.algo3.modelo.tablero.direcciones;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DireccionTest {

    @Test
    public void test01NorteDesde23VaA13() {
        // Arrange
        Posicion esperada = new Posicion(1, 3);
        Direccion norte = new Norte();

        // Act
        Posicion actual = norte.desplazar(2, 3);

        // Assert
        assertEquals(esperada, actual);
    }

    @Test
    public void test02SurDesde23VaA33() {
        // Arrange
        Posicion esperada = new Posicion(3, 3);
        Direccion sur = new Sur();

        // Act
        Posicion actual = sur.desplazar(2, 3);

        // Assert
        assertEquals(esperada, actual);
    }

    @Test
    public void test03EsteDesde23VaA24() {
        // Arrange
        Posicion esperada = new Posicion(2, 4);
        Direccion este = new Este();

        // Act
        Posicion actual = este.desplazar(2, 3);

        // Assert
        assertEquals(esperada, actual);
    }

    @Test
    public void test04OesteDesde23VaA22() {
        // Arrange
        Posicion esperada = new Posicion(2, 2);
        Direccion oeste = new Oeste();

        // Act
        Posicion actual = oeste.desplazar(2, 3);

        // Assert
        assertEquals(esperada, actual);
    }

    @Test
    public void test05DosDireccionesDistintasNoSonIguales() {
        // Arrange
        Direccion norte = new Norte();
        Direccion sur = new Sur();

        // Act & Assert
        assertNotEquals(norte, sur);
    }

    @Test
    public void test06Hay4Cardinales() {
        // Arrange
        int cantidadEsperada = 4;

        // Act
        List<Direccion> cardinales = Direccion.cardinales();

        // Assert
        assertEquals(cantidadEsperada, cardinales.size());
    }
}