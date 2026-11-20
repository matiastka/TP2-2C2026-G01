package edu.fiuba.algo3.modelo.unidades.veks;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Este;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Sur;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.AccionInvalidaException;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.AtaqueYaPreparadoException;
import edu.fiuba.algo3.modelo.unidades.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.unidades.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import org.junit.jupiter.api.Test;

public class EstadoTurnoVekTest {

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
    public void test01VekNoPuedeMoverseDosVecesEnElMismoTurno() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        vek.moverA(new Posicion(0, 2), tablero);

        assertThrows(MovimientoYaRealizadoException.class, () -> vek.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test02MovimientoRechazadoNoCambiaLaPosicionDelVek() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion primerDestino = new Posicion(0, 2);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        vek.moverA(primerDestino, tablero);

        assertThrows(MovimientoYaRealizadoException.class, () -> vek.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test03VekPreparaAtaqueSinHacerDanioInmediato() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion posVek = new Posicion(2, 2);
        Posicion posMech = new Posicion(2, 3);
        Vek vek = new Luciernaga();
        Mech mech = new Combate();
        vek.colocarseEn(posVek, tablero);
        mech.colocarseEn(posMech, tablero);
        vek.prepararAtaque(new Este());

        assertEquals(3, mech.obtenerVida());
    }

    @Test
    public void test04AtaquePreparadoSeResuelveYHaceDanio() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion posVek = new Posicion(2, 2);
        Posicion posMech = new Posicion(2, 3);
        Vek vek = new Luciernaga();
        Mech mech = new Combate();
        vek.colocarseEn(posVek, tablero);
        mech.colocarseEn(posMech, tablero);
        vek.prepararAtaque(new Este());
        vek.resolverAtaque(tablero);

        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test05VekNoPuedeMoverseSiYaPreparoUnAtaque() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        vek.prepararAtaque(new Sur());

        assertThrows(AtaqueYaPreparadoException.class, () -> vek.moverA(new Posicion(1, 2), tablero));
    }

    @Test
    public void test06MovimientoRechazadoConAtaquePreparadoNoMueveAlVek() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion origen = new Posicion(1, 1);
        Vek vek = new Luciernaga();
        vek.colocarseEn(origen, tablero);
        vek.prepararAtaque(new Sur());

        assertThrows(AtaqueYaPreparadoException.class, () -> vek.moverA(new Posicion(1, 2), tablero));
    }

    @Test
    public void test07VekVuelveAEstarDisponibleLuegoDeResolverSuAtaque() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion nuevoDestino = new Posicion(1, 3);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        vek.prepararAtaque(new Este());
        vek.resolverAtaque(tablero);
        vek.moverA(nuevoDestino, tablero);

        assertEquals(nuevoDestino, vek.obtenerPosicion());
    }

    @Test
    public void test08VekEmpujadoAtacaDesdeSuNuevaPosicion() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion posVekInicial = new Posicion(1, 1);
        Posicion posVekEmpujado = new Posicion(2, 1);
        Posicion posMech2 = new Posicion(2, 2);
        Vek vek = new Luciernaga();
        Mech mech1 = new Combate();
        Mech mech2 = new Combate();
        vek.colocarseEn(posVekInicial, tablero);
        mech1.colocarseEn(new Posicion(1, 2), tablero);
        mech2.colocarseEn(posMech2, tablero);
        vek.prepararAtaque(new Este());
        vek.serEmpujadoA(posVekEmpujado, tablero);
        vek.resolverAtaque(tablero);

        assertEquals(2, mech2.obtenerVida());
    }

    @Test
    public void test09ResolverAtaqueSinPreparacionNoImpideMoverse() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion destino = new Posicion(0, 2);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        vek.resolverAtaque(tablero);
        vek.moverA(destino, tablero);

        assertEquals(destino, vek.obtenerPosicion());
    }

    @Test
    public void test10MovimientoFueraDelTableroDeVekVoladorRetornaRechazo() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Vek vek = new Avispon();
        vek.colocarseEn(new Posicion(0, 0), tablero);

        assertThrows(DestinoFueraDelTableroException.class, () -> vek.moverA(new Posicion(-1, 0), tablero));
    }

    @Test
    public void test11MovimientoFueraDelTableroNoConsumeTurnoDeVekVolador() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion destinoValido = new Posicion(0, 1);
        Vek vek = new Avispon();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        intentarAccionInvalida(() -> vek.moverA(new Posicion(-1, 0), tablero));
        vek.moverA(destinoValido, tablero);

        assertEquals(destinoValido, vek.obtenerPosicion());
    }

    private void intentarAccionInvalida(Runnable accion) {
        try {
            accion.run();
        } catch (AccionInvalidaException esperada) {
        }
    }
}
