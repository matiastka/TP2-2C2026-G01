
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanonTest {

    private static final String RUTA_MAPA_MONTANA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana.txt";

    @Test
    public void test01CanonDaniaALaPrimeraUnidadEnLineaRecta() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);
        Mech canon = new Canon();
        Mech blanco1 = new Combate();
        Mech blanco2 = new Combate();
        canon.colocarseEn(posCanon, tablero);
        blanco1.colocarseEn(posBlanco1, tablero);
        blanco2.colocarseEn(posBlanco2, tablero);

        // Act
        canon.atacarA(posBlanco1, tablero);

        // Assert
        assertEquals(2, blanco1.obtenerVida());
    }

    @Test
    public void test02CanonNoDaniaALaSegundaUnidadEnLineaRecta() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);
        Mech canon = new Canon();
        Mech blanco1 = new Combate();
        Mech blanco2 = new Combate();
        canon.colocarseEn(posCanon, tablero);
        blanco1.colocarseEn(posBlanco1, tablero);
        blanco2.colocarseEn(posBlanco2, tablero);

        // Act
        canon.atacarA(posBlanco1, tablero);

        // Assert
        assertEquals(3, blanco2.obtenerVida());
    }

    @Test
    public void test03CanonEmpujaALaPrimeraUnidadUnaCasillaEnLaDireccionDelDisparo() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);
        Mech canon = new Canon();
        Mech blanco1 = new Combate();
        Mech blanco2 = new Combate();
        canon.colocarseEn(posCanon, tablero);
        blanco1.colocarseEn(posBlanco1, tablero);
        blanco2.colocarseEn(posBlanco2, tablero);

        // Act
        canon.atacarA(posBlanco1, tablero);

        // Assert
        assertSame(blanco1, tablero.enCasilla(new Posicion(0, 3)).obtenerOcupante());
    }

    @Test
    public void test04CanonImpactaContraObstaculoYNoALaUnidadDeAtras() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posCanon = new Posicion(2, 0);
        Posicion posBlanco = new Posicion(2, 3);
        Posicion posMontana = new Posicion(2, 2);
        Mech canon = new Canon();
        Mech blancoAtras = new Combate();
        canon.colocarseEn(posCanon, tablero);
        blancoAtras.colocarseEn(posBlanco, tablero);

        // Act
        canon.atacarA(posMontana, tablero);
        canon.reiniciarTurno();
        canon.atacarA(posMontana, tablero);
        canon.reiniciarTurno();
        canon.atacarA(posMontana, tablero);

        // Assert
        assertTrue(blancoAtras.obtenerVida() > 0);
    }

    @Test
    public void test05CanonQueFrenaEnUnaMontanaVaciaNoPisaALaUnidadDeAtras() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posCanon = new Posicion(2, 0);
        Posicion posDetras = new Posicion(2, 3);
        Mech canon = new Canon();
        Mech unidadDetras = new Combate();
        canon.colocarseEn(posCanon, tablero);
        unidadDetras.colocarseEn(posDetras, tablero);

        // Act
        canon.atacarA(new Posicion(2, 5), tablero);

        // Assert
        assertSame(unidadDetras, tablero.enCasilla(posDetras).obtenerOcupante());
    }

    @Test
    public void test06CanonDisparaEnLineaLibreSinErrores() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posCanon = new Posicion(1, 0);
        Mech canon = new Canon();
        canon.colocarseEn(posCanon, tablero);

        // Act & Assert
        assertDoesNotThrow(() -> canon.atacarA(new Posicion(1, 5), tablero));
    }

    @Test
    public void test07CasoBordeImpactaAUnidadAdyacenteYLaEmpuja() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posCanon = new Posicion(4, 0);
        Posicion posBlanco = new Posicion(4, 1);
        Mech canon = new Canon();
        Mech blanco = new Combate();
        canon.colocarseEn(posCanon, tablero);
        blanco.colocarseEn(posBlanco, tablero);

        // Act
        canon.atacarA(posBlanco, tablero);

        // Assert
        assertSame(blanco, tablero.enCasilla(new Posicion(4, 2)).obtenerOcupante());
    }

    @Test
    public void test08ElCanonNoEmpujaAUnaUnidadContraOtraYLaDeAtrasSigueEnSuLugar() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionCanon = new Posicion(4, 1);
        Posicion posicionBlanco1 = new Posicion(4, 3);
        Posicion posicionBlanco2 = new Posicion(4, 4);
        Mech canon = new Canon();
        Unidad blanco1 = new Luciernaga();
        Unidad blanco2 = new Luciernaga();
        canon.colocarseEn(posicionCanon, tablero);
        blanco1.colocarseEn(posicionBlanco1, tablero);
        blanco2.colocarseEn(posicionBlanco2, tablero);

        // Act
        canon.atacarA(posicionBlanco1, tablero);

        // Assert
        assertSame(blanco2, tablero.enCasilla(posicionBlanco2).obtenerOcupante());
    }

    @Test
    public void test09CanonEmpiezaCon3DeVida() {
        // Arrange
        Canon canon = new Canon();
        int vidaEsperada = 3;

        // Act
        int vidaActual = canon.obtenerVida();

        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    @Test
    public void test10CanonTieneRango3() {
        // Arrange
        Canon canon = new Canon();
        int rangoEsperado = 3;

        // Act
        int rangoActual = canon.obtenerRango();

        // Assert
        assertEquals(rangoEsperado, rangoActual);
    }
}