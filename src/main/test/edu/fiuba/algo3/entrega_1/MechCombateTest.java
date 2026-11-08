package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MechCombateTest {
    private static final String[] MAPA = {
            "AMESSMSM",
            "AMSSECSA",
            "ASESSCSA",
            "ASSESCSA",
            "ASSSSCSA",
            "ASSSECSA",
            "ASSSSCSA",
            "AMMMSCSA"
    };

    @Test
    public void test01UnMechCombateSeMueve1ALaDerecha(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Unidad mechCombate = new Combate();

        tablero.ocuparCasilla(2,3,mechCombate);

        // Act
        mechCombate.moverA(new Posicion(2,4), tablero);

        // Assert
        assertTrue(tablero.enCasilla(2,4).obtenerUnidad().esDelMismoTipoQue(new Combate()));

    }

    @Test
    public void test02UnMechCombateSeMueve1Arriba(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Unidad mechCombate = new Combate();

        tablero.ocuparCasilla(2,3,mechCombate);

        // Act
        mechCombate.moverA(new Posicion(1,3),tablero);

        // Assert
        assertTrue(tablero.enCasilla(1,3).obtenerUnidad().esDelMismoTipoQue(new Combate()));

    }
    @Test
    public void test03AlMoverseElMechLaCasillaDeOrigenQuedaLibre() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        tablero.ocuparCasilla(2, 3, mechCombate);

        // Act
        mechCombate.moverA(new Posicion(2, 4), tablero);

        // Assert
        assertTrue(tablero.enCasilla(2, 3).estaLibre());
    }

    @Test
    public void test04AlMoverseElMechQuedaEnLaCasillaDestino() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        tablero.ocuparCasilla(2, 3, mechCombate);

        // Act
        mechCombate.moverA(new Posicion(2, 4), tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(2, 4).obtenerUnidad());
    }

    @Test
    public void test05UnMechNoSeMueveFueraDeSuRango() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        tablero.ocuparCasilla(4, 1, mechCombate);

        // Se intenta mover 4 casillas a la derecha, cuando su rango es 3
        mechCombate.moverA(new Posicion(4, 5), tablero);

        assertSame(mechCombate, tablero.enCasilla(4, 1).obtenerUnidad()); // Esto podria directamente comparar cuando salga la exepcion
    }

    @Test
    public void test06UnMechCombatePuedeMoverseExactamenteSuRangoMaximo() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        tablero.ocuparCasilla(4, 1, mechCombate);

        mechCombate.moverA(new Posicion(4, 4), tablero);

        assertSame(mechCombate, tablero.enCasilla(4, 4).obtenerUnidad());
    }

    @Test
    public void test07UnMechNoPuedeAtravesarAOtroMech() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        Unidad otroMech = new Combate();

        tablero.ocuparCasilla(4, 1, mechCombate);
        tablero.ocuparCasilla(4, 2, otroMech);

        // mech intentar ir al (4,3) cuando tiene un mech en (4,2). Por ende no se moverá
        mechCombate.moverA(new Posicion(4, 3), tablero);

        assertSame(mechCombate, tablero.enCasilla(4, 1).obtenerUnidad()); // Esto podria directamente comparar cuando salga la exepcion
    }

    @Test
    public void test08UnMechNoPuedeAtravesarUnEdificio() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        tablero.ocuparCasilla(2, 1, mechCombate);

        mechCombate.moverA(new Posicion(2, 3), tablero);

        assertSame(mechCombate, tablero.enCasilla(2, 1).obtenerUnidad());
    }
}
