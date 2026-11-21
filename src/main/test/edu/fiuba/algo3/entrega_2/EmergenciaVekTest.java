package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmergenciaVekTest {
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

    @Test
    public void test01AlAnunciarLaEmergenciaElVekTodaviaNoEmerge() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Posicion posicion = new Posicion(3, 3);
        Vek vek = new Luciernaga();

        tablero.anunciarEmergencia(posicion, vek);

        assertTrue(tablero.enCasilla(posicion).estaLibre());
    }

    @Test
    public void test02CuandoLaCasillaEstaLibreElVekEmergeAlAvanzarElTurno() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Posicion posicion = new Posicion(3, 3);
        Vek vek = new Luciernaga();

        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();

        assertSame(vek, tablero.enCasilla(posicion).obtenerOcupante());
    }

    @Test
    public void test03UnaVezQueElVekEmergeLaEmergenciaNoVuelveAResolverse() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Posicion posicion = new Posicion(3, 3);
        Vek vek = new Luciernaga();

        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();
        tablero.resolverEmergencias();

        assertEquals(3, vek.obtenerVida());
    }

    @Test
    public void test04LaEmergenciaBloqueadaPorMechHace1DeDanioYNoHaceAparecerAlVek() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Posicion posicion = new Posicion(3, 3);
        Mech mech = new Combate();
        Vek vek = new Luciernaga();

        mech.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();

        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test05LaEmergenciaBloqueadaPorMechSeMantienePendienteParaElSiguienteTurno() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Posicion posicion = new Posicion(3, 3);
        Mech mech = new Combate();
        Vek vek = new Luciernaga();

        mech.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();
        tablero.resolverEmergencias();

        assertEquals(1, mech.obtenerVida());
    }

    @Test
    public void test06LaEmergenciaBloqueadaPorOtroVekHace1DeDanio() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Posicion posicion = new Posicion(3, 3);
        Unidad bloqueador = new Luciernaga();
        Vek vek = new Luciernaga();

        bloqueador.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();

        assertEquals(2, bloqueador.obtenerVida());
    }

    @Test
    public void test07LaEmergenciaBloqueadaPorOtroVekSigueActiva() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Posicion posicion = new Posicion(3, 3);
        Unidad bloqueador = new Luciernaga();
        Vek vek = new Luciernaga();

        bloqueador.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();
        tablero.resolverEmergencias();

        assertEquals(1, bloqueador.obtenerVida());
    }

    @Test
    public void test08TrasLiberarLaCasillaElVekEmergeEnElTurnoSiguiente() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Posicion posicion = new Posicion(3, 3);
        Mech mech = new Combate();
        Vek vek = new Luciernaga();

        mech.colocarseEn(posicion, tablero);
        tablero.anunciarEmergencia(posicion, vek);
        tablero.resolverEmergencias();
        tablero.desOcuparCasilla(posicion);
        tablero.resolverEmergencias();

        assertSame(vek, tablero.enCasilla(posicion).obtenerOcupante());
    }
}
