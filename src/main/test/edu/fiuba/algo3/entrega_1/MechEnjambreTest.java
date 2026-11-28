
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MechEnjambreTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01MechNoPuedeMoverseDosVecesEnElMismoTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        mech.moverA(new Posicion(0, 2), tablero);

        // Act & Assert
        assertThrows(MovimientoYaRealizadoException.class, () -> mech.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test02MovimientoInvalidoNoCambiaLaPosicionDelMech() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(0, 0);
        Mech mech = new Combate();
        mech.colocarseEn(origen, tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> mech.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test03MovimientoInvalidoNoConsumeLaAccionDelMech() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion destino = new Posicion(0, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        intentarAccionInvalida(() -> mech.moverA(new Posicion(0, 4), tablero));

        // Act
        mech.moverA(destino, tablero);

        // Assert
        assertEquals(destino, mech.obtenerPosicion());
    }

    @Test
    public void test04MechNoPuedeAtacarDosVecesEnElMismoTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(2, 2), tablero);
        new Luciernaga().colocarseEn(new Posicion(2, 3), tablero);
        mech.atacarA(new Posicion(2, 3), tablero);

        // Act & Assert
        assertThrows(TurnoAgotadoException.class, () -> mech.atacarA(new Posicion(3, 2), tablero));
    }

    @Test
    public void test05SegundoAtaqueDelMechNoAfectaOtroVek() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = new Combate();
        Unidad vek1 = new Luciernaga();
        Unidad vek2 = new Luciernaga();
        mech.colocarseEn(new Posicion(2, 2), tablero);
        vek1.colocarseEn(new Posicion(2, 3), tablero);
        vek2.colocarseEn(new Posicion(3, 2), tablero);
        mech.atacarA(new Posicion(2, 3), tablero);

        // Act & Assert
        assertThrows(TurnoAgotadoException.class, () -> mech.atacarA(new Posicion(3, 2), tablero));
    }

    @Test
    public void test06AlReiniciarTurnoElMechPuedeVolverAMoverse() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion destino = new Posicion(1, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(new Posicion(0, 2), tablero);
        mech.moverA(new Posicion(0, 1), tablero);
        mech.atacarA(new Posicion(0, 2), tablero);
        mech.reiniciarTurno();

        // Act
        mech.moverA(destino, tablero);

        // Assert
        assertEquals(destino, mech.obtenerPosicion());
    }

    @Test
    public void test07DeshacerMovimientoDeMechQueTodaviaNoAtaco() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(0, 0);
        Mech mech = new Combate();
        mech.colocarseEn(origen, tablero);
        mech.moverA(new Posicion(0, 1), tablero);

        // Act
        mech.deshacerMovimiento(tablero);

        // Assert
        assertEquals(origen, mech.obtenerPosicion());
    }

    @Test
    public void test08DeshacerDejaLaCasillaDestinoLibre() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion destino = new Posicion(0, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        mech.moverA(destino, tablero);

        // Act
        mech.deshacerMovimiento(tablero);

        // Assert
        assertTrue(tablero.enCasilla(destino).estaLibre());
    }

    @Test
    public void test09NoSePuedeDeshacerDosMovimientosEnElMismoTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        mech.moverA(new Posicion(0, 1), tablero);
        mech.deshacerMovimiento(tablero);
        mech.moverA(new Posicion(0, 2), tablero);

        // Act & Assert
        assertThrows(AccionNoDisponibleException.class, () -> mech.deshacerMovimiento(tablero));
    }

    @Test
    public void test10NoSePuedeDeshacerElMovimientoDespuesDeAtacar() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(new Posicion(0, 2), tablero);
        mech.moverA(new Posicion(0, 1), tablero);
        mech.atacarA(new Posicion(0, 2), tablero);

        // Act & Assert
        assertThrows(AccionNoDisponibleException.class, () -> mech.deshacerMovimiento(tablero));
    }

    @Test
    public void test11IntentarDeshacerDespuesDeAtacarNoMueveAlMech() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion destino = new Posicion(0, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(new Posicion(0, 2), tablero);
        mech.moverA(destino, tablero);
        mech.atacarA(new Posicion(0, 2), tablero);

        // Act & Assert
        assertThrows(AccionNoDisponibleException.class, () -> mech.deshacerMovimiento(tablero));
    }

    @Test
    public void test12AtaqueInvalidoRetornaMotivoDeRechazo() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion objetivoNoAdyacente = new Posicion(0, 2);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(objetivoNoAdyacente, tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> mech.atacarA(objetivoNoAdyacente, tablero));
    }

    @Test
    public void test13AtaqueInvalidoNoConsumeLaAccionDelMech() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion destino = new Posicion(0, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(new Posicion(0, 2), tablero);
        intentarAccionInvalida(() -> mech.atacarA(new Posicion(0, 2), tablero));

        // Act
        mech.moverA(destino, tablero);

        // Assert
        assertEquals(destino, mech.obtenerPosicion());
    }

    private void intentarAccionInvalida(Runnable accion) {
        try {
            accion.run();
        } catch (AccionInvalidaException esperada) {
        }
    }
}