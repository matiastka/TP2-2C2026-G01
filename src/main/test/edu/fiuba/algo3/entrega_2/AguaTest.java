package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.unidades.veks.*;
import edu.fiuba.algo3.modelo.unidades.mechs.*;
import edu.fiuba.algo3.modelo.unidades.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AguaTest {

    private static final String[] MAPA = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSASSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01UnaLuciernagaEmpujadaAlAguaPorElCanonMuere(){
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Posicion posicionVek = new Posicion(2,3);
        Posicion posicionMech = new Posicion(2,0);

        Vek luciernagaVek = new Luciernaga();
        Mech canonMech = new Canon();

        luciernagaVek.colocarseEn(posicionVek, tablero);
        canonMech.colocarseEn(posicionMech, tablero);

        int vidaEsperada = 0;

        // Act
        canonMech.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(vidaEsperada, luciernagaVek.obtenerVida());
    }

    @Test
    public void test02UnAvisponEmpujadoAlAguaPorElCanonNoMuere(){
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Posicion posicionVek = new Posicion(2,3);
        Posicion posicionMech = new Posicion(2,0);

        Vek avisponVek = new Avispon();
        Mech canonMech = new Canon();

        avisponVek.colocarseEn(posicionVek, tablero);
        canonMech.colocarseEn(posicionMech, tablero);

        int vidaEsperada = 1;

        // Act
        canonMech.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(vidaEsperada, avisponVek.obtenerVida());
    }

    @Test
    public void test03UnMechCanonEnElAguaNoPuedeAtacar(){
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Posicion posicionVek = new Posicion(2,5);
        Posicion posicionMech = new Posicion(2,3);
        Posicion posicionAgua = new Posicion(2,4);

        Vek avisponVek = new Avispon();
        Mech canonMech = new Canon();

        avisponVek.colocarseEn(posicionVek, tablero);
        canonMech.colocarseEn(posicionMech, tablero);

        canonMech.moverA(posicionAgua, tablero);

        assertThrows(ArmaSumergidaException.class, () -> canonMech.atacarA(posicionVek, tablero));
    }

    @Test
    public void test04DeshacerMovimientoDesdeElAguaRestauraElArma(){
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Posicion posicionMech = new Posicion(2,3);
        Posicion posicionAgua = new Posicion(2,4);
        Posicion posicionVek = new Posicion(2,5);

        Mech canonMech = new Canon();
        Vek avisponVek = new Avispon();
        canonMech.colocarseEn(posicionMech, tablero);
        avisponVek.colocarseEn(posicionVek, tablero);

        canonMech.moverA(posicionAgua, tablero);
        canonMech.deshacerMovimiento(tablero);

        assertDoesNotThrow(() -> canonMech.atacarA(posicionVek, tablero));
    }

    @Test
    public void test05UnMechSeMueveAlAguaSaleDelAguaYAtacaAUnVek(){
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Posicion posicionVek = new Posicion(2,6);
        Posicion posicionMech = new Posicion(2,3);
        Posicion posicionAgua = new Posicion(2,4);
        Posicion posicionSuelo = new Posicion(2,5);

        Vek avisponVek = new Avispon();
        Mech canonMech = new Canon();

        avisponVek.colocarseEn(posicionVek, tablero);
        canonMech.colocarseEn(posicionMech, tablero);

        canonMech.moverA(posicionAgua, tablero);

        canonMech.reiniciarTurno();

        canonMech.moverA(posicionSuelo, tablero);

        int vidaEsperada = 1;

        canonMech.atacarA(posicionVek, tablero);

        // Act & Assert
        assertEquals(vidaEsperada, avisponVek.obtenerVida());
    }

    @Test
    public void test06UnMechCombateEnElAguaNoPuedeAtacar(){
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Posicion posicionVek = new Posicion(2,5);
        Posicion posicionMech = new Posicion(2,3);
        Posicion posicionAgua = new Posicion(2,4);

        Vek avisponVek = new Avispon();
        Mech combateMech = new Combate();

        avisponVek.colocarseEn(posicionVek, tablero);
        combateMech.colocarseEn(posicionMech, tablero);

        combateMech.moverA(posicionAgua, tablero);

        assertThrows(ArmaSumergidaException.class, () -> combateMech.atacarA(posicionVek, tablero));
    }
}
