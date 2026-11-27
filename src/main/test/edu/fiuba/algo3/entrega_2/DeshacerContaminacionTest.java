
package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;

public class DeshacerContaminacionTest {

    private static final String RUTA_MAPA_ACIDO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_acido.txt";

    @Test
    public void test01DeshacerMovimientoDesdeAcidoRestauraEstadoSano() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_ACIDO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(1, 1);
        Posicion destinoAcido = new Posicion(1, 2);
        Combate mech = new Combate();
        mech.colocarseEn(origen, tablero);
        int vidaAntesDelAtaque = mech.obtenerVida();

        // Act
        mech.moverA(destinoAcido, tablero);
        mech.deshacerMovimiento(tablero);
        mech.recibirDanio(1);

        // Assert
        assertEquals(vidaAntesDelAtaque - 1, mech.obtenerVida());
    }
}