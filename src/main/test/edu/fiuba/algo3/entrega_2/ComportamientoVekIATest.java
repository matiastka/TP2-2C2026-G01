package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class ComportamientoVekIATest {

    private InteligenciaArtificialVek ia;

    @BeforeEach
    public void setUp() {
        this.ia = new InteligenciaArtificialVek();
    }

    @Test
    public void test01Prioridad1AtacarEdificioAntesQueAUnMechAlAlcance() {
        String[] mapa = {
                "SSSSSSSS",
                "SSSSSSSS",
                "SSESSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        };
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();

        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(4, 5), tablero);

        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 4), tablero);

        ia.decidirTurno(saltador, tablero);
        saltador.resolverAtaque(tablero);

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test02Prioridad2AtacarMechCuandoNoPuedeAlcanzarNingunEdificio() {
        String[] mapa = {
                "ESSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        };
        Tablero tablero = new ConstructorTablero().construir(mapa);

        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(5, 5), tablero);
        int vidaInicialMech = mech.obtenerVida();

        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(5, 3), tablero);

        ia.decidirTurno(saltador, tablero);
        saltador.resolverAtaque(tablero);

        assertEquals(vidaInicialMech - 2, mech.obtenerVida());
    }

    @Test
    public void test03Prioridad3SeAcercaAlEdificioMasCercanoSinAnunciarAtaque() {
        String[] mapa = {
                "ESSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        };
        Tablero tablero = new ConstructorTablero().construir(mapa);

        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(5, 0), tablero);

        ia.decidirTurno(saltador, tablero);
        saltador.resolverAtaque(tablero);

        assertEquals(new Posicion(2, 0), saltador.obtenerPosicion());
    }

    @Test
    public void test04VekTerrestreEvitaTerminarSuMovimientoEnAguaOAcido() {
        String[] mapa = {
                "SSESSSSS",
                "SSASSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        };
        Tablero tablero = new ConstructorTablero().construir(mapa);

        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(2, 2), tablero);

        ia.decidirTurno(saltador, tablero);

        // No termina en (1, 2) (Agua); se mueve a (2, 1) (suelo seguro a distancia 2 del edificio)
        assertEquals(new Posicion(2, 2), saltador.obtenerPosicion());
    }

    @Test
    public void test05AvisponVoladorSiPuedeTerminarEnAguaParaAtacarUnEdificio() {
        String[] mapa = {
                "SSESSSSS",
                "SSASSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        };
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();

        Vek avispon = new Avispon();
        avispon.colocarseEn(new Posicion(2, 2), tablero);

        ia.decidirTurno(avispon, tablero);
        avispon.resolverAtaque(tablero);

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test06DesempateDeterministaPorMenorFilaYMenorColumna() {
        String[] mapa = {
                "SSSESSSS",
                "SSSSSSSS",
                "SESSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        };
        Tablero tablero = new ConstructorTablero().construir(mapa);

        // Saltador en (2, 3): puede atacar el edificio (0, 3) desde (1, 3) o el edificio (2, 1) desde (2, 2).
        // Por menor fila y luego menor columna, debe elegir (1, 3).
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(2, 3), tablero);

        ia.decidirTurno(saltador, tablero);

        assertEquals(new Posicion(1, 3), saltador.obtenerPosicion());
    }

    @Test
    public void test07LuciernagaRespetaBloqueoDeMontanaAlBuscarLineaDeTiroHaciaEdificio() {
        String[] mapa = {
                "ESSSSSSS",
                "MSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        };
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();

        // Luciérnaga en (2, 1): sube en línea recta hasta (0, 1) y dispara al OESTE al edificio (0, 0)
        Vek luciernaga = new Luciernaga();
        luciernaga.colocarseEn(new Posicion(2, 1), tablero);

        ia.decidirTurno(luciernaga, tablero);
        luciernaga.resolverAtaque(tablero);

        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }
}