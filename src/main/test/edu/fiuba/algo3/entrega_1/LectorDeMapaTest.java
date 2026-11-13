package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.mapa.LectorDeMapa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LectorDeMapaTest {

    @Test
    public void test01ElMapaLeidoTiene8Filas() {
        LectorDeMapa lector = new LectorDeMapa();

        String[] mapa = lector.leer("src/main/java/edu/fiuba/algo3/mapa/mapa1.txt");

        assertEquals(8, mapa.length);
    }

    @Test
    public void test02LaPrimeraFilaDelMapaSeLeeCompleta() {
        LectorDeMapa lector = new LectorDeMapa();

        String[] mapa = lector.leer("src/main/java/edu/fiuba/algo3/mapa/mapa1.txt");

        assertEquals("AMESSMSM", mapa[0]);
    }

    @Test
    public void test03LeerUnMapaInexistenteLanzaExcepcion() {
        LectorDeMapa lector = new LectorDeMapa();

        assertThrows(TableroInvalidoException.class, () -> lector.leer("src/main/java/edu/fiuba/algo3/mapa/noExiste.txt"));
    }
}
