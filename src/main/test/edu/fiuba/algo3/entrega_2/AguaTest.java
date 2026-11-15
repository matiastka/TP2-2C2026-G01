package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.unidades.veks.*;
import edu.fiuba.algo3.modelo.unidades.mechs.*;
import edu.fiuba.algo3.modelo.unidades.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AguaTest {

    private static final String[] MAPA = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSASSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01UnaLuciernagaEmpujadaAlAguaPorElCanonMuere(){
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionVek = new Posicion(2,3);
        Posicion posicionMech = new Posicion(2,0);

        Vek luciernagaVek = new Luciernaga();
        Mech canonMech = new Canon();

        luciernagaVek.colocarseEn(posicionVek, tablero);
        canonMech.colocarseEn(posicionMech, tablero);

        canonMech.atacarA(posicionVek, tablero);

        int vidaEsperada = 0;

        assertEquals(vidaEsperada, luciernagaVek.obtenerVida());


    }
}
