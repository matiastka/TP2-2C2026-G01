
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovimientoTerrestreTest {

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
    public void test01DestinoDentroDeRangoYLibreDevuelveTrue() {
        Tablero tablero = new Tablero(MAPA_BASE);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 3);
        
        assertTrue(movimiento.puedeLlegar(origen, destino, 3, tablero));
    }

    @Test
    public void test02DestinoFueraDeRangoDevuelveFalse() {
        Tablero tablero = new Tablero(MAPA_BASE);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 4);
        
        assertFalse(movimiento.puedeLlegar(origen, destino, 3, tablero));
    }

    @Test
    public void test03CaminoBloqueadoPorMontanaDevuelveFalse() {
        String[] mapaConMontana = {
            "SMSSSSSS", "SSSSSSSS", "SSSSSSSS", "SSSSSSSS",
            "SSSSSSSS", "SSSSSSSS", "SSSSSSSS", "SSSSSSSS"
        };
        Tablero tablero = new Tablero(mapaConMontana);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);
        
        assertFalse(movimiento.puedeLlegar(origen, destino, 3, tablero));
    }

    @Test
    public void test04CasillaDestinoOcupadaPorOtraUnidadDevuelveFalse() {
        Tablero tablero = new Tablero(MAPA_BASE);
        tablero.ocuparCasilla(0, 2, new Combate());
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);
        
        assertFalse(movimiento.puedeLlegar(origen, destino, 3, tablero));
    }
}