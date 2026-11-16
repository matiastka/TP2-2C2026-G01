package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.RedElectrica;
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

    private static final String[] MAPA_VARIOS_EDIFICIOS = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSEESES",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @BeforeEach
    public void setUp() {
        RedElectrica.getInstancia().reiniciar();
    }

    @Test
    public void test01ElDanioAUnEdificioReduceLaRedElectricaEn1() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();

        Posicion origen = new Posicion(3, 2);
        Posicion edificio = new Posicion(3, 3);

        Mech mech = new Combate();
        mech.colocarseEn(origen, tablero);

        mech.atacarA(edificio, tablero);

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test02UnEdificioDestruidoPasaASerTransitable() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_EDIFICIO);

        Posicion origen = new Posicion(3, 2);
        Posicion posEdificio = new Posicion(3, 3);

        Mech atacante = new Combate();
        atacante.colocarseEn(origen, tablero);
        atacante.atacarA(posEdificio, tablero);

        assertTrue(tablero.enCasilla(posEdificio).esTransitable());

        // Otro Mech puede atravesar o pararse encima de la casilla del edificio destruido
        Mech otroMech = new Combate();
        otroMech.colocarseEn(new Posicion(2, 3), tablero);
        otroMech.moverA(posEdificio, tablero);

        assertEquals(posEdificio, otroMech.obtenerPosicion());
    }

    @Test
    public void test03AtacarUnEdificioYaDestruidoNoVuelveARestarRedElectrica() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();

        Posicion posEdificio = new Posicion(3, 3);

        Mech mech1 = new Combate();
        mech1.colocarseEn(new Posicion(3, 2), tablero);
        mech1.atacarA(posEdificio, tablero);

        Mech mech2 = new Combate();
        mech2.colocarseEn(new Posicion(3, 4), tablero);
        mech2.atacarA(posEdificio, tablero);

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test04DisparoEnLineaRectaAtraviesaUnEdificioYaDestruido() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_EDIFICIO);

        Posicion posEdificio = new Posicion(3, 3);
        Posicion posVek = new Posicion(3, 5);

        // Destruimos el edificio primero
        Mech combate = new Combate();
        combate.colocarseEn(new Posicion(2, 3), tablero);
        combate.atacarA(posEdificio, tablero);

        // Colocamos un Vek detrás del edificio destruido y disparamos con Cañón
        Vek vek = new Saltador();
        vek.colocarseEn(posVek, tablero);
        int vidaInicialVek = vek.obtenerVida();

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 1), tablero);
        canon.atacarA(posEdificio, tablero);

        assertEquals(vidaInicialVek - 1, vek.obtenerVida());
    }

    @Test
    public void test05EmpujarUnaUnidadContraUnEdificioDaniaAlEdificioYReduceRedElectrica() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();

        Posicion posMech = new Posicion(3, 1);
        Posicion posVek = new Posicion(3, 2);
        Posicion posEdificio = new Posicion(3, 3);

        Mech mech = new Combate();
        mech.colocarseEn(posMech, tablero);

        Vek vek = new Saltador();
        vek.colocarseEn(posVek, tablero);
        int vidaInicialVek = vek.obtenerVida();

        // El Mech golpea al Vek (2 de daño) y lo empuja contra el edificio (+1 de daño por colisión y -1 Red Eléctrica)
        mech.atacarA(posVek, tablero);

        assertEquals(posVek, vek.obtenerPosicion()); // No se movió porque chocó contra el edificio
        assertEquals(vidaInicialVek - 3, vek.obtenerVida()); // 2 del golpe + 1 del choque
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }
}