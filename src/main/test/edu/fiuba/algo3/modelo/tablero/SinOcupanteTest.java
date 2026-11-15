package edu.fiuba.algo3.modelo.tablero;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SinOcupanteTest {

    @Test
    public void test01SinOcupanteNoOcupaLaCasilla() {
        Ocupante sinOcupante = new SinOcupante();

        assertFalse(sinOcupante.ocupaCasilla());
    }
}

