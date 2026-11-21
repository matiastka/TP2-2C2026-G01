
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmergenciaVekTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01AlAnunciarLaEmergenciaElVekTodaviaNoEmerge() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Vek vek = new Luciernaga();

        // Act
        tablero.anunciarEmergencia(posicion, vek);

        // Assert
        assertTrue(tablero.enCasilla(posicion).estaLibre());
    }

    @Test
    public void test02CuandoLaCasillaEstaLibreElVekEmergeAlAvanzarElTurno() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Vek vek = new Luciernaga();
        tablero.anunciarEmergencia(posicion, vek);

        // Act
        tablero.resolverEmergencias();

        // Assert
        assertSame(vek, tablero.enCasilla(posicion).obtenerOcupante());
    }

    @Test
    public void test03UnaVezQueElVekEmergeLaEmergenciaNoVuelveAResolverse() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Vek vek = new Luciernaga();
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();

        // Act
        tablero.resolverEmergencias();

        // Assert
        assertEquals(3, vek.obtenerVida());
    }

    @Test
    public void test04LaEmergenciaBloqueadaPorMechHace1DeDanioYNoHaceAparecerAlVek() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Mech mech = new Combate();
        Vek vek = new Luciernaga();
        mech.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);

        // Act
        tablero.resolverEmergencias();

        // Assert
        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test05LaEmergenciaBloqueadaPorMechSeMantienePendienteParaElSiguienteTurno() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Mech mech = new Combate();
        Vek vek = new Luciernaga();
        mech.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();

        // Act
        tablero.resolverEmergencias();

        // Assert
        assertEquals(1, mech.obtenerVida());
    }

    @Test
    public void test06LaEmergenciaBloqueadaPorOtroVekHace1DeDanio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Unidad bloqueador = new Luciernaga();
        Vek vek = new Luciernaga();
        bloqueador.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);

        // Act
        tablero.resolverEmergencias();

        // Assert
        assertEquals(2, bloqueador.obtenerVida());
    }

    @Test
    public void test07LaEmergenciaBloqueadaPorOtroVekSigueActiva() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Unidad bloqueador = new Luciernaga();
        Vek vek = new Luciernaga();
        bloqueador.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();

        // Act
        tablero.resolverEmergencias();

        // Assert
        assertEquals(1, bloqueador.obtenerVida());
    }

    @Test
    public void test08TrasLiberarLaCasillaElVekEmergeEnElTurnoSiguiente() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Mech mech = new Combate();
        Vek vek = new Luciernaga();
        mech.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();
        tablero.desOcuparCasilla(posicion);

        // Act
        tablero.resolverEmergencias();

        // Assert
        assertSame(vek, tablero.enCasilla(posicion).obtenerOcupante());
    }
}