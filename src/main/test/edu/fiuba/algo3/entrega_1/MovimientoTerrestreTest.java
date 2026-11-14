
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
    private static final String[] MAPA_CON_MONTANA = {
            "SMSSSSSS",
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
        Tablero tablero = new Tablero(MAPA_CON_MONTANA);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        assertFalse(movimiento.puedeLlegar(origen, destino, 3, tablero));
    }

    @Test
    public void test04CasillaDestinoOcupadaPorOtraUnidadDevuelveFalse() {
        // Arrange
        Tablero tablero = new Tablero(MAPA_BASE);

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        MovimientoTerrestre movimiento = new MovimientoTerrestre();

        // Act
        tablero.ocuparCasilla(destino, new Combate(origen));

        // Assert
        assertFalse(movimiento.puedeLlegar(origen, destino, 3, tablero));
    }

    @Test
    public void test05AunqueTengaRangoNoPuedeRodearLaMontana() {
        Tablero tablero = new Tablero(MAPA_CON_MONTANA);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        assertFalse(movimiento.puedeLlegar(origen, destino, 4, tablero));
    }

    @Test
    public void test06DestinoQueNoEstaEnLaMismaFilaNiColumnaDevuelveFalse() {
        Tablero tablero = new Tablero(MAPA_BASE);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(1, 2);

        assertFalse(movimiento.puedeLlegar(origen, destino, 3, tablero));
    }

    @Test
    public void test07NoPuedeTerminarSobreUnaMontana() {
        Tablero tablero = new Tablero(MAPA_CON_MONTANA);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);

        assertFalse(movimiento.puedeLlegar(origen, destino, 3, tablero));
    }

}