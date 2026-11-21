
package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.direcciones.DireccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.tablero.direcciones.Norte;
import edu.fiuba.algo3.modelo.tablero.direcciones.Oeste;
import edu.fiuba.algo3.modelo.tablero.direcciones.Sur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PosicionTest {

    @Test
    public void test01DosPosicionesConLaMismaFilaYColumnaSonIguales() {
        // Arrange
        Posicion posicion = new Posicion(3, 3);
        Posicion esperada = new Posicion(3, 3);

        // Act & Assert
        assertEquals(esperada, posicion);
    }

    @Test
    public void test02DosPosicionesConDistintaFilaNoSonIguales() {
        // Arrange
        Posicion posicion = new Posicion(3, 3);
        Posicion otraPosicion = new Posicion(4, 3);

        // Act & Assert
        assertNotEquals(otraPosicion, posicion);
    }

    @Test
    public void test03DosPosicionesConDistintaColumnaNoSonIguales() {
        // Arrange
        Posicion posicion = new Posicion(3, 3);
        Posicion otraPosicion = new Posicion(3, 4);

        // Act & Assert
        assertNotEquals(otraPosicion, posicion);
    }

    @Test
    public void test04LaSiguientePosicionHaciaElNorteEstaUnaFilaArriba() {
        // Arrange
        Posicion posicion = new Posicion(3, 3);

        // Act
        Posicion siguiente = posicion.siguienteEn(new Norte());

        // Assert
        assertEquals(new Posicion(2, 3), siguiente);
    }

    @Test
    public void test05LaSiguientePosicionHaciaElSurEstaUnaFilaAbajo() {
        // Arrange
        Posicion posicion = new Posicion(3, 3);

        // Act
        Posicion siguiente = posicion.siguienteEn(new Sur());

        // Assert
        assertEquals(new Posicion(4, 3), siguiente);
    }

    @Test
    public void test06LaSiguientePosicionHaciaElEsteEstaUnaColumnaALaDerecha() {
        // Arrange
        Posicion posicion = new Posicion(3, 3);

        // Act
        Posicion siguiente = posicion.siguienteEn(new Este());

        // Assert
        assertEquals(new Posicion(3, 4), siguiente);
    }

    @Test
    public void test07LaSiguientePosicionHaciaElOesteEstaUnaColumnaALaIzquierda() {
        // Arrange
        Posicion posicion = new Posicion(3, 3);

        // Act
        Posicion siguiente = posicion.siguienteEn(new Oeste());

        // Assert
        assertEquals(new Posicion(3, 2), siguiente);
    }

    @Test
    public void test08UnaPosicionEnDiagonalNoEstaEnLineaRecta() {
        // Arrange
        Posicion origen = new Posicion(3, 3);
        Posicion diagonal = new Posicion(1, 5);

        // Act
        boolean enLineaRecta = origen.estaEnLineaRectaA(diagonal, 1);

        // Assert
        assertFalse(enLineaRecta);
    }

    @Test
    public void test09LaDireccionHaciaUnaPosicionDeArribaEsNorte() {
        // Arrange
        Posicion origen = new Posicion(3, 3);

        // Act
        Direccion direccion = origen.direccionHacia(new Posicion(0, 3));

        // Assert
        assertEquals(new Norte(), direccion);
    }

    @Test
    public void test10LaDireccionHaciaUnaPosicionDeAbajoEsSur() {
        // Arrange
        Posicion origen = new Posicion(3, 3);

        // Act
        Direccion direccion = origen.direccionHacia(new Posicion(6, 3));

        // Assert
        assertEquals(new Sur(), direccion);
    }

    @Test
    public void test11LaDireccionHaciaUnaPosicionALaDerechaEsEste() {
        // Arrange
        Posicion origen = new Posicion(3, 3);

        // Act
        Direccion direccion = origen.direccionHacia(new Posicion(3, 7));

        // Assert
        assertEquals(new Este(), direccion);
    }

    @Test
    public void test12LaDireccionHaciaUnaPosicionALaIzquierdaEsOeste() {
        // Arrange
        Posicion origen = new Posicion(3, 3);

        // Act
        Direccion direccion = origen.direccionHacia(new Posicion(3, 1));

        // Assert
        assertEquals(new Oeste(), direccion);
    }

    @Test
    public void test13LaDireccionHaciaUnaPosicionAdyacenteEsCorrecta() {
        // Arrange
        Posicion origen = new Posicion(3, 3);

        // Act
        Direccion direccion = origen.direccionHacia(new Posicion(2, 3));

        // Assert
        assertEquals(new Norte(), direccion);
    }

    @Test
    public void test14PedirLaDireccionHaciaUnaPosicionEnDiagonalLanzaExcepcion() {
        // Arrange
        Posicion origen = new Posicion(3, 3);
        Posicion diagonal = new Posicion(1, 5);

        // Act & Assert
        assertThrows(DireccionInvalidaException.class, () -> origen.direccionHacia(diagonal));
    }

    @Test
    public void test15PedirLaDireccionHaciaLaMismaPosicionLanzaExcepcion() {
        // Arrange
        Posicion origen = new Posicion(3, 3);

        // Act & Assert
        assertThrows(DireccionInvalidaException.class, () -> origen.direccionHacia(new Posicion(3, 3)));
    }

    @Test
    public void test16LaDistanciaEntreDosPosicionesEsLaSumaDeLasDiferencias() {
        // Arrange
        Posicion origen = new Posicion(1, 2);
        Posicion destino = new Posicion(3, 5);

        // Act
        int distancia = origen.distanciaA(destino);

        // Assert
        assertEquals(5, distancia);
    }
}