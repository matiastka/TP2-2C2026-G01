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

        Posicion posicionMechOrigen = new Posicion(2,3);
        Posicion posicionMechDestino = new Posicion(2,4);

        Unidad mechCombate = new Combate(posicionMechOrigen);


        tablero.ocuparCasilla(posicionMechOrigen,mechCombate);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerUnidad());

    }

    @Test
    public void test02UnMechCombateSeMueve1Arriba(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,3);
        Posicion posicionMechDestino = new Posicion(1,3);

        Unidad mechCombate = new Combate(posicionMechOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);

        // Act
        mechCombate.moverA(posicionMechDestino,tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerUnidad());

    }
    @Test
    public void test03AlMoverseElMechLaCasillaDeOrigenQuedaLibre() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,3);
        Posicion posicionMechDestino = new Posicion(2,4);

        Unidad mechCombate = new Combate(posicionMechOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertTrue(tablero.enCasilla(posicionMechOrigen).estaLibre());
    }

    @Test
    public void test04AlMoverseElMechQuedaEnLaCasillaDestino() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,3);
        Posicion posicionMechDestino = new Posicion(2,4);

        Unidad mechCombate = new Combate(posicionMechOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerUnidad());
    }

    @Test
    public void test05UnMechNoSeMueveFueraDeSuRango() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechDestino = new Posicion(4,5);
        Posicion posicionMechOrigen = new Posicion(4,1);

        Unidad mechCombate = new Combate(posicionMechOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);

        // Act
        // Se intenta mover 4 casillas a la derecha, cuando su rango es 3
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechOrigen).obtenerUnidad()); // Esto podria directamente comparar cuando salga la exepcion
    }

    @Test
    public void test06UnMechCombatePuedeMoverseExactamenteSuRangoMaximo() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,1);
        Posicion posicionRangoDestino = new Posicion(4,4);

        Unidad mechCombate = new Combate(posicionMechOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);

        // Act
        mechCombate.moverA(posicionRangoDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionRangoDestino).obtenerUnidad());
    }

    @Test
    public void test07UnMechNoPuedeAtravesarAOtroMech() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMech1Origen = new Posicion(4,1);
        Posicion posicionMech2Origen = new Posicion(4,2);
        Posicion posicionDestinoMech1 = new Posicion(4,3);

        Unidad mechCombate = new Combate(posicionMech1Origen);
        Unidad otroMech = new Combate(posicionMech2Origen);

        tablero.ocuparCasilla(posicionMech1Origen, mechCombate);
        tablero.ocuparCasilla(posicionMech2Origen, otroMech);

        // Act
        // mech intentar ir al (4,3) cuando tiene un mech en (4,2). Por ende no se moverá
        mechCombate.moverA(posicionDestinoMech1, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMech1Origen).obtenerUnidad()); // Esto podria directamente comparar cuando salga la exepcion
    }

    @Test
    public void test08UnMechNoPuedeAtravesarUnEdificioYSeQuedaEnElMismoLugar() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,1);
        Posicion posicionEdificio = new Posicion(2,3);

        Unidad mechCombate = new Combate(posicionMechOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);

        // Act
        mechCombate.moverA(posicionEdificio, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechOrigen).obtenerUnidad());
    }

    @Test
    public void test09ElPunioHace2DeDanioAUnaLucierganaAdyacente(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,2);
        Posicion posicionVekOrigen = new Posicion(4,3);

        Mech mechCombate = new Combate(posicionMechOrigen); // Duda mia (Mati) es correcto que usemos Mech en vez de Unidad. ¿Esto no viola el principio de "programe contra interfaces"?
        Unidad vekLuciernaga = new Luciernaga(posicionVekOrigen);

        int vidaVekEsperada = 1;

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);
        tablero.ocuparCasilla(posicionVekOrigen, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertEquals(vidaVekEsperada, vekLuciernaga.obtenerVida());
    }

    @Test
    public void test10ElPunioEmpujaAlObjetivoUnaCasillaEnDireccionAlGolpe(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,2);
        Posicion posicionVekOrigen = new Posicion(4,3);
        Posicion posicionTrasEmpuje = new Posicion(4,4);

        Mech mechCombate = new Combate(posicionMechOrigen);
        Unidad vekLuciernaga = new Luciernaga(posicionVekOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);
        tablero.ocuparCasilla(posicionVekOrigen, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionTrasEmpuje).obtenerUnidad());
    }

    @Test
    public void test11TrasSerEmpujadoLaCasillaDondeEstabaQuedaLibre(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,2);
        Posicion posicionVekOrigen = new Posicion(4,3);

        Mech mechCombate = new Combate(posicionMechOrigen);
        Unidad vekLuciernaga = new Luciernaga(posicionVekOrigen);


        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);
        tablero.ocuparCasilla(posicionVekOrigen, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertTrue(tablero.enCasilla(posicionVekOrigen).estaLibre());
    }

    @Test
    public void test12SeEmpujaAUnVekAlLimiteDelMapaElMismoQuedaEnElBorde(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(6,4);
        Posicion posicionVekOrigen = new Posicion(7,4);

        Mech mechCombate = new Combate(posicionMechOrigen);
        Unidad vekLuciernaga = new Luciernaga(posicionVekOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);
        tablero.ocuparCasilla(posicionVekOrigen, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVekOrigen).obtenerUnidad());
    }

    @Test
    public void test13AtacarAUnVekNoAdyacenteNoLeQuitaVida(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,1);
        Posicion posicionVekOrigen = new Posicion(4,3);

        Mech mechCombate = new Combate(posicionMechOrigen);
        Unidad vekLuciernaga = new Luciernaga(posicionVekOrigen);

        int vidaEsperada = 3;

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);
        tablero.ocuparCasilla(posicionVekOrigen, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertEquals(vidaEsperada, vekLuciernaga.obtenerVida());
    }

    @Test
    public void test14AtacarAUnVekNoAdyacenteNoLoEmpuja(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,1);
        Posicion posicionVekOrigen = new Posicion(4,3);

        Mech mechCombate = new Combate(posicionMechOrigen);
        Unidad vekLuciernaga = new Luciernaga(posicionVekOrigen);

        tablero.ocuparCasilla(posicionMechOrigen, mechCombate);
        tablero.ocuparCasilla(posicionVekOrigen, vekLuciernaga);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVekOrigen).obtenerUnidad());
    }

    @Test
    public void test15AtacarUnaCasillaVaciaNoDebeLanzarUnError(){ // Test polemico, por ahora lo dejo
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMech = new Posicion(4,2);
        Posicion posicionVacia = new Posicion(4,3);

        Mech mechCombate = new Combate(posicionMech);

        tablero.ocuparCasilla(posicionMech, mechCombate);

        assertDoesNotThrow(() -> mechCombate.atacarA(posicionVacia, tablero));
    }
}
