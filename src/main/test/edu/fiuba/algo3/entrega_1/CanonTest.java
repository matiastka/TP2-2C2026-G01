
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
    public void test01CanonImpactaPrimeraUnidadYNoALaSegundaEnLineaRecta() {
        //pendiente a cambios
        Tablero tablero = new Tablero(MAPA);
        Mech canon = new Canon();
        Mech blanco1 = new Combate();
        Mech blanco2 = new Combate();

        Posicion posCanon = new Posicion(0, 0);
        Posicion posBlanco1 = new Posicion(0, 2);
        Posicion posBlanco2 = new Posicion(0, 4);

        tablero.ocuparCasilla(posCanon, canon);
        tablero.ocuparCasilla(posBlanco1, blanco1);
        tablero.ocuparCasilla(posBlanco2, blanco2);

    canon.atacarA(posBlanco1, tablero);


    Posicion nuevaPosBlanco1 = tablero.buscarPosicionDe(blanco1);
    assertEquals(0, nuevaPosBlanco1.getFila());
    assertEquals(3, nuevaPosBlanco1.getColumna());

    canon.reiniciarTurno(); 
    canon.atacarA(nuevaPosBlanco1, tablero);

    Posicion posFinal = tablero.buscarPosicionDe(blanco1);


    canon.reiniciarTurno(); 
    canon.atacarA(posFinal, tablero);


    assertFalse(blanco1.obtenerVida() > 0);
    assertTrue(blanco2.obtenerVida() > 0);
        }

    @Test
    public void test02CanonImpactaContraObstaculoYNoALaUnidadDeAtras() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Mech canon = new Canon();
        Mech blancoAtras = new Combate();

        Posicion posCanon = new Posicion(2, 0);
        Posicion posBlanco = new Posicion(2, 2);
        
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
    public void test03CanonDisparaEnLineaLibreSinErrores() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Mech canon = new Canon();
        Posicion posCanon = new Posicion(1, 0);
        
        tablero.ocuparCasilla(posCanon, canon);
        Posicion posVacia = new Posicion(1, 5);

        // Act & Assert
        assertDoesNotThrow(() -> canon.atacarA(posVacia, tablero));
    }

    @Test
    public void test04CasoBordeImpactaAUnidadAdyacenteYLaEmpuja() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Mech canon = new Canon();
        Mech blanco = new Combate();

        Posicion posCanon = new Posicion(4, 0);
        Posicion posBlanco = new Posicion(4, 1);
        
        tablero.ocuparCasilla(posCanon, canon);
        tablero.ocuparCasilla(posBlanco, blanco);

        // Act
        canon.atacarA(posBlanco, tablero);

        // Assert
        Posicion nuevaPosBlanco = tablero.buscarPosicionDe(blanco);
        assertEquals(4, nuevaPosBlanco.getFila());
        assertEquals(2, nuevaPosBlanco.getColumna());
    }
}