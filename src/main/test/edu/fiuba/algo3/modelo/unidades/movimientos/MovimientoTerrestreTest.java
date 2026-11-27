
package edu.fiuba.algo3.modelo.unidades.movimientos;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.excepciones.CaminoBloqueadoException;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;

public class MovimientoTerrestreTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_MONTANA_FILA0 = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana_fila0.txt";

    @Test
    public void test01DestinoDentroDeRangoYLibreEsValido() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 3);

        // Act & Assert
        assertDoesNotThrow(() -> movimiento.validarCamino(origen, destino, 3, tablero));
    }

    @Test
    public void test02DestinoFueraDeRangoRetornaRechazo() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 4);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> movimiento.validarCamino(origen, destino, 3, tablero));
    }

    @Test
    public void test03CaminoBloqueadoPorMontanaRetornaRechazo() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA_FILA0);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> movimiento.validarCamino(origen, destino, 3, tablero));
    }

    @Test
    public void test04CasillaDestinoOcupadaPorOtraUnidadRetornaRechazo() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        tablero.ocuparCasilla(destino, new Combate());

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> movimiento.validarCamino(origen, destino, 3, tablero));
    }

    @Test
    public void test05AunqueTengaRangoNoPuedeRodearLaMontana() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA_FILA0);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> movimiento.validarCamino(origen, destino, 4, tablero));
    }

    @Test
    public void test06DestinoQueNoEstaEnLaMismaFilaNiColumnaRetornaRechazo() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(1, 2);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> movimiento.validarCamino(origen, destino, 3, tablero));
    }

    @Test
    public void test07NoPuedeTerminarSobreUnaMontana() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA_FILA0);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoTerrestre movimiento = new MovimientoTerrestre();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> movimiento.validarCamino(origen, destino, 3, tablero));
    }
}