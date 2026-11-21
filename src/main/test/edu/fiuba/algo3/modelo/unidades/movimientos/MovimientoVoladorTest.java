

package edu.fiuba.algo3.modelo.unidades.movimientos;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.Agua;
import edu.fiuba.algo3.modelo.unidades.excepciones.CaminoBloqueadoException;
import edu.fiuba.algo3.modelo.unidades.excepciones.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.unidades.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.unidades.excepciones.DestinoOcupadoException;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovimientoVoladorTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_MONTANA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana.txt";

    @Test
    public void test01ValidarCaminoPermiteSobrevolarUnaMontanaHastaUnDestinoLibre() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoVolador movimiento = new MovimientoVolador();
        Posicion origen = new Posicion(2, 0);
        Posicion destino = new Posicion(2, 4);

        // Act & Assert
        assertDoesNotThrow(() -> movimiento.validarCamino(origen, destino, 4, tablero));
    }

    @Test
    public void test02ValidarCaminoPermiteSobrevolarOtraUnidadHastaUnDestinoLibre() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        new Combate().colocarseEn(new Posicion(2, 1), tablero);
        MovimientoVolador movimiento = new MovimientoVolador();
        Posicion origen = new Posicion(2, 0);
        Posicion destino = new Posicion(2, 3);

        // Act & Assert
        assertDoesNotThrow(() -> movimiento.validarCamino(origen, destino, 4, tablero));
    }

    @Test
    public void test03ValidarCaminoLanzaExcepcionSiElDestinoEstaOcupadoPorUnaUnidad() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion destino = new Posicion(2, 2);
        new Combate().colocarseEn(destino, tablero);
        MovimientoVolador movimiento = new MovimientoVolador();
        Posicion origen = new Posicion(2, 0);

        // Act & Assert
        assertThrows(DestinoOcupadoException.class, () -> movimiento.validarCamino(origen, destino, 4, tablero));
    }

    @Test
    public void test04ValidarCaminoLanzaExcepcionSiElDestinoEsUnaMontana() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoVolador movimiento = new MovimientoVolador();
        Posicion origen = new Posicion(2, 0);
        Posicion destino = new Posicion(2, 2);

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> movimiento.validarCamino(origen, destino, 4, tablero));
    }

    @Test
    public void test05ValidarCaminoLanzaExcepcionSiElDestinoEstaFueraDeRango() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoVolador movimiento = new MovimientoVolador();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 5);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> movimiento.validarCamino(origen, destino, 4, tablero));
    }

    @Test
    public void test06ValidarCaminoLanzaExcepcionSiElDestinoEstaFueraDelTablero() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoVolador movimiento = new MovimientoVolador();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(-1, 0);

        // Act & Assert
        assertThrows(DestinoFueraDelTableroException.class, () -> movimiento.validarCamino(origen, destino, 4, tablero));
    }

    @Test
    public void test07ValidarCaminoLanzaExcepcionSiElDestinoNoEstaEnLineaRecta() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        MovimientoVolador movimiento = new MovimientoVolador();
        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(2, 2);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> movimiento.validarCamino(origen, destino, 4, tablero));
    }

    @Test
    public void test08CaerEnAguaNoHundeALaUnidad() {
        // Arrange
        Vek luciernaga = new Luciernaga();
        MovimientoVolador movimiento = new MovimientoVolador();

        // Act
        movimiento.caerEnAgua(luciernaga);

        // Assert
        assertEquals(3, luciernaga.obtenerVida());
    }

    @Test
    public void test09CaerEnAcidoNoContaminaALaUnidad() {
        // Arrange
        Mech mech = new Combate();
        MovimientoVolador movimiento = new MovimientoVolador();

        // Act
        movimiento.caerEnAcido(mech);
        mech.recibirDanio(1);

        // Assert
        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test10AgregarSiPuedeTerminarEnAgregaUnaCasillaDeAguaALosDestinos() {
        // Arrange
        Casilla casillaAgua = new Casilla(new Agua(), new Posicion(1, 1));
        List<Posicion> destinos = new ArrayList<>();
        MovimientoVolador movimiento = new MovimientoVolador();

        // Act
        movimiento.agregarSiPuedeTerminarEn(casillaAgua, destinos);

        // Assert
        assertTrue(destinos.contains(new Posicion(1, 1)));
    }
}