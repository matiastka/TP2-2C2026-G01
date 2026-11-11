
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
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
        Tablero tablero = new Tablero(MAPA);

        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);

        Mech canon = new Canon(posCanon);
        Mech blanco1 = new Combate(posBlanco1);
        Mech blanco2 = new Combate(posBlanco2);

        tablero.ocuparCasilla(posCanon, canon);
        tablero.ocuparCasilla(posBlanco1, blanco1);
        tablero.ocuparCasilla(posBlanco2, blanco2);

        canon.atacarA(posBlanco1, tablero);

        assertEquals(2, blanco1.obtenerVida()); // 3 - 1 = 2
    }

    @Test
    public void test02CanonNoDaniaALaSegundaUnidadEnLineaRecta() {
        Tablero tablero = new Tablero(MAPA);

        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);

        Mech canon = new Canon(posCanon);
        Mech blanco1 = new Combate(posBlanco1);
        Mech blanco2 = new Combate(posBlanco2);

        tablero.ocuparCasilla(posCanon, canon);
        tablero.ocuparCasilla(posBlanco1, blanco1);
        tablero.ocuparCasilla(posBlanco2, blanco2);

        canon.atacarA(posBlanco1, tablero);

        assertEquals(3, blanco2.obtenerVida());
    }

    @Test
    public void test03CanonEmpujaALaPrimeraUnidadUnaCasillaEnLaDireccionDelDisparo() {
        Tablero tablero = new Tablero(MAPA);

        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);

        Mech canon = new Canon(posCanon);
        Mech blanco1 = new Combate(posBlanco1);
        Mech blanco2 = new Combate(posBlanco2);

        tablero.ocuparCasilla(posCanon, canon);
        tablero.ocuparCasilla(posBlanco1, blanco1);
        tablero.ocuparCasilla(posBlanco2, blanco2);

        canon.atacarA(posBlanco1, tablero);

        assertSame(blanco1, tablero.enCasilla(new Posicion(0, 3)).obtenerUnidad());
    }

    @Test
    public void test04CanonImpactaContraObstaculoYNoALaUnidadDeAtras() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posCanon = new Posicion(2, 0);
        Posicion posBlanco = new Posicion(2, 2);

        Mech canon = new Canon(posCanon);
        Mech blancoAtras = new Combate(posBlanco);
        
        tablero.ocuparCasilla(posCanon, canon);
        tablero.ocuparCasilla(posBlanco, blancoAtras);

        // Act
        Posicion posMontana = new Posicion(2, 1);
        canon.atacarA(posMontana, tablero);
        canon.atacarA(posMontana, tablero);
        canon.atacarA(posMontana, tablero);

        // Assert
        assertTrue(blancoAtras.obtenerVida() > 0);
    }

    @Test
    public void test05CanonQueFrenaEnUnaMontanaVaciaNoPisaALaUnidadDeAtras() {
        Tablero tablero = new Tablero(MAPA);

        Posicion posCanon = new Posicion(2, 0);
        Posicion posDetras = new Posicion(2, 2); // Montaña en (2,1)

        Mech canon = new Canon(posCanon);
        Mech unidadDetras = new Combate(posDetras);

        tablero.ocuparCasilla(posCanon, canon);
        tablero.ocuparCasilla(posDetras, unidadDetras);

        canon.atacarA(new Posicion(2, 5), tablero);

        assertSame(unidadDetras, tablero.enCasilla(posDetras).obtenerUnidad());
    }

    @Test
    public void test06CanonDisparaEnLineaLibreSinErrores() {
        Tablero tablero = new Tablero(MAPA);
        Posicion posCanon = new Posicion(1, 0);
        Mech canon = new Canon(posCanon);
        tablero.ocuparCasilla(posCanon, canon);

        assertDoesNotThrow(() -> canon.atacarA(new Posicion(1, 5), tablero));
    }

    @Test
    public void test07CasoBordeImpactaAUnidadAdyacenteYLaEmpuja() {
        Tablero tablero = new Tablero(MAPA);
        Posicion posCanon = new Posicion(4, 0);
        Posicion posBlanco = new Posicion(4, 1);
        Mech canon = new Canon(posCanon);
        Mech blanco = new Combate(posBlanco);
        tablero.ocuparCasilla(posCanon, canon);
        tablero.ocuparCasilla(posBlanco, blanco);

        canon.atacarA(posBlanco, tablero);

        assertSame(blanco, tablero.enCasilla(new Posicion(4, 2)).obtenerUnidad());
    }
}