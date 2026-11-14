package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmpujeEntrega2Test {
    private static final String[] MAPA_BASE = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01AlEmpujarUnaUnidadContraOtraNoSeMueveYRecibeDanioDeColision() {
        RedElectrica.getInstancia().reiniciar();
        Tablero tablero = new Tablero(MAPA_BASE);

        Posicion origen = new Posicion(3, 2);
        Posicion objetivo = new Posicion(3, 3);
        Posicion bloqueo = new Posicion(3, 4);

        Mech mech = new Combate(origen);
        Unidad objetivoVek = new Luciernaga(objetivo);
        Unidad bloqueador = new Luciernaga(bloqueo);

        tablero.ocuparCasilla(origen, mech);
        tablero.ocuparCasilla(objetivo, objetivoVek);
        tablero.ocuparCasilla(bloqueo, bloqueador);

        mech.atacarA(objetivo, tablero);

        assertSame(objetivoVek, tablero.enCasilla(objetivo).obtenerUnidad());
        assertEquals(0, objetivoVek.obtenerVida());
    }
}
