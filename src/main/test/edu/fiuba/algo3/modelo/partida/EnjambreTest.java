
package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EnjambreTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01EnLaFaseDeResolucionAtacaPrimeroElPrimerVekDelTurno() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posicionVek1 = new Posicion(1, 1);
        Posicion posicionVek2 = new Posicion(1, 2);
        Saltador vekSaltador1 = new Saltador();
        Saltador vekSaltador2 = new Saltador();
        vekSaltador1.colocarseEn(posicionVek1, tablero);
        vekSaltador2.colocarseEn(posicionVek2, tablero);
        vekSaltador1.prepararAtaque(posicionVek2);
        vekSaltador2.prepararAtaque(posicionVek1);
        Enjambre enjambre = new Enjambre(List.of(vekSaltador1, vekSaltador2), new InteligenciaArtificialVek());

        // Act
        enjambre.faseResolucion(tablero);

        // Assert
        assertEquals(2, vekSaltador1.obtenerVida());
    }

    @Test
    public void test02SiCambiaElOrdenDelTurnoCambiaQuienAtacaPrimero() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Saltador primero = new Saltador();
        Saltador segundo = new Saltador();
        primero.colocarseEn(new Posicion(1, 1), tablero);
        segundo.colocarseEn(new Posicion(1, 2), tablero);
        primero.prepararAtaque(new Posicion(1, 2));
        segundo.prepararAtaque(new Posicion(1, 1));
        Enjambre enjambre = new Enjambre(List.of(segundo, primero), new InteligenciaArtificialVek());

        // Act
        enjambre.faseResolucion(tablero);

        // Assert
        assertEquals(0, primero.obtenerVida());
    }

    @Test
    public void test03EnLaFaseVekCadaVekAnunciaSuAtaqueYSeEjecutaEnLaResolucion() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Saltador saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
        Enjambre enjambre = new Enjambre(List.of(saltador), new InteligenciaArtificialVek());
        int redInicial = tablero.obtenerRedElectrica();

        // Act
        enjambre.faseVek(tablero);
        enjambre.faseResolucion(tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test04LaFaseVekSoloAnunciaYNoEjecutaElAtaque() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Saltador saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
        Enjambre enjambre = new Enjambre(List.of(saltador), new InteligenciaArtificialVek());
        int redInicial = tablero.obtenerRedElectrica();

        // Act
        enjambre.faseVek(tablero);

        // Assert
        assertEquals(redInicial, tablero.obtenerRedElectrica());
    }

    @Test
    public void test05EnLaFaseDeEmergenciaEmergeElVekAnunciado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posicion = new Posicion(3, 3);
        Saltador vekQueEmerge = new Saltador();
        tablero.anunciarEmergencia(posicion, vekQueEmerge);
        Enjambre enjambre = new Enjambre(List.of(), new InteligenciaArtificialVek());

        // Act
        enjambre.faseEmergencia(tablero);

        // Assert
        assertSame(vekQueEmerge, tablero.enCasilla(posicion).obtenerOcupante());
    }

    @Test
    public void test06ElVekQueEmergeSeSumaAlEnjambre() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Saltador vekQueEmerge = new Saltador();
        tablero.anunciarEmergencia(new Posicion(3, 3), vekQueEmerge);
        Enjambre enjambre = new Enjambre(List.of(), new InteligenciaArtificialVek());

        // Act
        enjambre.faseEmergencia(tablero);

        // Assert
        assertTrue(enjambre.integrantes().contains(vekQueEmerge));
    }

    @Test
    public void test07UnEnjambreNuevoNoTieneVeksEliminados() {
        // Arrange
        Enjambre enjambre = new Enjambre(List.of(new Saltador()), new InteligenciaArtificialVek());

        // Act
        int eliminados = enjambre.cantidadDeEliminados();

        // Assert
        assertEquals(0, eliminados);
    }

    @Test
    public void test08AlRetirarUnVekDestruidoSeCuentaComoEliminado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Saltador saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 3), tablero);
        Enjambre enjambre = new Enjambre(List.of(saltador), new InteligenciaArtificialVek());
        saltador.recibirDanio(5);

        // Act
        enjambre.retirarDestruidos(tablero);

        // Assert
        assertEquals(1, enjambre.cantidadDeEliminados());
    }

    @Test
    public void test09AlRetirarUnVekDestruidoDejaDeSerParteDelEnjambre() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Saltador saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 3), tablero);
        Enjambre enjambre = new Enjambre(List.of(saltador), new InteligenciaArtificialVek());
        saltador.recibirDanio(5);

        // Act
        enjambre.retirarDestruidos(tablero);

        // Assert
        assertFalse(enjambre.integrantes().contains(saltador));
    }

    @Test
    public void test10UnVekConVidaNoSeRetiraDelEnjambre() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Saltador saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 3), tablero);
        Enjambre enjambre = new Enjambre(List.of(saltador), new InteligenciaArtificialVek());

        // Act
        enjambre.retirarDestruidos(tablero);

        // Assert
        assertEquals(0, enjambre.cantidadDeEliminados());
    }

}