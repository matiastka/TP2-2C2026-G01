package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmpujeTest {
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
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);

        Posicion origen = new Posicion(3, 2);
        Posicion objetivo = new Posicion(3, 3);
        Posicion bloqueo = new Posicion(3, 4);

        Mech mech = new Combate();
        Unidad objetivoVek = new Luciernaga();
        Unidad bloqueador = new Luciernaga();

        mech.colocarseEn(origen, tablero);
        objetivoVek.colocarseEn(objetivo, tablero);
        bloqueador.colocarseEn(bloqueo, tablero);

        mech.atacarA(objetivo, tablero);

        assertSame(objetivoVek, tablero.enCasilla(objetivo).obtenerOcupante());
        assertEquals(0, objetivoVek.obtenerVida());
    }
}
