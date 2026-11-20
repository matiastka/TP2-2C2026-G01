
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CanonTest {

    private static final String[] MAPA = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SMSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01CanonDaniaALaPrimeraUnidadEnLineaRecta() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);

        Mech canon = new Canon();
        Mech blanco1 = new Combate();
        Mech blanco2 = new Combate();

        canon.colocarseEn(posCanon, tablero);
        blanco1.colocarseEn(posBlanco1, tablero);
        blanco2.colocarseEn(posBlanco2, tablero);

        canon.atacarA(posBlanco1, tablero);

        assertEquals(2, blanco1.obtenerVida());
    }

    @Test
    public void test02CanonNoDaniaALaSegundaUnidadEnLineaRecta() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);

        Mech canon = new Canon();
        Mech blanco1 = new Combate();
        Mech blanco2 = new Combate();

        canon.colocarseEn(posCanon, tablero);
        blanco1.colocarseEn(posBlanco1, tablero);
        blanco2.colocarseEn(posBlanco2, tablero);

        canon.atacarA(posBlanco1, tablero);

        assertEquals(3, blanco2.obtenerVida());
    }

    @Test
    public void test03CanonEmpujaALaPrimeraUnidadUnaCasillaEnLaDireccionDelDisparo() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);

        Mech canon = new Canon();
        Mech blanco1 = new Combate();
        Mech blanco2 = new Combate();

        canon.colocarseEn(posCanon, tablero);
        blanco1.colocarseEn(posBlanco1, tablero);
        blanco2.colocarseEn(posBlanco2, tablero);

        canon.atacarA(posBlanco1, tablero);

        assertSame(blanco1, tablero.enCasilla(new Posicion(0, 3)).obtenerOcupante());
    }

    @Test
    public void test04CanonImpactaContraObstaculoYNoALaUnidadDeAtras() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posCanon = new Posicion(2, 0);
        Posicion posBlanco = new Posicion(2, 2);

        Mech canon = new Canon();
        Mech blancoAtras = new Combate();
        
        canon.colocarseEn(posCanon, tablero);
        blancoAtras.colocarseEn(posBlanco, tablero);

        // Act
        Posicion posMontana = new Posicion(2, 1);
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
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posCanon = new Posicion(2, 0);
        Posicion posDetras = new Posicion(2, 2); // Montaña en (2,1)

        Mech canon = new Canon();
        Mech unidadDetras = new Combate();

        canon.colocarseEn(posCanon, tablero);
        unidadDetras.colocarseEn(posDetras, tablero);

        canon.atacarA(new Posicion(2, 5), tablero);

        assertSame(unidadDetras, tablero.enCasilla(posDetras).obtenerOcupante());
    }

    @Test
    public void test06CanonDisparaEnLineaLibreSinErrores() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posCanon = new Posicion(1, 0);

        Mech canon = new Canon();

        canon.colocarseEn(posCanon, tablero);

        assertEquals("exito", canon.atacarA(new Posicion(1, 5), tablero)
                .fold(ignorado -> "exito", motivo -> "rechazo"));
    }

    @Test
    public void test07CasoBordeImpactaAUnidadAdyacenteYLaEmpuja() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion posCanon = new Posicion(4, 0);
        Posicion posBlanco = new Posicion(4, 1);

        Mech canon = new Canon();
        Mech blanco = new Combate();

        canon.colocarseEn(posCanon, tablero);
        blanco.colocarseEn(posBlanco, tablero);

        canon.atacarA(posBlanco, tablero);

        assertSame(blanco, tablero.enCasilla(new Posicion(4, 2)).obtenerOcupante());
    }

    @Test
    public void test08ElCanonNoEmpujaAUnaUnidadContraOtraYLaDeAtrasSigueEnSuLugar(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionCanon = new Posicion(4,1);
        Posicion posicionBlanco1 = new Posicion(4,3);
        Posicion posicionBlanco2 = new Posicion(4,4);

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