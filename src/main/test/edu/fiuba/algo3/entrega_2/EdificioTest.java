
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdificioTest {

    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01ElDanioAUnEdificioReduceLaRedElectricaEn1() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion posicionOrigen = new Posicion(3, 2);
        Posicion posicionEdificio = new Posicion(3, 3);
        Mech mech = new Combate();
        mech.colocarseEn(posicionOrigen, tablero);

        // Act
        mech.atacarA(posicionEdificio, tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test02UnEdificioDestruidoPasaASerTransitableParaOtroMech() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Posicion posicionEdificio = new Posicion(3, 3);
        Mech atacante = new Combate();
        atacante.colocarseEn(new Posicion(3, 2), tablero);
        atacante.atacarA(posicionEdificio, tablero);
        Mech otroMech = new Combate();
        otroMech.colocarseEn(new Posicion(2, 3), tablero);

        // Act
        otroMech.moverA(posicionEdificio, tablero);

        // Assert
        assertEquals(posicionEdificio, otroMech.obtenerPosicion());
    }

    @Test
    public void test03AtacarUnEdificioYaDestruidoNoVuelveARestarRedElectrica() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion posicionEdificio = new Posicion(3, 3);
        Mech primerAtacante = new Combate();
        primerAtacante.colocarseEn(new Posicion(3, 2), tablero);
        primerAtacante.atacarA(posicionEdificio, tablero);
        Mech segundoAtacante = new Combate();
        segundoAtacante.colocarseEn(new Posicion(3, 4), tablero);

        // Act
        segundoAtacante.atacarA(posicionEdificio, tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test04DisparoEnLineaRectaAtraviesaUnEdificioYaDestruido() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Posicion posicionEdificio = new Posicion(3, 3);
        Posicion posicionVekDetrasDelEdificio = new Posicion(3, 5);
        Mech combate = new Combate();
        combate.colocarseEn(new Posicion(2, 3), tablero);
        combate.atacarA(posicionEdificio, tablero);
        Vek vek = new Saltador();
        vek.colocarseEn(posicionVekDetrasDelEdificio, tablero);
        int vidaInicialVek = vek.obtenerVida();
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 1), tablero);

        // Act
        canon.atacarA(posicionEdificio, tablero);

        // Assert
        assertEquals(vidaInicialVek - 1, vek.obtenerVida());
    }

    @Test
    public void test05EmpujarUnaUnidadContraUnEdificioReduceLaRedElectrica() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion posicionVek = new Posicion(3, 2);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 1), tablero);
        Vek vek = new Saltador();
        vek.colocarseEn(posicionVek, tablero);

        // Act
        mech.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test06EmpujarUnaUnidadContraUnEdificioLeAplicaDanioAdicionalPorColision() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Posicion posicionVek = new Posicion(3, 2);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 1), tablero);
        Vek vek = new Saltador();
        vek.colocarseEn(posicionVek, tablero);
        int vidaInicialVek = vek.obtenerVida();

        // Act
        mech.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(vidaInicialVek - 3, vek.obtenerVida());
    }

    @Test
    public void test07EmpujarUnaUnidadContraUnEdificioNoLaDesplaza() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Posicion posicionVek = new Posicion(3, 2);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 1), tablero);
        Vek vek = new Saltador();
        vek.colocarseEn(posicionVek, tablero);

        // Act
        mech.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(posicionVek, vek.obtenerPosicion());
    }
}