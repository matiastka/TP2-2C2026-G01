package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SinUnidadTest {

    @Test
    public void test01SinUnidadNoOcupaLaCasilla() {
        Unidad sinUnidad = new SinUnidad(new Posicion(0,0 ));

        assertFalse(sinUnidad.ocupaCasilla());
    }

    @Test
    public void test02UnaUnidadRealOcupaLaCasilla() {
        Unidad mech = new Combate(new Posicion(1,1));

        assertTrue(mech.ocupaCasilla());
    }

    @Test
    public void test03SinUnidadNoPierdeVidaAlRecibirDanio() {
        Unidad sinUnidad = new SinUnidad(new Posicion(0,0));

        sinUnidad.recibirDanio(2);

        assertEquals(0, sinUnidad.obtenerVida());
    }
}

