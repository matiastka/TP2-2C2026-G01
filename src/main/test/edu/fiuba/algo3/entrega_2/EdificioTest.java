package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class EdificioTest {

    private static final String[] MAPA_CON_EDIFICIO = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSESSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01ElDanioAUnEdificioReduceLaRedElectricaEn1() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion posicionOrigen = new Posicion(3, 2);
        Posicion posicionEdificio = new Posicion(3, 3);
        Mech mech = new Combate();
        mech.colocarseEn(posicionOrigen, tablero);

        mech.atacarA(posicionEdificio, tablero);

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test02UnEdificioDestruidoPasaASerTransitableParaOtroMech() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);
        Posicion posicionEdificio = new Posicion(3, 3);
        Mech atacante = new Combate();
        atacante.colocarseEn(new Posicion(3, 2), tablero);
        atacante.atacarA(posicionEdificio, tablero);

        Mech otroMech = new Combate();
        otroMech.colocarseEn(new Posicion(2, 3), tablero);
        otroMech.moverA(posicionEdificio, tablero);

        assertEquals(posicionEdificio, otroMech.obtenerPosicion());
    }

    @Test
    public void test03AtacarUnEdificioYaDestruidoNoVuelveARestarRedElectrica() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion posicionEdificio = new Posicion(3, 3);

        Mech primerAtacante = new Combate();
        primerAtacante.colocarseEn(new Posicion(3, 2), tablero);
        primerAtacante.atacarA(posicionEdificio, tablero);

        Mech segundoAtacante = new Combate();
        segundoAtacante.colocarseEn(new Posicion(3, 4), tablero);
        segundoAtacante.atacarA(posicionEdificio, tablero);

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test04DisparoEnLineaRectaAtraviesaUnEdificioYaDestruido() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);
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
        canon.atacarA(posicionEdificio, tablero);

        assertEquals(vidaInicialVek - 1, vek.obtenerVida());
    }

    @Test
    public void test05EmpujarUnaUnidadContraUnEdificioReduceLaRedElectrica() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion posicionVek = new Posicion(3, 2);

        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 1), tablero);

        Vek vek = new Saltador();
        vek.colocarseEn(posicionVek, tablero);

        mech.atacarA(posicionVek, tablero);

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test06EmpujarUnaUnidadContraUnEdificioLeAplicaDanioAdicionalPorColision() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);
        Posicion posicionVek = new Posicion(3, 2);

        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 1), tablero);

        Vek vek = new Saltador();
        vek.colocarseEn(posicionVek, tablero);
        int vidaInicialVek = vek.obtenerVida();

        mech.atacarA(posicionVek, tablero);

        assertEquals(vidaInicialVek - 3, vek.obtenerVida());
    }

    @Test
    public void test07EmpujarUnaUnidadContraUnEdificioNoLaDesplaza() {
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);
        Posicion posicionVek = new Posicion(3, 2);

        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 1), tablero);

        Vek vek = new Saltador();
        vek.colocarseEn(posicionVek, tablero);

        mech.atacarA(posicionVek, tablero);

        assertEquals(posicionVek, vek.obtenerPosicion());
    }
}