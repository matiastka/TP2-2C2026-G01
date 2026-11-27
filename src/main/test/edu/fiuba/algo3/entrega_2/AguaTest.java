
package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.excepciones.ArmaSumergidaException;
import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;

public class AguaTest {

    private static final String RUTA_MAPA_AGUA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_agua.txt";

    @Test
    public void test01UnaLuciernagaEmpujadaAlAguaPorElCanonMuere() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionVek = new Posicion(2, 3);
        Posicion posicionMech = new Posicion(2, 0);
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
    public void test02UnAvisponEmpujadoAlAguaPorElCanonNoMuere() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionVek = new Posicion(2, 3);
        Posicion posicionMech = new Posicion(2, 0);
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
    public void test03UnMechCanonEnElAguaNoPuedeAtacar() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionVek = new Posicion(2, 5);
        Posicion posicionMech = new Posicion(2, 3);
        Posicion posicionAgua = new Posicion(2, 4);
        Vek avisponVek = new Avispon();
        Mech canonMech = new Canon();
        avisponVek.colocarseEn(posicionVek, tablero);
        canonMech.colocarseEn(posicionMech, tablero);
        canonMech.moverA(posicionAgua, tablero);

        // Act & Assert
        assertThrows(ArmaSumergidaException.class, () -> canonMech.atacarA(posicionVek, tablero));
    }

    @Test
    public void test04DeshacerMovimientoDesdeElAguaRestauraElArma() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMech = new Posicion(2, 3);
        Posicion posicionAgua = new Posicion(2, 4);
        Posicion posicionVek = new Posicion(2, 5);
        Mech canonMech = new Canon();
        Vek avisponVek = new Avispon();
        canonMech.colocarseEn(posicionMech, tablero);
        avisponVek.colocarseEn(posicionVek, tablero);
        canonMech.moverA(posicionAgua, tablero);
        canonMech.deshacerMovimiento(tablero);

        // Act & Assert
        assertDoesNotThrow(() -> canonMech.atacarA(posicionVek, tablero));
    }

    @Test
    public void test05UnMechSeMueveAlAguaSaleDelAguaYAtacaAUnVek() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionVek = new Posicion(2, 6);
        Posicion posicionMech = new Posicion(2, 3);
        Posicion posicionAgua = new Posicion(2, 4);
        Posicion posicionSuelo = new Posicion(2, 5);
        Vek avisponVek = new Avispon();
        Mech canonMech = new Canon();
        avisponVek.colocarseEn(posicionVek, tablero);
        canonMech.colocarseEn(posicionMech, tablero);
        canonMech.moverA(posicionAgua, tablero);
        canonMech.reiniciarTurno();
        canonMech.moverA(posicionSuelo, tablero);
        int vidaEsperada = 1;

        // Act
        canonMech.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(vidaEsperada, avisponVek.obtenerVida());
    }

    @Test
    public void test06UnMechCombateEnElAguaNoPuedeAtacar() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionVek = new Posicion(2, 5);
        Posicion posicionMech = new Posicion(2, 3);
        Posicion posicionAgua = new Posicion(2, 4);
        Vek avisponVek = new Avispon();
        Mech combateMech = new Combate();
        avisponVek.colocarseEn(posicionVek, tablero);
        combateMech.colocarseEn(posicionMech, tablero);
        combateMech.moverA(posicionAgua, tablero);

        // Act & Assert
        assertThrows(ArmaSumergidaException.class, () -> combateMech.atacarA(posicionVek, tablero));
    }
}