package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.Acido;
import edu.fiuba.algo3.modelo.terrenos.Suelo;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeshacerContaminacionTest {

    @Test
    public void testDeshacerMovimientoDesdeAcidoRestauraEstadoSano() {
        // Arrange
        Map<Posicion, Casilla> casillas = new HashMap<>();
        Posicion origen = new Posicion(1, 1);
        Posicion destinoAcido = new Posicion(1, 2);
        
        casillas.put(origen, new Casilla(new Suelo(), origen));
        casillas.put(destinoAcido, new Casilla(new Acido(), destinoAcido));
        
        Tablero tablero = new Tablero(casillas);
        
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
