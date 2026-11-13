package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CasillaTest {

    @Test
    public void test01UnaCasillaNuevaEstaLibre() {
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0,0));

        assertTrue(casilla.estaLibre());
    }

    @Test
    public void test02UnaCasillaOcupadaNoEstaLibre() {
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0,0));

        casilla.ocupar(new Combate(new Posicion(0,0)));

        assertFalse(casilla.estaLibre());
    }

    @Test
    public void test03UnaCasillaDesocupadaVuelveAEstarLibre() {
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0,0));
        casilla.ocupar(new Combate(new Posicion(0,0)));

        casilla.desOcupar();

        assertTrue(casilla.estaLibre());
    }

    @Test
    public void test04UnaCasillaLibreNoDevuelveNull() {
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0,0));

        assertNotNull(casilla.obtenerUnidad());
    }

}

