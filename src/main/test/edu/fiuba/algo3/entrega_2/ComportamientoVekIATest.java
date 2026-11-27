
package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;

public class ComportamientoVekIATest {

    private static final String RUTA_MAPA_EDIFICIO_FILA2 = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_ia_edificio_fila2.txt";
    private static final String RUTA_MAPA_EDIFICIO_ESQUINA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_ia_edificio_esquina.txt";
    private static final String RUTA_MAPA_AGUA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_ia_agua_edificio.txt";
    private static final String RUTA_MAPA_DESEMPATE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_ia_desempate.txt";
    private static final String RUTA_MAPA_MONTANA_BLOQUEO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_ia_montana_bloqueo.txt";

    private InteligenciaArtificialVek ia;

    @BeforeEach
    public void setUp() {
        this.ia = new InteligenciaArtificialVek();
    }

    @Test
    public void test01Prioridad1AtacarEdificioAntesQueAUnMechAlAlcance() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO_FILA2);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(4, 5), tablero);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 4), tablero);

        // Act
        ia.decidirTurno(saltador, tablero);
        saltador.resolverAtaque(tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test02Prioridad2AtacarMechCuandoNoPuedeAlcanzarNingunEdificio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO_ESQUINA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(5, 5), tablero);
        int vidaInicialMech = mech.obtenerVida();
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(5, 3), tablero);

        // Act
        ia.decidirTurno(saltador, tablero);
        saltador.resolverAtaque(tablero);

        // Assert
        assertEquals(vidaInicialMech - 2, mech.obtenerVida());
    }

    @Test
    public void test03Prioridad3SeAcercaAlEdificioMasCercanoSinAnunciarAtaque() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO_ESQUINA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(5, 0), tablero);

        // Act
        ia.decidirTurno(saltador, tablero);
        saltador.resolverAtaque(tablero);

        // Assert
        assertEquals(new Posicion(2, 0), saltador.obtenerPosicion());
    }

    @Test
    public void test04VekTerrestreEvitaTerminarSuMovimientoEnAguaOAcido() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_AGUA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(2, 2), tablero);

        // Act
        ia.decidirTurno(saltador, tablero);

        // Assert
        assertEquals(new Posicion(2, 2), saltador.obtenerPosicion());
    }

    @Test
    public void test05AvisponVoladorSiPuedeTerminarEnAguaParaAtacarUnEdificio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_AGUA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();
        Vek avispon = new Avispon();
        avispon.colocarseEn(new Posicion(2, 2), tablero);

        // Act
        ia.decidirTurno(avispon, tablero);
        avispon.resolverAtaque(tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test06DesempateDeterministaPorMenorFilaYMenorColumna() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_DESEMPATE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(2, 3), tablero);

        // Act
        ia.decidirTurno(saltador, tablero);

        // Assert
        assertEquals(new Posicion(1, 3), saltador.obtenerPosicion());
    }

    @Test
    public void test07LuciernagaRespetaBloqueoDeMontanaAlBuscarLineaDeTiroHaciaEdificio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA_BLOQUEO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();
        Vek luciernaga = new Luciernaga();
        luciernaga.colocarseEn(new Posicion(2, 1), tablero);

        // Act
        ia.decidirTurno(luciernaga, tablero);
        luciernaga.resolverAtaque(tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }
}