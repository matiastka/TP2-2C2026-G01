package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
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

        assertEquals(new Posicion(2, 3), posicion.siguienteEn(Direccion.NORTE));
    }

    @Test
    public void test05LaSiguientePosicionHaciaElSurEstaUnaFilaAbajo() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(4, 3), posicion.siguienteEn(Direccion.SUR));
    }

    @Test
    public void test06LaSiguientePosicionHaciaElEsteEstaUnaColumnaALaDerecha() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(3, 4), posicion.siguienteEn(Direccion.ESTE));
    }

    @Test
    public void test07LaSiguientePosicionHaciaElOesteEstaUnaColumnaALaIzquierda() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(3, 2), posicion.siguienteEn(Direccion.OESTE));
    }

    @Test
    public void test08LaSiguientePosicionHaciaNingunaEsLaMismaPosicion() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(new Posicion(3, 3), posicion.siguienteEn(Direccion.NINGUNA));
    }

    @Test
    public void test09LaDireccionHaciaUnaPosicionDeArribaEsNorte() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(Direccion.NORTE, origen.direccionHacia(new Posicion(0, 3)));
    }

    @Test
    public void test10LaDireccionHaciaUnaPosicionDeAbajoEsSur() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(Direccion.SUR, origen.direccionHacia(new Posicion(6, 3)));
    }

    @Test
    public void test11LaDireccionHaciaUnaPosicionALaDerechaEsEste() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(Direccion.ESTE, origen.direccionHacia(new Posicion(3, 7)));
    }

    @Test
    public void test12LaDireccionHaciaUnaPosicionALaIzquierdaEsOeste() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(Direccion.OESTE, origen.direccionHacia(new Posicion(3, 1)));
    }

    @Test
    public void test13LaDireccionHaciaUnaPosicionAdyacenteEsCorrecta() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(Direccion.NORTE, origen.direccionHacia(new Posicion(2, 3)));
    }

    @Test
    public void test14LaDireccionHaciaUnaPosicionEnDiagonalEsNinguna() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(Direccion.NINGUNA, origen.direccionHacia(new Posicion(1, 5)));
    }

    @Test
    public void test15LaDireccionHaciaLaMismaPosicionEsNinguna() {
        Posicion origen = new Posicion(3, 3);

        assertEquals(Direccion.NINGUNA, origen.direccionHacia(new Posicion(3, 3)));
    }

    @Test
    public void test16LaDistanciaEntreDosPosicionesEsLaSumaDeLasDiferencias() {
        Posicion origen = new Posicion(1, 2);

        assertEquals(5, origen.distanciaA(new Posicion(3, 5)));
    }
}