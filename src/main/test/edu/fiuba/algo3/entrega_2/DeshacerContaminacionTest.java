
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeshacerContaminacionTest {

    private static final String[] MAPA_CON_ACIDO = {
            "SSSSSSSS",
            "SSCSSSSS", 
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void testDeshacerMovimientoDesdeAcidoRestauraEstadoSano() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_ACIDO);
        
        Posicion origen = new Posicion(1, 1);
        Posicion destinoAcido = new Posicion(1, 2);
        
        Combate mech = new Combate(); 
        mech.colocarseEn(origen, tablero);

        // Act
        mech.moverA(destinoAcido, tablero);
        mech.deshacerMovimiento(tablero);

        int vidaAntesDelAtaque = mech.obtenerVida();
        mech.recibirDanio(1);

        // Assert
        assertEquals(vidaAntesDelAtaque - 1, mech.obtenerVida());
    }
}