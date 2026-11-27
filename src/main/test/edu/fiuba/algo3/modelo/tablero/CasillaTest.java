
package edu.fiuba.algo3.modelo.tablero;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.terrenos.Suelo;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;

public class CasillaTest {

    @Test
    public void test01UnaCasillaNuevaEstaLibre() {
        // Arrange
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0, 0));

        // Act
        boolean estaLibre = casilla.estaLibre();

        // Assert
        assertTrue(estaLibre);
    }

    @Test
    public void test02UnaCasillaOcupadaNoEstaLibre() {
        // Arrange
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0, 0));

        // Act
        casilla.ocupar(new Combate());

        // Assert
        assertFalse(casilla.estaLibre());
    }

    @Test
    public void test03UnaCasillaDesocupadaVuelveAEstarLibre() {
        // Arrange
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0, 0));
        casilla.ocupar(new Combate());

        // Act
        casilla.desOcupar();

        // Assert
        assertTrue(casilla.estaLibre());
    }

    @Test
    public void test04UnaCasillaLibreNoDevuelveNull() {
        // Arrange
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0, 0));

        // Act
        Ocupante ocupante = casilla.obtenerOcupante();

        // Assert
        assertNotNull(ocupante);
    }
}