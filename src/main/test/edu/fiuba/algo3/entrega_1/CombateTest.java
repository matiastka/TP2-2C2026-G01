package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombateTest {
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

        tablero.ocuparCasilla(2,3,mechCombate); // En vez de pasar las coordenadas, creo que podriamos usar Posicion

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
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        tablero.ocuparCasilla(4, 1, mechCombate);

        // Act
        // Se intenta mover 4 casillas a la derecha, cuando su rango es 3
        mechCombate.moverA(new Posicion(4, 5), tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(4, 1).obtenerUnidad()); // Esto podria directamente comparar cuando salga la exepcion
    }

    @Test
    public void test06UnMechCombatePuedeMoverseExactamenteSuRangoMaximo() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        tablero.ocuparCasilla(4, 1, mechCombate);

        // Act
        mechCombate.moverA(new Posicion(4, 4), tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(4, 4).obtenerUnidad());
    }

    @Test
    public void test07UnMechNoPuedeAtravesarAOtroMech() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        Unidad otroMech = new Combate();

        tablero.ocuparCasilla(4, 1, mechCombate);
        tablero.ocuparCasilla(4, 2, otroMech);

        // Act
        // mech intentar ir al (4,3) cuando tiene un mech en (4,2). Por ende no se moverá
        mechCombate.moverA(new Posicion(4, 3), tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(4, 1).obtenerUnidad()); // Esto podria directamente comparar cuando salga la exepcion
    }

    @Test
    public void test08UnMechNoPuedeAtravesarUnEdificio() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Unidad mechCombate = new Combate();
        tablero.ocuparCasilla(2, 1, mechCombate);

        // Act
        mechCombate.moverA(new Posicion(2, 3), tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(2, 1).obtenerUnidad());
    }

    @Test
    public void test09ElPunioHace2DeDanioAUnaLucierganaAdyacente(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mechCombate = new Combate(); // Momentaneamente se utilizará el tipado de Combate y no Unidad. Cuando se hagan los tests de Artilleria y Cañon, hay que cambiarlo
        Unidad vekLuciernaga = new Luciernaga();
        Posicion posicionMech = new Posicion(4,2);
        Posicion posicionVek = new Posicion(4,3);
        int vidaVekEsperada = 1;

        tablero.ocuparCasilla(4, 2, mechCombate);
        tablero.ocuparCasilla(4, 3, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(vidaVekEsperada, vekLuciernaga.obtenerVida());
    }

    @Test
    public void test10ElPunioEmpujaAlObjetivoUnaCasillaEnDireccionAlGolpe(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mechCombate = new Combate(); // Momentaneamente se utilizará el tipado de Combate y no Unidad. Cuando se hagan los tests de Artilleria y Cañon, hay que cambiarlo
        Unidad vekLuciernaga = new Luciernaga();
        Posicion posicionMech = new Posicion(4,2); // Esto lo dejo para un posible refactor de Tablero
        Posicion posicionVek = new Posicion(4,3);

        tablero.ocuparCasilla(4, 2, mechCombate);
        tablero.ocuparCasilla(4, 3, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVek, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(4, 4).obtenerUnidad());
    }

    @Test
    public void test11TrasSerEmpujadoLaCasillaDondeEstabaQuedaLibre(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mechCombate = new Combate(); // Momentaneamente se utilizará el tipado de Combate y no Unidad. Cuando se hagan los tests de Artilleria y Cañon, hay que cambiarlo
        Unidad vekLuciernaga = new Luciernaga();
        Posicion posicionMech = new Posicion(4,2); // Esto lo dejo para un posible refactor de Tablero
        Posicion posicionVek = new Posicion(4,3);

        tablero.ocuparCasilla(4, 2, mechCombate);
        tablero.ocuparCasilla(4, 3, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVek, tablero);

        // Assert
        assertTrue(tablero.enCasilla(4, 3).estaLibre());
    }

    @Test
    public void test12SeEmpujaAUnVekAlLimiteDelMapaElMismoQuedaEnElBorde(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mechCombate = new Combate(); // Momentaneamente se utilizará el tipado de Combate y no Unidad. Cuando se hagan los tests de Artilleria y Cañon, hay que cambiarlo
        Unidad vekLuciernaga = new Luciernaga();
        Posicion posicionMech = new Posicion(6,4); // Esto lo dejo para un posible refactor de Tablero
        Posicion posicionVek = new Posicion(7,4);

        tablero.ocuparCasilla(6, 4, mechCombate);
        tablero.ocuparCasilla(7, 4, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVek, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(7, 4).obtenerUnidad());
    }

    @Test
    public void test13AtacarAUnVekNoAdyacenteNoLeQuitaVida(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mechCombate = new Combate(); // Momentaneamente se utilizará el tipado de Combate y no Unidad. Cuando se hagan los tests de Artilleria y Cañon, hay que cambiarlo
        Unidad vekLuciernaga = new Luciernaga();
        Posicion posicionMech = new Posicion(4,1); // Esto lo dejo para un posible refactor de Tablero
        Posicion posicionVek = new Posicion(4,3);
        int vidaEsperada = 3;


        tablero.ocuparCasilla(4, 1, mechCombate);
        tablero.ocuparCasilla(4, 3, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(vidaEsperada, vekLuciernaga.obtenerVida());
    }

    @Test
    public void test14AtacarAUnVekNoAdyacenteNoLoEmpuja(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mechCombate = new Combate(); // Momentaneamente se utilizará el tipado de Combate y no Unidad. Cuando se hagan los tests de Artilleria y Cañon, hay que cambiarlo
        Unidad vekLuciernaga = new Luciernaga();
        Posicion posicionMech = new Posicion(4,1); // Esto lo dejo para un posible refactor de Tablero
        Posicion posicionVek = new Posicion(4,3);


        tablero.ocuparCasilla(4, 1, mechCombate);
        tablero.ocuparCasilla(4, 3, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVek, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(4, 3).obtenerUnidad());
    }
}
