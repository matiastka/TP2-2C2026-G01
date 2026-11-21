package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.excepciones.AccionNoDisponibleException;
import edu.fiuba.algo3.modelo.unidades.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.unidades.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.unidades.excepciones.TurnoAgotadoException;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;

public class MechTurnoTest {

    private static final String[] MAPA = {
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
    public void test01MechNoPuedeMoverseDosVecesEnElMismoTurno() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        mech.moverA(new Posicion(0, 2), tablero);

        assertThrows(MovimientoYaRealizadoException.class, () -> mech.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test02MovimientoInvalidoNoCambiaLaPosicionDelMech() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion origen = new Posicion(0, 0);
        Mech mech = new Combate();
        mech.colocarseEn(origen, tablero);

        assertThrows(DestinoNoAlcanzableException.class, () -> mech.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test03MovimientoInvalidoNoConsumeLaAccionDelMech() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion destino = new Posicion(0, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        intentarAccionInvalida(() -> mech.moverA(new Posicion(0, 4), tablero));
        mech.moverA(destino, tablero);

        assertEquals(destino, mech.obtenerPosicion());
    }

    @Test
    public void test04MechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(2, 2), tablero);
        new Luciernaga().colocarseEn(new Posicion(2, 3), tablero);
        mech.atacarA(new Posicion(2, 3), tablero);

        assertThrows(TurnoAgotadoException.class, () -> mech.atacarA(new Posicion(3, 2), tablero));
    }

    @Test
    public void test05SegundoAtaqueDelMechNoAfectaOtroVek() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Mech mech = new Combate();
        Unidad vek1 = new Luciernaga();
        Unidad vek2 = new Luciernaga();
        mech.colocarseEn(new Posicion(2, 2), tablero);
        vek1.colocarseEn(new Posicion(2, 3), tablero);
        vek2.colocarseEn(new Posicion(3, 2), tablero);
        mech.atacarA(new Posicion(2, 3), tablero);

        assertThrows(TurnoAgotadoException.class, () -> mech.atacarA(new Posicion(3, 2), tablero));
    }

    @Test
    public void test06AlReiniciarTurnoElMechPuedeVolverAMoverse() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion destino = new Posicion(1, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(new Posicion(0, 2), tablero);
        mech.moverA(new Posicion(0, 1), tablero);
        mech.atacarA(new Posicion(0, 2), tablero);
        mech.reiniciarTurno();
        mech.moverA(destino, tablero);

        assertEquals(destino, mech.obtenerPosicion());
    }

    @Test
    public void test07DeshacerMovimientoDeMechQueTodaviaNoAtaco() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion origen = new Posicion(0, 0);
        Mech mech = new Combate();
        mech.colocarseEn(origen, tablero);
        mech.moverA(new Posicion(0, 1), tablero);
        mech.deshacerMovimiento(tablero);

        assertEquals(origen, mech.obtenerPosicion());
    }

    @Test
    public void test08DeshacerDejaLaCasillaDestinoLibre() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion destino = new Posicion(0, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        mech.moverA(destino, tablero);
        mech.deshacerMovimiento(tablero);

        assertTrue(tablero.enCasilla(destino).estaLibre());
    }

    @Test
    public void test09NoSePuedeDeshacerDosMovimientosEnElMismoTurno() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        mech.moverA(new Posicion(0, 1), tablero);
        mech.deshacerMovimiento(tablero);
        mech.moverA(new Posicion(0, 2), tablero);

        assertThrows(AccionNoDisponibleException.class, () -> mech.deshacerMovimiento(tablero));
    }

    @Test
    public void test10NoSePuedeDeshacerElMovimientoDespuesDeAtacar() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(new Posicion(0, 2), tablero);
        mech.moverA(new Posicion(0, 1), tablero);
        mech.atacarA(new Posicion(0, 2), tablero);

        assertThrows(AccionNoDisponibleException.class, () -> mech.deshacerMovimiento(tablero));
    }

    @Test
    public void test11IntentarDeshacerDespuesDeAtacarNoMueveAlMech() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion destino = new Posicion(0, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(new Posicion(0, 2), tablero);
        mech.moverA(destino, tablero);
        mech.atacarA(new Posicion(0, 2), tablero);

        assertThrows(AccionNoDisponibleException.class, () -> mech.deshacerMovimiento(tablero));
    }

    @Test
    public void test12AtaqueInvalidoRetornaMotivoDeRechazo() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion objetivoNoAdyacente = new Posicion(0, 2);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(objetivoNoAdyacente, tablero);

        assertThrows(DestinoNoAlcanzableException.class, () -> mech.atacarA(objetivoNoAdyacente, tablero));
    }

    @Test
    public void test13AtaqueInvalidoNoConsumeLaAccionDelMech() {
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion destino = new Posicion(0, 1);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        new Luciernaga().colocarseEn(new Posicion(0, 2), tablero);
        intentarAccionInvalida(() -> mech.atacarA(new Posicion(0, 2), tablero));
        mech.moverA(destino, tablero);

        assertEquals(destino, mech.obtenerPosicion());
    }

    private void intentarAccionInvalida(Runnable accion) {
        try {
            accion.run();
        } catch (AccionInvalidaException esperada) {
        }
    }
}
