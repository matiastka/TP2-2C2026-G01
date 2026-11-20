
package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
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
    public void test01DestinoDentroDeRangoYLibreEsValido() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 3);

        ResultadoAccion<Void> resultado = movimiento.validarCamino(origen, destino, 3, tablero);
        assertEquals("exito", resultado.fold(ignorado -> "exito", motivo -> "rechazo"));
    }

    @Test
    public void test02DestinoFueraDeRangoRetornaRechazo() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 4);

        assertEquals(MotivoRechazoAccion.DESTINO_NO_ALCANZABLE,
                motivoRechazo(movimiento.validarCamino(origen, destino, 3, tablero)));
    }

    @Test
    public void test03CaminoBloqueadoPorMontanaRetornaRechazo() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_MONTANA);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        assertEquals(MotivoRechazoAccion.CAMINO_BLOQUEADO,
                motivoRechazo(movimiento.validarCamino(origen, destino, 3, tablero)));
    }

    @Test
    public void test04CasillaDestinoOcupadaPorOtraUnidadRetornaRechazo() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        MovimientoTerrestre movimiento = new MovimientoTerrestre();

        // Act
        tablero.ocuparCasilla(destino, new Combate());

        // Assert
        assertEquals(MotivoRechazoAccion.CAMINO_BLOQUEADO,
                motivoRechazo(movimiento.validarCamino(origen, destino, 3, tablero)));
    }

    @Test
    public void test05AunqueTengaRangoNoPuedeRodearLaMontana() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_MONTANA);

        MovimientoTerrestre movimiento = new MovimientoTerrestre();

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        assertEquals(MotivoRechazoAccion.CAMINO_BLOQUEADO,
                motivoRechazo(movimiento.validarCamino(origen, destino, 4, tablero)));
    }

    @Test
    public void test06DestinoQueNoEstaEnLaMismaFilaNiColumnaRetornaRechazo() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);

        MovimientoTerrestre movimiento = new MovimientoTerrestre();

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(1, 2);

        assertEquals(MotivoRechazoAccion.DESTINO_NO_ALCANZABLE,
                motivoRechazo(movimiento.validarCamino(origen, destino, 3, tablero)));
    }

    @Test
    public void test07NoPuedeTerminarSobreUnaMontana() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_MONTANA);

        MovimientoTerrestre movimiento = new MovimientoTerrestre();

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);

        assertEquals(MotivoRechazoAccion.CAMINO_BLOQUEADO,
                motivoRechazo(movimiento.validarCamino(origen, destino, 3, tablero)));
    }

    private MotivoRechazoAccion motivoRechazo(ResultadoAccion<?> resultado) {
        return resultado.fold(ignorado -> null, motivo -> motivo);
    }
}