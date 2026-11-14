package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdificioTest {
    private static final String[] MAPA_CON_EDIFICIO = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSESSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test02ElDanioAUnEdificioReduceLaRedElectricaEn1() {
        RedElectrica.getInstancia().reiniciar();
        Tablero tablero = new Tablero(MAPA_CON_EDIFICIO);

        Posicion origen = new Posicion(3, 2);
        Posicion edificio = new Posicion(3, 3);

        Mech mech = new Combate(origen);
        tablero.ocuparCasilla(origen, mech);

        mech.atacarA(edificio, tablero);

        assertEquals(2, tablero.obtenerRedElectrica());
    }
}
