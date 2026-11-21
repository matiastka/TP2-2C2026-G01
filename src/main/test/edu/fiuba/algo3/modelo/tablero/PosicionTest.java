package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.tablero.direcciones.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PosicionTest {

    @Test
    public void test01DosPosicionesConLaMismaFilaYColumnaSonIguales() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(3, 3), posicion);
    }

    @Test
    public void test02DosPosicionesConDistintaFilaNoSonIguales() {
        Posicion posicion = new Posicion(3, 3);

        assertNotEquals(new Posicion(4, 3), posicion);
    }

    @Test
    public void test03DosPosicionesConDistintaColumnaNoSonIguales() {
        Posicion posicion = new Posicion(3, 3);

        assertNotEquals(new Posicion(3, 4), posicion);
    }

    @Test
    public void test04LaSiguientePosicionHaciaElNorteEstaUnaFilaArriba() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(2, 3), posicion.siguienteEn(new Norte()));
    }

    @Test
    public void test05LaSiguientePosicionHaciaElSurEstaUnaFilaAbajo() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(4, 3), posicion.siguienteEn(new Sur()));
    }

    @Test
    public void test06LaSiguientePosicionHaciaElEsteEstaUnaColumnaALaDerecha() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(3, 4), posicion.siguienteEn(new Este()));
    }

    @Test
    public void test07LaSiguientePosicionHaciaElOesteEstaUnaColumnaALaIzquierda() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(3, 2), posicion.siguienteEn(new Oeste()));
    }

    @Test
    public void test08UnaPosicionEnDiagonalNoEstaEnLineaRecta() {
        Posicion origen = new Posicion(3, 3);

        assertFalse(origen.estaEnLineaRectaA(new Posicion(1, 5), 1));
    }

    @Test
    public void test09LaDireccionHaciaUnaPosicionDeArribaEsNorte() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(new Norte(), origen.direccionHacia(new Posicion(0, 3)));
    }

    @Test
    public void test10LaDireccionHaciaUnaPosicionDeAbajoEsSur() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(new Sur(), origen.direccionHacia(new Posicion(6, 3)));
    }

    @Test
    public void test11LaDireccionHaciaUnaPosicionALaDerechaEsEste() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(new Este(), origen.direccionHacia(new Posicion(3, 7)));
    }

    @Test
    public void test12LaDireccionHaciaUnaPosicionALaIzquierdaEsOeste() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(new Oeste(), origen.direccionHacia(new Posicion(3, 1)));
    }

    @Test
    public void test13LaDireccionHaciaUnaPosicionAdyacenteEsCorrecta() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(new Norte(), origen.direccionHacia(new Posicion(2, 3)));
    }

    @Test
    public void test14PedirLaDireccionHaciaUnaPosicionEnDiagonalLanzaExcepcion() {
        Posicion origen = new Posicion(3, 3);

        assertThrows(DireccionInvalidaException.class, () -> origen.direccionHacia(new Posicion(1, 5)));
    }

    @Test
    public void test15PedirLaDireccionHaciaLaMismaPosicionLanzaExcepcion() {
        Posicion origen = new Posicion(3, 3);

        assertThrows(DireccionInvalidaException.class, () -> origen.direccionHacia(new Posicion(3, 3)));
    }

    @Test
    public void test16LaDistanciaEntreDosPosicionesEsLaSumaDeLasDiferencias() {
        Posicion origen = new Posicion(1, 2);

        assertEquals(5, origen.distanciaA(new Posicion(3, 5)));
    }
}