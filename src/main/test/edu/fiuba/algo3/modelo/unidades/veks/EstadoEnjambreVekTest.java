
package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.AtaqueYaPreparadoException;
import edu.fiuba.algo3.modelo.excepciones.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstadoEnjambreVekTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01VekNoPuedeMoverseDosVecesEnElMismoTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        vek.moverA(new Posicion(0, 2), tablero);

        // Act & Assert
        assertThrows(MovimientoYaRealizadoException.class, () -> vek.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test02MovimientoRechazadoNoCambiaLaPosicionDelVek() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion primerDestino = new Posicion(0, 2);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        vek.moverA(primerDestino, tablero);

        // Act & Assert
        assertThrows(MovimientoYaRealizadoException.class, () -> vek.moverA(new Posicion(0, 4), tablero));
    }

    @Test
    public void test03VekPreparaAtaqueSinHacerDanioInmediato() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posVek = new Posicion(2, 2);
        Posicion posMech = new Posicion(2, 3);
        Vek vek = new Luciernaga();
        Mech mech = new Combate();
        vek.colocarseEn(posVek, tablero);
        mech.colocarseEn(posMech, tablero);

        // Act
        vek.prepararAtaque(posMech);

        // Assert
        assertEquals(3, mech.obtenerVida());
    }

    @Test
    public void test04AtaquePreparadoSeResuelveYHaceDanio() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posVek = new Posicion(2, 2);
        Posicion posMech = new Posicion(2, 3);
        Vek vek = new Luciernaga();
        Mech mech = new Combate();
        vek.colocarseEn(posVek, tablero);
        mech.colocarseEn(posMech, tablero);

        // Act
        vek.prepararAtaque(posMech);
        vek.resolverAtaque(tablero);

        // Assert
        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test05VekNoPuedeMoverseSiYaPreparoUnAtaque() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        vek.prepararAtaque(new Posicion(2, 1));

        // Act & Assert
        assertThrows(AtaqueYaPreparadoException.class, () -> vek.moverA(new Posicion(1, 2), tablero));
    }

    @Test
    public void test06MovimientoRechazadoConAtaquePreparadoNoMueveAlVek() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(1, 1);
        Vek vek = new Luciernaga();
        vek.colocarseEn(origen, tablero);
        vek.prepararAtaque(new Posicion(2, 1));

        // Act & Assert
        assertThrows(AtaqueYaPreparadoException.class, () -> vek.moverA(new Posicion(1, 2), tablero));
    }

    @Test
    public void test07VekVuelveAEstarDisponibleLuegoDeResolverSuAtaque() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion nuevoDestino = new Posicion(1, 3);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        vek.prepararAtaque(new Posicion(1, 2));
        vek.resolverAtaque(tablero);

        // Act
        vek.moverA(nuevoDestino, tablero);

        // Assert
        assertEquals(nuevoDestino, vek.obtenerPosicion());
    }

    @Test
    public void test08VekEmpujadoAtacaDesdeSuNuevaPosicion() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posVekInicial = new Posicion(1, 1);
        Posicion posVekEmpujado = new Posicion(2, 1);
        Posicion posMech2 = new Posicion(2, 2);
        Vek vek = new Luciernaga();
        Mech mech1 = new Combate();
        Mech mech2 = new Combate();
        vek.colocarseEn(posVekInicial, tablero);
        mech1.colocarseEn(new Posicion(1, 2), tablero);
        mech2.colocarseEn(posMech2, tablero);

        // Act
        vek.prepararAtaque(new Posicion(1, 2));
        vek.serEmpujadoA(posVekEmpujado, tablero);
        vek.resolverAtaque(tablero);

        // Assert
        assertEquals(2, mech2.obtenerVida());
    }

    @Test
    public void test09ResolverAtaqueSinPreparacionNoImpideMoverse() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion destino = new Posicion(0, 2);
        Vek vek = new Luciernaga();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        vek.resolverAtaque(tablero);

        // Act
        vek.moverA(destino, tablero);

        // Assert
        assertEquals(destino, vek.obtenerPosicion());
    }

    @Test
    public void test10MovimientoFueraDelTableroDeVekVoladorRetornaRechazo() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Vek vek = new Avispon();
        vek.colocarseEn(new Posicion(0, 0), tablero);

        // Act & Assert
        assertThrows(DestinoFueraDelTableroException.class, () -> vek.moverA(new Posicion(-1, 0), tablero));
    }

    @Test
    public void test11MovimientoFueraDelTableroNoConsumeTurnoDeVekVolador() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion destinoValido = new Posicion(0, 1);
        Vek vek = new Avispon();
        vek.colocarseEn(new Posicion(0, 0), tablero);
        intentarAccionInvalida(() -> vek.moverA(new Posicion(-1, 0), tablero));

        // Act
        vek.moverA(destinoValido, tablero);

        // Assert
        assertEquals(destinoValido, vek.obtenerPosicion());
    }

    private void intentarAccionInvalida(Runnable accion) {
        try {
            accion.run();
        } catch (AccionInvalidaException esperada) {
        }
    }
}