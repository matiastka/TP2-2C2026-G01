
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.unidades.excepciones.AccionNoDisponibleException;
import edu.fiuba.algo3.modelo.unidades.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.unidades.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.unidades.excepciones.TurnoAgotadoException;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MechTurnoTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01MechNoPuedeMoverseDosVecesEnElMismoTurno() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        mech.moverA(new Posicion(0, 2), tablero);

        // Act & Assert
        assertThrows(MovimientoYaRealizadoException.class, () -> mech.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test02MovimientoInvalidoNoCambiaLaPosicionDelMech() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(0, 0);
        Mech mech = new Combate();
        mech.colocarseEn(origen, tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> mech.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test03MovimientoInvalidoNoConsumeLaAccionDelMech() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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